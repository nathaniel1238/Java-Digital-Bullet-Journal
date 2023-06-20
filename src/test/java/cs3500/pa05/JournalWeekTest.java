package cs3500.pa05;

import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalWeek;
import cs3500.pa05.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JournalWeekTest {

  private JournalWeek journalWeek;

  @BeforeEach
  public void setUp() {
    journalWeek = new JournalWeek("Default Theme");
  }

  @Test
  public void testSetMaxEvents() {
    journalWeek.setMaxEvents(10);
    assertEquals(10, journalWeek.getMaxEvents());
  }

  @Test
  public void testSetMaxTasks() {
    journalWeek.setMaxTasks(20);
    assertEquals(20, journalWeek.getMaxTasks());
  }

  @Test
  public void testSetTitle() {
    String title = "Test Title";
    journalWeek.setTitle(title);
    assertEquals(title, journalWeek.getTitle());
  }

  @Test
  public void testAddEvent() {
    Event
        event = new Event("Test Event", "Test Event Description", DayType.TUESDAY.rep, "10:00", "60");
    journalWeek.addEvent(DayType.TUESDAY.rep, event);
    assertEquals(1, journalWeek.getDaysEvents(DayType.TUESDAY.rep));
  }

  @Test
  public void testSetAndGetTheme() {
    String theme = "Test Theme";
    journalWeek.setTheme(theme);
    assertEquals(theme, journalWeek.getTheme());
  }

  @Test
  public void testClear() {
    journalWeek.clear();
    assertNull(journalWeek.getTitle());
    assertEquals(Integer.MAX_VALUE, journalWeek.getMaxEvents());
    assertEquals(Integer.MAX_VALUE, journalWeek.getMaxTasks());
  }
}


