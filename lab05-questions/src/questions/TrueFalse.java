package questions;

/**
 * This class represents a true/false question on a computer-based test. A true/false question is a
 * question that asks the user to determine if a statement is true or false.
 */
public class TrueFalse implements Question {
  private final String text;
  private final String answer;

  public TrueFalse(String text, String answer) {
    this.text = text;
    this.answer = answer;
  }

  @Override
  public String answer(String answer) {
    return this.answer.equals(answer) ? CORRECT : INCORRECT;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public int compareTo(Question o) {
    int typeComparison = Integer.compare(QuestionType.getOrderForQuestion(this),
            QuestionType.getOrderForQuestion(o));
    if (typeComparison != 0) {
      return typeComparison;
    }
    return this.text.compareTo(((TrueFalse) o).text);
  }
}
