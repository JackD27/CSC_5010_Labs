package questions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a multiple select question. A multiple select question is a question that asks the
 * user to select the correct answer from a set of choices. The user can select multiple choices.
 * The user must select all the correct choices to get the question correct.
 */
public class MultipleSelect implements Question {
  private final String text;
  private final String[] answers;
  private final String[] choices;

  /**
   * Constructs a MultipleSelect question with the given text, answers, and choices.
   *
   * @param text the text of the question
   * @param answers the answers to the question
   * @param options the choices for the question
   */
  public MultipleSelect(String text, String answers, String... options) {
    this.text = text;
    this.answers = answers.split(" ");
    this.choices = options;
    if (choices.length < 3 || choices.length > 8) {
      throw new IllegalArgumentException("Number of options must be between 3 and 8.");
    }
  }

  @Override
  public String answer(String answer) {
    Set<String> userAnswerSet = new HashSet<>(Arrays.asList(answer.split(" ")));
    Set<String> correctAnswerSet = new HashSet<>(Arrays.asList(answers));

    if (userAnswerSet.equals(correctAnswerSet)) {
      return CORRECT;
    } else {
      return INCORRECT;
    }
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
    return this.text.compareTo(((MultipleSelect) o).text);
  }
}
