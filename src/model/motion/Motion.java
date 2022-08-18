package model.motion;

import model.AnimatedShape;
import model.shape.Shape;

/**
 * Specifies all the operations on the motion belonging to an {@link AnimatedShape}.
 */
public interface Motion {

  /**
   * Returns the shape this motion belongs to.
   *
   * @return the shape this motion belongs to
   */
  AnimatedShape getShape();

  /**
   * Returns the kind of this {@link Motion}.
   *
   * @return the kind of this {@link Motion}
   */
  Motions getKind();

  /**
   * Returns the start time of this {@link Motion}.
   *
   * @return the start time of this {@link Motion}
   */
  int getStart();


  /**
   * Returns the end time of this {@link Motion}.
   *
   * @return the end time of this {@link Motion}
   */
  int getEnd();


  /**
   * Returns the end form of the {@link AnimatedShape} this {@link Motion} belongs to.
   *
   * @return the end form of the {@link AnimatedShape} this {@link Motion} belongs to
   */
  Shape getEndShape();

  /**
   * Returns a new version of the given {@link Shape} transformed according to changes made by this
   * {@link Motion} at the given timestamp.
   *
   * @param intermediate a {@link Shape} to be transformed
   * @param timestamp    a timestamp to compute the transformation at
   * @return a new version of the given {@link Shape} transformed according to changes made by this
   *     {@link Motion}
   */
  Shape transform(Shape intermediate, int timestamp);
}
