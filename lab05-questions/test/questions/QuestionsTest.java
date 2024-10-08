package questions;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * This class tests the Questions class. The Questions class is a subclass
 * of the Question class.
 */
public class QuestionsTest {
  @Test
  public void testQuestionOrdering() {
    List<Question> questions = new ArrayList<>();
    questions.add(new MultipleSelect("Which of these are included in the Bill of Rights?",
            "1", "1", "2", "3"));
    questions.add(new Likert("How satisfied are you with our service?"));
    questions.add(new TrueFalse("Is Java a programming language?", "true"));
    questions.add(new MultipleChoice("What is the capital of France?", "Paris",
            new String[]{"Paris", "London", "Berlin"}));

    Collections.sort(questions); // Sort the questions

    assertEquals("Expected first question text",
            "Is Java a programming language?", questions.get(0).getText());
    assertEquals("Expected second question text",
            "What is the capital of France?", questions.get(1).getText());
    assertEquals("Expected third question text",
            "Which of these are included in the Bill of Rights?",
            questions.get(2).getText());
    assertEquals("Expected fourth question text",
            "How satisfied are you with our service?", questions.get(3).getText());
  }
}
