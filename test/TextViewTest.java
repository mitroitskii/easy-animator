import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import model.AnimationModelImpl;
import model.AnimationModelView;
import org.junit.Test;
import util.AnimationBuilderModel;
import util.AnimationReader;
import view.TextView;
import view.View;

/**
 * Test cases for the animation textual view. Verifying that animation properly described in text.
 */
public class TextViewTest {

  /**
   * Checks that the text view correctly outputs the toh-3.txt animation sample.
   */
  @Test
  public void testReadAndViewToh3() {

    Readable readable;
    StringBuilder log = new StringBuilder();

    try {
      readable = new FileReader("./test/samples/toh-3.txt");
      AnimationModelView model = (AnimationModelView) AnimationReader.parseFile(readable,
          new AnimationBuilderModel());
      View view = new TextView(model);
      view.run(log, 0);
    } catch (FileNotFoundException e) {
      fail("Could not read sample file");
    }

    String expected = String.join("\n"
        , "Shapes:"
        , "Name: disk1"
        , "Kind: rectangle"
        , "Min corner: (190,180), Width/Height: (20,30), Color: (0,49,90)"
        , "Appears at t=1"
        , "Disappears at t=302"
        , ""
        , "Name: disk2"
        , "Kind: rectangle"
        , "Min corner: (167,210), Width/Height: (65,30), Color: (6,247,41)"
        , "Appears at t=1"
        , "Disappears at t=302"
        , ""
        , "Name: disk3"
        , "Kind: rectangle"
        , "Min corner: (145,240), Width/Height: (110,30), Color: (11,45,175)"
        , "Appears at t=1"
        , "Disappears at t=302"
        , ""
        , "Shape disk3 stays frozen with properties (145,240,110,30,11,45,175) from t=1 to t=1"
        , "Shape disk3 stays frozen with properties (145,240,110,30,11,45,175) from t=1 to t=121"
        , "Shape disk1 stays frozen with properties (190,180,20,30,0,49,90) from t=1 to t=1"
        , "Shape disk1 stays frozen with properties (190,180,20,30,0,49,90) from t=1 to t=25"
        , "Shape disk2 stays frozen with properties (167,210,65,30,6,247,41) from t=1 to t=1"
        , "Shape disk2 stays frozen with properties (167,210,65,30,6,247,41) from t=1 to t=57"
        , "Shape disk1 moves from (190,180) to (190,50) from t=25 to t=35"
        , "Shape disk1 stays frozen with properties (190,50,20,30,0,49,90) from t=35 to t=36"
        , "Shape disk1 moves from (190,50) to (490,50) from t=36 to t=46"
        , "Shape disk1 stays frozen with properties (490,50,20,30,0,49,90) from t=46 to t=47"
        , "Shape disk1 moves from (490,50) to (490,240) from t=47 to t=57"
        , "Shape disk1 stays frozen with properties (490,240,20,30,0,49,90) from t=57 to t=89"
        , "Shape disk2 moves from (167,210) to (167,50) from t=57 to t=67"
        , "Shape disk2 stays frozen with properties (167,50,65,30,6,247,41) from t=67 to t=68"
        , "Shape disk2 moves from (167,50) to (317,50) from t=68 to t=78"
        , "Shape disk2 stays frozen with properties (317,50,65,30,6,247,41) from t=78 to t=79"
        , "Shape disk2 moves from (317,50) to (317,240) from t=79 to t=89"
        , "Shape disk1 moves from (490,240) to (490,50) from t=89 to t=99"
        , "Shape disk2 stays frozen with properties (317,240,65,30,6,247,41) from t=89 to t=185"
        , "Shape disk1 stays frozen with properties (490,50,20,30,0,49,90) from t=99 to t=100"
        , "Shape disk1 moves from (490,50) to (340,50) from t=100 to t=110"
        , "Shape disk1 stays frozen with properties (340,50,20,30,0,49,90) from t=110 to t=111"
        , "Shape disk1 moves from (340,50) to (340,210) from t=111 to t=121"
        , "Shape disk3 moves from (145,240) to (145,50) from t=121 to t=131"
        , "Shape disk1 stays frozen with properties (340,210,20,30,0,49,90) from t=121 to t=153"
        , "Shape disk3 stays frozen with properties (145,50,110,30,11,45,175) from t=131 to t=132"
        , "Shape disk3 moves from (145,50) to (445,50) from t=132 to t=142"
        , "Shape disk3 stays frozen with properties (445,50,110,30,11,45,175) from t=142 to t=143"
        , "Shape disk3 moves from (445,50) to (445,240) from t=143 to t=153"
        , "Shape disk3 changes color from (11,45,175) to (0,255,0) from t=153 to t=161"
        , "Shape disk1 moves from (340,210) to (340,50) from t=153 to t=163"
        , "Shape disk3 stays frozen with properties (445,240,110,30,0,255,0) from t=161 to t=302"
        , "Shape disk1 stays frozen with properties (340,50,20,30,0,49,90) from t=163 to t=164"
        , "Shape disk1 moves from (340,50) to (190,50) from t=164 to t=174"
        , "Shape disk1 stays frozen with properties (190,50,20,30,0,49,90) from t=174 to t=175"
        , "Shape disk1 moves from (190,50) to (190,240) from t=175 to t=185"
        , "Shape disk1 stays frozen with properties (190,240,20,30,0,49,90) from t=185 to t=217"
        , "Shape disk2 moves from (317,240) to (317,50) from t=185 to t=195"
        , "Shape disk2 stays frozen with properties (317,50,65,30,6,247,41) from t=195 to t=196"
        , "Shape disk2 moves from (317,50) to (467,50) from t=196 to t=206"
        , "Shape disk2 stays frozen with properties (467,50,65,30,6,247,41) from t=206 to t=207"
        , "Shape disk2 moves from (467,50) to (467,210) from t=207 to t=217"
        , "Shape disk1 moves from (190,240) to (190,50) from t=217 to t=227"
        , "Shape disk2 changes color from (6,247,41) to (0,255,0) from t=217 to t=225"
        , "Shape disk2 stays frozen with properties (467,210,65,30,0,255,0) from t=225 to t=302"
        , "Shape disk1 stays frozen with properties (190,50,20,30,0,49,90) from t=227 to t=228"
        , "Shape disk1 moves from (190,50) to (490,50) from t=228 to t=238"
        , "Shape disk1 stays frozen with properties (490,50,20,30,0,49,90) from t=238 to t=239"
        , "Shape disk1 moves from (490,50) to (490,180) from t=239 to t=249"
        , "Shape disk1 changes color from (0,49,90) to (0,255,0) from t=249 to t=257"
        , "Shape disk1 stays frozen with properties (490,180,20,30,0,255,0) from t=257 to t=302"
    );

    // split the output into an array of lines
    String[] lines = log.toString().split("\n");
    String actual = String.join("\n", lines);

    assertEquals(expected, actual);

  }

