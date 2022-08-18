package model.shape.properties;

import model.shape.Shape;

/**
 * Represents an abstract x/y-axis property of the {@link Shape}. Implements all the operations of
 * the {@link AxisProperty}.
 */
abstract class AbstractAxisProperty implements AxisProperty {

  private final int x;
  private final int y;

  /**
   * Instantiates an axis property with the provided values.
   *
   * @param x the x-axis value of this property
   * @param y the y-axis value of this property
   */
  protected AbstractAxisProperty(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }


  // a helper method for implementing the .equals for Dimensions class
  protected boolean equalsDimensions(Dimensions other) {
    return false;
  }

  // a helper method for implementing the .equals for Position class
  protected boolean equalsPosition(Position other) {
    return false;
  }


  @Override
  public String toString() {
    return "" + this.x + "," + this.y;
  }

}
