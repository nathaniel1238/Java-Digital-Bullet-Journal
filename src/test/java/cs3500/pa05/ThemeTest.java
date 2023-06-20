package cs3500.pa05;

import cs3500.pa05.model.Theme;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.text.Font;
import static org.junit.jupiter.api.Assertions.*;

public class ThemeTest {

  private Theme theme;

  @BeforeEach
  public void setUp() {
    theme = new Theme(Color.WHITE, Color.BLACK, new Font("Arial", 12), null);
  }

  @Test
  public void testGetBackgroundColor() {
    assertEquals(Color.WHITE, theme.getBackgroundColor());
  }

  @Test
  public void testGetFontColor() {
    assertEquals(Color.BLACK, theme.getFontColor());
  }

  @Test
  public void testGetFont() {
    assertEquals(new Font("Arial", 12), theme.getFont());
  }

  @Test
  public void testGetIconSet() {
    assertNull(theme.getIconSet());
  }
}

