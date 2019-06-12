class Cells {

    private int[][] currentState;

    private static final int LIVING_STATE = 1;
    private static final int DIE_STATE = 0;

    Cells(int[][] currentState) {
        this.currentState = currentState;
    }

    int nextState(int row, int column) {

        int livingCellCount =livingCellCount(row, column);
        return getNextState(row, column, livingCellCount);
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
        //todo:边界问题
        int livingCellCount = 0;
        for (int rowIndex = row - 1; rowIndex <= row + 1; rowIndex++) {
            for (int columnIndex = column - 1; columnIndex <= column + 1; columnIndex++) {
                livingCellCount += currentState[rowIndex][columnIndex];
            }
        }
        livingCellCount = livingCellCount - currentState[row][column];
        return livingCellCount;
    }
}
