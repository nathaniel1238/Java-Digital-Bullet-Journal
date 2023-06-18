package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

public class Day {
  private final String day;
  private ArrayList<Event> events = new ArrayList<>();
  private ArrayList<Task> tasks = new ArrayList<>();

  Day(String day) {
    this.day = day;
  }

  public void addEvent(Event e){
    events.add(e);
  }

  public void addTask(Task t) {
    tasks.add(t);
  }

  public int getEvents(){
    return events.size();
  }

  public int getTasks() {
    return tasks.size();
  }

  public String getDay() {
    return this.day;
  }

  public DayJson toJson() {
    List<EventJson> eventJsons = new ArrayList<>();
    List<TaskJson> taskJsons = new ArrayList<>();
    for(Event e: events) {
      eventJsons.add(e.toJson());
    }
    for(Task t: tasks){
      taskJsons.add(t.toJson());
    }
    return new DayJson(this.day,eventJsons,taskJsons);
  }
}
