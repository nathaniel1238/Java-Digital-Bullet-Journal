package cs3500.pa05.model;

public class Task extends SchedulingItem{
  private boolean isCompleted;

  public Task(String itemName, String description, String day) {
    super(itemName,description,day);
    this.isCompleted = false;
  }

  public void setCompleted() {
    this.isCompleted = true;
  }

  public boolean getCompleted() {
    return this.isCompleted;
  }

  public String toString() {
    return "Task Name: " + name + "\n"
        + "Description: " + description + "\n"
        + "Completed?: " + isCompleted;
  }

  public TaskJson toJson() {
    return new TaskJson(this.name,this.description,this.day, this.isCompleted);
  }
}
