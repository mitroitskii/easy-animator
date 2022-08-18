package model.shape.properties;

import java.util.Objects;
import model.shape.Shape;

/**
 * Represents a position of the {@link Shape} on the x/y-axis.
 */
public class Position extends AbstractAxisProperty {

  /**
   * Instantiates a position with the provided values.
   *
   * @param x the coordinate on the x-axis
   * @param y the coordinate on the y-axis
   */
  public Position(int x, int y) {
    super(x, y);
  }

  @Override
  protected boolean equalsPosition(Position other) {
    return other.getX() == this.getX() && other.getY() == this.getY();
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractAxisProperty) {
      AbstractAxisProperty property = (AbstractAxisProperty) other;
      return property.equalsPosition(this);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getX(), this.getY());
  }

}
