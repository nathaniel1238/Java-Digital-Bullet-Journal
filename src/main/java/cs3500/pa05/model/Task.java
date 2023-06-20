package cs3500.pa05.model;

public class Task extends SchedulingItem{
  private boolean isCompleted;

  public Task(String itemName, String description, String day, Boolean isCompleted) {
    super(itemName,description,day);
    this.isCompleted = isCompleted;
  }

  public void setCompleted() {
    this.isCompleted = true;
  }

  public boolean getCompleted() {
    return this.isCompleted;
  }

  public String toString() {
    String completion;
    if(isCompleted) {
      completion = " Yes";
    } else {
      completion = " No";
    }
    return "Day: " + this.day + "\n"
        + "Task Name: " + name + "\n"
        + "Description: " + description + "\n"
        + "Completed?: " + completion;
  }

  public TaskJson toJson() {
    return new TaskJson(this.name,this.description,this.day, this.isCompleted);
  }

  public boolean equals(Object o) {
    if(!(o instanceof Task)) {
      return false;
    }
    Task t = (Task) o;

    return this.name.equals(t.name)
        && this.day.equals(t.day)
        && this.description.equals(t.description)
        && this.isCompleted == t.isCompleted;
  }
}
