package cs3500.marblesolitaire.controller;

/**
 * This interface represents the controller for a MarbleSolitaireModel game
 */
public interface MarbleSolitaireController {

  /**
   * Play a new game of Marble Solitaire
   *
   * @throws IllegalArgumentException if the controller is unable to successfully read input or
   * transmit output
   */
  void playGame() throws IllegalStateException;
}
