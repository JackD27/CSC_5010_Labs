package questions;

/**
 * Represents a Likert question. A Likert question is a question that asks the user to rate their
 * opinion on a statement. The user can choose from a set of choices.
 */
public class Likert implements Question {
  private final String text;
  private final String[] choices = {"Strongly Agree", "Agree", "Neither Agree nor Disagree",
      "Disagree", "Strongly Disagree"};

  public Likert(String text) {
    this.text = text;
  }

  @Override
  public String answer(String answer) {
    try {
      int option = Integer.parseInt(answer);
      if (option >= 1 && option <= 5) {
        return CORRECT;
      }
    } catch (NumberFormatException e) {
      return INCORRECT;
    }
    return INCORRECT;
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
    return this.text.compareTo(((Likert) o).text);
  }
}
