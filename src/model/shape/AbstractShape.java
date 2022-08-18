package model.shape;

import java.util.Objects;
import model.shape.properties.Color;
import model.shape.properties.Dimensions;
import model.shape.properties.Position;

/**
 * Represents a geometric shape and all its properties. Implements all the operations of the {@link
 * Shape}.
 */
class AbstractShape implements Shape {

  private final Position position;
  private final Dimensions dimensions;
  private final Color color;
  private final Shapes kind;


  /**
   * Instantiates a shape with the provided position, dimensions and a color.
   *
   * @param position   an x/y position of the shape, represented as a {@link Position} object
   * @param dimensions dimensions of the shape, represented as a {@link Dimensions} object
   * @param color      a color of the shape represented as a {@link Color} object
   * @throws NullPointerException if any of the position is null
   * @throws NullPointerException if any of dimension object is null
   * @throws NullPointerException if any of color is null
   */
  protected AbstractShape(Position position, Dimensions dimensions, Color color, Shapes kind)
      throws NullPointerException {
    Objects.requireNonNull(position, "A position cannot be null");
    Objects.requireNonNull(dimensions, "A dimension cannot be null");
    Objects.requireNonNull(color, "A color cannot be null");
    this.position = position;
    this.dimensions = dimensions;
    this.color = color;
    this.kind = kind;
  }


  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public Dimensions getDimensions() {
    return this.dimensions;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public Shapes getKind() {
    return this.kind;
  }


}
