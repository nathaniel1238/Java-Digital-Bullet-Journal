package cs3500.pa05.model;

public class Event extends SchedulingItem {
  private final String startTime;
  private final String duration;

  public Event(String itemName, String description, String day, String start, String duration){
    super(itemName,description,day);
    this.startTime = start;
    this.duration = duration;
  }

  public String getStartTime() {
    return this.startTime;
  }

  public String getDuration() {
    return this.duration;
  }

  public String toString() {
    return "Event Name: " + name + "\n"
        + "Description: " + description + "\n"
        + "Time: " + startTime + "\n"
        + "Duration: " + duration + " minutes";
  }

  public EventJson toJson() {
    return new EventJson(this.name,this.description,this.day,this.startTime,this.duration);
  }
}
