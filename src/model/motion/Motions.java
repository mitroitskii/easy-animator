package model.motion;

import model.AnimatedShape;

/**
 * Represents the kinds of motions of the {@link AnimatedShape}.
 */
public enum Motions {
  MOVE("moves"),
  SCALE("scales"),
  COLOR_CHANGE("changes color"),
  FREEZE("stays frozen");

  final String action;

  /**
   * Instantiates an enum constant with the provided action of the given constant.
   *
   * @param action description of the action of the given motion
   */
  Motions(String action) {
    this.action = action;
  }

  @Override
  public String toString() {
    return this.action;
  }
}
