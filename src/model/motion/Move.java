package model.motion;

import model.AnimatedShape;
import model.shape.Shape;
import model.shape.ShapeFactory;
import model.shape.properties.Position;

/**
 * Represents a move (i.e. a change of position) of an {@link AnimatedShape}.
 */
public class Move extends AbstractMotion {

  /**
   * Initializes this Move.
   *
   * @param shape      the  {@link AnimatedShape} that moves
   * @param start      this {@link Move }'s start time on a timescale from 1 to infinity
   * @param end        this {@link Move }'s end time on a timescale from 1 to infinity
   * @param startShape a form the {@link AnimatedShape}, that moves, has at the beginning of the
   *                   {@link Move}
   * @param endShape   a form the {@link AnimatedShape}, that moves, has at the end of the {@link
   *                   Move}
   * @throws NullPointerException     if the {@link AnimatedShape} is null
   * @throws NullPointerException     if the kind of the {@link Move} is null
   * @throws NullPointerException     if the starting shape of the {@link Move} is null
   * @throws NullPointerException     if the end shape of the {@link Move} is null
   * @throws IllegalArgumentException if the start time is less than 1
   * @throws IllegalArgumentException if the end time is before the start time
   */
  public Move(AnimatedShape shape, int start, int end,
      Shape startShape, Shape endShape) {
    super(shape, Motions.MOVE, start, end, startShape, endShape);
  }


  @Override
  public Shape transform(Shape intermediate, int timestamp) {
    return ShapeFactory.make(
        this.getEndShape().getKind(),
        new Position(
            interpolate(timestamp, this.getStart(), this.getEnd(),
                this.getStartShape().getPosition().getX(),
                this.getEndShape().getPosition().getX()),
            interpolate(timestamp, this.getStart(), this.getEnd(),
                this.getStartShape().getPosition().getY(),
                this.getEndShape().getPosition().getY())
        ),
        intermediate.getDimensions(),
        intermediate.getColor());
  }

  @Override
  public String toString() {
    return super.toString() + " " + this.getKind() +
        " " + "from" + " " + "(" + this.getStartShape().getPosition() + ")" +
        " " + "to" + " " + "(" + this.getEndShape().getPosition() + ")" +
        " " + "from t=" + this.getStart() + " " + "to t=" + this.getEnd();
  }
}
