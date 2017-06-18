package Tail;

import java.io.*;
import java.util.ArrayDeque;

public class Tail {

    private Integer number;
    private Integer mode; //mode 1 - tail of Characters | mode 2 - tail of Strings

    public Tail(Integer number, Integer mode) {
        if (number == null && mode == null) {
            this.number = 10;
            this.mode = 2;
        }
        else {
            this.number = number;
            this.mode = mode;
        }
    }

    private String pickCharacter(InputStream in) throws IOException {
        ArrayDeque<Character> queue = new ArrayDeque<>();
        ArrayDeque<Character> result = new ArrayDeque<>();
        try (InputStreamReader reader = new InputStreamReader(in)) {
            int i = reader.read();
            while (i != -1) {
                queue.addLast((char) i);
                i = reader.read();
            }
        }
        if (queue.size() > number){
            for (int i = 0; i != number; i++){
                result.addFirst(queue.removeLast());
            }
        }
        else {
            result = queue;
        }
        String resultString = "";
        for (int k : result) {
            resultString = resultString + (char) k;
        }
        return resultString;
    }

    private String pickString(InputStream in) throws IOException {
        ArrayDeque<String> queue = new ArrayDeque<>();
        ArrayDeque<String> result = new ArrayDeque<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = reader.readLine();
            while (line != null) {
                queue.addLast(line);
                line = reader.readLine();
            }
        }
        if (queue.size() > number){
            for (int i = 0; i != number; i++){
                result.addFirst(queue.removeLast());
            }
        }
        else {
            result = queue;
        }
        String resultString = "";
        for (String line : result) {
            resultString = resultString + line + "\n";
        }
        return resultString.trim();
    }

    public String pickTail(InputStream in) throws IOException {
        if (mode == 1) {
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