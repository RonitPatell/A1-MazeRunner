package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    private static final Logger logger = LogManager.getLogger();
    private char[][] grid;

    public Maze(String inputPath) {
        processMaze(inputPath);
    }

    private void processMaze(String inputPath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(inputPath));
            int rowNum = lines.size();
            int colNum = 0;

            for (String line : lines) {
                if (line.length() > colNum) {
                    colNum = line.length();
                }
            }

            grid = new char[rowNum][colNum];

            for (int row = 0; row < rowNum; row++) {
                String line = lines.get(row);
                for (int col = 0; col < colNum; col++) {
                    grid[row][col] = (col < line.length()) ? line.charAt(col) : ' ';
                }
            }
        } catch (IOException e) {
            logger.error("Couldn't load the maze: " + e.getMessage());
        }
    }
}
