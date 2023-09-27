import cs3500.marblesolitaire.controller.SwingGuiController;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * controller that plays Triangle Solitaire Game
 */
public class TriangleSolitaireGUIMain {
  public static void main(String[] args) {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    SwingGuiController controller = new SwingGuiController(model, view);
  }
}
