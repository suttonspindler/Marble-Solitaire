package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the visual board for the MarbleSolitaire game
 */
public class BoardPanel extends JPanel implements IBoardPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot;
  private final int cellDimension;
  private int originX,originY;

  private ControllerFeatures features;
  
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
              new Dimension((this.modelState.getBoardSize() + 4) * cellDimension,
                  (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }
  }

  /**
   * Draw board to the screen
   *
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    //Graphics2D g2d = (Graphics2D) g;
    originX = (int) (this.getPreferredSize().getWidth() / 2 - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2 - this.modelState.getBoardSize() * cellDimension / 2);

    //paint the whole board
    if (this.modelState instanceof TriangleSolitaireModel) {
      this.paintTriangle(g);
    }
    else {
      this.paintNormal(g);
    }
  }

  /**
   * Paint board as triangle
   *
   * @param g Graphics panel to paint to
   */
  private void paintTriangle(Graphics g) {
    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        int extra = (int)(cellDimension * 0.5 * (this.modelState.getBoardSize() - i - 1));
        switch (this.modelState.getSlotAt(i, j)) {
          case Invalid:
            g.drawImage(blankSlot, originX + j * cellDimension + extra, originY + i * cellDimension,
                null);
            break;
          case Marble:
            g.drawImage(marbleSlot, originX + j * cellDimension + extra, originY + i * cellDimension,
                null);
            break;
          case Empty:
            g.drawImage(emptySlot, originX + j * cellDimension + extra, originY + i * cellDimension,
                null);
            break;
        }
      }
    }
  }

  /**
   * Paint board normally (for english or european)
   *
   * @param g Graphics panel to paint to
   */
  private void paintNormal(Graphics g) {
    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        switch (this.modelState.getSlotAt(i, j)) {
          case Invalid:
            g.drawImage(blankSlot, originX + j * cellDimension, originY + i * cellDimension, null);
            break;
          case Marble:
            g.drawImage(marbleSlot, originX + j * cellDimension, originY + i * cellDimension, null);
            break;
          case Empty:
            g.drawImage(emptySlot, originX + j * cellDimension, originY + i * cellDimension, null);
            break;
        }
      }
    }
  }

  /**
   * Set feature provided
   *
   * @param features feature to be set
   */
  @Override
  public void setFeatures(ControllerFeatures features) {
    this.features = features;
    this.addMouseListener(new BoardMouseListener());
  }

  /**
   * Represent mouse interactions for playing game
   */
  private class BoardMouseListener extends MouseAdapter {

    /**
     * Override mouse clocked method to make move
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      //find out row and column
      int row = (e.getY() - originY) / BoardPanel.this.cellDimension;
      int col;
      if (modelState instanceof TriangleSolitaireModel) {
         col =
            (e.getX() - (int) ((BoardPanel.this.modelState.getBoardSize() - row) * cellDimension
                * 0.5) - originX) / BoardPanel.this.cellDimension;
      }
      else {
         col = (e.getX() - originX) / BoardPanel.this.cellDimension;
      }
      BoardPanel.this.features.input(row, col);
    }
  }
}
