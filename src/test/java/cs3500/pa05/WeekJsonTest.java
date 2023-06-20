package cs3500.pa05;

import cs3500.pa05.model.DayJson;
import cs3500.pa05.model.WeekJson;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class WeekJsonTest {

  private WeekJson weekJson;
  private List<DayJson> dayJsonList;

  @BeforeEach
  public void setUp() {
    dayJsonList = Arrays.asList(
        new DayJson("Monday", new ArrayList<>(), new ArrayList<>()),
        new DayJson("Tuesday", new ArrayList<>(), new ArrayList<>())
    );
    weekJson = new WeekJson("Test Week", 5, 10, "Blue Theme", dayJsonList);
  }

  @Test
  public void testRecordAttributes() {
    assertEquals("Test Week", weekJson.title());
    assertEquals(5, weekJson.maxTasks());
    assertEquals(10, weekJson.maxEvents());
    assertEquals("Blue Theme", weekJson.theme());
    assertEquals(dayJsonList, weekJson.days());
  }
}

