package model;

import java.util.List;
import model.shape.Shape;

/**
 * Specifies all the non-mutating operations available for the model of the animation that a View is
 * allowed to get access to.
 */
public interface AnimationModelView {

  /**
   * Returns the length of this animation.
   *
   * @return the length of this animation
   */
  int getLength();

  /**
   * Returns the canvas of this animation.
   *
   * @return the canvas of this animation
   */
  Canvas getCanvas();

  /**
   * Computes the state of the animation at the given timestamp and returns a list of all the shapes
   * present in the frame at the given timestamp.
   *
   * @param timestamp a time to compute the state at
   * @return list of all the shapes present in the frame at the given timestamp
   */
  List<Shape> getStateAt(int timestamp);

}


