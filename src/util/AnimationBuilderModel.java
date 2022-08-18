package util;

import model.AnimationModel;
import model.AnimationModelImpl;
import model.shape.Shapes;

/**
 * Implements all the operations of {@link AnimationBuilder} based on {@link AnimationModel}.
 */
public class AnimationBuilderModel implements AnimationBuilder<AnimationModel> {

  final AnimationModel model;

  /**
   * Initializes new builder with a {@link AnimationModelImpl} as a target document.
   */
  public AnimationBuilderModel() {
    this.model = new AnimationModelImpl();
  }

  @Override
  public AnimationModel build() {
    return this.model;
  }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    this.model.setCanvas(x, y, width, height);
  }

  /**
   * /** Adds a new shape to the growing document.
   *
   * @param name The unique name of the shape to be added. No shape with this name should already
   *             exist.
   * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
   *             shapes is unspecified, but should include "ellipse" and "rectangle" as a minimum.
   * @throws IllegalArgumentException if the provided type is not supported by the model
   */
  @Override
  public void declareShape(String name, String type)
      throws IllegalArgumentException {
    if (type.equalsIgnoreCase("ellipse")) {
      this.model.addShape(name, Shapes.ELLIPSE);
    } else if (type.equalsIgnoreCase("rectangle")) {
      this.model.addShape(name, Shapes.RECTANGLE);
    } else {
      throw new IllegalArgumentException("No such type supported");
    }
  }

  @Override
  public void addMotion(String name, int t1, int x1, int y1, int w1,
      int h1, int r1,
      int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    this.model.addMotion(name, t1, t2, x1, y1, w1, h1, r1, g1, b1, x2, y2, w2, h2, r2, g2, b2);
  }
}
