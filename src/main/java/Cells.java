import java.io.*;
import java.util.Random;

class Cells {

    private int[][] currentState;

    private static final int LIVING_STATE = 1;
    private static final int DEAD_STATE = 0;

    private static int rowCount = 0;
    private static int columnCount = 0;

    private static final int WAKE_UP_DEAD_CELL = 3;

    private static final int LIVE_ON_MIN = 2;
    private static final int LIVE_ON_MAX = 3;

    Cells(int[][] currentState) {
        this.currentState = currentState;
        rowCount = currentState.length;
        columnCount = currentState[0].length;
    }

    Cells(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        String str = reader.readLine();
        rowCount = Integer.valueOf(str.split(" ")[0]);
        columnCount = Integer.valueOf(str.split(" ")[1]);
        currentState = new int[rowCount][columnCount];
        int row = 0;
        while ((str = reader.readLine()) != null) {
            String[] aRow = str.split(" ");
            for (int i = 0; i < aRow.length; i++) {
                currentState[row][i] = Integer.valueOf(aRow[i]);
            }
            row++;
        }
    }

    Cells(int rowCount, int columnCount) {
        currentState = new int[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                currentState[row][column] = new Random().nextBoolean() ? 1 : 0;
            }
        }
    }

    void nextState() {
        int[][] newState = new int[rowCount][columnCount];
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                newState[rowIndex][columnIndex] = cellNextState(rowIndex, columnIndex);
            }
        }
        currentState = newState;
    }

    int[][] currentState() {
        return currentState;
    }

    private int cellNextState(int row, int column) {

        int livingCellCount = livingCellCount(row, column);
        switch (currentState[row][column]) {
            case LIVING_STATE:
                return livingCellNextState(livingCellCount);
            case DEAD_STATE:
                return dieCellNextState(livingCellCount);
        }
        throw new IllegalStateException();
    }

    private int dieCellNextState(int livingCellCount) {
        return (livingCellCount == WAKE_UP_DEAD_CELL) ? LIVING_STATE : DEAD_STATE;
    }

    private int livingCellNextState(int livingCellCount) {
        return (livingCellCount >= LIVE_ON_MIN && livingCellCount <= LIVE_ON_MAX) ? LIVING_STATE : DEAD_STATE;
    }

    private int livingCellCount(int row, int column) {

        int livingCellCount = 0;
        for (int rowIndex = row - 1; rowIndex <= row + 1; rowIndex++) {
            for (int columnIndex = column - 1; columnIndex <= column + 1; columnIndex++) {
                livingCellCount += cellState(rowIndex, columnIndex);
            }
        }
        livingCellCount = livingCellCount - currentState[row][column];
        return livingCellCount;
    }

    int cellState(int row, int column) {
        if (outOfRowBound(row) || outOfColumnBound(column)) {
            return DEAD_STATE;
        }
        return currentState[row][column];
    }

    private boolean outOfColumnBound(int column) {
        return column >= columnCount || column < 0;
    }

    private boolean outOfRowBound(int row) {
        return row >= rowCount || row < 0;
    }
}
