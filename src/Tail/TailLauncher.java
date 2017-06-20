package Tail;

import java.io.*;
import java.util.ArrayList;
import org.kohsuke.args4j.*;

public class TailLauncher {

    @Option(name = "-c")
    private Integer characterNumber = null;

    @Option(name = "-n")
    private Integer stringNumber = null;

    @Option(name = "-o")
    private String outputFile = null;

    @Argument(multiValued = true)
    private ArrayList<String> inputFiles = null;

    public static void main(String[] args) {
        new TailLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (stringNumber != null && characterNumber != null) {
                throw new CmdLineException(parser, "Using both -c and -n is not allowed");
            }
        }
        catch (CmdLineException e) {
            System.out.println(e.getMessage());
            System.out.println("java -jar Task2Project.jar [-c num | -n num] [-o ofile] file0 file1 file2 ...");
            return;
        }
        Tail tail;
        if (characterNumber != null){
            tail = new Tail(characterNumber, true);
        }
        else {
            tail = new Tail(stringNumber, false);
        }
        try {
            final OutputStream outputStream = (outputFile == null) ? System.out : new FileOutputStream(outputFile);
            final OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream);
            if (inputFiles != null) {
                for (String inputFileName : inputFiles) {
                    outputWriter.write(inputFileName + "\n\n" + tail.pickFileTail(inputFileName) + "\n\n");
                }
            }
            else {
                outputWriter.write(tail.pickTail(System.in));
            }
            outputWriter.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
