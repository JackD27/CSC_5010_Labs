package questions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class tests the Likert class. The Likert class is a subclass of the Question class.
 */
public class LikertTest {

  @Test
  public void correctAnswer() {
    Likert likert = new Likert("Do you like this class?");
    assertEquals(Question.CORRECT, likert.answer("1"));
  }

  @Test
  public void incorrectAnswer() {
    Likert likert = new Likert("Do you like this class?");
    assertEquals(Question.INCORRECT, likert.answer("6"));
  }

  @Test
  public void getText() {
    Likert likert = new Likert("Do you like this class?");
    assertEquals("Do you like this class?", likert.getText());
  }

  @Test
  public void getChoices() {
    Likert likert = new Likert("Do you like this class?");
    assertEquals(5, likert.getChoices().length);
  }

  @Test
  public void compareTo() {

  }
}