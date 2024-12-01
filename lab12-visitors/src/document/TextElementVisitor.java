package document;

import document.elements.BasicText;
import document.elements.BoldText;
import document.elements.Heading;
import document.elements.HyperText;
import document.elements.ItalicText;
import document.elements.Paragraph;

/**
 * A visitor for text elements.
 *
 * @param <R> the return type of the visitor.
 */
public interface TextElementVisitor<R> {
  /**
   * Visits a paragraph.
   *
   * @param paragraph the paragraph to visit
   * @return the result of visiting the paragraph
   */

  R visitParagraphText(Paragraph paragraph);
  /**
   * Visits italic text.
   *
   * @param italic the italic text to visit
   * @return the result of visiting the italic text
   */

  R visitItalicText(ItalicText italic);

  /**
   * Visits a hyperlink.
   *
   * @param hyperlink the hyperlink to visit
   * @return the result of visiting the hyperlink
   */

  R visitHyperText(HyperText hyperlink);

  /**
   * Visits a heading.
   *
   * @param heading the heading to visit
   * @return the result of visiting the heading
   */
  R visitHeadingText(Heading heading);

  /**
   * Visits bold text.
   *
   * @param bold the bold text to visit
   * @return the result of visiting the bold text
   */
  R visitBoldText(BoldText bold);

  /**
   * Visits basic text.
   *
   * @param basicText the basic text to visit
   * @return the result of visiting the basic text
   */

  R visitBasicText(BasicText basicText);
}
