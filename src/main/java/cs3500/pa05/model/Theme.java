package cs3500.pa05.model;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;

public class Theme {
  private Color backgroundColor;
  private Color fontColor;
  private Font font;
  private Image iconSet;

  public Theme(Color backgroundColor, Color fontColor, Font font, Image iconSet) {
    this.backgroundColor = backgroundColor;
    this.fontColor = fontColor;
    this.font = font;
    this.iconSet = iconSet;
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

  public Color getFontColor() {
    return fontColor;
  }

  public Font getFont() {
    return font;
  }

  public Image getIconSet() {
    return iconSet;
  }
}
