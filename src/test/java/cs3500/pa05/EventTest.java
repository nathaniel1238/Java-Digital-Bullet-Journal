package cs3500.pa05;

import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

  private Event event;

  @BeforeEach
  public void setUp() {
    event = new Event("Test Event", "Test Event Description", DayType.MONDAY.rep, "10:00", "60");
  }

  @Test
  public void testGetStartTime() {
    assertEquals("10:00", event.getStartTime());
  }

  @Test
  public void testGetDuration() {
    assertEquals("60", event.getDuration());
  }
}

