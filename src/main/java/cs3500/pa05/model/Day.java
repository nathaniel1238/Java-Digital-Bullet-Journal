package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

public class Day {
  private final String day;
  private ArrayList<Event> events = new ArrayList<>();
  private ArrayList<Task> tasks = new ArrayList<>();

  public Day(String day) {
    this.day = day;
  }

  public void clear() {
    this.events = new ArrayList<>();
    this.tasks = new ArrayList<>();
  }

  public void removeTask(Task t) {
    for (Task task : tasks) {
      if (task.equals(t)) {
        tasks.remove(task);
        return;
      }
    }
  }

  public void removeEvent(Event e) {
    for (Event event : events) {
      if (event.equals(e)) {
        events.remove(event);
        return;
      }
    }
  }

  public int getCompletedTasks() {
    int count = 0;
    for (Task t : tasks) {
      if (t.getCompleted()) {
        count++;
      }
    }
    return count;
  }

  public ArrayList<String> getTaskList() {
    ArrayList<String> taskList = new ArrayList<>();
    for (Task t : tasks) {
      taskList.add(t.toString());
    }
    return taskList;
  }

  public void addEvent(Event e) {
    events.add(e);
  }

  public void addTask(Task t) {
    tasks.add(t);
  }

  public int getEvents() {
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
    for (Event e : events) {
      eventJsons.add(e.toJson());
    }
    for (Task t : tasks) {
      taskJsons.add(t.toJson());
    }
    return new DayJson(this.day, eventJsons, taskJsons);
  }
}
