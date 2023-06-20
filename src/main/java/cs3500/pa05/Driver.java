package cs3500.pa05;


import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.JournalWeek;
import cs3500.pa05.view.JournalView;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents a Whack-A-Mole game application.
 */
public class Driver extends Application {
  /**
   * Starts the GUI for a game of Whack-A-Mole.
   *
   * @param stage the JavaFX stage to add elements to
   */

  @Override
  public void start(Stage stage) throws IOException {

    JournalWeek week = new JournalWeek("changedSchedules/MondayRoyalWeek.fxml");
    JournalController controller = new JournalController(stage, week);
    JournalView wgvi = new JournalView(controller, "changedSchedules/MondayRoyalWeek.fxml");


    try {
      // load and place the view's scene onto the stage
      stage.setScene(wgvi.load());
      controller.run();
      stage.setTitle("Welcome Screen");
      // render the stage
      stage.show();
    } catch (IllegalStateException exc) {
      exc.printStackTrace();
      System.err.println("Unable to load GUI.");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Entry point for a game of Whack-a-Mole.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch();
  }
}