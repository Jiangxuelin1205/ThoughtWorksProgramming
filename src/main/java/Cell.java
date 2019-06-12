class Cell {

    private int state;

    private int row;
    private int column;

    private static final int LIVING_STATE = 1;
    private static final int DIE_STATE = 0;

    private static final int WAKE_UP_DEAD_CELL = 3;

    private static final int LIVE_ON_MIN = 2;
    private static final int LIVE_ON_MAX = 3;

    Cell(int state, int row, int column) {
        this.state = state;
        this.row = row;
        this.column = column;
    }

    int nextState(int rowCount, int columnCount, Cells cells) {
        int livingCellCount = livingCellCount(rowCount, columnCount, cells);
        switch (state) {
            case LIVING_STATE:
                return livingCellNextState(livingCellCount);
            case DIE_STATE:
                return dieCellNextState(livingCellCount);
        }
        return -1;
    }

    private int dieCellNextState(int livingCellCount) {
        return (livingCellCount == WAKE_UP_DEAD_CELL) ? LIVING_STATE : DIE_STATE;
    }

    private int livingCellNextState(int livingCellCount) {
        return (livingCellCount >= LIVE_ON_MIN && livingCellCount <= LIVE_ON_MAX) ? LIVING_STATE : DIE_STATE;
    }

    private int livingCellCount(int rowCount, int columnCount, Cells cells) {

        int livingCellCount = 0;

        int rowStart = lowerBound(row);
        int rowEnd = upperBound(row, rowCount);
        int columnStart = lowerBound(column);
        int columnEnd = upperBound(column, columnCount);

        for (int rowIndex = rowStart; rowIndex <= rowEnd; rowIndex++) {
            for (int columnIndex = columnStart; columnIndex <= columnEnd; columnIndex++) {
                livingCellCount += cells.singleState(rowIndex, columnIndex);
            }
        }
        return livingCellCount - cells.singleState(row, column);
    }

    private int upperBound(int j, int bound) {
        return j + 1 > bound - 1 ? bound - 1 : j + 1;//todo:变量命名
    }

    private int lowerBound(int i) {
        return i - 1 < 0 ? 0 : i - 1;//todo:变量命名
    }

    int state() {
        return this.state;
    }


    void setState(int state) {
        this.state = state;
    }
}
