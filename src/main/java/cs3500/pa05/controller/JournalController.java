package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.BujoFileWalker;
import cs3500.pa05.model.DayJson;
import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventJson;
import cs3500.pa05.model.JournalWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskJson;
import cs3500.pa05.model.WeekJson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class JournalController implements IController {


  @FXML
  private ComboBox<Button> comboC;
  @FXML
  private ListView<Button> monView;
  @FXML
  private ListView<Button> tuesView;

  @FXML
  private ListView<Button> wedView;

  @FXML
  private ListView<Button> thursView;

  @FXML
  private ListView<Button> friView;
  @FXML
  private ListView<Button> satView;

  @FXML
  private ListView<Button> sunView;

  @FXML
  private Button task;

  @FXML
  private Button saveFile;

  @FXML
  private Button event;

  @FXML
  private Button maxE;

  @FXML
  private Button maxT;

  @FXML
  private Button title;
  @FXML
  private Label weekTitle;

  @FXML
  private Label maxTaskCount;

  @FXML
  private Label popupLabel;

  @FXML
  private Label maxEventCount;

  @FXML
  private Button blueThemeButton;
  @FXML
  private Button purpleThemeButton;
  @FXML
  private Button blackThemeButton;

  private static String bad_input = "BAD_INPUT";


  private final Stage stage;

  private final JournalWeek week;

  private FileReader fileReader;
  private final ObjectMapper mapper = new ObjectMapper();


  public JournalController(Stage stage, JournalWeek week){
    this.stage = stage;
    this.week = week;
  }

  private void loadFile(String s) throws FileNotFoundException {
    fileReader = new FileReader(s);

    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.fileReader);
      WeekJson message = parser.readValueAs(WeekJson.class);
      loadButtonsAndHeadings(message);
      loadDays(message.days());

    } catch (IOException e) {

    }

  }

  private void loadDays(List<DayJson> days) throws IOException {
    for(DayJson d: days){
      if (d.day().equalsIgnoreCase(DayType.MONDAY.rep)) {
        loadTasks(monView, d.tasks());
        loadEvents(monView,d.events());
      } else if (d.day().equalsIgnoreCase(DayType.TUESDAY.rep)) {
        loadTasks(tuesView, d.tasks());
        loadEvents(tuesView,d.events());
      } else if (d.day().equalsIgnoreCase(DayType.WEDNESDAY.rep)) {
        loadTasks(wedView, d.tasks());
        loadEvents(wedView,d.events());
      } else if (d.day().equalsIgnoreCase(DayType.THURSDAY.rep)) {
        loadTasks(thursView, d.tasks());
        loadEvents(thursView,d.events());
      } else if (d.day().equalsIgnoreCase(DayType.FRIDAY.rep)) {
        loadTasks(friView, d.tasks());
        loadEvents(friView,d.events());
      } else if (d.day().equalsIgnoreCase(DayType.SATURDAY.rep)) {
        loadTasks(satView, d.tasks());
        loadEvents(satView,d.events());
      } else  {
        loadTasks(sunView, d.tasks());
        loadEvents(sunView,d.events());
      }
    }
  }

  private  void loadTasks(ListView<Button> view, List<TaskJson> tasks) throws IOException {
    for(TaskJson t: tasks){
      Task task = new Task(t.name(),t.description(),t.day());
      week.addTask(task.getDay(),task);
      view.getItems().addAll(inCalendar(task.getName(), task.toString()));
    }
  }

  private  void loadEvents(ListView<Button> view, List<EventJson> events) throws IOException {
    for(EventJson e: events){
      Event event = new Event(e.name(),e.description(),e.day(),e.start(),e.duration());
      week.addEvent(event.getDay(),event);
      view.getItems().addAll(inCalendar(event.getName(), event.toString()));
    }
  }
  private void loadButtonsAndHeadings(WeekJson message) {
    weekTitle.setText(message.title());
    week.setTitle(message.title());
    week.setMaxEvents(message.maxEvents());
    week.setMaxTasks(message.maxTasks());
    if(week.getMaxTasks() == Integer.MAX_VALUE){
      maxTaskCount.setText("Max Daily Tasks: N/A");
    }
    if(week.getMaxEvents() == Integer.MAX_VALUE) {
      maxEventCount.setText("Max Daily Events: N/A");
    }
    if(!(week.getMaxTasks() == Integer.MAX_VALUE) && !(week.getMaxEvents() == Integer.MAX_VALUE)) {
      maxTaskCount.setText("Max Daily Tasks: " + week.getMaxTasks());
      maxEventCount.setText("Max Daily Events: " + week.getMaxEvents());
    }
  }

  @FXML
  public void run() throws IllegalStateException, IOException {
    initComboButton();
    task.setOnAction(e -> createTask());
    event.setOnAction(e -> createEvent());
    maxT.setOnAction(e -> setTasks());
    maxE.setOnAction(e -> setEvents());
    title.setOnAction(e -> setTitle());
    saveFile.setOnAction(e -> {
      try {
        saveToBujo();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
  }

  private void initComboButton() throws IOException {
    BujoFileWalker visitor = new BujoFileWalker();
    Files.walkFileTree(Paths.get(""), visitor);
    ArrayList<String> files = visitor.getBujoFiles();
    ArrayList<Button> comboButtons = new ArrayList<>();
    for(String s: files) {
      Button b = new Button(s);
      b.setOnAction(e -> {
        try {
          loadFile(s);
        } catch (FileNotFoundException ex) {
          throw new RuntimeException(ex);
        }
      });
      comboButtons.add(b);
    }
    comboC.getItems().addAll(comboButtons);
  }
  private void saveToBujo() throws IOException {
    week.saveToBujo();
  }

  private void setTitle() {
    String t = getInfo("Enter a title for this week's calendar:");
    weekTitle.setText(t);
    week.setTitle(t);
  }
  private Button inCalendar(String name, String popupMsg) throws IOException {
    Button button = new Button(name);
    Popup aPopup = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("myPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    aPopup.getContent().add(s.getRoot());
    popupLabel.setText(popupMsg);
    button.setOnAction(e -> makePopup(aPopup));
    Button b = new Button("Close");
    b.setOnAction(e -> aPopup.hide());
    aPopup.getContent().add(b);
    return button;
  }

  @FXML
  private void makePopup(Popup p) {
    p.show(this.stage);
  }

  private void createTask() {
    try {
      String name = getInfo("Enter the name");
      String description = getInfo("Enter the description");
      String day = bad_input;
      while (day.equals(bad_input)) {
        day = validateDay(getInfo("Enter the day"));
      }
      if(week.getDaysTasks(day) >= week.getMaxTasks()){
        showErrorMsg("Max Tasks Reached!");
      } else {
        Task task = new Task(name,description,day);
        properDay(day).getItems().addAll(inCalendar(name, task.toString()));
        week.addTask(day,task);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void createEvent() {
    try {
      String name = getInfo("Enter the name");
      String description = getInfo("Enter the description");
      String day = bad_input;
      while (day.equals(bad_input)) {
        day = validateDay(getInfo("Enter the day"));
      }
      String time = bad_input;
      while (time.equals(bad_input)) {

        time = validateTime(getInfo("Enter the time in 00:00 format (24 hr clock)"));
      }

      String duration = bad_input;
      while (duration.equals(bad_input)) {

        duration = validateDuration(getInfo("Enter the duration in minutes (an integer)"));
      }
      if(week.getDaysEvents(day) >= week.getMaxEvents()){
        showErrorMsg("Max Events Reached!");
      } else {
        Event event = new Event(name, description, day, time, duration);
        properDay(day).getItems().addAll(inCalendar(name, event.toString()));
        week.addEvent(day, event);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private ListView<Button> properDay(String input) {
    if (input.equalsIgnoreCase(DayType.MONDAY.rep)) {
      return monView;
    } else if (input.equalsIgnoreCase(DayType.TUESDAY.rep)) {
      return tuesView;
    } else if (input.equalsIgnoreCase(DayType.WEDNESDAY.rep)) {
      return wedView;
    } else if (input.equalsIgnoreCase(DayType.THURSDAY.rep)) {
      return thursView;
    } else if (input.equalsIgnoreCase(DayType.FRIDAY.rep)) {
      return friView;
    } else if (input.equalsIgnoreCase(DayType.SATURDAY.rep)) {
      return satView;
    } else  {
      return sunView;
    }
  }

  private String getInfo(String msg) {
    TextInputDialog td = setupTextDialog("...");
    td.setHeaderText(msg);
    td.showAndWait();
    return td.getResult();
  }
  private String validateTime(String input) {
   int hour;
   int minute;
   try {
     hour = Integer.parseInt(input.substring(0,2));
     minute = Integer.parseInt(input.substring(4));
   } catch (NumberFormatException e) {
     return bad_input;
   }
   if(hour <= 23 && minute <= 59 && input.charAt(2) == ':') {
     return input;
   }
   return bad_input;
  }

  private String validateDuration(String input) {
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e){
      return bad_input;
    }
    return input;
  }

  private String validateDay(String input) {
    if (input.equalsIgnoreCase(DayType.MONDAY.rep)) {
      return DayType.MONDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.TUESDAY.rep)) {
      return DayType.TUESDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.WEDNESDAY.rep)) {
      return DayType.WEDNESDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.THURSDAY.rep)) {
      return DayType.THURSDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.FRIDAY.rep)) {
      return DayType.FRIDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.SATURDAY.rep)) {
      return DayType.SATURDAY.rep;
    } else if (input.equalsIgnoreCase(DayType.SUNDAY.rep)) {
      return DayType.SUNDAY.rep;
    }
    return bad_input;
  }

  private void setEvents() {
    TextInputDialog td = setupTextDialog("ENTER AN INTEGER");
    td.setHeaderText("Enter Maximum Daily Events");

    td.showAndWait().ifPresent(string -> {
      try {
        int newCount = validateInts(string, maxEventCount, week.getMaxEvents(), "Events: ");
        week.setMaxEvents(newCount);
      } catch (IOException e) {
        try {
          showErrorMsg("Not a valid integer!");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
  }

  private void showErrorMsg(String str) throws IOException {
    Popup aPopup = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("myPopup.fxml"));
    loader.setController(this);
    Scene s = loader.load();
    aPopup.getContent().add(s.getRoot());
    popupLabel.setText(str);
    aPopup.show(this.stage);
    Button b = new Button("Close");
    b.setOnAction(e -> aPopup.hide());
    aPopup.getContent().add(b);
  }

  private void setTasks() {
    TextInputDialog td = setupTextDialog("ENTER AN INTEGER");
    td.setHeaderText("Enter Maximum Daily Tasks");
    td.showAndWait().ifPresent(string -> {
      try {
        int newCount = validateInts(string, maxTaskCount, week.getMaxTasks(), "Tasks: ");
        week.setMaxTasks(newCount);
      } catch (IOException e) {

      }
    });
  }

  private int validateInts(String string, Label label, int itemCount, String end) throws IOException {
    int count = itemCount;
    try {
      count = Integer.parseInt(string);
      label.setText("Max Daily " + end + string);
    } catch (NumberFormatException exe) {
      Popup aPopup = new Popup();
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("myPopup.fxml"));
      loader.setController(this);
      Scene s = loader.load();
      aPopup.getContent().add(s.getRoot());
      popupLabel.setText("Not a valid integer!");
      aPopup.show(this.stage);
      Button b = new Button("Close");
      b.setOnAction(e -> aPopup.hide());
      aPopup.getContent().add(b);
    }
    return count;
  }

  private TextInputDialog setupTextDialog(String msg) {
    Image img = new Image("calendar.png");
    ImageView image = new ImageView(img);
    image.setFitHeight(100);
    image.setFitWidth(100);
    TextInputDialog td = new TextInputDialog(msg);
    td.setGraphic(image);
    return td;
  }


}
