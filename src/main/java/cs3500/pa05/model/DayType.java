package cs3500.pa05.model;

public enum DayType {
  MONDAY("MONDAY"),TUESDAY("TUESDAY"),WEDNESDAY("WEDNESDAY"),
  THURSDAY("THURSDAY"),FRIDAY("FRIDAY"),SATURDAY("SATURDAY"),SUNDAY("SUNDAY");
  public final String rep;
  DayType(String s){
    this.rep = s;
  }
}
