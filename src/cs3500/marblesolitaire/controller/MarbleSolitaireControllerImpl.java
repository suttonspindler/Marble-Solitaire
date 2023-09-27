package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.IOException;
import java.util.Scanner;


/**
 * This class represents a controller for a MarbleSolitaireModel game.  It can play
 * the game based on the inputs passed in by the Readable object
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable rd;
  private boolean quit;
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
      Readable rd) throws IllegalStateException {
    if (model == null) {
      throw new IllegalArgumentException("Provided model is null");
    }
    if (view == null) {
      throw new IllegalArgumentException("Provided view is null");
    }
    if (rd == null) {
      throw new IllegalArgumentException("Provided readable is null");
    }
    this.model = model;
    this.view = view;
    this.rd = rd;
    this.quit = false;
  }

  /**
   * Play a new game of Marble Solitaire
   *
   * @throws IllegalArgumentException if the controller is unable to successfully read input or
   * transmit output
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner sc = new Scanner(rd);
    while (!this.model.isGameOver()) {
      // Using the view, render the current state of the game
      try {
        this.view.renderBoard();
      }
      catch (Exception e) {
        throw new IllegalStateException("Failed to read from readable");
      }
      // Using the view, transmit "Score: N" , replacing N with the actual score
      try {
        this.view.renderMessage("\nScore: " + this.model.getScore() + "\n\n");
      }
      catch (Exception e) {
        throw new IllegalStateException("Failed to transmit from readable");
      }
      int[] coordinates = this.getCoords(sc);
      if (this.quit) {
        break;
      }

      // Parse these inputs and pass the information on to the model to make the move
      try {
        this.model.move(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
      } catch (IllegalArgumentException e) {
        try {
          this.view.renderMessage("Invalid move. Play again. " + e.toString() + "\n");
        } catch (IOException ex) {
          throw new IllegalStateException(ex);
        }
      }
    }
    // End game
    this.gameEnd();
  }

  /**
   * Get move coordinates from user
   *
   * @param sc Scanner to obtain coordinates from
   * @return Coordinates
   */
  private int[] getCoords(Scanner sc) {
    // If the game is ongoing, obtain the next user input from the Readable object
    int[] coordinates = new int[4];
    for (int i = 0; i < 4; i++) {
      String input = sc.next();
      try {
        int n = Integer.parseInt(input);
        if (n > 0) {
          coordinates[i] = n - 1;
        }
        else {
          this.view.renderMessage("Invalid move. Play again. Input number must be positive\n\n");
          i--;
        }
      } catch (Exception e) {
        if (input.equals("q") || input.equals("Q")) {
          this.quit = true;
          break;
        }
        else {
          try {
            this.view.renderMessage("Invalid move. Play again. Input must be positive number or"
                + " q/Q\n\n");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
          i--;
        }
      }
    }
    return coordinates;
  }

  /**
   * If the game is not over, repeat the above steps. If the game is over, the method should
   * transmit the following data
   */
  private void gameEnd() {
    if (!this.quit) {
      try {
        this.view.renderMessage("Game over!\n");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }
    else {
      try {
        this.view.renderMessage("Game quit!\n");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore() + "\n\n");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
