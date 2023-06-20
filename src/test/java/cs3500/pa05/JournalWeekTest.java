package cs3500.pa05;

import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalWeek;
import cs3500.pa05.model.Task;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JournalWeekTest {

  private JournalWeek journalWeek;

  @BeforeEach
  public void setUp() {
    journalWeek = new JournalWeek("Default Theme");
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

//  @Test
//  public void testSaveToBujo() throws IOException {
//    journalWeek.addTask("Monday", new Task("Task 1", "Description 1", "Monday", false));
//    journalWeek.addTask("Monday", new Task("Task 2", "Description 2", "Monday", true));
//    journalWeek.addEvent("Wednesday", new Event("Event 1", "Description 1", "Wednesday", "10:00 AM", "20"));
//    journalWeek.addEvent("Thursday", new Event("Event 2", "Description 2", "Thursday", "2:00 PM", "200"));
//
//    String filePath = journalWeek.saveToBujo();
//    assertNotNull(filePath);
//    File file = new File(filePath);
//    assertTrue(file.exists());
//    file.delete();
//  }
//
//  @Test
//  public void testSaveToBujo_NoTasksOrEvents() throws IOException {
//    String filePath = journalWeek.saveToBujo();
//    assertNotNull(filePath);
//    File file = new File(filePath);
//    assertTrue(file.exists());
//    file.delete();
//  }

  @Test
  public void testSaveToBujo_IOException() {
    journalWeek.setTitle("Invalid/Directory");

    assertThrows(IOException.class, () -> {
      journalWeek.saveToBujo();
    });
  }
}


