package model.shape.properties;

import java.util.Objects;
import model.shape.Shape;

/**
 * Represents a color of the {@link Shape}.
 */
public class Color {

  private final int r;
  private final int g;
  private final int b;

  /**
   * Instantiates an RGB color with the provided values or throws an exception if any of the
   * color-values is not within the 0-255 range.
   *
   * @param r the amount of red in the final color, as value from 0 to 255
   * @param g the amount of green in the final color, as value from 0 to 255
   * @param b the amount of blue in the final color, as value from 0 to 255
   * @throws IllegalArgumentException if f any of the color-values is not within the 0-255 range
   */
  public Color(int r, int g, int b) throws IllegalArgumentException {

    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("A color value must be within 0 and 255.");
    }

    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Returns the amount of red in this color.
   *
   * @return the amount of red in this color
   */
  public int getR() {
    return this.r;
  }

  /**
   * Returns the amount of green in this color.
   *
   * @return the amount of green in this color
   */
  public int getG() {
    return this.g;
  }

  /**
   * Returns the amount of blue in this color.
   *
   * @return the amount of blue in this color
   */
  public int getB() {
    return this.b;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Color) {
      Color color = (Color) other;
      return color.getR() == this.r && color.getG() == this.g && color.getB() == this.b;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.r, this.g, this.b);
  }

  @Override
  public String toString() {
    return "" + this.r + "," + this.g + "," + this.b;
  }

}
