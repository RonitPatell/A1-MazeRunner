package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        logger.info("Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", true, "Maze input file path");

        CommandLineParser parser = new DefaultParser();
        String inputFile;
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("i")) {
                inputFile = cmd.getOptionValue("i");
            } else {
                logger.error("Maze couldn't be uploaded");
                return;
            }
        } catch (ParseException e) {
            logger.error("Parsing couldn't be done");
            return;
        }

        try {
            logger.info("Reading the maze from file: {}", inputFile);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    logger.info("Empty or space-only line detected");
                    // Handle lines that are empty or filled with spaces
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == ' ') {
                            logger.info("PASS");
                        }
                    }
                } else {
                    logger.info("Line read: '{}'", line);
                    StringBuilder lineOutput = new StringBuilder();
                    for (char c : line.toCharArray()) {
                        if (c == '#') {
                            lineOutput.append("WALL ");
                        } else if (c == ' ') {
                            lineOutput.append("PASS ");
                        }
                    }
                    logger.info(lineOutput.toString().trim());
                }
            }
            reader.close();
        } catch (IOException e) {
            logger.error("An error has occurred while reading the file: " + e.getMessage());
        }
        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
