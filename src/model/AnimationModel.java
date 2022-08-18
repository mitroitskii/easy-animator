package model;

import java.util.List;
import model.shape.Shape;
import model.shape.Shapes;

/**
 * Specifies all the operations available for the model of the animation.
 */
public interface AnimationModel {

  /**
   * Sets the canvas of this animation. It cannot be reset.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the canvas
   * @param height The height of the canvas
   * @throws IllegalStateException    if the canvas has already been set.
   * @throws IllegalArgumentException if provided width or height properties are non-positive
   **/
  void setCanvas(int x, int y, int width, int height);

  /**
   * Adds a motion to the animated shape with the given name.
   *
   * @param name            a name of the animated shape the motion is added to
   * @param start           a starting time of the motion added
   * @param end             an end time of the motion added
   * @param xPositionStart  an x coordinate of the position of the shape at the start of the motion
   *                        added
   * @param yPositionStart  a y coordinate of the position of the shape at the start of the motion
   *                        added
   * @param xDimensionStart a length of the dimension on the x-axis of the shape at the start of the
   *                        motion added
   * @param yDimensionStart a length of the dimension on the y-axis of the shape at the start of the
   *                        motion added
   * @param rStart          an amount of red in the color of the shape at the start of the motion
   * @param gStart          an amount of green in the color of the shape at the start of the motion
   * @param bStart          an amount of blue in the color of the shape at the start of the motion
   * @param xPositionEnd    an x coordinate of the position of the shape at the end of the motion
   *                        added
   * @param yPositionEnd    a y coordinate of the position of the shape at the end of the motion
   *                        added
   * @param xDimensionEnd   a length of the dimension on the x-axis of the shape at the end of the
   *                        motion added
   * @param yDimensionEnd   a  length of the dimension on the y-axis of the shape at the end of the
   *                        motion added
   * @param rEnd            an amount of red in the color of the shape at the end of the motion
   * @param gEnd            an amount of green in the color of the shape at the end of the motion
   * @param bEnd            an amount of blue in the color of the shape at the end of the motion
   * @throws NullPointerException     if the name of the shape is null
   * @throws IllegalArgumentException if the shape with given name doesn't exist
   * @throws IllegalArgumentException if the start time is less than 0
   * @throws IllegalArgumentException if the end time is before the start time
   * @throws IllegalArgumentException if the RGB color value is not within 0-255 range
   * @throws IllegalStateException    if there is another motion of the same kind happening during
   *                                  given timeframe to the shape with this name
   * @throws IllegalStateException    if there is a FREEZE motion happening during given timeframe
   *                                  to the shape with this name
   */
  void addMotion(String name, int start, int end,
      int xPositionStart, int yPositionStart,
      int xDimensionStart, int yDimensionStart,
      int rStart, int gStart, int bStart,
      int xPositionEnd, int yPositionEnd,
      int xDimensionEnd, int yDimensionEnd,
      int rEnd, int gEnd, int bEnd) throws NullPointerException, IllegalArgumentException,
      IllegalStateException;

  /**
   * Adds a new shape of the given kind and name to the animation. Throws an exception if the shape
   * with this name already exists in the animation. Names with the same characters but in different
   * case are not considered equal.
   *
   * @param name a name of the given shape, represented as a {@link String}
   * @param kind a kind of the given shape, represented by a {@link Shapes} constant
   * @throws NullPointerException     if the name of the shape is null
   * @throws NullPointerException     if the kind of the shape is null
   * @throws IllegalArgumentException if the shape with this name already exists in the animation
   *                                  (names containing the same characters but in different case
   *                                  are not considered equal)
   */
  void addShape(String name, Shapes kind)
      throws IllegalArgumentException, NullPointerException;

  /**
   * Computes the state of the animation at the given timestamp and returns a list of all the shapes
   * present in the frame at the given timestamp.
   *
   * @param timestamp a time to compute the state at
   * @return list of all the shapes present in the frame at the given timestamp
   */
  List<Shape> getStateAt(int timestamp);

}
