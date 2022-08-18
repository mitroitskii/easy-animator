package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.motion.ColorChange;
import model.motion.Freeze;
import model.motion.Motion;
import model.motion.Motions;
import model.motion.Move;
import model.motion.Scale;
import model.shape.Shape;
import model.shape.Shapes;
import model.shape.properties.Color;
import model.shape.properties.Dimensions;
import model.shape.properties.Position;

/**
 * Represents. Implements all the operations of the {@link AnimatedShape}. Uses a list to store the
 * sequence of motions.
 */
class AnimatedShapeArrayList implements AnimatedShape {

  private final String name;
  private final Shapes kind;
  private final List<Motion> sequence;
  private Shape initialShape;
  private int appearsAt;
  private int disappearsAt;

  /**
   * Instantiates an animated list with the provided name and kind.
   *
   * @param name a name of this animated shape
   * @param kind a kind of this animated shape
   * @throws NullPointerException if the provided name is null
   * @throws NullPointerException if the provided kind is null
   */
  public AnimatedShapeArrayList(String name, Shapes kind) throws NullPointerException {
    Objects.requireNonNull(name, "A name of the animated shape can't be null");
    Objects.requireNonNull(kind, "A kind of the animated shape can't be null");
    this.name = name;
    this.kind = kind;
    this.sequence = new ArrayList<>();
    this.initialShape = null;
    this.appearsAt = 0;
    this.disappearsAt = 0;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<Motion> getSequence() {
    return new ArrayList<>(this.sequence);
  }

  @Override
  public void addMotion(int start, int end, Shape startShape, Shape endShape)
      throws NullPointerException, IllegalArgumentException {

    if (this.appearsAt == 0) {
      this.appearsAt = start;
    }
    if (this.disappearsAt < end) {
      this.disappearsAt = end;
    }
    if (this.initialShape == null) {
      this.initialShape = startShape;
    }

    if (!endShape.getPosition().equals(startShape.getPosition())) {
      tryAddMotion(new Move(this, start, end, startShape, endShape));
    }
    if (!endShape.getDimensions().equals(startShape.getDimensions())) {
      tryAddMotion(new Scale(this, start, end, startShape, endShape));
    }
    if (!endShape.getColor().equals(startShape.getColor())) {
      tryAddMotion(new ColorChange(this, start, end, startShape, endShape));
    }

    if (endShape.getDimensions().equals(startShape.getDimensions()) &&
        endShape.getPosition().equals(startShape.getPosition()) &&
        endShape.getColor().equals(startShape.getColor())) {
      tryAddMotion(new Freeze(this, start, end, startShape, endShape));
    }

  }

  /*
   * Checks if the provided kind of motion can be added to the sequence, and adds it if possible.
   *
   * @throws IllegalArgumentException if the start time is less than 1
   * @throws IllegalArgumentException if the end time is before the start time
   * @throws IllegalStateException    if another motion of the same kind exists in the sequence
   *                                  within the given timeframe
   * @throws IllegalStateException    if there is a FREEZE motion happening during given timeframe
   *                                  to the shape with this name
   */
  private void tryAddMotion(Motion m) throws IllegalArgumentException, NullPointerException {

    if (this.sequence.stream().anyMatch(other -> (
        other.getKind() == m.getKind() || other.getKind() == Motions.FREEZE)
        && checkTimeOverlap(other, m))) {
      throw new IllegalStateException(
          "There is another motion of the same kind or a freeze in progress");
    }

    if (this.sequence.stream().anyMatch(other ->
        m.getKind() == Motions.FREEZE && checkTimeOverlap(other, m))) {
      throw new IllegalStateException("Cannot freeze shape when there is a motion in progress");
    }

    this.sequence.add(m);
  }

  /*
  Helper method to check if the motion timeframe overlaps.
   */
  private boolean checkTimeOverlap(Motion other, Motion m) {
    return m.getStart() < other.getEnd() && other.getEnd() < m.getEnd() ||
        m.getStart() < other.getStart() && other.getStart() < m.getEnd() ||
        other.getStart() < m.getEnd() && m.getEnd() < other.getEnd() ||
        other.getStart() < m.getStart() && m.getStart() < other.getEnd();
  }

  @Override
  public String toString() {
    return
        "Name:" + " " + this.name
            + "\n" + "Kind:" + " " + this.kind +
            (this.initialShape != null ?
                "\n" + this.initialShape
                : "") +
            (this.appearsAt > 0 ?
                "\n" + "Appears at t=" + this.appearsAt
                    + "\n" + "Disappears at t=" + this.disappearsAt
                : "");
  }

  /**
   * Returns the **initial** position of this {@link AnimatedShape}.
   *
   * @return the **initial** position of this {@link AnimatedShape}
   */
  @Override
  public Position getPosition() {
    return this.initialShape == null ? null : initialShape.getPosition();
  }

  /**
   * Returns the **initial** dimensions of this {@link AnimatedShape}.
   *
   * @return the **initial** dimensions of this {@link AnimatedShape}
   */
  @Override
  public Dimensions getDimensions() {
    return this.initialShape == null ? null : initialShape.getDimensions();
  }

  /**
   * Returns the **initial** color of this {@link AnimatedShape}.
   *
   * @return the **initial** color of this {@link AnimatedShape}
   */
  @Override
  public Color getColor() {
    return this.initialShape == null ? null : initialShape.getColor();
  }


  @Override
  public Shapes getKind() {
    return this.kind;
  }

}
