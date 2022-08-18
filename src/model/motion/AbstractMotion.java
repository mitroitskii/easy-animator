package model.motion;


import java.util.Objects;
import model.AnimatedShape;
import model.shape.Shape;

/**
 * Represents an abstract type of motion belonging to an {@link AnimatedShape}. Implements all the
 * operations of the {@link Motion}.
 */
abstract class AbstractMotion implements Motion {

  private final AnimatedShape shape;
  private final Motions kind;

  private final int start;
  private final int end;

  private final Shape startShape;
  private final Shape endShape;

  /**
   * Initializes this motion.
   *
   * @param shape      the  {@link AnimatedShape} this motions belongs to
   * @param kind       the kind of {@link Motion} this is
   * @param start      a {@link Motion } start time on a timescale from 1 to infinity
   * @param end        a {@link Motion } end time on a timescale from 1 to infinity
   * @param startShape a form the {@link AnimatedShape}, this Motion belongs to, has at the
   *                   beginning of the {@link Motion}
   * @param endShape   a form the {@link AnimatedShape}, this Motion belongs to, has at the end of
   *                   the {@link Motion}
   * @throws NullPointerException     if the {@link AnimatedShape} is null
   * @throws NullPointerException     if the kind of the {@link Motion} is null
   * @throws NullPointerException     if the starting shape of the {@link Motion} is null
   * @throws NullPointerException     if the end shape of the {@link Motion} is null
   * @throws IllegalArgumentException if the start time is less than 0
   * @throws IllegalArgumentException if the end time is before the start time
   */
  protected AbstractMotion(AnimatedShape shape, Motions kind, int start, int end, Shape startShape,
      Shape endShape) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(shape, "The animated shape the motion belongs to cannot be null");
    Objects.requireNonNull(kind, "The kind of motion cannot be null");
    Objects.requireNonNull(kind, "The starting shape of motion cannot be null");
    Objects.requireNonNull(kind, "The end shape of motion cannot be null");
    if (start < 0) {
      throw new IllegalArgumentException("The start time cannot be less than 0");
    }
    if (end < start) {
      throw new IllegalArgumentException("The end time cannot be before the start time");
    }
    this.shape = shape;
    this.kind = kind;
    this.start = start;
    this.end = end;
    this.startShape = startShape;
    this.endShape = endShape;
  }


  /*
Helper method that interpolates the between two values of the given property between the start
time and the end time.
*/
  protected int interpolate(int timestamp, int start, int end, int init,
      int fin) {
    return (end - start) == 0 ? fin :
        (init * ((end - timestamp) / (end - start)) +
            fin * ((timestamp - start) / (end - start)));
  }

  @Override
  public Motions getKind() {
    return this.kind;
  }

  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public int getEnd() {
    return this.end;
  }

  @Override
  public AnimatedShape getShape() {
    return this.shape;
  }

  public Shape getStartShape() {
    return this.startShape;
  }

  @Override
  public Shape getEndShape() {
    return this.endShape;
  }

  @Override
  public String toString() {
    return "Shape" + " " + this.shape.getName();
  }
}
