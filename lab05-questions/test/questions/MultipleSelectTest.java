package questions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class tests the MultipleSelect class. The MultipleSelect class is a subclass
 * of the Question class.
 */
public class MultipleSelectTest {
  @Test
  public void correctAnswer() {
    MultipleSelect multipleSelect = new MultipleSelect("text", "1 3 4",
            "1", "3", "4");
    assertEquals(Question.CORRECT, multipleSelect.answer("1 3 4"));
  }

  @Test
  public void correctAnswer2() {
    MultipleSelect multipleSelect = new MultipleSelect("text", "1 3 4",
            "1", "3", "4");
    assertEquals(Question.CORRECT, multipleSelect.answer("4 1 3"));
  }

  @Test
  public void incorrectAnswer() {
    MultipleSelect multipleSelect = new MultipleSelect("text", "1",
            "1", "3", "4");
    assertEquals(Question.INCORRECT, multipleSelect.answer("1 3"));
  }

  @Test
  public void incorrectAnswer2() {
    MultipleSelect multipleSelect = new MultipleSelect("text", "1",
            "3", "4", "1");
    assertEquals(Question.INCORRECT, multipleSelect.answer("1 4"));
  }

  @Test
  public void getText() {
    MultipleSelect multipleSelect = new MultipleSelect("text", "1",
            "3", "4", "choices");
    assertEquals("text", multipleSelect.getText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getChoicesIncorrect() {
    new MultipleSelect("text", "1", "choices");
  }

  @Test
  public void getChoicesMultiple() {
    MultipleSelect multipleSelect = new MultipleSelect("text", "1",
            "choices", "choices2", "choices3");
    assertEquals(3, multipleSelect.getChoices().length);
  }
}
