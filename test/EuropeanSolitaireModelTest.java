import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw02.SolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test EuropeanSolitaireModel class
 */
public class EuropeanSolitaireModelTest {
  private SolitaireModel s1;
  private SolitaireModel s2;
  private SolitaireModel s3;
  private SolitaireModel s4;

  private MarbleSolitaireTextView v1;
  private MarbleSolitaireTextView v2;
  private MarbleSolitaireTextView v3;
  private MarbleSolitaireTextView v4;

  @Before
  public void setup() {
    s1 = new EuropeanSolitaireModel();
    s2 = new EuropeanSolitaireModel(2, 2);
    s3 = new EuropeanSolitaireModel(5);
    s4 = new EuropeanSolitaireModel(3, 0, 4);

    v1 = new MarbleSolitaireTextView(s1);
    v2 = new MarbleSolitaireTextView(s2);
    v3 = new MarbleSolitaireTextView(s3);
    v4 = new MarbleSolitaireTextView(s4);
  }

  @Test
  public void testInstantiation1stConstructor() {
    assertEquals(s1.getArmThickness(), 3);
    assertEquals(s1.getSRow(), 3);
    assertEquals(s1.getSCol(), 3);
  }

  @Test
  public void testInstantiation2ndConstructor() {
    assertEquals(s2.getArmThickness(), 3);
    assertEquals(s2.getSRow(), 2);
    assertEquals(s2.getSCol(), 2);

    try {
      EuropeanSolitaireModel s0 = new EuropeanSolitaireModel(0, 0);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Invalid empty cell position (0,0)");
    }

    try {
      EuropeanSolitaireModel s0 = new EuropeanSolitaireModel(7, 7);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Invalid empty cell position (7,7)");
    }
  }

  @Test
  public void testInstantiation3rdConstructor() {

    assertEquals(s3.getArmThickness(), 5);
    assertEquals(s3.getSRow(), 6);
    assertEquals(s3.getSCol(), 6);

    try {
      EuropeanSolitaireModel s0 = new EuropeanSolitaireModel(4);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Thickness is not a positive odd number");
    }

    try {
      EuropeanSolitaireModel s0 = new EuropeanSolitaireModel(-1);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Thickness is not a positive odd number");
    }
  }

  @Test
  public void testInstantiation4thConstructor() {
    assertEquals(s4.getArmThickness(), 3);
    assertEquals(s4.getSRow(), 0);
    assertEquals(s4.getSCol(), 4);

    try {
      EuropeanSolitaireModel s0 = new EuropeanSolitaireModel(3, 0, 0);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Invalid empty cell position (0,0)");
    }

    try {
      EuropeanSolitaireModel s0 = new EuropeanSolitaireModel(3, 7, 7);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Invalid empty cell position (7,7)");
    }

    try {
      EuropeanSolitaireModel s0 = new EuropeanSolitaireModel(4, 7, 7);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Thickness is not a positive odd number");
    }

    try {
      EuropeanSolitaireModel s0 = new EuropeanSolitaireModel(-1, 7, 7 );
    }
    catch (IllegalArgumentException e) {
      System.out.println("Thickness is not a positive odd number");
    }
  }

