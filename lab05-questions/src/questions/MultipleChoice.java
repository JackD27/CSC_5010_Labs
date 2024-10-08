package questions;

/**
 * This class represents a multiple choice question on a computer-based test. A multiple choice
 * question is a question that asks the user to select the correct answer from a set of choices.
 * The user can only select one choice.
 */
public class MultipleChoice implements Question {
  private final String text;
  private final String answer;
  private final String[] choices;

  /**
   * Constructs a MultipleChoice question with the given text, answer, and choices.
   *
   * @param text the text of the question
   * @param answer the answer to the question
   * @param choices the choices for the question
   */
  public MultipleChoice(String text, String answer, String[] choices) {
    this.text = text;
    this.answer = answer;
    this.choices = choices;
  }

  @Override
  public String answer(String answer) {
    return this.answer.equals(answer) ? CORRECT : INCORRECT;
  }

  @Override
  public String getText() {
    return text;
  }

  public String[] getChoices() {
    return choices;
  }

  @Override
  public int compareTo(Question o) {
    int typeComparison = Integer.compare(QuestionType.getOrderForQuestion(this),
            QuestionType.getOrderForQuestion(o));
    if (typeComparison != 0) {
      return typeComparison;
    }
    return this.text.compareTo(((MultipleChoice) o).text);
  }
}
