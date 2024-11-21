package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * A strategy for scheduling tasks.
 */
public class AssignedSchedulingStrategy implements SchedulingStrategy {

  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignment list cannot be null");
    }
    assignments.sort(Comparator.comparingInt(Assignment::getNumber));
    return "assigned";
  }
}
