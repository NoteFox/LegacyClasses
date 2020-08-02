import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileTableControllerTest {

    File testFile;

    @BeforeEach
    void setUp() throws IOException {
        testFile = new File("test");
        testFile.createNewFile();
    }

    @AfterEach
    void tearDown() {
        testFile.delete();
        testFile = null;
    }

    @Test
    void addNewEntryAndReadLastEntry() {
        String entry = "test";
        try {
            FileTableController.addNewEntry(testFile, entry);
        } catch (IOException e) {
            fail(e);
        }

        try {
            assertEquals(entry, FileTableController.readLastEntry(testFile));
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void readEntry() {
        String entry = "test";
        for (int i = 0; i < 10; i++) {
            try {
                FileTableController.addNewEntry(testFile, entry+(i + 1));
            } catch (IOException e) {
                fail(e);
            }
        }
        try {
            assertEquals(entry + "3", FileTableController.readEntry(testFile, 3));
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void readAllEntries() {
        String[] entry = {"test", "testtest", "24241", "hfgkh94uzt", "jfolsownfhdofj", "kdjakflhsdk", "123456789"};
        for (int i = 0; i < entry.length; i++) {
            try {
                FileTableController.addNewEntry(testFile, entry[i]);
            } catch (IOException e) {
                fail(e);
            }
        }

        try {
            assertArrayEquals(entry, FileTableController.readAllEntries(testFile));
        } catch (IOException e) {
            fail(e);
        }
    }

    @Test
    void numberOfEntries() {
        String[] entry = {"test", "testtest", "24241", "hfgkh94uzt", "jfolsownfhdofj", "kdjakflhsdk", "123456789"};
        for (int i = 0; i < entry.length; i++) {
            try {
                FileTableController.addNewEntry(testFile, entry[i]);
            } catch (IOException e) {
                fail(e);
            }
        }

        try {
            assertEquals(entry.length, FileTableController.numberOfEntries(testFile));
        } catch (IOException e) {
            fail(e);
        }
    }
}