  @Test
  public void testMove() {
    // test for exceptions
    try {
      s1.move(0, 0, 0, 2);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Invalid from position (0,0)");
    }

    try {
      s1.move(2, 1, 0, 1);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Invalid to position (0,0)");
    }

    try {
      s1.move(3, 3, 1, 3);
    }
    catch (IllegalArgumentException e) {
      System.out.println("No marble at specified from position");
    }

    try {
      s1.move(4, 3, 2, 3);
    }
    catch (IllegalArgumentException e) {
      System.out.println("To position is not empty");
    }

    try {
      s1.move(1, 1, 3, 3);
    }
    catch (IllegalArgumentException e) {
      System.out.println("To and from positions are not exactly two positions away");
    }

    assertEquals(s1.getBoard().get(1).get(3), SlotState.Marble);
    assertEquals(s1.getBoard().get(2).get(3), SlotState.Marble);
    assertEquals(s1.getBoard().get(3).get(3), SlotState.Empty);
    s1.move(1, 3, 3, 3);
    assertEquals(s1.getBoard().get(1).get(3), SlotState.Empty);
    assertEquals(s1.getBoard().get(2).get(3), SlotState.Empty);
    assertEquals(s1.getBoard().get(3).get(3), SlotState.Marble);

    try {
      s1.move(0, 3, 2, 3);
    }
    catch (IllegalArgumentException e) {
      System.out.println("There is no marble in the slot between the to and from positions");
    }

    assertEquals(s2.getBoard().get(4).get(2), SlotState.Marble);
    assertEquals(s2.getBoard().get(3).get(2), SlotState.Marble);
    assertEquals(s2.getBoard().get(2).get(2), SlotState.Empty);
    s2.move(4, 2, 2, 2);
    assertEquals(s2.getBoard().get(4).get(2), SlotState.Empty);
    assertEquals(s2.getBoard().get(3).get(2), SlotState.Empty);
    assertEquals(s2.getBoard().get(2).get(2), SlotState.Marble);

    assertEquals(s3.getBoard().get(4).get(6), SlotState.Marble);
    assertEquals(s3.getBoard().get(5).get(6), SlotState.Marble);
    assertEquals(s3.getBoard().get(6).get(6), SlotState.Empty);
    s3.move(4, 6, 6, 6);
    assertEquals(s3.getBoard().get(4).get(6), SlotState.Empty);
    assertEquals(s3.getBoard().get(5).get(6), SlotState.Empty);
    assertEquals(s3.getBoard().get(6).get(6), SlotState.Marble);

    assertEquals(s4.getBoard().get(2).get(4), SlotState.Marble);
    assertEquals(s4.getBoard().get(1).get(4), SlotState.Marble);
    assertEquals(s4.getBoard().get(0).get(4), SlotState.Empty);
    s4.move(2, 4, 0, 4);
    assertEquals(s4.getBoard().get(2).get(4), SlotState.Empty);
    assertEquals(s4.getBoard().get(1).get(4), SlotState.Empty);
    assertEquals(s4.getBoard().get(0).get(4), SlotState.Marble);
  }

  @Test
  public void testGetScore() {
    assertEquals(s1.getScore(), 36);
    s1.move(1, 3, 3, 3);
    assertEquals(s1.getScore(), 35);
    s1.move(2, 1, 2, 3);
    assertEquals(s1.getScore(), 34);

    assertEquals(s3.getScore(), 128);
    s3.move(4, 6, 6, 6);
    assertEquals(s3.getScore(), 127);
    s3.move(5, 4, 5, 6);
    assertEquals(s3.getScore(), 126);
  }

  @Test
  public void testIsGameOver() {
    assertFalse(s1.isGameOver());

    ArrayList<ArrayList<SlotState>> board = s1.getBoard();
    for (int i = 0; i < s1.getBoardSize(); i++) {
      for (int j = 0; j < s1.getBoardSize(); j++) {
        if (board.get(i).get(j).equals(SlotState.Marble)) {
          board.get(i).set(j, SlotState.Empty);
        }
      }
    }

    board.get(4).set(4, SlotState.Marble);

    assertTrue(s1.isGameOver());

    assertEquals(s3.isGameOver(), false);

    board = s3.getBoard();
    for (int i = 0; i < s3.getBoardSize(); i++) {
      for (int j = 0; j < s3.getBoardSize(); j++) {
        if (board.get(i).get(j).equals(SlotState.Marble)) {
          board.get(i).set(j, SlotState.Empty);
        }
      }
    }

    board.get(6).set(6, SlotState.Marble);

    assertTrue(s3.isGameOver());
  }

  @Test
  public void testTextView() throws IOException {
    // test IllegalArgumentException
    try {
      new MarbleSolitaireTextView(null);
    } catch (IllegalArgumentException e) {
      System.out.println("Provided model is null");
    }

    System.out.println(v1 + "\n");

    assertEquals(v1.toString(), ""
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O");

    System.out.println(v2 + "\n");

    assertEquals(v2.toString(), ""
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O _ O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O");

    System.out.println(v3 + "\n");

    assertEquals(v3.toString(), ""
        + "        O O O O O\n"
        + "      O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O _ O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "O O O O O O O O O O O O O\n"
        + "  O O O O O O O O O O O\n"
        + "    O O O O O O O O O\n"
        + "      O O O O O O O\n"
        + "        O O O O O");

    System.out.println(v4 + "\n");

    assertEquals(v4.toString(), ""
        + "    O O _\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O");
  }
}

