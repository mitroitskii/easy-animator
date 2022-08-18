package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import model.motion.Motion;
import model.shape.Shape;
import model.shape.ShapeFactory;
import model.shape.Shapes;
import model.shape.properties.Color;
import model.shape.properties.Dimensions;
import model.shape.properties.Position;

/**
 * Represents an animation model, consisting of animated shapes and all their motions.
 */
public final class AnimationModelImpl implements AnimationModel, AnimationModelView {

  private final Map<String, AnimatedShape> scene;
  private List<Motion> completeSequence;
  private Canvas canvas;
  private int length;

  /**
   * Initiates a model of the animation.
   */
  public AnimationModelImpl() {
    this.scene = new HashMap<>();
    this.completeSequence = null;
    this.canvas = null;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    if (this.canvas != null) {
      throw new IllegalStateException("Canvas cannot be reset");
    }
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Width and height must be a non-negative number");
    }

    this.canvas = new Canvas(x, y, width, height);
  }

  public Canvas getCanvas() {
    return this.canvas;
  }

  public int getLength() {
    return this.length;
  }

  @Override
  public void addShape(String name, Shapes kind)
      throws IllegalArgumentException, NullPointerException {
    if (this.scene.containsKey(name)) {
      throw new IllegalArgumentException("A shape with this name already exists. Can't add twice.");
    }
    this.scene.put(name, new AnimatedShapeArrayList(name, kind));
  }

  @Override
  public void addMotion(String name, int start, int end,
      int xPositionStart, int yPositionStart,
      int xDimensionStart, int yDimensionStart,
      int rStart, int gStart, int bStart,
      int xPositionEnd, int yPositionEnd,
      int xDimensionEnd, int yDimensionEnd,
      int rEnd, int gEnd, int bEnd) throws NullPointerException, IllegalArgumentException,
      IllegalStateException {

    Objects.requireNonNull(name, "The shape's name cannot be null");
    if (!this.scene.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape with such name. Add it "
          + "first.");
    }

    AnimatedShape shape = this.scene.get(name);

    Shapes kind = shape.getKind();
    shape.addMotion(start, end,
        ShapeFactory.make(kind,
            new Position(xPositionStart, yPositionStart),
            new Dimensions(xDimensionStart, yDimensionStart),
            new Color(rStart, gStart,
                bStart)),
        ShapeFactory.make(kind,
            new Position(xPositionEnd, yPositionEnd),
            new Dimensions(xDimensionEnd, yDimensionEnd),
            new Color(rEnd, gEnd, bEnd)));

    if (this.length < end) {
      this.length = end;
    }

    if (this.completeSequence != null) {
      this.completeSequence = generateSequence();
    }
  }

  /*
  Generates a complete sequence of all motions of all shapes in order in anticipation of further
  calls of the .getStateAt() method.
  */
  private List<Motion> generateSequence() {
    return this.scene.values().stream().
        flatMap(ani -> ani.getSequence().stream()).
        sorted(Comparator.comparingInt(Motion::getStart)).
        collect(
            Collectors.toList());
  }

  @Override
  public String toString() {

    List<Motion> sequence = generateSequence();

    return
        "Shapes:\n" +
            this.scene.entrySet().stream()
                .sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey()))
                .map(entry -> entry.getValue().toString())
                .collect(
                    Collectors.joining("\n\n")) +
            (sequence.isEmpty() ? "" : "\n\n" + sequence.stream().map(Motion::toString).
                collect(Collectors.joining("\n")));

  }

  @Override
  public List<Shape> getStateAt(int timestamp) {

    if (this.completeSequence == null) {
      this.completeSequence = generateSequence();
    }

    List<Motion> filteredSequence =
        this.completeSequence.stream()
            .filter(m -> m.getStart() <= timestamp && timestamp <= m.getEnd())
            .collect(Collectors.toList());

    Map<AnimatedShape, Shape> latestShapes = new HashMap<>();
    this.scene.values().forEach(ani -> latestShapes.put(ani,
        ani.getSequence().stream().anyMatch(m -> m.getEnd() <= timestamp) ?
            (ani.getSequence().stream()
                .filter(m -> m.getEnd() <= timestamp).
                max(Comparator.comparingInt(Motion::getEnd)).get().getEndShape()) :
            ShapeFactory.make(ani.getKind(),
                new Position(0, 0),
                new Dimensions(0, 0),
                new Color(0, 0, 0))
    ));

    filteredSequence.forEach(m -> {
      Shape intermediate = latestShapes.get(m.getShape());
      latestShapes.put(m.getShape(), m.transform(intermediate, timestamp));
    });

    return new ArrayList<>(latestShapes.values());
  }


}
