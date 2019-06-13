package Backend;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Cells {

    private int[][] currentState;

    public static final int LIVING_STATE = 1;
    private static final int DEAD_STATE = 0;

    private int rowCount;
    private int columnCount;

    private static final int WAKE_UP_DEAD_CELL = 3;

    private static final int LIVE_ON_MIN = 2;
    private static final int LIVE_ON_MAX = 3;

    public Cells(int[][] currentState) {
        this.currentState = currentState;
        rowCount = currentState.length;
        columnCount = currentState[0].length;
    }

    /**
     * 从文件中读入初始状态
     */
    public Cells(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        String str = reader.readLine();

        rowCount = Integer.valueOf(str.split(" ")[0]);
        columnCount = Integer.valueOf(str.split(" ")[1]);

        this.currentState = new int[rowCount][columnCount];
        int row = 0;
        while ((str = reader.readLine()) != null) {
            String[] aRow = str.split(" ");
            for (int i = 0; i < aRow.length; i++) {
                currentState[row][i] = Integer.valueOf(aRow[i]);
            }
            row++;
        }
    }

    /**
     * 随机生成rowCount*columnCount的图片
     */
    public Cells(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        currentState = new int[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                currentState[row][column] = new Random().nextBoolean() ? 1 : 0;
            }
        }
    }

    public void nextState() {
        int[][] newState = new int[rowCount][columnCount];
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                newState[rowIndex][columnIndex] = cellNextState(rowIndex, columnIndex);
            }
        }
        currentState = newState;
    }

    /**
     * 单个细胞的更新状态
     */
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
                livingCellCount += cellCurrentState(rowIndex, columnIndex);
            }
        }
        livingCellCount = livingCellCount - currentState[row][column];
        return livingCellCount;
    }

    public int cellCurrentState(int row, int column) {
        if (outOfRowBound(row) || outOfColumnBound(column)) {
            return DEAD_STATE;
        }
        return currentState[row][column];
    }

    public int rowCount() {
        return rowCount;
    }

    public int columnCount() {
        return columnCount;
    }

    private boolean outOfColumnBound(int column) {
        return column >= columnCount || column < 0;
    }

    private boolean outOfRowBound(int row) {
        return row >= rowCount || row < 0;
    }
}
