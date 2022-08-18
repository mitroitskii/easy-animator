package model.shape;

import java.util.Objects;
import model.shape.properties.Color;
import model.shape.properties.Dimensions;
import model.shape.properties.Position;

/**
 * Creates {@link Shape}s based on the provided kind of shape.
 */
public class ShapeFactory {

  /**
   * Returns a {@link Shape} of the provided kind.
   *
   * @param kind       a kind of shape, defined as a {@link Shapes} constant
   * @param position   position of the created shape
   * @param dimensions dimensions of the created shape
   * @param color      color of the created shape
   * @return a {@link Shape} of the provided kind
   * @throws NullPointerException if the provided kind of shape is null
   */
  public static Shape make(Shapes kind, Position position, Dimensions dimensions, Color color)
      throws NullPointerException {

    Objects.requireNonNull(kind);

    if (kind == Shapes.ELLIPSE) {
      return new Ellipse(position, dimensions, color);
    }
    return new Rectangle(position, dimensions, color);

  }
}
