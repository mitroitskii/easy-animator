package model.motion;

import model.AnimatedShape;
import model.shape.Shape;
import model.shape.ShapeFactory;
import model.shape.properties.Color;

/**
 * Represents a change in color of an {@link AnimatedShape}.
 */
public class ColorChange extends AbstractMotion {

  /**
   * Initializes this Color Change.
   *
   * @param shape      the  {@link AnimatedShape} that changes color
   * @param start      this {@link ColorChange }'s start time on a timescale from 1 to infinity
   * @param end        this {@link ColorChange }'s end time on a timescale from 1 to infinity
   * @param startShape a form the {@link AnimatedShape}, that changes color, has at the beginning of
   *                   the {@link ColorChange}
   * @param endShape   a form the {@link AnimatedShape}, that changes color, has at the end of the
   *                   {@link ColorChange}
   * @throws NullPointerException     if the {@link AnimatedShape} is null
   * @throws NullPointerException     if the kind of the {@link ColorChange} is null
   * @throws NullPointerException     if the starting shape of the {@link ColorChange} is null
   * @throws NullPointerException     if the end shape of the {@link ColorChange} is null
   * @throws IllegalArgumentException if the start time is less than 1
   * @throws IllegalArgumentException if the end time is before the start time
   */
  public ColorChange(AnimatedShape shape, int start, int end,
      Shape startShape, Shape endShape) {
    super(shape, Motions.COLOR_CHANGE, start, end, startShape, endShape);
  }


  @Override
  public Shape transform(Shape intermediate, int timestamp) {
    return ShapeFactory.make(
        this.getEndShape().getKind(),
        intermediate.getPosition(),
        intermediate.getDimensions(),
        new Color(
            interpolate(timestamp, this.getStart(), this.getEnd(),
                this.getStartShape().getColor().getR(),
                this.getEndShape().getColor().getR()),
            interpolate(timestamp, this.getStart(), this.getEnd(),
                this.getStartShape().getColor().getG(),
                this.getEndShape().getColor().getG()),
            interpolate(timestamp, this.getStart(), this.getEnd(),
                this.getStartShape().getColor().getB(),
                this.getEndShape().getColor().getB())
        ));
  }

  @Override
  public String toString() {
    return super.toString() + " " + this.getKind() +
        " " + "from" + " " + "(" + this.getStartShape().getColor() + ")" +
        " " + "to" + " " + "(" + this.getEndShape().getColor() + ")" +
        " " + "from t=" + this.getStart() + " " + "to t=" + this.getEnd();
  }
}
