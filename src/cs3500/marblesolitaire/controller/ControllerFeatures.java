package cs3500.marblesolitaire.controller;

/**
 * This interface represents the features for the controller
 */
public interface ControllerFeatures {
  /**
   * accept inputs and make the move if all coordinates provided are valid
   *
   * @param row row to move from or to
   * @param col column to move from or to
   */
  void input(int row, int col);
}
