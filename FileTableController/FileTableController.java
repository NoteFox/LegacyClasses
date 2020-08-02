import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;

public class FileTableController {

    public static void addNewEntry(@NotNull File file, String s) throws IOException {
        String[] split = s.split("\n");
        writeFileLines(file, split);
    }

    public static String readLastEntry(@NotNull File file) throws IOException {
        String[] read = readFileLines(file);
        if (read.length == 0) {
            return "";
        } else {
            return read[read.length - 1];
        }
    }

    public static String readEntry(@NotNull File file, int i) throws IOException {
        String[] read = readFileLines(file);
        if (read.length < i || i <= 0) {
            throw new IndexOutOfBoundsException("given index ("+ i +") does not exist in given File");
        }
        if (read.length == 0) {
            return "";
        }
        return read[i - 1];
    }

    public static String[] readAllEntries(@NotNull File file) throws IOException {
        return readFileLines(file);
    }

    public static int numberOfEntries(@NotNull File file) throws IOException {
        return readFileLines(file).length;
    }

    private static String[] readFileLines(@NotNull File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        if (!file.canRead()) {
            throw new IOException("given file cannot be read");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        ArrayList<String> readStrings = new ArrayList<>();

        String read = "";
        while ((read = br.readLine()) != null)
            readStrings.add(read);

        br.close();
        return readStrings.toArray(new String[0]);
    }

    private static void writeFileLines(@NotNull File file, String[] strings) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("given file does not exist");
        }

        if (strings == null || strings.length == 0) {
            throw new NullPointerException("given array has a size of null or was null");
        }

        String[] read = readFileLines(file);
        StringBuilder write = new StringBuilder();

        for (String s : read) {
            write.append(s).append("\n");
        }
        for (int i = 0; i < strings.length - 1; i++) {
            write.append(strings[i]).append("\n");
        }
        write.append(strings[strings.length - 1]);

        if (!file.canWrite()) {
            throw new IOException("file not writable");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        bw.write(write.toString());
        bw.close();
    }
}
