package questions;

/**
 * This enum represents the type of a question on a computer-based test. The type of a question
 * determines the behavior of the question. The order of the enum values determines the order in
 * which the questions should be displayed.
 */
public enum QuestionType {
  TRUE_FALSE(1),
  MULTIPLE_CHOICE(2),
  MULTIPLE_SELECT(3),
  LIKERT(4);

  private final int order;

  QuestionType(int order) {
    this.order = order;
  }

  public int getOrder() {
    return order;
  }

  /**
   * Returns the order of the given question.
   *
   * @param question the question
   * @return the order of the question
   */
  public static int getOrderForQuestion(Question question) {
    if (question instanceof TrueFalse) {
      return TRUE_FALSE.getOrder();
    }
    if (question instanceof MultipleChoice) {
      return MULTIPLE_CHOICE.getOrder();
    }
    if (question instanceof MultipleSelect) {
      return MULTIPLE_SELECT.getOrder();
    }
    if (question instanceof Likert) {
      return LIKERT.getOrder();
    }
    return Integer.MAX_VALUE;
  }
}
