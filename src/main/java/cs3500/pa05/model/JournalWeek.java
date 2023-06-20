package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JournalWeek implements IWeek {

  private String title;

  private Day[] days = {new Day(DayType.SUNDAY.rep), new Day(DayType.MONDAY.rep),
      new Day(DayType.TUESDAY.rep), new Day(DayType.WEDNESDAY.rep), new Day(DayType.THURSDAY.rep),
      new Day(DayType.FRIDAY.rep), new Day(DayType.SATURDAY.rep)};

  private int maxEvents = Integer.MAX_VALUE;

  private int maxTasks = Integer.MAX_VALUE;

  private String theme;

  public JournalWeek(String theme) {
    this.theme = theme;
  }

  public void clear() {
    this.title = null;
    this.maxEvents = Integer.MAX_VALUE;
    this.maxTasks = Integer.MAX_VALUE;
    for(Day d: days){
      d.clear();
    }
  }

  public void setTheme(String theme) {
    this.theme = theme;
  }

  public String getTheme() {
    return this.theme;
  }

  public void setMaxEvents(int num) {
    this.maxEvents = num;
  }

  public void setMaxTasks(int num) {
    this.maxTasks = num;
  }

  public int getMaxEvents() {
    return this.maxEvents;
  }

  public int getMaxTasks() {
    return this.maxTasks;
  }

  public void addTask(String day, Task t) {
    for (Day d : days) {
      if (d.getDay().equals(day)) {
        d.addTask(t);
      }
    }
  }

  public void addEvent(String day, Event e) {
    for (Day d : days) {
      if (d.getDay().equals(day)) {
        d.addEvent(e);
      }
    }
  }

  public void setTitle(String str) {
    this.title = str;
  }

  public String getTitle() {
    return this.title;
  }

  public int getDaysTasks(String day) {
    for (Day d : days) {
      if (d.getDay().equals(day)) {
        return d.getTasks();
      }
    }
    return -1;
  }

  public int getDaysEvents(String day) {
    for (Day d : days) {
      if (d.getDay().equals(day)) {
        return d.getEvents();
      }
    }
    return -1;
  }

  public String saveToBujo() throws IOException {

    List<DayJson> daysJson = new ArrayList<>();
    for(Day d: days){
      daysJson.add(d.toJson());
    }
    WeekJson weekJson = new WeekJson(this.title,this.maxTasks,this.maxEvents,this.theme, daysJson);
    JsonNode node = JsonUtils.serializeRecord(weekJson);
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString;
    try {
      jsonString = objectMapper.writeValueAsString(node);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    String file = "bujoFiles/" + this.title + ".bujo";
    System.out.println(node);
    Write.writeToFile(jsonString, file);
    return file;
  }
}