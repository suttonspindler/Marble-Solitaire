import cs3500.marblesolitaire.controller.SwingGuiController;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * controller that plays English Solitaire Game
 */
public class EnglishSolitaireGUIMain {
  public static void main(String[] args) {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    SwingGuiController controller = new SwingGuiController(model, view);
  }
}
