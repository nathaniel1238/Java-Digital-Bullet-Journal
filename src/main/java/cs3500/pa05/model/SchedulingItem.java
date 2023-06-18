package cs3500.pa05.model;

public abstract class SchedulingItem {
  protected final String name;
  protected final String description;

  protected final String day;

  SchedulingItem(String itemName, String description, String day) {
    this.name = itemName;
    this.description = description;
    this.day = day;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public String getDay() {
    return this.day;
  }
}
