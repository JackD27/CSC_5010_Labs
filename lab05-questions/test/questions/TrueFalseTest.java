package questions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class tests the TrueFalse class. The TrueFalse class is a subclass of the Question class.
 */
public class TrueFalseTest {
  @Test
  public void correctAnswer() {
    TrueFalse trueFalse = new TrueFalse("text", "answer");
    assertEquals(Question.CORRECT, trueFalse.answer("answer"));
  }

  @Test
  public void incorrectAnswer() {
    TrueFalse trueFalse = new TrueFalse("text", "answer");
    assertEquals(Question.INCORRECT, trueFalse.answer("answ"));
  }

  @Test
  public void getText() {
    TrueFalse trueFalse = new TrueFalse("text", "answer");
    assertEquals("text", trueFalse.getText());
  }

  @Test
  public void compareTo() {
    TrueFalse trueFalse = new TrueFalse("text", "answer");
    assertEquals(0, trueFalse.compareTo(null));
  }
}
