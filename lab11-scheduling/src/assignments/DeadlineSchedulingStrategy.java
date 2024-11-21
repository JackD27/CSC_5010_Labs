package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * A strategy for scheduling tasks by deadline.
 */
public class DeadlineSchedulingStrategy implements SchedulingStrategy {

  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignment list cannot be null");
    }
    assignments.sort(Comparator.comparing(Assignment::getEndDate)
            .thenComparing(Assignment::getDescription));
    return "deadline";
  }
}
