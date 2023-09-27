import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.SolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the Controller for each of the three (English, European, Triangle) versions
 * of Marble Solitaire game
 */
public class MarbleSolitaireControllerTest {
  private SolitaireModel englishS;
  private SolitaireModel europeanS;
  private SolitaireModel triangleS;

  private MarbleSolitaireView englishView;
  private MarbleSolitaireView europeanView;
  private MarbleSolitaireView triangleView;


  private MarbleSolitaireController c;
  private StringBuilder append;

  /**
   * Move(), GetScore(), IsGameOver(), TextView() methods have been tested in EnglishSolitaireModelTest.
   * Please check back if it’s the requirements for assignment 2 included.
   */
  @Before
  public void setup() {
    append = new StringBuilder();

    englishS = new EnglishSolitaireModel();
    europeanS = new EuropeanSolitaireModel();
    triangleS = new TriangleSolitaireModel();

    englishView = new MarbleSolitaireTextView(englishS);
    europeanView = new MarbleSolitaireTextView(europeanS);
    triangleView = new TriangleSolitaireTextView(triangleS);
  }

  // This test plays a game out so that it wins.  It essentially tests the playGame() method
  // (including gameEnd() and getCoords() helper methods) and renderBoard()
  @Test
  public void testGameWin() {
    StringReader s = new StringReader("4 6 4 4 2 5 4 5"
        + " 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5"
        + " 3 2 3 4 1 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3"
        + " 6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 1 5 3"
        + " 5 6 5 4 7 5 5 5 7 3 7 5 4 5 6 5 7 5 5 5"
        + " 4 3 2 3 2 3 2 5 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 3"
        + " 4 4 2 4 4 2 4 4 5 4 3 4 2 4 4 4");
    c = new MarbleSolitaireControllerImpl(englishS, englishView, s);

    c.playGame();
  }

  // This tests instances in which the reader is passed in a "q" or "Q" indicating the game should
  // be quit; this also ensures renderBoard() and renderMessage() work properly
  @Test
  public void testGameQuit() {
    //English game
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("4 6 4 4 q"));

    c.playGame();
    String expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "\n";

    assertEquals(append.toString(), expected);

