package cs3500.pa05;

import cs3500.pa05.model.Write;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class WriteTest {

  @Test
  public void testWriteToFile() {
    String node = "{\"name\": \"John\", \"age\": 30}";
    String filePath = "output.txt";

    try {
      Write.writeToFile(node, filePath);
      File file = new File(filePath);
      assertTrue(file.exists());
      String content = new String(Files.readAllBytes(Paths.get(filePath)));
      assertEquals(node, content);
      file.delete();
    } catch (IOException e) {
      fail("IOException occurred: " + e.getMessage());
    }
  }
}
