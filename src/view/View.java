package view;

/**
 * An MVC view for the Animation.
 */
public interface View {

  /**
   * Output the view of the provided animation to the provided appendable.
   *
   * @param appendable a source to output this animation to
   * @param speed      a speed of the animation
   * @throws NullPointerException  if the provided appendable is null
   * @throws IllegalStateException if there is an error when trying to output the animation
   */
  void run(Appendable appendable, int speed) throws NullPointerException, IllegalStateException;

}
