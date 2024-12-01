package document.elements;

import document.TextElementVisitor;

/**
 * Representation for any of the text elements of a document.
 */
public interface TextElement {

  /**
   * Returns the text of the element.
   *
   * @return the text
   *
   */
  public String getText();

  /**
   * Accepts a visitor and applies its logic to this text element.
   * This method allows the element to interact with the visitor
   * and return a result of the specified type.
   *
   * @param <R> the return type of the visitor's operation
   * @param visitor the visitor to be applied to this text element
   * @return the result of the visitor's operation on this element
   */
  <R> R accept(TextElementVisitor<R> visitor);
}
