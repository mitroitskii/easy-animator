package model.motion;

import model.AnimatedShape;
import model.shape.Shape;


/**
 * Represents a freeze in motion of an {@link AnimatedShape}.
 */
public class Freeze extends AbstractMotion {

  /**
   * Initializes this Freeze.
   *
   * @param shape      the  {@link AnimatedShape} that freezes
   * @param start      this {@link Freeze }'s start time on a timescale from 1 to infinity
   * @param end        this {@link Freeze }'s end time on a timescale from 1 to infinity
   * @param startShape a form the {@link AnimatedShape}, that freezes, has at the beginning of the
   *                   {@link Freeze}
   * @param endShape   a form the {@link AnimatedShape}, that freezes, has at the end of the {@link
   *                   Freeze}
   * @throws NullPointerException     if the {@link AnimatedShape} is null
   * @throws NullPointerException     if the kind of the {@link Freeze} is null
   * @throws NullPointerException     if the starting shape of the {@link Freeze} is null
   * @throws NullPointerException     if the end shape of the {@link Freeze} is null
   * @throws IllegalArgumentException if the start time is less than 1
   * @throws IllegalArgumentException if the end time is before the start time
   */
  public Freeze(AnimatedShape shape, int start, int end,
      Shape startShape, Shape endShape) {
    super(shape, Motions.FREEZE, start, end, startShape, endShape);
  }


  @Override
  public Shape transform(Shape intermediate, int timestamp) {
    return this.getEndShape();
  }

  @Override
  public String toString() {
    return super.toString() + " " + this.getKind() +
        " " + "with properties" + " " +
        "(" + this.getStartShape().getPosition() + "," +
        this.getStartShape().getDimensions() + "," +
        this.getStartShape().getColor() + ")" +
        " " + "from t=" + this.getStart() + " " + "to t=" + this.getEnd();
  }

}