  /**
   * Checks that the text view correctly outputs the smalldemo.txt animation sample.
   */
  @Test
  public void testReadAndViewSmalldemo() {

    Readable readable;
    StringBuilder log = new StringBuilder();

    try {
      readable = new FileReader("./test/samples/smalldemo.txt");
      AnimationModelView model = (AnimationModelView) AnimationReader.parseFile(readable,
          new AnimationBuilderModel());
      View view = new TextView(model);
      view.run(log, 0);
    } catch (FileNotFoundException e) {
      fail("Could not read sample file");
    }

    String expected = String.join("\n"
        , "Shapes:"
        , "Name: C"
        , "Kind: ellipse"
        , "Center: (440,70), X/Y Radius: (120,60), Color: (0,0,255)"
        , "Appears at t=6"
        , "Disappears at t=100"
        , ""
        , "Name: R"
        , "Kind: rectangle"
        , "Min corner: (200,200), Width/Height: (50,100), Color: (255,0,0)"
        , "Appears at t=1"
        , "Disappears at t=100"
        , ""
        , "Shape R stays frozen with properties (200,200,50,100,255,0,0) from t=1 to t=10"
        , "Shape C stays frozen with properties (440,70,120,60,0,0,255) from t=6 to t=20"
        , "Shape R moves from (200,200) to (300,300) from t=10 to t=50"
        , "Shape C moves from (440,70) to (440,250) from t=20 to t=50"
        , "Shape R stays frozen with properties (300,300,50,100,255,0,0) from t=50 to t=51"
        , "Shape C moves from (440,250) to (440,370) from t=50 to t=70"
        , "Shape C changes color from (0,0,255) to (0,170,85) from t=50 to t=70"
        , "Shape R scales from (50,100) to (25,100) from t=51 to t=70"
        , "Shape R moves from (300,300) to (200,200) from t=70 to t=100"
        , "Shape C changes color from (0,170,85) to (0,255,0) from t=70 to t=80"
        , "Shape C stays frozen with properties (440,370,120,60,0,255,0) from t=80 to t=100"
    );

    // split the output into an array of lines
    String[] lines = log.toString().split("\n");
    String actual = String.join("\n", lines);

    assertEquals(expected, actual);

  }

  /**
   * Tests that the TextView throws an exception when the data sink is broken.
   */
  @Test
      (expected = IllegalStateException.class)
  public void testFailingAppendable() {
    AnimationModelView model = new AnimationModelImpl();
    Appendable log = new FailingAppendable();
    View view = new TextView(model);
    view.run(log, 0);
  }


}
