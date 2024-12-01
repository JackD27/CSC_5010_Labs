package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;

/**
 * A visitor for text elements that counts the number of words in the document.
 */
public class WordCountVisitor implements TextElementVisitor<Integer> {

  private int wordCount = 0;

  @Override
  public Integer visitParagraphText(Paragraph paragraph) {
    wordCount += paragraph.getText().split("\\s+").length;
    return wordCount;
  }

  @Override
  public Integer visitItalicText(ItalicText italic) {
    wordCount += italic.getText().split("\\s+").length;
    return wordCount;
  }

  @Override
  public Integer visitHyperText(HyperText hyperlink) {
    wordCount += hyperlink.getText().split("\\s+").length;
    return wordCount;
  }

  @Override
  public Integer visitHeadingText(Heading heading) {
    wordCount += heading.getText().split("\\s+").length;
    return wordCount;
  }

  @Override
  public Integer visitBoldText(BoldText bold) {
    wordCount += bold.getText().split("\\s+").length;
    return wordCount;
  }

  @Override
  public Integer visitBasicText(BasicText basicText) {
    wordCount += basicText.getText().split("\\s+").length;
    return wordCount;
  }

  public int getWordCount() {
    return wordCount;
  }
}
