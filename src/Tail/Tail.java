package Tail;

import java.io.*;
import java.util.ArrayDeque;

public class Tail {

    private Integer number;
    private Boolean modeChar;

    public Tail(Integer number, Boolean modeChar) {
        if (number == null && modeChar == null) {
            this.number = 10;
            this.modeChar = false;
        }
        else {
            this.number = number;
            this.modeChar = modeChar;
        }
    }

    private String pickCharacter(InputStream in) throws IOException {
        ArrayDeque<Character> queue = new ArrayDeque<>();
        try (InputStreamReader reader = new InputStreamReader(in)) {
            int i = reader.read();
            while (i != -1) {
                if(queue.size() >= number) {
                    queue.removeFirst();
                }
                queue.addLast((char) i);
                i = reader.read();
            }
        }
        String resultString = "";
        for (int k : queue) {
            resultString = resultString + (char) k;
        }
        return resultString;
    }

    private String pickString(InputStream in) throws IOException {
        ArrayDeque<String> queue = new ArrayDeque<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = reader.readLine();
            while (line != null) {
                if(queue.size() >= number) {
                    queue.removeFirst();
                }
                queue.addLast(line);
                line = reader.readLine();
            }
        }
        String resultString = "";
        for (String line : queue) {
            resultString = resultString + line + "\n";
        }
        return resultString.trim();
    }

    public String pickTail(InputStream in) throws IOException {
        if (modeChar) {
            return pickCharacter(in);
        }
        else {
            return pickString(in);
        }
    }

    public String pickFileTail(String inputFile) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            return pickTail(inputStream);
        }
    }
}