    append = new StringBuilder();
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("4 6 4 4 Q"));

    c.playGame();

    expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: No marble at specified from position\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "\n";

    assertEquals(append.toString(), expected);

    //European game
    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("4 6 4 4 q"));

    c.playGame();
    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 35\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 35\n"
        + "\n";

    assertEquals(append.toString(), expected);

    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("4 6 4 4 Q"));

    c.playGame();

    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 35\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: No marble at specified from position\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 35\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 35\n"
        + "\n";

    assertEquals(append.toString(), expected);

    //Triangle game
    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("4 6 4 4 q"));

    c.playGame();
    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid from position (3,5)\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);

    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("4 6 4 4 Q"));

    c.playGame();

    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid from position (3,5)\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);
  }

  // tests for invalid inputs passed in by the reader.  First whether input was character that
  // was not a number, then whether it was a number but not a positive one
  @Test
  public void testGameInvalidInput() {
    //English game
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("4 6 4 4 a q"));

    c.playGame();
    String expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "\n"
        + "Invalid move. Play again. Input must be positive number or q/Q\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "\n";

    assertEquals(append.toString(), expected);

    this.setup();

    append = new StringBuilder();
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("4 6 0 4 4 2 5 q"));
    c.playGame();

    expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Invalid move. Play again. Input number must be positive\n"
        + "\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 31\n"
        + "\n";

    assertEquals(append.toString(), expected);

    //European game
    this.setup();

    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("4 6 4 4 a q"));

    c.playGame();
    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 35\n"
        + "\n"
        + "Invalid move. Play again. Input must be positive number or q/Q\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 35\n"
        + "\n";

    assertEquals(append.toString(), expected);

    this.setup();

    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("4 6 0 4 4 2 5 q"));
    c.playGame();

    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Invalid move. Play again. Input number must be positive\n"
        + "\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 35\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O O _ _ O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 35\n"
        + "\n";

    assertEquals(append.toString(), expected);

    //Triangle game
    this.setup();

    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("4 6 4 4 a q"));

    c.playGame();
    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid from position (3,5)\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. Input must be positive number or q/Q\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);

    this.setup();

    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("4 6 0 4 4 2 5 q"));
    c.playGame();

    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. Input number must be positive\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid from position (3,5)\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);
  }

  // tests that exceptions are transmitted and displayed correctly
  @Test
  public void testMoveExceptions() {
    // English game

    // test for exceptions
    // "Invalid from position (0,0)"
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("1 1 1 3 q"));

    c.playGame();
    String expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid from position (0,0)\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "Invalid to position (0,0)"
    append = new StringBuilder();
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("3 2 1 2 q"));

    c.playGame();
    expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid to position (0,1)\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "No marble at specified from position"
    append = new StringBuilder();
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("4 4 2 4 q"));

    c.playGame();
    expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: No marble at specified from position\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "To position is not empty"
    append = new StringBuilder();
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("5 4 3 4 q"));

    c.playGame();
    expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: To position is not empty\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "To and from positions are not exactly two positions away"
    append = new StringBuilder();
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("2 2 4 4 q"));

    c.playGame();
    expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid from position (1,1)\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n";

    assertEquals(append.toString(), expected);

    //"There is no marble in the slot between the to and from positions"
    append = new StringBuilder();
    englishView = new MarbleSolitaireTextView(englishS, append);
    c = new MarbleSolitaireControllerImpl(englishS, englishView, new StringReader("3 2 1 2 q"));

    c.playGame();
    expected = "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid to position (0,1)\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "    O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O O O\n"
        + "    O O O\n"
        + "Score: 32\n"
        + "\n";

    assertEquals(append.toString(), expected);

    //European game

    // test for exceptions
    // "Invalid from position (0,0)"
    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("1 1 1 3 q"));

    c.playGame();
    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid from position (0,0)\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "Invalid to position (0,0)"
    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("3 2 1 2 q"));

    c.playGame();
    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid to position (0,1)\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "No marble at specified from position"
    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("4 4 2 4 q"));

    c.playGame();
    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: No marble at specified from position\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "To position is not empty"
    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("5 4 3 4 q"));

    c.playGame();
    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: To position is not empty\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "To and from positions are not exactly two positions away"
    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("2 2 4 4 q"));

    c.playGame();
    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: To and from positions are not exactly two positions away\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n";

    assertEquals(append.toString(), expected);

    //"There is no marble in the slot between the to and from positions"
    append = new StringBuilder();
    europeanView = new MarbleSolitaireTextView(europeanS, append);
    c = new MarbleSolitaireControllerImpl(europeanS, europeanView, new StringReader("3 2 1 2 q"));

    c.playGame();
    expected = "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid to position (0,1)\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n"
        + "Game quit!\n"
        + "    O O O\n"
        + "  O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "  O O O O O\n"
        + "    O O O\n"
        + "Score: 36\n"
        + "\n";

    assertEquals(append.toString(), expected);

    //Triangle game

    // test for exceptions
    // "Invalid from position (0,0)"
    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("1 1 1 3 q"));

    c.playGame();
    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid to position (0,2)\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "Invalid to position (0,0)"
    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("3 2 1 2 q"));

    c.playGame();
    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid to position (0,1)\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "No marble at specified from position"
    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("4 4 2 4 q"));

    c.playGame();
    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid to position (1,3)\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "To position is not empty"
    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("5 4 3 4 q"));

    c.playGame();
    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid to position (2,3)\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);

    // "To and from positions are not exactly two positions away"
    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("2 2 4 4 q"));

    c.playGame();
    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: To position is not empty\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);

    //"There is no marble in the slot between the to and from positions"
    append = new StringBuilder();
    triangleView = new TriangleSolitaireTextView(triangleS, append);
    c = new MarbleSolitaireControllerImpl(triangleS, triangleView, new StringReader("3 2 1 2 q"));

    c.playGame();
    expected = "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Invalid move. Play again. java.lang.IllegalArgumentException: Invalid to position (0,1)\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n"
        + "Game quit!\n"
        + "    _\n"
        + "   O O\n"
        + "  O O O\n"
        + " O O O O\n"
        + "O O O O O\n"
        + "Score: 14\n"
        + "\n";

    assertEquals(append.toString(), expected);
  }
}