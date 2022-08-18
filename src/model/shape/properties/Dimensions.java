package model.shape.properties;

import java.util.Objects;
import model.shape.Shape;

/**
 * Represents dimensions of the {@link Shape} on the x/y-axis.
 */
public class Dimensions extends AbstractAxisProperty {

  /**
   * Instantiates dimensions of the shape with the provided values.
   *
   * @param x the length of the dimension along the x-axis
   * @param y the length of the dimension along the y-axis
   */
  public Dimensions(int x, int y) {
    super(x, y);
  }

  @Override
  protected boolean equalsDimensions(Dimensions other) {
    return other.getX() == this.getX() && other.getY() == this.getY();
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractAxisProperty) {
      AbstractAxisProperty property = (AbstractAxisProperty) other;
      return property.equalsDimensions(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getX(), this.getY());
  }

}
