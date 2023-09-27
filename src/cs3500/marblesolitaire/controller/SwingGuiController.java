package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

/**
 * Represents the GuiController for MarbleSolitaire game, displaying it visually
 */
public class SwingGuiController implements ControllerFeatures {
  private MarbleSolitaireModel model;
  private MarbleSolitaireGuiView view;
  private int fromRow, fromCol, toRow, toCol;

  public SwingGuiController(MarbleSolitaireModel model, MarbleSolitaireGuiView view) {
    this.model = model;
    this.view = view;
    this.view.setFeatures(this);
    this.view.refresh();
    this.fromRow = -1;
    this.fromCol = -1;
    this.toRow = -1;
    this.toCol = -1;
  }

  /**
   * accept inputs and make the move if all coordinates provided are valid
   *
   * @param row row to move from or to
   * @param col column to move from or to
   */
  @Override
  public void input(int row, int col) {
    if (row >= 0 && col >= 0) {
      if (this.fromRow == -1) {
        this.fromRow = row;
        this.fromCol = col;
      }
      else {
        this.toRow = row;
        this.toCol = col;
        //make the move
        try {
          this.model.move(this.fromRow, this.fromCol, this.toRow, this.toCol);
          if (this.model.isGameOver()) {
            this.view.renderMessage("Game Over!");
          }
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Invalid move");
        }
        this.fromRow = this.fromCol = this.toRow = this.toCol = -1;
      }
      this.view.refresh();
    }
  }
}
