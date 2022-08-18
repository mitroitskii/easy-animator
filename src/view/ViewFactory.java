package view;

import java.util.Objects;
import model.AnimationModelView;

/**
 * Makes views.
 */
public class ViewFactory {

  /**
   * Returns a view of the provided type, instantiated with the provided model.
   *
   * @param type  a type of the view to return
   * @param model a model to instantiate a view with
   * @return a view
   * @throws NullPointerException     if any of the provided arguments is null
   * @throws IllegalArgumentException if the provided type of view is not implemented
   */
  public static View make(String type, AnimationModelView model) throws NullPointerException,
      IllegalArgumentException {
    Objects.requireNonNull(type);
    Objects.requireNonNull(model);
    switch (type.toLowerCase()) {
      case "text":
        return new TextView(model);
      case "visual":
        return new GraphicalView(model);
      case "svg":
      default:
        System.out.println("Not null");
        throw new IllegalArgumentException("That view is not implemented yet");
    }
  }
}
