import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import assignments.AlphabeticalSchedulingStrategy;
import assignments.AssignedSchedulingStrategy;
import assignments.Assignment;
import assignments.AssignmentList;
import assignments.DeadlineSchedulingStrategy;
import assignments.DifficultySchedulingStrategy;
import assignments.SchedulingStrategy;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/** Class that tests the tasks. */
public class AssignmentTest {

  private AssignmentList assignmentList;
  private Assignment assignment1;
  private Assignment assignment2;
  private Assignment assignment3;
  private Assignment assignment4;

  /** Set up the tasks. */
  @Before
  public void setUp() {
    assignmentList = new AssignmentList();

    assignment1 = new Assignment("Math Homework");
    assignment1.setStart(9, 10, 2024);
    assignment1.setDeadline(12, 8, 2024);

    assignment2 = new Assignment("Computer Science Project");
    assignment2.setStart(10, 20, 2024);
    assignment2.setDeadline(11, 10, 2024);

    assignment3 = new Assignment("Mobile App Project");
    assignment3.setStart(11, 3, 2024);
    assignment3.setDeadline(11, 18, 2024);

    assignment4 = new Assignment("Web Development Project");
    assignment4.setStart(9, 5, 2024);
    assignment4.setDeadline(11, 3, 2024);

    assignmentList.add(assignment1);
    assignmentList.add(assignment2);
    assignmentList.add(assignment3);
    assignmentList.add(assignment4);
  }

  /** Testing constructor and toString(). */
  @Test
  public void testConstructor() {
    LocalDate now = LocalDate.now();
    System.out.println(now);
    Assignment t1 = new Assignment("task 1");
    assertEquals("task 1, starting " + now + ", ending " + now, t1.toString());
    Assignment t2 = new Assignment("task 2");
    t2.setDeadline(3, 4, 2025);
    assertEquals("task 2, starting " + now + ", ending 2025-03-04", t2.toString());
  }

  @Test
  public void getTotalCount() {
    assertEquals(4, Assignment.getTotalCount());
  }

  @Test
  public void getDescription() {
    assertEquals("Math Homework", assignment1.getDescription());
    assertEquals("Computer Science Project", assignment2.getDescription());
    assertEquals("Mobile App Project", assignment3.getDescription());
    assertEquals("Web Development Project", assignment4.getDescription());
  }

  @Test
  public void getStartDate() {
    assertEquals(LocalDate.of(2024, 9, 10), assignment1.getStartDate());
    assertEquals(LocalDate.of(2024, 10, 20), assignment2.getStartDate());
    assertEquals(LocalDate.of(2024, 11, 3), assignment3.getStartDate());
    assertEquals(LocalDate.of(2024, 9, 5), assignment4.getStartDate());
  }

  @Test
  public void getEndDate() {
    assertEquals(LocalDate.of(2024, 12, 8), assignment1.getEndDate());
    assertEquals(LocalDate.of(2024, 11, 10), assignment2.getEndDate());
    assertEquals(LocalDate.of(2024, 11, 18), assignment3.getEndDate());
    assertEquals(LocalDate.of(2024, 11, 3), assignment4.getEndDate());
  }

  @Test
  public void getNumber() {
    assertEquals(0, assignment1.getNumber());
    assertEquals(1, assignment2.getNumber());
    assertEquals(2, assignment3.getNumber());
    assertEquals(3, assignment4.getNumber());
  }

  @Test
  public void getDifficulty() {
    assertEquals(88, assignment1.getDifficulty());
    assertEquals(21, assignment2.getDifficulty());
    assertEquals(15, assignment3.getDifficulty());
    assertEquals(59, assignment4.getDifficulty());
  }

  @Test
  public void testToString() {
    assertEquals("Math Homework, starting 2024-09-10, ending 2024-12-08",
            assignment1.toString());
    assertEquals("Computer Science Project, starting 2024-10-20, ending 2024-11-10",
            assignment2.toString());
    assertEquals("Mobile App Project, starting 2024-11-03, ending 2024-11-18",
            assignment3.toString());
    assertEquals("Web Development Project, starting 2024-09-05, ending 2024-11-03",
            assignment4.toString());
  }

  @Test
  public void testEquals() {
    assertEquals(assignment1, assignment1);
    assertNotEquals(assignment1, assignment2);
  }

