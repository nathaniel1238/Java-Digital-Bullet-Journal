package cs3500.pa05.model;

public enum DayType {
  MONDAY("MONDAY", 1), TUESDAY("TUESDAY", 2), WEDNESDAY("WEDNESDAY", 3),
  THURSDAY("THURSDAY", 4), FRIDAY("FRIDAY", 5), SATURDAY("SATURDAY", 6), SUNDAY("SUNDAY", 0);
  public final String rep;
  public final int numRep;

  DayType(String s, int numRep) {
    this.rep = s;
    this.numRep = numRep;
  }
}
