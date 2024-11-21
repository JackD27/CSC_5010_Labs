package assignments;

import java.util.Comparator;
import java.util.List;

/**
 * A strategy for scheduling tasks by difficulty.
 */
public class DifficultySchedulingStrategy implements SchedulingStrategy {

  @Override
  public String schedule(List<Assignment> assignments) {
    if (assignments == null) {
      throw new IllegalArgumentException("Assignment list cannot be null");
    }
    assignments.sort(Comparator.comparingInt(Assignment::getDifficulty).reversed());
    return "difficulty";
  }
}
