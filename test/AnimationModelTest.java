import static model.shape.Shapes.ELLIPSE;
import static model.shape.Shapes.RECTANGLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import model.AnimationModel;
import model.AnimationModelImpl;
import model.shape.Shape;
import model.shape.ShapeFactory;
import model.shape.Shapes;
import model.shape.properties.Color;
import model.shape.properties.Dimensions;
import model.shape.properties.Position;
import org.junit.Test;

/**
 * Test cases for the animation Model. Verifying that animation state is properly managed, and all
 * operations are properly functioning.
 */

public class AnimationModelTest {


  /**
   * Checks that adding a shape with null name throws an exception.
   */
  @Test
  public void testAddShapeNullName() {
    AnimationModel model = new AnimationModelImpl();
    try {
      model.addShape(null, ELLIPSE);
      fail("Adding null name should have thrown an exception");
    } catch (NullPointerException npe) {
      assertEquals("A name of the animated shape can't be null", npe.getMessage());
    }
  }

  /**
   * Checks that adding a shape with null kind throws an exception.
   */
  @Test
  public void testAddShapeNullKind() {
    AnimationModel model = new AnimationModelImpl();
    try {
      model.addShape("R", null);
      fail("Adding null kind should have thrown an exception");
    } catch (NullPointerException npe) {
      assertEquals("A kind of the animated shape can't be null", npe.getMessage());
    }
  }

  /**
   * Checks that adding a shape with the same name twice throws an exception.
   */
  @Test
  public void testAddShapeSameNameTwice() {
    AnimationModel model = new AnimationModelImpl();
    model.addShape("R", Shapes.RECTANGLE);
    try {
      model.addShape("R", ELLIPSE);
      fail("Adding the same named shape twice should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A shape with this name already exists. Can't add twice.", iae.getMessage());
    }
  }

  /**
   * Checks that the model can add an ellipse and have it printed correctly.s
   */
  @Test
  public void testAddEllipse() {
    AnimationModel model = new AnimationModelImpl();
    model.addShape("E", ELLIPSE);
    String expected =
        "Shapes:\n" +
            "Name: E\n" +
            "Kind: ellipse";
    assertEquals(expected, model.toString());
  }

  /**
   * Checks that the model can add a rectangle and have it printed correctly.s
   */
  @Test
  public void testAddRectangle() {
    AnimationModel model = new AnimationModelImpl();
    model.addShape("R", Shapes.RECTANGLE);
    String expected =
        "Shapes:\n" +
            "Name: R\n" +
            "Kind: rectangle";
    assertEquals(expected, model.toString());
  }

  /**
   * Checks that the model can add a rectangle and have it printed correctly.s
   */
  @Test
  public void testAddShapes() {
    AnimationModel model = new AnimationModelImpl();
    model.addShape("R", Shapes.RECTANGLE);
    model.addShape("O", ELLIPSE);
    model.addShape("r", Shapes.RECTANGLE);
    String expected =
        "Shapes:\n" +
            "Name: O\n" +
            "Kind: ellipse" +
            "\n\n" +
            "Name: R\n" +
            "Kind: rectangle" +
            "\n\n" +
            "Name: r\n" +
            "Kind: rectangle";
    assertEquals(expected, model.toString());
  }

  /**
   * Checks that adding a motion to a shape with null name throws an exception.
   */
  @Test
  public void testAddMotionNullName() {
    AnimationModel model = new AnimationModelImpl();
    try {
      model.addMotion(null, 1, 1,
          1, 1, 1, 1,
          1, 1, 1,
          1, 1, 1, 1,
          1, 1, 1);
      fail("Adding a motion with null name should have thrown an exception");
    } catch (NullPointerException npe) {
      assertEquals("The shape's name cannot be null", npe.getMessage());
    }
  }

