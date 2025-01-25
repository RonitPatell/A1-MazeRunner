package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    private static final Logger logger = LogManager.getLogger();
    private String inputPath;
    private char[][] grid;
    private int entranceRow, exitRow, totalRows;
    private int totalCols;
    private Player player;
    private boolean solved = false;
    private StringBuilder resultPath = new StringBuilder();

    public Maze(String inputPath) {
        this.inputPath = inputPath;
        processMaze();
        player = new Player(entranceRow);
    }

    private void processMaze() {
        List<String> fileLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (Exception e) {
            logger.error("Failed to load the maze: " + e.getMessage());
            return;
        }
        totalRows = fileLines.size();
        totalCols = fileLines.get(0).length();
        grid = new char[totalRows][totalCols];

        for (int row = 0; row < totalRows; row++) {
            String line = fileLines.get(row);
            for (int col = 0; col < totalCols; col++) {
                if (col < line.length()) {
                    grid[row][col] = line.charAt(col);
                } else {
                    grid[row][col] = ' ';
                }
            }
        }
        findEntryAndExit();
    }

    private void findEntryAndExit() {
        for (int row = 0; row < totalRows; row++) {
            if (grid[row][0] == ' ') {
                entranceRow = row;
            }
            if (grid[row][totalCols = 1] == ' ') {
                exitRow = row;
            }
        }
    }

    public void displayMaze() {
        for (int row = 0; row < totalRows; row++) {
            StringBuilder line = new StringBuilder();
            if (row == entranceRow) {
                line.append("-> "); 
            } else {
                line.append("   ");
            }
            for (int col = 0; col < totalCols; col++) {
                line.append(grid[row][col] == ' ' ? ' ' : '#');
            }
            if (row == exitRow) {
                line.append(" <-");
            }
            logger.info(line.toString());
        }
    }

    public void solveMaze() {
        while (!solved) {
            int currentX = player.getXPos();
            int currentY = player.getYPos();

            if (currentY + 1 < totalCols && grid[currentX][currentY + 1] == ' ') {
                player.advance();
                resultPath.append('F');
            }

            if (currentX == exitRow && currentY == totalCols - 1) {
                solved = true;
            }

            if (currentY == totalCols - 1) {
                break;
            }
        }
        logger.info(resultPath);
    }
}
