package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import java.io.InputStreamReader;

/**
 * Create board using arguments passed in by user, then allow user to play game by letting
 * them type into the console with System.in
 */
public final class MarbleSolitaire {
  public static void main(String[] args) {
    MarbleSolitaireModel s = null;
    MarbleSolitaireView v;

    String gameType = args[0];
    if (gameType.equals("english")) {
      if (args.length > 1) {
        if (args[1].equals("-size")) {
          int size = Integer.parseInt(args[2]);
          if (args.length > 3) {
            s = new EnglishSolitaireModel(size, Integer.parseInt(args[3]), Integer.parseInt(args[4]));
          }
          else {
            s = new EnglishSolitaireModel(size);
          }
        }
        else if (args[1].equals("hole")) {
          s = new EnglishSolitaireModel(Integer.parseInt(args[2]), Integer.parseInt(args[4]));
        }
      }
      else {
        s = new EnglishSolitaireModel();
      }
      v = new MarbleSolitaireTextView(s);
    }
    else if (gameType.equals("european")) {
      if (args.length > 1) {
        if (args[1].equals("-size")) {
          int size = Integer.parseInt(args[2]);
          if (args.length > 3) {
            s = new EuropeanSolitaireModel(size, Integer.parseInt(args[3]), Integer.parseInt(args[4]));
          }
          else {
            s = new EuropeanSolitaireModel(size);
          }
        }
        else if (args[1].equals("hole")) {
          s = new EuropeanSolitaireModel(Integer.parseInt(args[2]), Integer.parseInt(args[4]));
        }
      }
      else {
        s = new EuropeanSolitaireModel();
      }
      v = new MarbleSolitaireTextView(s);
    }
    else {
      if (args.length > 1) {
        if (args[1].equals("-size")) {
          int size = Integer.parseInt(args[2]);
          if (args.length > 3) {
            s = new TriangleSolitaireModel(size, Integer.parseInt(args[3]), Integer.parseInt(args[4]));
          }
          else {
            s = new TriangleSolitaireModel(size);
          }
        }
        else if (args[1].equals("hole")) {
          s = new TriangleSolitaireModel(Integer.parseInt(args[2]), Integer.parseInt(args[4]));
        }
      }
      else {
        s = new TriangleSolitaireModel();
      }
      v = new TriangleSolitaireTextView(s);
    }

    MarbleSolitaireController c;

    c = new MarbleSolitaireControllerImpl(s, v, new InputStreamReader(System.in));
    c.playGame();
  }
}