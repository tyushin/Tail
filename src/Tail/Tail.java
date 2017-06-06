package Tail;

import java.io.*;
import java.util.ArrayList;

public class Tail {

    private Integer characterNumber;
    private Integer stringNumber;

    public Tail(Integer characterNumber, Integer stringNumber) {
        if (characterNumber == null && stringNumber == null) {
            this.stringNumber = 10;
        } else {
            this.characterNumber = characterNumber;
            this.stringNumber = stringNumber;
        }
    }

    private String pickCharacter(InputStream in) throws IOException {
        ArrayList<Character> segment1 = new ArrayList<>();
        ArrayList<Character> segment2 = new ArrayList<>();
        ArrayList<Character> resultSegment = new ArrayList<>();
        try (InputStreamReader reader = new InputStreamReader(in)) {
            int i = reader.read();
            while (i != -1) {
                segment2 = segment1;
                segment1 = new ArrayList<>();
                for (int j = 0; j < characterNumber && i != -1; j++) {
                    segment1.add((char) i);
                    i = reader.read();
                }
            }
        }
        if (segment2.isEmpty()) {
            resultSegment = segment1;
        } 
        else {
            final Integer remainNumber = characterNumber - segment1.size();
            resultSegment.addAll(segment2.subList(segment2.size() - remainNumber, segment2.size()));
            resultSegment.addAll(segment1);
        }
        String resultString = "";
        for (int k : resultSegment) {
            resultString = resultString + (char) k;
        }
        return resultString;
    }

    private String pickString(InputStream in) throws IOException {
        ArrayList<String> segment1 = new ArrayList<>();
        ArrayList<String> segment2 = new ArrayList<>();
        ArrayList<String> resultSegment = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = reader.readLine();
            while (line != null) {
                segment2 = segment1;
                segment1 = new ArrayList<>();
                for (int i = 0; i < stringNumber && line != null; i++) {
                    segment1.add(line);
                    line = reader.readLine();
                }
            }
        }
        if (segment2.isEmpty()) {
            resultSegment = segment1;
        }
        else {
            final Integer num = stringNumber - segment1.size();
            resultSegment.addAll(segment2.subList(segment2.size() - num, stringNumber));
            resultSegment.addAll(segment1);
        }
        String resultString = "";
        for (String line : resultSegment) {
            resultString = resultString + line + "\n";
        }
        return resultString.trim();
    }

    public String pickTail(InputStream in) throws IOException {
        if (characterNumber != null) {
            return pickCharacter(in);
        } else {
            return pickString(in);
        }
    }

    public String pickFileTail(String inputFile) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            return pickTail(inputStream);
        }
    }
}