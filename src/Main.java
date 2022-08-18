import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.AnimationModelImpl;
import model.AnimationModelView;
import util.AnimationBuilderModel;
import util.AnimationReader;
import view.View;
import view.ViewFactory;

/**
 * Runs the animation.
 */
public class Main {

  /**
   * Runs the animation from the given input to the given output.
   *
   * @param args the animation input, output and speed
   * @throws IllegalStateException when there is an error outputting the file
   */
  public static void main(String[] args) throws IllegalStateException {

    if (args.length % 2 != 0) {
      System.out.println("Arguments must be provided in pairs");
      return;
    }

    Readable in = null;
    View view = null;
    String viewString = null;
    int speed = 1;
    Appendable out = System.out;
    AnimationModelView model = new AnimationModelImpl();

    for (int i = 0; i < args.length; i += 2) {
      String first = args[i];
      String second = args[i + 1];
      
      switch (first) {
        case "-in":
          try {
            in = new FileReader(second);
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
          }
          break;
        case "-out":
          try {
            out = new FileWriter(second);
          } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
          }
          break;
        case "-view":
          if (second.equals("visual") || second.equals("svg") || second.equals("text")) {
            viewString = second;
          } else {
            System.out.println("A view can be visual / svg / text");
          }
          break;
        case "-speed":
          try {
            speed = Integer.parseInt(second);
          } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
          }
          if (speed < 1) {
            System.out.println("Speed cannot be less than 1");
            return;
          }
          break;
        default:
          System.out.println("Incorrect argument");
          return;
      }
    }

    if (in == null) {
      System.out.println("Must provide input file");
      return;
    }

    if (viewString == null) {
      System.out.println("Must provide the target view");
      return;
    }

    model = (AnimationModelView) AnimationReader.parseFile(in, new AnimationBuilderModel());
    try {
      view = ViewFactory.make(viewString, model);
    } catch (IllegalArgumentException | NullPointerException e) {
      System.out.println(e.getMessage());
      return;
    }
    view.run(out, speed);

  }

}
