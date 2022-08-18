package model.shape;

import model.shape.properties.Color;
import model.shape.properties.Dimensions;
import model.shape.properties.Position;

/**
 * Represents an ellipse with its corner, dimensions and a color.
 */
public class Ellipse extends AbstractShape {
  

  /**
   * Instantiates an ellipse with the provided values.
   *
   * @param center     a {@link Position} object, containing the x/y coordinates of the center of
   *                   this ellipse
   * @param dimensions a {@link Dimensions} object, containing the x and y radius of this ellipse
   * @param color      a {@link Color} object, containing the color of this ellipse
   * @throws NullPointerException if any of the provided properties is null
   */
  public Ellipse(Position center, Dimensions dimensions, Color color)
      throws NullPointerException {
    super(center, dimensions, color, Shapes.ELLIPSE);
  }

  @Override
  public String toString() {
    return "Center:" + " " + "(" + getPosition().toString() + ")" + "," + " " +
        "X/Y Radius:" + " " + "(" + getDimensions().toString() + ")" + "," + " " +
        "Color:" + " " + "(" + getColor().toString() + ")";
  }
}
