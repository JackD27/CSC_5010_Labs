package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * A strategy for scheduling tasks alphabetically.
 */
public class AlphabeticalSchedulingStrategy implements SchedulingStrategy {

  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignment list cannot be null");
    }
    assignments.sort(Comparator.comparing(Assignment::getDescription));
    return "alphabetical";
  }
}
