package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;
import document.elements.TextElement;
import java.util.List;

/**
 * A visitor for text elements that converts the elements to HTML.
 */
public class HtmlStringVisitor implements TextElementVisitor<String> {

  private final StringBuilder result = new StringBuilder();
  private boolean isFirstElement = true;

  @Override
  public String visitParagraphText(Paragraph paragraph) {
    result.append("\n<p>");
    List<BasicText> elements = paragraph.getContent();
    for (int i = 0; i < elements.size(); i++) {
      if (i > 0) {
        result.append("\n");
      }
      result.append(elements.get(i).accept(new HtmlStringVisitor()));
    }
    result.append("\n</p>");
    return result.toString();
  }

  @Override
  public String visitItalicText(ItalicText italic) {
    appendWithNewline("<i>" + italic.getText() + "</i>");
    return result.toString();
  }

  @Override
  public String visitHyperText(HyperText hyperlink) {
    appendWithNewline("<a href=\"" + hyperlink.getUrl() + "\">" + hyperlink.getText() + "</a>");
    return result.toString();
  }

  @Override
  public String visitHeadingText(Heading heading) {
    appendWithNewline("<h" + heading.getLevel() + ">"
            + heading.getText() + "</h" + heading.getLevel() + ">");
    return result.toString();
  }

  @Override
  public String visitBoldText(BoldText bold) {
    appendWithNewline("<b>" + bold.getText() + "</b>");
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
