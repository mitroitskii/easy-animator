package model.motion;

import model.AnimatedShape;
import model.shape.Shape;
import model.shape.ShapeFactory;
import model.shape.properties.Dimensions;

/**
 * Represents a scaling (i.e. a change of dimensions) of an {@link AnimatedShape}.
 */
public class Scale extends AbstractMotion {

  /**
   * Initializes this Scale.
   *
   * @param shape      the  {@link AnimatedShape} that scales
   * @param start      this {@link Scale }'s start time on a timescale from 1 to infinity
   * @param end        this {@link Scale }'s end time on a timescale from 1 to infinity
   * @param startShape a form the {@link AnimatedShape}, that scales, has at the beginning of the
   *                   {@link Scale}
   * @param endShape   a form the {@link AnimatedShape}, that scales, has at the end of the {@link
   *                   Scale}
   * @throws NullPointerException     if the {@link AnimatedShape} is null
   * @throws NullPointerException     if the kind of the {@link Scale} is null
   * @throws NullPointerException     if the starting shape of the {@link Scale} is null
   * @throws NullPointerException     if the end shape of the {@link Scale} is null
   * @throws IllegalArgumentException if the start time is less than 1
   * @throws IllegalArgumentException if the end time is before the start time
   */
  public Scale(AnimatedShape shape, int start, int end,
      Shape startShape, Shape endShape) {
    super(shape, Motions.SCALE, start, end, startShape, endShape);
  }

  @Override
  public Shape transform(Shape intermediate, int timestamp) {
    return ShapeFactory.make(
        this.getEndShape().getKind(),
        intermediate.getPosition(),
        new Dimensions(
            super.interpolate(timestamp, this.getStart(), this.getEnd(),
                this.getStartShape().getDimensions().getX(),
                this.getEndShape().getDimensions().getX()),
            super.interpolate(timestamp, this.getStart(), this.getEnd(),
                this.getStartShape().getDimensions().getY(),
                this.getEndShape().getDimensions().getY())
        ),
        intermediate.getColor());
  }

  @Override
  public String toString() {
    return super.toString() + " " + this.getKind() +
        " " + "from" + " " + "(" + this.getStartShape().getDimensions() + ")" +
        " " + "to" + " " + "(" + this.getEndShape().getDimensions() + ")" +
        " " + "from t=" + this.getStart() + " " + "to t=" + this.getEnd();
  }
}
