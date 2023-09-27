import cs3500.marblesolitaire.controller.SwingGuiController;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * controller that plays European Solitaire Game
 */
public class EuropeanSolitaireGUIMain {
  public static void main(String[] args) {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    SwingGuiController controller = new SwingGuiController(model, view);
  }
}
