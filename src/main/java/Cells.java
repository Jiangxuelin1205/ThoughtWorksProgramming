class Cells {

    private int[][] currentState;

    private static final int LIVING_STATE = 1;
    private static final int DIE_STATE = 0;

    private int rowCount;
    private int columnCount;

    Cells(int[][] currentState) {//todo:currentState
        this.currentState = currentState;
        rowCount = currentState.length;
        columnCount = currentState[0].length;
    }

    int nextState(int row, int column) {

        int livingCellCount = livingCellCount(row, column);
        return getNextState(row, column, livingCellCount);//todo
    }

    private int getNextState(int row, int column, int livingCellCount) {

        switch (currentState[row][column]) {//todo:currentState
            case LIVING_STATE:
                return livingCellNextState(livingCellCount);
            case DIE_STATE:
                return dieCellNextState(livingCellCount);
        }
        return -1;//todo:传入数组的合理性
    }

    private int dieCellNextState(int livingCellCount) {
        return (livingCellCount == 3) ? 1 : 0;
    }

    private int livingCellNextState(int livingCellCount) {
        return (livingCellCount > 3) || (livingCellCount <= 1) ? 0 : 1;
    }

    private int livingCellCount(int row, int column) {
        int livingCellCount = 0;

        int rowStart = row - 1 < 0 ? 0 : row - 1;
        int rowEnd = row + 1 > rowCount - 1 ? rowCount - 1 : row + 1;
        int columnStart = column - 1 < 0 ? 0 : column - 1;
        int columnEnd = column + 1 > columnCount - 1 ? columnCount - 1 : column + 1;

        for (int rowIndex = rowStart; rowIndex <= rowEnd; rowIndex++) {
            for (int columnIndex = columnStart; columnIndex <= columnEnd; columnIndex++) {
                livingCellCount += currentState[rowIndex][columnIndex];
            }
        }
        livingCellCount = livingCellCount - currentState[row][column];
        return livingCellCount;
    }
}
