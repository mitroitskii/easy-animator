package model.shape;

import model.shape.properties.Color;
import model.shape.properties.Dimensions;
import model.shape.properties.Position;

/**
 * Represents a rectangle with its corner, dimensions and a color.
 */
public class Rectangle extends AbstractShape {

  /**
   * Instantiates an rectangle with the provided values.
   *
   * @param corner     a {@link Position} object, containing the x/y coordinates of the corner of
   *                   this rectangle
   * @param dimensions a {@link Dimensions} object, containing the width and heigh of this
   *                   rectangle
   * @param color      a {@link Color} object, containing the color of this rectangle
   * @throws NullPointerException if any of the provided properties is null
   */
  public Rectangle(Position corner, Dimensions dimensions, Color color)
      throws NullPointerException {
    super(corner, dimensions, color, Shapes.RECTANGLE);
  }

  @Override
  public String toString() {
    return "Min corner:" + " " + "(" + getPosition().toString() + ")" + "," + " " +
        "Width/Height:" + " " + "(" + getDimensions().toString() + ")" + "," + " " +
        "Color:" + " " + "(" + getColor().toString() + ")";

  }
}
