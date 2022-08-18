package model.shape;

import model.shape.properties.Color;
import model.shape.properties.Dimensions;
import model.shape.properties.Position;

/**
 * Specifies all the operations on the geometric shape.
 */
public interface Shape {

  /**
   * Returns a positions of this shape.
   *
   * @return an x/y positions of the shape, represented as a {@link Position} object
   */
  Position getPosition();


  /**
   * Returns dimensions of this shape.
   *
   * @return dimensions of the shape, represented as a {@link Dimensions} object
   */
  Dimensions getDimensions();

  /**
   * Returns a color of this shape.
   *
   * @return a color of the shape represented as a {@link Color} object
   */
  Color getColor();

  /**
   * Returns the kind of shape this shape is.
   *
   * @return a kind of the shape represented as a {@link Shapes} constant
   */
  Shapes getKind();


}