  @Test
  public void testAddingLateTask() {
    SchedulingStrategy strategy = new AlphabeticalSchedulingStrategy();
    assignmentList.scheduleAssignments(strategy);

    String expected = "Ordered by alphabetical\n"
            + "1 -- Computer Science Project, starting 2024-10-20, ending 2024-11-10\n"
            + "2 -- Math Homework, starting 2024-09-10, ending 2024-12-08\n"
            + "3 -- Mobile App Project, starting 2024-11-03, ending 2024-11-18\n"
            + "4 -- Web Development Project, starting 2024-09-05, ending 2024-11-03\n";

    assertEquals(expected, assignmentList.toString());
    Assignment lateTask = new Assignment("Late Task");
    lateTask.setStart(11, 3, 2024);
    lateTask.setDeadline(12, 1, 2024);
    assignmentList.add(lateTask);
    expected += "5 -- Late Task, starting 2024-11-03, ending 2024-12-01\n";
    assertEquals(expected, assignmentList.toString());
    assignmentList.scheduleAssignments(strategy);
    expected = "Ordered by alphabetical\n"
            + "1 -- Computer Science Project, starting 2024-10-20, ending 2024-11-10\n"
            + "2 -- Late Task, starting 2024-11-03, ending 2024-12-01\n"
            + "3 -- Math Homework, starting 2024-09-10, ending 2024-12-08\n"
            + "4 -- Mobile App Project, starting 2024-11-03, ending 2024-11-18\n"
            + "5 -- Web Development Project, starting 2024-09-05, ending 2024-11-03\n";
    assertEquals(expected, assignmentList.toString());
  }

  @Test
  public void testAssignedSchedulingStrategy() {
    SchedulingStrategy strategy = new AssignedSchedulingStrategy();
    assignmentList.scheduleAssignments(strategy);

    String expected = "Ordered by assigned\n"
            + "1 -- Math Homework, starting 2024-09-10, ending 2024-12-08\n"
            + "2 -- Computer Science Project, starting 2024-10-20, ending 2024-11-10\n"
            + "3 -- Mobile App Project, starting 2024-11-03, ending 2024-11-18\n"
            + "4 -- Web Development Project, starting 2024-09-05, ending 2024-11-03\n";

    assertEquals(expected, assignmentList.toString());
  }

  @Test
  public void testAlphabeticalSchedulingStrategy() {
    SchedulingStrategy strategy = new AlphabeticalSchedulingStrategy();
    assignmentList.scheduleAssignments(strategy);

    String expected = "Ordered by alphabetical\n"
            + "1 -- Computer Science Project, starting 2024-10-20, ending 2024-11-10\n"
            + "2 -- Math Homework, starting 2024-09-10, ending 2024-12-08\n"
            + "3 -- Mobile App Project, starting 2024-11-03, ending 2024-11-18\n"
            + "4 -- Web Development Project, starting 2024-09-05, ending 2024-11-03\n";

    assertEquals(expected, assignmentList.toString());
  }

  @Test
  public void testDeadlineSchedulingStrategy() {
    SchedulingStrategy strategy = new DeadlineSchedulingStrategy();
    assignmentList.scheduleAssignments(strategy);

    String expected = "Ordered by deadline\n"
            + "1 -- Web Development Project, starting 2024-09-05, ending 2024-11-03\n"
            + "2 -- Computer Science Project, starting 2024-10-20, ending 2024-11-10\n"
            + "3 -- Mobile App Project, starting 2024-11-03, ending 2024-11-18\n"
            + "4 -- Math Homework, starting 2024-09-10, ending 2024-12-08\n";

    assertEquals(expected, assignmentList.toString());
  }

  @Test
  public void testDifficultySchedulingStrategy() {
    SchedulingStrategy strategy = new DifficultySchedulingStrategy();
    assignmentList.scheduleAssignments(strategy);

    String expected = "Ordered by difficulty\n"
            + "1 -- Math Homework, starting 2024-09-10, ending 2024-12-08\n"
            + "2 -- Web Development Project, starting 2024-09-05, ending 2024-11-03\n"
            + "3 -- Computer Science Project, starting 2024-10-20, ending 2024-11-10\n"
            + "4 -- Mobile App Project, starting 2024-11-03, ending 2024-11-18\n";

    assertEquals(expected, assignmentList.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullStrategyThrowsException() {
    assignmentList.scheduleAssignments(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullTaskListThrowsException() {
    AssignmentList nullList = new AssignmentList();
    nullList.scheduleAssignments(new AssignedSchedulingStrategy());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullTaskThrowsException() {
    assignmentList.add(null);
  }
}
