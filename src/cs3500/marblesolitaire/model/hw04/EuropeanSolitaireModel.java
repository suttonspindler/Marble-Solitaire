package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.SolitaireModel;

/**
 * This class represents the european marble solitaire model, where it is in the shape of an
 * octagon
 */
public class EuropeanSolitaireModel extends SolitaireModel {

  // Constructor 1: No parameters (default game board)
  public EuropeanSolitaireModel() {
    super();
  }

  // Constructor 2: Empty slot position
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
  }

  // Constructor 3: Arm thickness
  public EuropeanSolitaireModel(int armThickness) {
    super(armThickness);
  }

  // Constructor 4: Arm thickness and empty slot position
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  @Override
  protected boolean inCorner(int row, int col) {
    return (row < this.armThickness - 1 && col < this.armThickness - 1 && row + col < this.armThickness - 1)
        || (row < this.armThickness - 1 && col > (this.armThickness - 1) * 2 && col - row > (this.armThickness - 1) * 2)
        || (row > (this.armThickness - 1) * 2 && col < this.armThickness - 1 && row - col > (this.armThickness - 1) * 2)
        || (row > (this.armThickness - 1) * 2 && col > (this.armThickness - 1) * 2 && row + col > (this.armThickness - 1) * 5);
  }
}
