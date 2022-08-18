package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import model.Canvas;
import model.shape.Shape;
import model.shape.Shapes;

/**
 * Represents a panel where the animation is being painted.
 */
public class GraphicsPanel extends JPanel {

  private List<Shape> state;

  /**
   * Initializes this panel with the provided canvas.
   *
   * @param c a canvas with its size and starting corner
   */
  public GraphicsPanel(Canvas c) {

    this.setLocation(c.getX(), c.getY());
    this.setPreferredSize(
        new Dimension(c.getWidth(), c.getHeight()));

    this.setBackground(Color.WHITE);
  }

  /**
   * Sets the current state of the animation.
   *
   * @param state the current state of the animation
   */
  public void setState(List<Shape> state) {
    this.state = state;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.state.forEach(s -> {

      g.setColor(
          new Color(
              s.getColor().getR(),
              s.getColor().getG(),
              s.getColor().getB()));

      if (s.getKind() == Shapes.ELLIPSE) {
        g.fillOval(s.getPosition().getX(), s.getPosition().getY(),
            s.getDimensions().getX(), s.getDimensions().getY());
      } else {
        g.fillRect(s.getPosition().getX(), s.getPosition().getY(),
            s.getDimensions().getX(),
            s.getDimensions().getY());
      }

    });

  }


}
