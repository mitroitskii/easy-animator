package view;

import java.awt.Dimension;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import model.AnimationModelView;
import model.Canvas;
import model.shape.Shape;

/**
 * Represents a visual view of the animation, and all its operations.
 */
public class GraphicalView extends JFrame implements View {

  private final GraphicsPanel graphics;
  private final AnimationModelView model;


  /**
   * Initializes a graphical view of the provided animation model.
   *
   * @param model an animation to create a graphical view of
   */
  public GraphicalView(AnimationModelView model) {

    super("easy-animator");

    this.model = model;
    Canvas canvas = this.model.getCanvas();
    this.setLocation(canvas.getX(), canvas.getY());
    this.setPreferredSize(
        new Dimension(canvas.getWidth(), canvas.getHeight()));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.graphics = new GraphicsPanel(canvas);
    this.add(this.graphics);

    this.pack();
    this.graphics.setVisible(true);
    this.setVisible(true);


  }

  /*
  Helper method used inside the TimerTask to run the animation.
   */
  private void runTask(int time) {
    List<Shape> state = this.model.getStateAt(time);
    this.graphics.setState(state);
    this.graphics.repaint();
    this.repaint();
  }

  @Override
  public void run(Appendable ignored, int speed) throws NullPointerException,
      IllegalStateException {

    int length = this.model.getLength();

    TimerTask task = new TimerTask() {
      int l = 0;

      public void run() {
        runTask(l);
        l++;
        if (l == length) {
          cancel();
        }
      }
    };

    Timer timer = new Timer("Timer");
    timer.schedule(task, 0, 1000 / speed);

  }


}
