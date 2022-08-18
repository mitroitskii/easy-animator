package model.shape;

/**
 * Represents all the available kinds of {@link Shape}.
 */
public enum Shapes {
  RECTANGLE("rectangle"),
  ELLIPSE("ellipse");

  final String value;

  /**
   * Instantiates an enum constant with the provided string value of the given constant.
   *
   * @param value string value of the given constant
   */
  Shapes(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
