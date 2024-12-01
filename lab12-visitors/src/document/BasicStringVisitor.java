package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;

/**
 * A visitor for text elements that returns a string representation of the text.
 */
public class BasicStringVisitor implements TextElementVisitor<String> {

  private final StringBuilder result = new StringBuilder();

  @Override
  public String visitParagraphText(Paragraph paragraph) {
    if (result.length() > 0) {
      result.append(" ");
    }
    result.append(paragraph.getText());
    return result.toString();
  }

  @Override
  public String visitItalicText(ItalicText italic) {
    if (result.length() > 0) {
      result.append(" ");
    }
    result.append(italic.getText());
    return result.toString();
  }

  @Override
  public String visitHyperText(HyperText hyperlink) {
    if (result.length() > 0) {
      result.append(" ");
    }
    result.append(hyperlink.getText());
    return result.toString();
  }

  @Override
  public String visitHeadingText(Heading heading) {
    if (result.length() > 0) {
      result.append(" ");
    }
    result.append(heading.getText());
    return result.toString();
  }

  @Override
  public String visitBoldText(BoldText bold) {
    if (result.length() > 0) {
      result.append(" ");
    }
    result.append(bold.getText());
    return result.toString();
  }

  @Override
  public String visitBasicText(BasicText basicText) {
    if (result.length() > 0) {
      result.append(" ");
    }
    result.append(basicText.getText());
    return result.toString();
  }

  @Override
  public String toString() {
    return result.toString();
  }
}
