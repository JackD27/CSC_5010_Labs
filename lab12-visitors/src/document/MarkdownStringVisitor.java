package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import java.util.List;

/**
 * A visitor for text elements that converts the document to a markdown string.
 */
public class MarkdownStringVisitor implements TextElementVisitor<String> {

  private final StringBuilder result = new StringBuilder();
  private boolean isFirstElement = true;

  @Override
  public String visitParagraphText(Paragraph paragraph) {
    result.append("\n\n");
    List<BasicText> elements = paragraph.getContent();
    for (int i = 0; i < elements.size(); i++) {
      if (i > 0) {
        result.append("\n");
      }
      result.append(elements.get(i).accept(new MarkdownStringVisitor()));
    }
    return result.toString();
  }

  @Override
  public String visitItalicText(ItalicText italic) {
    appendWithNewline("*" + italic.getText() + "*");
    return result.toString();
  }

  @Override
  public String visitHyperText(HyperText hyperlink) {
    appendWithNewline("[" + hyperlink.getText() + "](" + hyperlink.getUrl() + ")");
    return result.toString();
  }

  @Override
  public String visitHeadingText(Heading heading) {
    appendWithNewline("#".repeat(heading.getLevel()) + " " + heading.getText());
    return result.toString();
  }

  @Override
  public String visitBoldText(BoldText bold) {
    appendWithNewline("**" + bold.getText() + "**");
    return result.toString();
  }

  @Override
  public String visitBasicText(BasicText basicText) {
    appendWithNewline(basicText.getText());
    return result.toString();
  }

  private void appendWithNewline(String text) {
    if (!isFirstElement) {
      result.append("\n");
    }
    result.append(text);
    isFirstElement = false;
  }

  @Override
  public String toString() {
    return result.toString();
  }
}
