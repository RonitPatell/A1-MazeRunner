package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

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
            Maze maze = new Maze(inputFile);
            maze.displayMaze();
            maze.solveMaze();
            
        } catch (Exception e) {
            logger.error("An error has occurred: " + e.getMessage());
        }
        logger.debug("**** Computing path");
        logger.debug("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
