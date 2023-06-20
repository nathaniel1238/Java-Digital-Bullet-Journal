package cs3500.pa05;

import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalWeek;
import cs3500.pa05.model.Task;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JournalWeekTest {

  private JournalWeek journalWeek;
  private JournalWeek journalWeek2;
  private Task task;

  @BeforeEach
  public void setUp() {
    task = new Task("Test Task", "Task Description", DayType.MONDAY.rep, false);
    journalWeek = new JournalWeek("Default Theme");
    journalWeek2 = new JournalWeek("Default Theme");
    journalWeek.setTitle("Test Title");
    journalWeek.setMaxTasks(5);
    journalWeek.setMaxEvents(3);
    journalWeek.clear();
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
        event =
        new Event("Test Event", "Test Event Description", DayType.TUESDAY.rep, "10:00", "60");
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


  @Test
  public void testSaveToBujo_IOException() {
    journalWeek.setTitle("Invalid/Directory");

    assertThrows(IOException.class, () -> {
      journalWeek.saveToBujo();
    });
  }

  @Test
  public void testAddAndGetDaysTasks() {
    assertEquals(0, journalWeek2.getDaysTasks(DayType.MONDAY.rep));

    journalWeek2.addTask(DayType.MONDAY.rep, task);

    assertEquals(1, journalWeek2.getDaysTasks(DayType.MONDAY.rep));

    journalWeek2.addTask(DayType.MONDAY.rep, task);
    journalWeek2.addTask(DayType.MONDAY.rep, task);

    assertEquals(3, journalWeek2.getDaysTasks(DayType.MONDAY.rep));

    assertEquals(0, journalWeek2.getDaysTasks(DayType.TUESDAY.rep));
  }
}


