package cs3500.pa05.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class represents a theme used for journaling, including color themes, font, and icons.
 */
public class Theme {
  private Color backgroundColor;
  private Color fontColor;
  private Font font;
  private Image iconSet;

  /**
   * Constructs a new Theme with the specified background color, font color, font, and icon set.
   *
   * @param backgroundColor the background color
   * @param fontColor       the font color
   * @param font            the font
   * @param iconSet         the icon set
   */
  public Theme(Color backgroundColor, Color fontColor, Font font, Image iconSet) {
    this.backgroundColor = backgroundColor;
    this.fontColor = fontColor;
    this.font = font;
    this.iconSet = iconSet;
  }

  /**
   * Returns the background color of the theme.
   *
   * @return the background color of the theme
   */
  public Color getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * Returns the font color of the theme.
   *
   * @return the font color of the theme
   */
  public Color getFontColor() {
    return fontColor;
  }

  /**
   * Returns the font of the theme.
   *
   * @return the font of the theme
   */
  public Font getFont() {
    return font;
  }

  /**
   * Returns the icon set of the theme.
   *
   * @return the icon set of the theme
   */
  public Image getIconSet() {
    return iconSet;
  }
}
