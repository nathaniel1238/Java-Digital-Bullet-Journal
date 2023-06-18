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

  public void saveToBujo() throws IOException {

    List<DayJson> daysJson = new ArrayList<>();
    for(Day d: days){
      daysJson.add(d.toJson());
    }
    WeekJson weekJson = new WeekJson(this.title,this.maxTasks,this.maxEvents,daysJson);
    JsonNode node = JsonUtils.serializeRecord(weekJson);
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString;
    try {
      jsonString = objectMapper.writeValueAsString(node);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    System.out.println(node);
    Write.writeToFile(jsonString, this.title + ".bujo");
  }


}
