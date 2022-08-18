package model;

/**
 * Represents an animation canvas with its values of the top-left corner, width and height.
 */
public class Canvas {

  final int x;
  final int y;
  final int width;
  final int height;

  /**
   * Instantiates a canvas with the provided properties.
   *
   * @param x      The leftmost x value
   * @param y      The topmost y value
   * @param width  The width of the canvas
   * @param height The height of the canvas
   */

  public Canvas(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Returns the leftmost x value of this canvas.
   *
   * @return the leftmost x value of this canvas
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the topmost y value of this canvas.
   *
   * @return the topmost y value of this canvas
   */
  public int getY() {
    return y;
  }

  /**
   * Returns the width of the canvas.
   *
   * @return the width of the canvas
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of the canvas.
   *
   * @return the height of the canvas
   */
  public int getHeight() {
    return height;
  }
}
