package assignments;

import java.util.List;

/**
 * A strategy for scheduling tasks.
 */
public interface SchedulingStrategy {

  /**
   * Schedule the tasks.
   *
   * @param assignments the tasks to schedule.
   * @return the schedule
   */
  String schedule(List<Assignment> assignments);
}
