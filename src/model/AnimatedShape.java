package model;

import java.util.List;
import model.motion.Motion;
import model.shape.Shape;

/**
 * Specifies all the operations on a {@link Shape} within an animation. Extends the {@link Shape}
 * interface.
 */
public interface AnimatedShape extends Shape {

  /**
   * Adds a motion to the sequence of motions of this animated shape.
   *
   * @param start      a starting time of the motion added
   * @param end        an end time of the motion added
   * @param startShape a form of this {@link AnimatedShape} at the start of the motion added
   * @param endShape   a form of this {@link AnimatedShape} at the end of the motion added
   * @throws NullPointerException     if the starting shape of the {@link Motion} is null
   * @throws NullPointerException     if the end shape of the {@link Motion} is null
   * @throws IllegalArgumentException if the start time is less than 0
   * @throws IllegalArgumentException if the end time is before the start time
   * @throws IllegalStateException    if another motion of the same kind exists in the sequence
   *                                  withing the given timeframe
   * @throws IllegalStateException    if there is a FREEZE motion happening during given timeframe
   *                                  to the shape with this name
   */
  void addMotion(int start, int end, Shape startShape, Shape endShape);

  /**
   * Returns the name of this animated shape.
   *
   * @return the name of this animated shape
   */
  String getName();

  /**
   * Returns a list of all the motions of this animated shape.
   *
   * @return a list of all the motions of this animated shape
   */
  List<Motion> getSequence();

}
