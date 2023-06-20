package cs3500.pa05;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.EventJson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventJsonTest {

  @Test
  public void testEventJsonRecord() {
    EventJson eventJson = new EventJson(
        "Test Event",
        "This is a test event",
        "Monday",
        "10:00 AM",
        "2 hours"
    );

    assertEquals("Test Event", eventJson.name());
    assertEquals("This is a test event", eventJson.description());
    assertEquals("Monday", eventJson.day());
    assertEquals("10:00 AM", eventJson.start());
    assertEquals("2 hours", eventJson.duration());
  }
}

