import org.junit.Assert;
import org.junit.Test;

public class BoundaryCellTest {

    @Test
    public void living_cell_with_fewer_than_two_neighbours_left_top_will_die() {
        //given
        int[][] currentState = new int[][]{
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(0, 0), 0);
    }

    @Test
    public void living_cell_with_more_than_three_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 1, 1}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(0, 1), 0);
    }

    @Test
    public void living_cell_with_two_neighbours_right_bottom_will_live_on() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 0},
                {0, 0, 1, 1}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(2, 3), 1);
    }

    @Test
    public void living_cell_with_three_neighbours_will_live_on() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 1}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(1, 1), 1);
    }

    @Test
    public void died_cell_with_three_neighbours_will_live_on() {
        //given
        int[][] currentState = new int[][]{
                {0, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 1}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(1, 2), 0);
    }

    @Test
    public void died_cell_with_more_than_three_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 0, 1},
                {1, 1, 1, 0},
                {0, 0, 0, 1}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(0, 1), 0);
    }

    @Test
    public void died_cell_with_fewer_than_three_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 0, 1},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(2, 2), 0);
    }
}