  /**
   * Checks that providing illegal RGB values to the addMotion method throws an exception.
   */
  @Test
  public void testAddMotionColorOutOfRange() {

    AnimationModel model = new AnimationModelImpl();
    model.addShape("R", Shapes.RECTANGLE);

    //  -1 for starting red throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          -1, 1, 1,
          1, 1, 1, 1,
          1, 1, 1);
      fail("Giving -1 red should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  -1 for ending red throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 1, 1,
          1, 1, 1, 1,
          -1, 1, 1);
      fail("Giving -1 red should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  256 for starting red throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          256, 1, 1,
          1, 1, 1, 1,
          1, 1, 1);
      fail("Giving 256 red should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  256 for ending red throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 1, 1,
          1, 1, 1, 1,
          256, 1, 1);
      fail("Giving 256 red should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  -1 for starting green throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, -1, 1,
          1, 1, 1, 1,
          1, 1, 1);
      fail("Giving -1 green should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  -1 for ending green throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 1, 1,
          1, 1, 1, 1,
          1, -1, 1);
      fail("Giving -1 green should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  256 for starting green throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 256, 1,
          1, 1, 1, 1,
          1, 1, 1);
      fail("Giving 256 green should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  256 for ending green throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 1, 1,
          1, 1, 1, 1,
          1, 256, 1);
      fail("Giving 256 green should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  -1 for starting blue throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 1, -1,
          1, 1, 1, 1,
          1, 1, 1);
      fail("Giving -1 blue should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  -1 for ending blue throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 1, 1,
          1, 1, 1, 1,
          1, 1, -1);
      fail("Giving -1 blue should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  256 for starting blue throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 1, 256,
          1, 1, 1, 1,
          1, 1, 1);
      fail("Giving 256 blue should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

    //  256 for ending blue throws exception
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 1, 1,
          1, 1, 1, 1,
          1, 1, 256);
      fail("Giving 256 blue should have thrown an exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("A color value must be within 0 and 255.", iae.getMessage());
    }

  }


  /**
   * Checks that adding a motion to a shape, that hasn't been previously added, throws an
   * exception.
   */
  @Test
  public void testAddMotionNoShape() {
    AnimationModel model = new AnimationModelImpl();
    try {
      model.addMotion("R", 1, 1,
          1, 1, 1, 1,
          1, 1, 1,
          1, 1, 1, 1,
          1, 1, 1);
      fail("Adding a motion to a shape, that hasn't been previously added, should have thrown an "
          + "exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("There is no shape with such name. Add it "
          + "first.", iae.getMessage());
    }
  }

  /**
   * Checks that adding a motion with a start time less of than 1 throws an exception.
   */
  @Test
  public void testAddMotionIllegalStartTime() {
    AnimationModel model = new AnimationModelImpl();
    model.addShape("R", Shapes.RECTANGLE);

    for (int i = -1; i > -100; i--) {
      try {
        model.addMotion("R", i, 1,
            1, 1, 1, 1,
            1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1);
        fail(
            "Adding a motion with a start time less than 0 should have thrown an exception");
      } catch (IllegalArgumentException iae) {
        assertEquals("The start time cannot be less than 0", iae.getMessage());
      }
    }

  }

  /**
   * Checks that adding a motion with a start time less of than 1 throws an exception.
   */
  @Test
  public void testAddMotionIllegalEndTime() {
    AnimationModel model = new AnimationModelImpl();
    model.addShape("R", Shapes.RECTANGLE);

    for (int i = 1; i < 100; i++) {
      int j = i - 1;
      try {
        model.addMotion("R", i, j,
            1, 1, 1, 1,
            1, 1, 1,
            1, 1, 1, 1,
            1, 1, 1);
        fail(
            "Adding a motion with an end time less of than 1 should have thrown an exception");
      } catch (IllegalArgumentException iae) {
        assertEquals("The end time cannot be before the start time", iae.getMessage());
      }
    }

  }

  /**
   * Checks that adding an ellipse and adding a motion to it prints the initial properties of shape
   * and shape appear / disappear times correctly.
   */
  @Test
  public void testEllipseInitialShape() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("E", ELLIPSE);
      Random r = new Random();

      int appearsAt = r.nextInt(10) + 1;
      int end = appearsAt + r.nextInt(10);
      int xPositionStart = r.nextInt();
      int yPositionStart = r.nextInt();
      int xDimensionStart = r.nextInt();
      int yDimensionStart = r.nextInt();
      int rStart = r.nextInt(256);
      int gStart = r.nextInt(256);
      int bStart = r.nextInt(256);
      int xPositionEnd = r.nextInt();
      int yPositionEnd = r.nextInt();
      int xDimensionEnd = r.nextInt();
      int yDimensionEnd = r.nextInt();
      int rEnd = r.nextInt(256);
      int gEnd = r.nextInt(256);
      int bEnd = r.nextInt(256);

      // adding an initial motion
      model.addMotion("E", appearsAt, end,
          xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
          rStart, gStart, bStart,
          xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
          rEnd, gEnd, bEnd);

      int start;
      // adding many more motions to this shape
      for (int j = 1; j < 100; j++) {
        // making sure that new motions added are always after the initial motion
        start = end + r.nextInt(10);
        end = start + r.nextInt(10);
        model.addMotion("E", start, end,
            r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
            r.nextInt(256), r.nextInt(256), r.nextInt(256),
            r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
            r.nextInt(256), r.nextInt(256), r.nextInt(256));

      }

      String expected =
          "Shapes:\n" +
              "Name: E\n" +
              "Kind: ellipse\n" +
              "Center:" + " " + "(" + xPositionStart + "," + yPositionStart + ")" + "," + " " +
              "X/Y Radius:" + " " + "(" + xDimensionStart + "," + yDimensionStart + ")" + "," + " "
              + "Color:" + " " + "(" + rStart + "," + gStart + "," + bStart + ")" + "\n" +
              "Appears at t=" + appearsAt + "\n" +
              "Disappears at t=" + end;

      String actual = Arrays.stream(model.toString().split("\n")).limit(6)
          .collect(Collectors.joining("\n"));
      assertEquals(expected, actual);

    }

  }

  /**
   * Checks that adding a rectangle and adding a motion to it prints the initial properties of shape
   * and shape appear / disappear times correctly.
   */
  @Test
  public void testRectangleInitialShape() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int appearsAt = r.nextInt(10) + 1;
      int end = appearsAt + r.nextInt(10);
      int xPositionStart = r.nextInt();
      int yPositionStart = r.nextInt();
      int xDimensionStart = r.nextInt();
      int yDimensionStart = r.nextInt();
      int rStart = r.nextInt(256);
      int gStart = r.nextInt(256);
      int bStart = r.nextInt(256);
      int xPositionEnd = r.nextInt();
      int yPositionEnd = r.nextInt();
      int xDimensionEnd = r.nextInt();
      int yDimensionEnd = r.nextInt();
      int rEnd = r.nextInt(256);
      int gEnd = r.nextInt(256);
      int bEnd = r.nextInt(256);

      // adding an initial motion
      model.addMotion("R", appearsAt, end,
          xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
          rStart, gStart, bStart,
          xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
          rEnd, gEnd, bEnd);

      int start;
      // adding many more motions to this shape
      for (int j = 1; j < 100; j++) {
        // making sure that new motions added are always after the initial motion
        start = end + r.nextInt(10);
        end = start + r.nextInt(10);
        model.addMotion("R", start, end,
            r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
            r.nextInt(256), r.nextInt(256), r.nextInt(256),
            r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
            r.nextInt(256), r.nextInt(256), r.nextInt(256));

      }

      String expected =
          "Shapes:\n" +
              "Name: R\n" +
              "Kind: rectangle\n" +
              "Min corner:" + " " + "(" + xPositionStart + "," + yPositionStart + ")" + "," + " " +
              "Width/Height:" + " " + "(" + xDimensionStart + "," + yDimensionStart + ")" + ","
              + " "
              + "Color:" + " " + "(" + rStart + "," + gStart + "," + bStart + ")" + "\n" +
              "Appears at t=" + appearsAt + "\n" +
              "Disappears at t=" + end;

      String actual = Arrays.stream(model.toString().split("\n")).limit(6)
          .collect(Collectors.joining("\n"));
      assertEquals(expected, actual);

    }

  }

  /**
   * Checks that adding various shapes and adding motions to them prints the initial properties of
   * shape and shape appear / disappear times correctly.
   */
  @Test
  public void testInitialShapesAndTimes() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      model.addShape("E", ELLIPSE);
      model.addShape("S", Shapes.RECTANGLE);

      Random r = new Random();

      int appearsAt = r.nextInt(10) + 1;
      int end = appearsAt + r.nextInt(10);
      int xPositionStart = r.nextInt();
      int yPositionStart = r.nextInt();
      int xDimensionStart = r.nextInt();
      int yDimensionStart = r.nextInt();
      int rStart = r.nextInt(256);
      int gStart = r.nextInt(256);
      int bStart = r.nextInt(256);
      int xPositionEnd = r.nextInt();
      int yPositionEnd = r.nextInt();
      int xDimensionEnd = r.nextInt();
      int yDimensionEnd = r.nextInt();
      int rEnd = r.nextInt(256);
      int gEnd = r.nextInt(256);
      int bEnd = r.nextInt(256);

      // adding an initial motion
      model.addMotion("R", appearsAt, end,
          xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
          rStart, gStart, bStart,
          xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
          rEnd, gEnd, bEnd);
      model.addMotion("E", appearsAt, end,
          xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
          rStart, gStart, bStart,
          xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
          rEnd, gEnd, bEnd);
      model.addMotion("S", appearsAt, end,
          xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
          rStart, gStart, bStart,
          xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
          rEnd, gEnd, bEnd);

      String expected =
          "Shapes:\n" +
              "Name: E\n" +
              "Kind: ellipse\n" +
              "Center:" + " " + "(" + xPositionStart + "," + yPositionStart + ")" + "," + " " +
              "X/Y Radius:" + " " + "(" + xDimensionStart + "," + yDimensionStart + ")" + ","
              + " "
              + "Color:" + " " + "(" + rStart + "," + gStart + "," + bStart + ")" + "\n" +
              "Appears at t=" + appearsAt + "\n" +
              "Disappears at t=" + end + "\n\n" +
              "Name: R\n" +
              "Kind: rectangle\n" +
              "Min corner:" + " " + "(" + xPositionStart + "," + yPositionStart + ")" + "," + " " +
              "Width/Height:" + " " + "(" + xDimensionStart + "," + yDimensionStart + ")" + ","
              + " "
              + "Color:" + " " + "(" + rStart + "," + gStart + "," + bStart + ")" + "\n" +
              "Appears at t=" + appearsAt + "\n" +
              "Disappears at t=" + end + "\n\n" +
              "Name: S\n" +
              "Kind: rectangle\n" +
              "Min corner:" + " " + "(" + xPositionStart + "," + yPositionStart + ")" + "," + " " +
              "Width/Height:" + " " + "(" + xDimensionStart + "," + yDimensionStart + ")" + ","
              + " "
              + "Color:" + " " + "(" + rStart + "," + gStart + "," + bStart + ")" + "\n" +
              "Appears at t=" + appearsAt + "\n" +
              "Disappears at t=" + end;

      String actual = Arrays.stream(model.toString().split("\n")).limit(18)
          .collect(Collectors.joining("\n"));
      assertEquals(expected, actual);

    }

  }

  /**
   * Checks that the model correctly prints the time that the shape disappears at when the shape
   * motions are added without breaks.
   */
  @Test
  public void testDisappearTimeForNoBreakMotions() {
    AnimationModel model = new AnimationModelImpl();
    model.addShape("R", Shapes.RECTANGLE);
    Random r = new Random();

    int appearsAt = r.nextInt(10) + 1;
    int end = appearsAt + r.nextInt(10);
    int start;

    model.addMotion("R", appearsAt, end,
        r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
        r.nextInt(256), r.nextInt(256), r.nextInt(256),
        r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
        r.nextInt(256), r.nextInt(256), r.nextInt(256));

    for (int i = 1; i < 100; i++) {
      start = end;
      end = start + r.nextInt(10);

      model.addMotion("R", start, end,
          r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
          r.nextInt(256), r.nextInt(256), r.nextInt(256),
          r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
          r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    String expected = "Disappears at t=" + end;
    // getting the 5th line of the output from Model's .toString
    String actual =
        Arrays.stream(model.toString().split("\n")).limit(6).
            collect(Collectors.toList()).get(5);

    assertEquals(expected, actual);

  }


  /**
   * Checks that the model correctly prints the time that the shape disappears at when the shape
   * motions are added **with** breaks.
   */
  @Test
  public void testDisappearTimeWithBreaks() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int appearsAt = r.nextInt(10) + 1;
      int end = appearsAt + r.nextInt(10);
      int start;

      model.addMotion("R", appearsAt, end,
          r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
          r.nextInt(256), r.nextInt(256), r.nextInt(256),
          r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
          r.nextInt(256), r.nextInt(256), r.nextInt(256));

      for (int j = 1; j < 100; j++) {
        start = end + r.nextInt(10) + 1;
        end = start + r.nextInt(10);

        model.addMotion("R", start, end,
            r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
            r.nextInt(256), r.nextInt(256), r.nextInt(256),
            r.nextInt(), r.nextInt(), r.nextInt(), r.nextInt(),
            r.nextInt(256), r.nextInt(256), r.nextInt(256));
      }

      String expected = "Disappears at t=" + end;
      // getting the 5th line of the output from Model's .toString
      String actual =
          Arrays.stream(model.toString().split("\n")).limit(6).
              collect(Collectors.toList()).get(5);

      assertEquals(expected, actual);

    }
  }


  /**
   * Checks that adding the motions of the same kind at the parallel timeframe throws an exception.
   */
  @Test
  public void testParallelMotionsException() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      // adding a motion with a color change
      model.addMotion("R", 10, 100,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          r.nextInt(256), r.nextInt(256), r.nextInt(256));

      int start;
      int end;

      // checking that the **start** time of another motion of the same kind cannot be in-between
      // existing motion's timeframe
      for (int j = 1; j < 90; j++) {
        start = 10 + j;
        end = start + r.nextInt(1000);

        try {
          model.addMotion("R", start, end,
              1, 1, 1, 1,
              0, 0, 0,
              1, 1, 1, 1,
              r.nextInt(256), r.nextInt(256), r.nextInt(256));
          fail("Exception should have been thrown");
        } catch (IllegalStateException ise) {
          assertEquals(
              "There is another motion of the same kind or a freeze in progress",
              ise.getMessage());
        }
      }

      // checking that the **end** time of another motion of the same kind cannot be in-between
      // existing motion's timeframe
      for (int k = 0; k < 100; k++) {
        start = 1;
        end = 11 + r.nextInt(89);

        try {
          model.addMotion("R", start, end,
              1, 1, 1, 1,
              0, 0, 0,
              1, 1, 1, 1,
              r.nextInt(256), r.nextInt(256), r.nextInt(256));
          fail("Exception should have been thrown");
        } catch (IllegalStateException ise) {
          assertEquals(
              "There is another motion of the same kind or a freeze in progress",
              ise.getMessage());
        }
      }


    }
  }

  /**
   * Checks that adding the motions of any kind when there is a freeze in progress throws an
   * exception.
   */
  @Test
  public void testFreezeInProgressException() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      // adding a FREEZE
      model.addMotion("R", 10, 100,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          0, 0, 0);

      int start;
      int end;

      // checking that the **start** time of another motion cannot be in-between
      // FREEZE motion's timeframe
      for (int j = 1; j < 90; j++) {
        start = 10 + j;
        end = start + r.nextInt(1000);

        try {
          model.addMotion("R", start, end,
              1, 1, 1, 1,
              0, 0, 0,
              1, 1, 1, 1,
              r.nextInt(256), r.nextInt(256), r.nextInt(256));
          fail("Exception should have been thrown");
        } catch (IllegalStateException ise) {
          assertEquals(
              "There is another motion of the same kind or a freeze in progress",
              ise.getMessage());
        }
      }

      // checking that the **end** time of another motion of the same kind cannot be in-between
      // FREEZE motion's timeframe
      for (int k = 0; k < 100; k++) {
        start = 1;
        end = 11 + r.nextInt(89);

        try {
          model.addMotion("R", start, end,
              1, 1, 1, 1,
              0, 0, 0,
              1, 1, 1, 1,
              r.nextInt(256), r.nextInt(256), r.nextInt(256));
          fail("Exception should have been thrown");
        } catch (IllegalStateException ise) {
          assertEquals(
              "There is another motion of the same kind or a freeze in progress",
              ise.getMessage());
        }
      }


    }
  }

  /**
   * Checks that adding a FREEZE motion when there is any kind of motion in progress, throws an
   * exception.
   */
  @Test
  public void testParallelFreezeException() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      // adding a motion with a color change
      model.addMotion("R", 10, 100,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          r.nextInt(256), r.nextInt(256), r.nextInt(256));

      int start;
      int end;

      // checking that the **start** time of FREEZE motion cannot be in-between
      // existing motion's timeframe
      for (int j = 1; j < 90; j++) {
        start = 10 + j;
        end = start + r.nextInt(1000);

        try {
          model.addMotion("R", start, end,
              1, 1, 1, 1,
              0, 0, 0,
              1, 1, 1, 1,
              0, 0, 0);
          fail("Exception should have been thrown");
        } catch (IllegalStateException ise) {
          assertEquals("Cannot freeze shape when there is a motion in "
                  + "progress",
              ise.getMessage());
        }
      }

      // checking that the **end** time of FREEZE motion cannot be in-between
      // existing motion's timeframe
      for (int k = 0; k < 100; k++) {
        start = 1;
        end = 11 + r.nextInt(89);

        try {
          model.addMotion("R", start, end,
              1, 1, 1, 1,
              0, 0, 0,
              1, 1, 1, 1,
              0, 0, 0);
          fail("Exception should have been thrown");
        } catch (IllegalStateException ise) {
          assertEquals("Cannot freeze shape when there is a motion in "
                  + "progress",
              ise.getMessage());
        }
      }

    }
  }


  /**
   * Checks that adding a motion that starts / ends **exactly** at the end / start time of another
   * motion of the same kind **does not** throw an exception.
   */
  @Test
  public void testAddSequentialMotions() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int start = r.nextInt(100);
      int end = start + r.nextInt(100);

      // adding a first motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          r.nextInt(256), r.nextInt(256), r.nextInt(256));

      // checking that adding motions sequentially one after another does not throw an exception
      for (int j = 1; j < 100; j++) {
        start = end;
        end = start + r.nextInt(100);

        try {
          model.addMotion("R", start, end,
              1, 1, 1, 1,
              0, 0, 0,
              1, 1, 1, 1,
              r.nextInt(256), r.nextInt(256), r.nextInt(256));
        } catch (IllegalStateException ignored) {
          fail("Exception should not have been thrown");
        }
      }

    }
  }

  /**
   * Checks that the model correctly identifies the motion added and describes it in the animation
   * when only the position of the shape changes.
   */
  @Test
  public void testMove() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int start = r.nextInt(10) + 1;
      int end = start + r.nextInt(10);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          2, 2, 1, 1,
          0, 0, 0);

      StringBuilder expected = new StringBuilder();

      expected.append("Shape R moves from (").append(1).append(",")
          .append(1).append(") to (").append(2).append(",")
          .append(2).append(") from t=").append(start).append(" to t=").append(end);

      for (int j = 0; j < 20; j++) {
        start = end;
        end = start + r.nextInt(10);
        int xPositionStart = r.nextInt();
        int yPositionStart = r.nextInt();
        int xPositionEnd = r.nextInt();
        int yPositionEnd = r.nextInt();

        model.addMotion("R", start, end,
            xPositionStart, yPositionStart, 1, 1,
            0, 0, 0,
            xPositionEnd, yPositionEnd, 1, 1,
            0, 0, 0);

        expected.append("\n").append("Shape R moves from (").append(xPositionStart).append(",")
            .append(yPositionStart).append(") to (").append(xPositionEnd).append(",")
            .append(yPositionEnd).append(") from t=").append(start).append(" to t=").append(end);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 7, 28));
      assertEquals(expected.toString(), actual);

    }
  }


  /**
   * Checks that the model correctly identifies the motion added and describes it in the animation
   * when only the dimensions of the shape change.
   */
  @Test
  public void testScale() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int start = r.nextInt(10) + 1;
      int end = start + r.nextInt(10);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 2, 2,
          0, 0, 0);

      StringBuilder expected = new StringBuilder();

      expected.append("Shape R scales from (").append(1).append(",")
          .append(1).append(") to (").append(2).append(",")
          .append(2).append(") from t=").append(start).append(" to t=").append(end);

      for (int j = 0; j < 20; j++) {
        start = end;
        end = start + r.nextInt(10);
        int xDimensionStart = r.nextInt();
        int yDimensionStart = r.nextInt();
        int xDimensionEnd = r.nextInt();
        int yDimensionEnd = r.nextInt();

        model.addMotion("R", start, end,
            1, 1, xDimensionStart, yDimensionStart,
            0, 0, 0,
            1, 1, xDimensionEnd, yDimensionEnd,
            0, 0, 0);

        expected.append("\n").append("Shape R scales from (").append(xDimensionStart).append(",")
            .append(yDimensionStart).append(") to (").append(xDimensionEnd).append(",")
            .append(yDimensionEnd).append(") from t=").append(start).append(" to t=").append(end);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 7, 28));
      assertEquals(expected.toString(), actual);

    }
  }


  /**
   * Checks that the model correctly identifies the motion added and describes it in the animation
   * when only the color of the shape changes.
   */
  @Test
  public void testColorChange() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int start = r.nextInt(10) + 1;
      int end = start + r.nextInt(10);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          2, 2, 2);

      StringBuilder expected = new StringBuilder();

      expected.append("Shape R changes color from (0,0,0) to (2,2,2) from t=").
          append(start).append(" to t=").append(end);

      for (int j = 0; j < 20; j++) {
        start = end;
        end = start + r.nextInt(10);

        int rStart = r.nextInt(256);
        int gStart = r.nextInt(256);
        int bStart = r.nextInt(256);
        int rEnd = r.nextInt(256);
        int gEnd = r.nextInt(256);
        int bEnd = r.nextInt(256);

        model.addMotion("R", start, end,
            1, 1, 1, 1,
            rStart, gStart, bStart,
            1, 1, 1, 1,
            rEnd, gEnd, bEnd);

        expected.append("\n").append("Shape R changes color from (")
            .append(rStart).append(",").append(gStart).append(",").append(bStart)
            .append(") to (")
            .append(rEnd).append(",").append(gEnd).append(",").append(bEnd)
            .append(") from t=").append(start).append(" to t=").append(end);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 7, 28));
      assertEquals(expected.toString(), actual);

    }
  }

  /**
   * Checks that the model correctly identifies the motion added and describes it in the animation
   * when none of the properties of the shape change.
   */
  @Test
  public void testFreeze() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int start = r.nextInt(10) + 1;
      int end = start + r.nextInt(10);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          2, 2, 2);

      StringBuilder expected = new StringBuilder();

      expected.append("Shape R changes color from (0,0,0) to (2,2,2) from t=").
          append(start).append(" to t=").append(end);

      for (int j = 0; j < 20; j++) {
        start = end;
        end = start + r.nextInt(10);

        model.addMotion("R", start, end,
            1, 1, 1, 1,
            0, 0, 0,
            1, 1, 1, 1,
            0, 0, 0);

        expected.append("\n").append("Shape R stays frozen with properties (1,1,1,1,0,0,0) from "
            + "t=").append(start).append(
            " to t=").append(end);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 7, 28));
      assertEquals(expected.toString(), actual);

    }
  }

  /**
   * Checks that the model correctly identifies the motion added and describes it in the animation
   * when both the position and the color of the shape change.
   */
  @Test
  public void testMoveAndColorChange() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int start = r.nextInt(10) + 1;
      int end = start + r.nextInt(10);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          2, 2, 2);

      StringBuilder expected = new StringBuilder();

      // adding initial motion string
      expected.append("Shape R changes color from (0,0,0) to (2,2,2) from t=").
          append(start).append(" to t=").append(end);

      for (int j = 0; j < 20; j++) {
        start = end;
        end = start + r.nextInt(10);
        int xPositionStart = r.nextInt();
        int yPositionStart = r.nextInt();
        int xPositionEnd = r.nextInt();
        int yPositionEnd = r.nextInt();
        int rStart = r.nextInt(256);
        int gStart = r.nextInt(256);
        int bStart = r.nextInt(256);
        int rEnd = r.nextInt(256);
        int gEnd = r.nextInt(256);
        int bEnd = r.nextInt(256);

        model.addMotion("R", start, end,
            xPositionStart, yPositionStart, 1, 1,
            rStart, gStart, bStart,
            xPositionEnd, yPositionEnd, 1, 1,
            rEnd, gEnd, bEnd);

        // adding the position change string
        expected.append("\n").append("Shape R moves from (").append(xPositionStart).append(",")
            .append(yPositionStart).append(") to (").append(xPositionEnd).append(",")
            .append(yPositionEnd).append(") from t=").append(start).append(" to t=").append(end);

        // adding the color change string
        expected.append("\n").append("Shape R changes color from (")
            .append(rStart).append(",").append(gStart).append(",").append(bStart)
            .append(") to (")
            .append(rEnd).append(",").append(gEnd).append(",").append(bEnd)
            .append(") from t=").append(start).append(" to t=").append(end);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 7, 48));
      assertEquals(expected.toString(), actual);

    }
  }

  /**
   * Checks that the model correctly identifies the motion added and describes it in the animation
   * when both the color and the dimensions of the shape change.
   */
  @Test
  public void testScaleAndColorChange() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int start = r.nextInt(10) + 1;
      int end = start + r.nextInt(10);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          2, 2, 2);

      StringBuilder expected = new StringBuilder();

      // adding initial motion string
      expected.append("Shape R changes color from (0,0,0) to (2,2,2) from t=").
          append(start).append(" to t=").append(end);

      for (int j = 0; j < 20; j++) {
        start = end;
        end = start + r.nextInt(10);
        int xDimensionStart = r.nextInt();
        int yDimensionStart = r.nextInt();
        int xDimensionEnd = r.nextInt();
        int yDimensionEnd = r.nextInt();
        int rStart = r.nextInt(256);
        int gStart = r.nextInt(256);
        int bStart = r.nextInt(256);
        int rEnd = r.nextInt(256);
        int gEnd = r.nextInt(256);
        int bEnd = r.nextInt(256);

        model.addMotion("R", start, end,
            1, 1, xDimensionStart, yDimensionStart,
            rStart, gStart, bStart,
            1, 1, xDimensionEnd, yDimensionEnd,
            rEnd, gEnd, bEnd);

        // adding the dimensions change string
        expected.append("\n").append("Shape R scales from (").append(xDimensionStart).append(",")
            .append(yDimensionStart).append(") to (").append(xDimensionEnd).append(",")
            .append(yDimensionEnd).append(") from t=").append(start).append(" to t=").append(end);

        // adding the color change string
        expected.append("\n").append("Shape R changes color from (")
            .append(rStart).append(",").append(gStart).append(",").append(bStart)
            .append(") to (")
            .append(rEnd).append(",").append(gEnd).append(",").append(bEnd)
            .append(") from t=").append(start).append(" to t=").append(end);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 7, 48));
      assertEquals(expected.toString(), actual);

    }
  }

  /**
   * Checks that the model correctly identifies the motion added and describes it in the animation
   * when both the position and the dimensions of the shape change.
   */
  @Test
  public void testMoveAndScale() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int start = r.nextInt(10) + 1;
      int end = start + r.nextInt(10);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          2, 2, 2);

      StringBuilder expected = new StringBuilder();

      // adding initial motion string
      expected.append("Shape R changes color from (0,0,0) to (2,2,2) from t=").
          append(start).append(" to t=").append(end);

      for (int j = 0; j < 20; j++) {
        start = end;
        end = start + r.nextInt(10);
        int xPositionStart = r.nextInt();
        int yPositionStart = r.nextInt();
        int xPositionEnd = r.nextInt();
        int yPositionEnd = r.nextInt();
        int xDimensionStart = r.nextInt();
        int yDimensionStart = r.nextInt();
        int xDimensionEnd = r.nextInt();
        int yDimensionEnd = r.nextInt();

        model.addMotion("R", start, end,
            xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
            0, 0, 0,
            xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
            0, 0, 0);

        // adding the position change string
        expected.append("\n").append("Shape R moves from (").append(xPositionStart).append(",")
            .append(yPositionStart).append(") to (").append(xPositionEnd).append(",")
            .append(yPositionEnd).append(") from t=").append(start).append(" to t=").append(end);

        // adding the dimensions change string
        expected.append("\n").append("Shape R scales from (").append(xDimensionStart).append(",")
            .append(yDimensionStart).append(") to (").append(xDimensionEnd).append(",")
            .append(yDimensionEnd).append(") from t=").append(start).append(" to t=").append(end);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 7, 48));
      assertEquals(expected.toString(), actual);

    }
  }

  /**
   * Checks that the model correctly identifies the motion added and describes it in the animation
   * when all the position, the dimensions and the color of the shape change.
   */
  @Test
  public void testAllMotionsAtOnce() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int start = r.nextInt(10) + 1;
      int end = start + r.nextInt(10);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          2, 2, 2);

      StringBuilder expected = new StringBuilder();

      // adding initial motion string
      expected.append("Shape R changes color from (0,0,0) to (2,2,2) from t=").
          append(start).append(" to t=").append(end);

      for (int j = 0; j < 20; j++) {
        start = end;
        end = start + r.nextInt(10);
        int xPositionStart = r.nextInt();
        int yPositionStart = r.nextInt();
        int xPositionEnd = r.nextInt();
        int yPositionEnd = r.nextInt();
        int xDimensionStart = r.nextInt();
        int yDimensionStart = r.nextInt();
        int xDimensionEnd = r.nextInt();
        int yDimensionEnd = r.nextInt();
        int rStart = r.nextInt(256);
        int gStart = r.nextInt(256);
        int bStart = r.nextInt(256);
        int rEnd = r.nextInt(256);
        int gEnd = r.nextInt(256);
        int bEnd = r.nextInt(256);

        model.addMotion("R", start, end,
            xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
            rStart, gStart, bStart,
            xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
            rEnd, gEnd, bEnd);

        // adding the position change string
        expected.append("\n").append("Shape R moves from (").append(xPositionStart).append(",")
            .append(yPositionStart).append(") to (").append(xPositionEnd).append(",")
            .append(yPositionEnd).append(") from t=").append(start).append(" to t=").append(end);

        // adding the dimensions change string
        expected.append("\n").append("Shape R scales from (").append(xDimensionStart).append(",")
            .append(yDimensionStart).append(") to (").append(xDimensionEnd).append(",")
            .append(yDimensionEnd).append(") from t=").append(start).append(" to t=").append(end);

        // adding the color change string
        expected.append("\n").append("Shape R changes color from (")
            .append(rStart).append(",").append(gStart).append(",").append(bStart)
            .append(") to (")
            .append(rEnd).append(",").append(gEnd).append(",").append(bEnd)
            .append(") from t=").append(start).append(" to t=").append(end);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 7, 68));
      assertEquals(expected.toString(), actual);

    }
  }


  /**
   * Checks that the model correctly identifies and prints the motions added in correct order when
   * motions are added in reverse order.
   */
  @Test
  public void testAddMotionsReverseOrder() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      int end = 1000;
      int start = end - r.nextInt(50);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          2, 2, 2);

      StringBuilder expected = new StringBuilder();

      // adding initial motion string
      expected.append("Shape R changes color from (0,0,0) to (2,2,2) from t=").
          append(start).append(" to t=").append(end);

      for (int j = 0; j < 19; j++) {
        end = start;
        start = end - 1 - r.nextInt(50);
        int xPositionStart = r.nextInt();
        int yPositionStart = r.nextInt();
        int xPositionEnd = r.nextInt();
        int yPositionEnd = r.nextInt();
        int xDimensionStart = r.nextInt();
        int yDimensionStart = r.nextInt();
        int xDimensionEnd = r.nextInt();
        int yDimensionEnd = r.nextInt();
        int rStart = r.nextInt(256);
        int gStart = r.nextInt(256);
        int bStart = r.nextInt(256);
        int rEnd = r.nextInt(256);
        int gEnd = r.nextInt(256);
        int bEnd = r.nextInt(256);

        model.addMotion("R", start, end,
            xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
            rStart, gStart, bStart,
            xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
            rEnd, gEnd, bEnd);

        StringBuilder current = new StringBuilder();

        // adding the position change string
        current.append("Shape R moves from (").append(xPositionStart).append(",")
            .append(yPositionStart).append(") to (").append(xPositionEnd).append(",")
            .append(yPositionEnd).append(") from t=").append(start).append(" to t=").append(end);

        // adding the dimensions change string
        current.append("\n").append("Shape R scales from (").append(xDimensionStart).append(",")
            .append(yDimensionStart).append(") to (").append(xDimensionEnd).append(",")
            .append(yDimensionEnd).append(") from t=").append(start).append(" to t=").append(end);

        // adding the color change string
        current.append("\n").append("Shape R changes color from (")
            .append(rStart).append(",").append(gStart).append(",").append(bStart)
            .append(") to (")
            .append(rEnd).append(",").append(gEnd).append(",").append(bEnd)
            .append(") from t=").append(start).append(" to t=").append(end);

        expected = current.append("\n").append(expected);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 7, 65));
      assertEquals(expected.toString(), actual);

    }
  }

  /**
   * Checks that the model correctly identifies and prints the motions added in correct order when
   * motions are added in reverse order **to multiple shapes**.
   */
  @Test
  public void testAddMotionsManyShapes() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      model.addShape("E", ELLIPSE);
      model.addShape("S", Shapes.RECTANGLE);
      Random r = new Random();

      int end = 1000;
      int start = end - r.nextInt(50);

      // adding initial motion
      model.addMotion("R", start, end,
          1, 1, 1, 1,
          0, 0, 0,
          1, 1, 1, 1,
          2, 2, 2);

      StringBuilder expected = new StringBuilder();

      // adding initial motion string
      expected.append("Shape R changes color from (0,0,0) to (2,2,2) from t=").
          append(start).append(" to t=").append(end);

      for (int j = 0; j < 19; j++) {
        end = start - 2;
        start = end - 1 - r.nextInt(50);
        int xPositionStart = r.nextInt();
        int yPositionStart = r.nextInt();
        int xPositionEnd = r.nextInt();
        int yPositionEnd = r.nextInt();
        int xDimensionStart = r.nextInt();
        int yDimensionStart = r.nextInt();
        int xDimensionEnd = r.nextInt();
        int yDimensionEnd = r.nextInt();
        int rStart = r.nextInt(256);
        int gStart = r.nextInt(256);
        int bStart = r.nextInt(256);
        int rEnd = r.nextInt(256);
        int gEnd = r.nextInt(256);
        int bEnd = r.nextInt(256);

        model.addMotion("R", start - 2, end - 2,
            xPositionStart, yPositionStart, 1, 1,
            0, 0, 0,
            xPositionEnd, yPositionEnd, 1, 1,
            0, 0, 0);

        model.addMotion("E", start - 1, end - 1,
            1, 1, xDimensionStart, yDimensionStart,
            0, 0, 0,
            1, 1, xDimensionEnd, yDimensionEnd,
            0, 0, 0);

        model.addMotion("S", start, end,
            1, 1, 1, 1,
            rStart, gStart, bStart,
            1, 1, 1, 1,
            rEnd, gEnd, bEnd);

        StringBuilder current = new StringBuilder();

        // adding the position change string
        current.append("Shape R moves from (").append(xPositionStart).append(",")
            .append(yPositionStart).append(") to (").append(xPositionEnd).append(",")
            .append(yPositionEnd).append(") from t=").append(start - 2).append(" to t=")
            .append(end - 2);

        // adding the dimensions change string
        current.append("\n").append("Shape E scales from (").append(xDimensionStart).append(",")
            .append(yDimensionStart).append(") to (").append(xDimensionEnd).append(",")
            .append(yDimensionEnd).append(") from t=").append(start - 1).append(" to t=")
            .append(end - 1);

        // adding the color change string
        current.append("\n").append("Shape S changes color from (")
            .append(rStart).append(",").append(gStart).append(",").append(bStart)
            .append(") to (")
            .append(rEnd).append(",").append(gEnd).append(",").append(bEnd)
            .append(") from t=").append(start).append(" to t=").append(end);

        expected = current.append("\n").append(expected);
      }

      String actual = String.join("\n",
          Arrays.copyOfRange(model.toString().split("\n"), 19, 77));
      assertEquals(expected.toString(), actual);

    }
  }

  /*
  Helper method that interpolates the between two values of the given property between the start
  time and the end time.
   */
  private int interpolate(int timestamp, int start, int end, int init,
      int fin) {

    return (end - start) == 0 ? fin :
        (init * ((end - timestamp) / (end - start)) +
            fin * ((timestamp - start) / (end - start)));
  }

  /*
  Helper method that checks if two shapes are equal.
   */
  private boolean shapesEqual(Shape a, Shape b) {
    return a.getKind().equals(b.getKind()) && a.getPosition().equals(b.getPosition())
        && a.getDimensions().equals(b.getDimensions()) && a.getColor().equals(b.getColor());
  }

  /**
   * Checks that the .getStateAt method correctly computes and returns the state of the animation at
   * the given timestamp when there is one shape in the animation.
   */
  @Test

  public void testGetStateAtOneShape() {
    for (int i = 1; i < 100; i++) {
      AnimationModel model = new AnimationModelImpl();
      model.addShape("R", Shapes.RECTANGLE);
      Random r = new Random();

      Map<Integer, List<Shape>> timeframe = new HashMap<>();

      int start;
      int end = 0;

      assertEquals("Shapes:", model.toString().split("\n")[0]);

      for (int j = 0; j < 20; j++) {
        start = end + 1;
        end = start + 1 + r.nextInt(10);
        int xPositionStart = r.nextInt();
        int yPositionStart = r.nextInt();
        int xPositionEnd = r.nextInt();
        int yPositionEnd = r.nextInt();
        int xDimensionStart = r.nextInt();
        int yDimensionStart = r.nextInt();
        int xDimensionEnd = r.nextInt();
        int yDimensionEnd = r.nextInt();
        int rStart = r.nextInt(256);
        int gStart = r.nextInt(256);
        int bStart = r.nextInt(256);
        int rEnd = r.nextInt(256);
        int gEnd = r.nextInt(256);
        int bEnd = r.nextInt(256);

        model.addMotion("R", start, end,
            xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
            rStart, gStart, bStart,
            xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
            rEnd, gEnd, bEnd);

        for (int k = start; k < end + 1; k++) {

          Shape interpolated = ShapeFactory.make(RECTANGLE,
              new Position(
                  interpolate(k, start, end, xPositionStart, xPositionEnd),
                  interpolate(k, start, end, yPositionStart, yPositionEnd)),
              new Dimensions(
                  interpolate(k, start, end, xDimensionStart, xDimensionEnd),
                  interpolate(k, start, end, yDimensionStart, yDimensionEnd)),
              new Color(
                  interpolate(k, start, end, rStart, rEnd),
                  interpolate(k, start, end, gStart, gEnd),
                  interpolate(k, start, end, bStart, bEnd)));
          if (!timeframe.containsKey(k)) {
            timeframe.put(k, new LinkedList<>());
          }
          timeframe.get(k).add(interpolated);

        }
      }

      timeframe.forEach((time, frame) -> {
        List<Shape> state = model.getStateAt(time);
        assertEquals(state.size(), frame.size());
        frame.forEach(shape ->
            assertTrue(state.stream().anyMatch(s -> shapesEqual(shape, s)))
        );
      });

    }
  }

  /**
   * Checks that the .getStateAt method correctly computes and returns the state of the animation at
   * the given timestamp when there are many shapes in the animation.
   */
  @Test

  public void testGetStateAtManyShapes() {

    Random r = new Random();
    AnimationModel model = new AnimationModelImpl();
    Map<Integer, List<Shape>> timeframe = new HashMap<>();

    String[] names = {"W", "H", "Y", "N", "O", "T"};
    Shapes[] kinds = {RECTANGLE, ELLIPSE};

    for (String name : names) {

      Shapes kind = kinds[r.nextInt(1)];
      model.addShape(name, kind);

      assertEquals("Shapes:", model.toString().split("\n")[0]);

      int start;
      int end = 0;

      for (int j = 0; j < 20; j++) {
        start = end + 1;
        end = start + 1 + r.nextInt(10);
        int xPositionStart = r.nextInt();
        int yPositionStart = r.nextInt();
        int xPositionEnd = r.nextInt();
        int yPositionEnd = r.nextInt();
        int xDimensionStart = r.nextInt();
        int yDimensionStart = r.nextInt();
        int xDimensionEnd = r.nextInt();
        int yDimensionEnd = r.nextInt();
        int rStart = r.nextInt(256);
        int gStart = r.nextInt(256);
        int bStart = r.nextInt(256);
        int rEnd = r.nextInt(256);
        int gEnd = r.nextInt(256);
        int bEnd = r.nextInt(256);

        model.addMotion(name, start, end,
            xPositionStart, yPositionStart, xDimensionStart, yDimensionStart,
            rStart, gStart, bStart,
            xPositionEnd, yPositionEnd, xDimensionEnd, yDimensionEnd,
            rEnd, gEnd, bEnd);

        for (int k = start; k < end + 1; k++) {

          Shape interpolated = ShapeFactory.make(kind,
              new Position(
                  interpolate(k, start, end, xPositionStart, xPositionEnd),
                  interpolate(k, start, end, yPositionStart, yPositionEnd)),
              new Dimensions(
                  interpolate(k, start, end, xDimensionStart, xDimensionEnd),
                  interpolate(k, start, end, yDimensionStart, yDimensionEnd)),
              new Color(
                  interpolate(k, start, end, rStart, rEnd),
                  interpolate(k, start, end, gStart, gEnd),
                  interpolate(k, start, end, bStart, bEnd)));

          if (!timeframe.containsKey(k)) {
            timeframe.put(k, new LinkedList<>());
          }
          timeframe.get(k).add(interpolated);
        }
      }
    }

    timeframe.forEach((time, frame) -> {
      List<Shape> state = model.getStateAt(time);
      assertEquals(state.size(), frame.size());
      frame.forEach(shape ->
          assertTrue(state.stream().anyMatch(s -> shapesEqual(shape, s)))
      );
    });

  }

}
