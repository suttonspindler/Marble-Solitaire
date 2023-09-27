package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.controller.ControllerFeatures;

/**
 * Interface representing board panel which draws MarbleSolitaire game
 */
public interface IBoardPanel {

  /**
   * Set features provided
   *
   * @param features to set
   */
  void setFeatures(ControllerFeatures features);
}
