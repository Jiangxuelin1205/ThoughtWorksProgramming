class Cells {

    private Cell[][] state;

    private int rowCount;
    private int columnCount;

    Cells(int[][] state) {
        rowCount = state.length;
        columnCount = state[0].length;
        this.state = new Cell[rowCount][columnCount];
        parse(state);
    }

    private void parse(int[][] state) {
        for (int rowIndex = 0; rowIndex < state.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < state[0].length; columnIndex++) {
                this.state[rowIndex][columnIndex] = new Cell(state[rowIndex][columnIndex], rowIndex, columnIndex);
            }
        }
    }

   void nextState() {
        int[][] nextState = new int[rowCount][columnCount];
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                nextState[rowIndex][columnIndex]=this.state[rowIndex][columnIndex].nextState(rowCount,columnCount,this);
            }
        }
       for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
           for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
               this.state[rowIndex][columnIndex].setState(nextState[rowIndex][columnIndex]);
           }
       }
    }

    Cell[][] currentState(){
        return this.state;
    }

    int singleState(int row,int column){
        return this.state[row][column].state();
    }
}
