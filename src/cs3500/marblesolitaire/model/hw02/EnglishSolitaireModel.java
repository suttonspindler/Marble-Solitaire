// Completed with Vincent Yang

package cs3500.marblesolitaire.model.hw02;

/**
 * This class represents the english marble solitaire model, where it is in the shape of a plus
 */
public class EnglishSolitaireModel extends SolitaireModel {

  // Constructor 1: No parameters (default game board)
  public EnglishSolitaireModel() {
    super();
  }

  // Constructor 2: Empty slot position
  public EnglishSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);
  }

  // Constructor 3: Arm thickness
  public EnglishSolitaireModel(int armThickness) {
    super(armThickness);
  }

  // Constructor 4: Arm thickness and empty slot position
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  /**
   * Returns true if coordinates are in the four corners, in an invalid space
   *
   * @param row row coordinate to check
   * @param col col coordinate to check
   * @return boolean whether coordinates are in 4 corners
   */
  @Override
  protected boolean inCorner(int row, int col) {
    return (row < this.armThickness - 1 && col < this.armThickness - 1)
        || (row < this.armThickness - 1 && col > (this.armThickness - 1) * 2)
        || (row > (this.armThickness - 1) * 2 && col < this.armThickness - 1)
        || (row > (this.armThickness - 1) * 2 && col > (this.armThickness - 1) * 2);
  }
}
