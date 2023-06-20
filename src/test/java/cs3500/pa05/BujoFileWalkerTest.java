package cs3500.pa05;

import cs3500.pa05.model.BujoFileWalker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BujoFileWalkerTest {
  private BujoFileWalker walker;
  private Path testDir;
  private Path testFile;
  private BasicFileAttributes attrs;

  @BeforeEach
  public void setUp() throws IOException {
    walker = new BujoFileWalker();
    testDir = Paths.get("");
    testFile = Paths.get("");

    attrs = Files.readAttributes(testFile, BasicFileAttributes.class);

    // Create a temporary directory and file for the test
    if (!Files.exists(testDir)) {
      Files.createDirectory(testDir);
    }

    if (!Files.exists(testFile)) {
      Files.createFile(testFile);
    }
  }

  @Test
  public void testPreVisitDirectory() throws IOException {
    assertEquals(FileVisitResult.CONTINUE, walker.preVisitDirectory(testDir, attrs));
  }

  @Test
  public void testVisitFileFailed() {
    IOException ex = assertThrows(IOException.class, () -> walker.visitFileFailed(testFile, new IOException()));
    assertEquals("Bad File!", ex.getMessage());
  }

  @Test
  public void testVisitFile() throws IOException {
    assertEquals(FileVisitResult.CONTINUE, walker.visitFile(testFile, attrs));
    assertFalse(walker.getBujoFiles().contains(testFile.toString()));
  }

  @Test
  public void testPostVisitDirectory() throws IOException {
    assertEquals(FileVisitResult.CONTINUE, walker.postVisitDirectory(testDir, null));
  }

  @Test
  public void testGetBujoFiles() throws IOException {
    walker.visitFile(testFile, attrs);
    ArrayList<String> bujoFiles = walker.getBujoFiles();
    assertFalse(bujoFiles.contains(testFile.toString()));
  }
}

