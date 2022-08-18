package view;

import java.io.IOException;
import java.util.Objects;
import model.AnimationModelView;

/**
 * Represents the textual view of the animation, and all its operations.
 */
public class TextView implements View {

  private final AnimationModelView model;
  private Appendable out;

  /**
   * Instantiates a textual view of the provided animation.
   *
   * @param animation an animation model to create a textual view of
   */
  public TextView(AnimationModelView animation) {
    this.model = animation;
  }

  /*
A helper method that tries to append the given message to the output. If the message cannot be
appended, it throws an IllegalStateException.
 */
  private void tryOutput(String message) throws IllegalStateException {
    try {
      out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Error when trying to output the message.");
    }
  }

  @Override
  public void run(Appendable appendable, int ignored) throws NullPointerException,
      IllegalStateException {
    Objects.requireNonNull(appendable);
    this.out = appendable;
    tryOutput(this.model.toString());
  }
}
