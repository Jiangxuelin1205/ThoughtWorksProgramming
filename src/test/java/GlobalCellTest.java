import org.junit.Assert;
import org.junit.Test;

public class GlobalCellTest {

    @Test
    public void global_test() {
        int[][] currentState = new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}
        };

        Cells cells = new Cells(currentState);
        cells.nextState();

        int[][] expectedState = new int[][]{
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
        };
        Cells actual = new Cells(expectedState);
        for (int row = 0; row < expectedState.length; row++) {
            for (int column = 0; column < expectedState[0].length; column++) {
                Assert.assertEquals(cells.singleState(row, column), actual.singleState(row, column));
            }
        }
    }

    @Test
    public void global_test2() {
        int[][] currentState = new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 1, 0, 1}
        };

        Cells cells = new Cells(currentState);
        cells.nextState();

        int[][] expectedState = new int[][]{
                {0, 1, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 1, 0}
        };
        Cells actual = new Cells(expectedState);
        for (int row = 0; row < expectedState.length; row++) {
            for (int column = 0; column < expectedState[0].length; column++) {
                Assert.assertEquals(cells.singleState(row, column), actual.singleState(row, column));
            }
        }
    }
}
