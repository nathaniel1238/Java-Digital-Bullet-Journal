package cs3500.pa05;

import cs3500.pa05.model.DayType;
import cs3500.pa05.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

  private Task task;
  private Task task2;

  @BeforeEach
  public void setUp() {
    task = new Task("Test Task", "Task Description", DayType.MONDAY.rep, false);
    task2 = new Task("Test Task 2", "Task Description", DayType.MONDAY.rep, true);
  }

  @Test
  public void testSetCompleted() {
    task.setCompleted();
    assertTrue(task.getCompleted());
  }

  @Test
  public void testToStringNo() {
    String expectedString = "Day: MONDAY\n"
        + "Task Name: Test Task\n"
        + "Description: Task Description\n"
        + "Completed?:  No";
    assertEquals(expectedString, task.toString());
  }

  @Test
  public void testToStringYes() {
    String expectedString = "Day: MONDAY\n"
        + "Task Name: Test Task 2\n"
        + "Description: Task Description\n"
        + "Completed?:  Yes";
    assertEquals(expectedString, task2.toString());
  }

  @Test
  public void testEqualsNotInstanceOf() {
    // Comparing to a string (or any other non-Task object) should return false
    String notATask = "Not a Task";
    assertFalse(task.equals(notATask));
  }

  @Test
  public void testEquals() {
    // Another task with the same parameters
    Task sameTask = new Task("Test Task", "Task Description", DayType.MONDAY.rep, false);
    assertTrue(task.equals(sameTask));

    // Change one parameter and the tasks should not be equal
    Task differentTask = new Task("Test Task", "Different Task Description", DayType.MONDAY.rep, false);
    assertFalse(task.equals(differentTask));
  }

}
