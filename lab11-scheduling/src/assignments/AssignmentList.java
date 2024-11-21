package assignments;

import java.util.ArrayList;
import java.util.List;

/**
 * A list of task that need to be completed.
 */
public class AssignmentList {

  private List<Assignment> tasks;
  private String ordering;

  /** Default constructor. */
  public AssignmentList() {
    tasks = new ArrayList<>();
    ordering = "assigned";
  }

  /**
   * Add a task to the task list.
   *
   * @param t the task
   */
  public void add(Assignment t) {
    if (t == null) {
      throw new IllegalArgumentException("Task cannot be null");
    }
    tasks.add(t);
  }

  /**
   * Schedule the tasks.
   *
   * @param strategy the strategy to use.
   */
  public void scheduleAssignments(SchedulingStrategy strategy) {
    if (strategy == null) {
      throw new IllegalArgumentException("No scheduling strategy provided");
    }
    if (tasks == null || tasks.isEmpty()) {
      throw new IllegalArgumentException("Task list does not exist");
    }
    this.ordering = strategy.schedule(tasks);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Ordered by ").append(ordering).append("\n");    
    for (int i = 0; i < tasks.size(); i++) {
      sb.append(i + 1).append(" -- ").append(tasks.get(i)).append("\n");
    }
    return sb.toString();
  }
}
