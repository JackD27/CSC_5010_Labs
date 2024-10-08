package questions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class tests the MultipleChoice class. The MultipleChoice class is a subclass
 * of the Question class.
 */
public class MultipleChoiceTest {
  @Test
  public void correctAnswer() {
    MultipleChoice multipleChoice = new MultipleChoice("text", "answer",
            new String[]{"choices"});
    assertEquals(Question.CORRECT, multipleChoice.answer("answer"));
  }

  @Test
  public void incorrectAnswer() {
    MultipleChoice multipleChoice = new MultipleChoice("text", "answer",
            new String[]{"choices"});
    assertEquals(Question.INCORRECT, multipleChoice.answer("answ"));
  }

  @Test
  public void incorrectAnswer2() {
    MultipleChoice multipleChoice = new MultipleChoice("text", "answer",
            new String[]{"choices"});
    assertEquals(Question.INCORRECT, multipleChoice.answer("ans"));
  }

  @Test
  public void getText() {
    MultipleChoice multipleChoice = new MultipleChoice("text", "answer",
            new String[]{"choices"});
    assertEquals("text", multipleChoice.getText());
  }

  @Test
  public void getChoicesOne() {
    MultipleChoice multipleChoice = new MultipleChoice("text", "answer",
            new String[]{"choices"});
    assertEquals(1, multipleChoice.getChoices().length);
  }

  @Test
  public void getChoicesMultiple() {
    MultipleChoice multipleChoice = new MultipleChoice("text", "answer",
            new String[]{"choices", "choices2", "choices3"});
    assertEquals(3, multipleChoice.getChoices().length);
  }
}
