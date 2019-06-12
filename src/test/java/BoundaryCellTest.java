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
        //when
        Cells cells = new Cells(currentState);
        //then
        Assert.assertEquals(cells.nextState(0, 0), 0);
    }

    @Test
    public void living_cell_with_more_than_three_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 1, 1}
        };
        //when
        Cells cells = new Cells(currentState);
        //then
        Assert.assertEquals(cells.nextState(0, 2), 0);
    }

    @Test
    public void living_cell_with_two_neighbours_right_bottom_will_live_on() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 0},
                {0, 0, 1, 1}
        };
        //when
        Cells cells = new Cells(currentState);
        //then
        Assert.assertEquals(cells.nextState(2, 3), 1);
    }

    @Test
    public void living_cell_with_three_neighbours_will_live_on() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 1}
        };
        //when
        Cells cells = new Cells(currentState);
        //then
        Assert.assertEquals(cells.nextState(1, 1), 1);
    }

    @Test
    public void died_cell_with_three_neighbours_will_live_on() {
        //given
        int[][] currentState = new int[][]{
                {0, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 0, 0, 1}
        };
        //when
        Cells cells = new Cells(currentState);
        //then
        Assert.assertEquals(cells.nextState(0, 0), 1);
    }

    @Test
    public void died_cell_with_more_than_three_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 0, 1},
                {1, 1, 1, 0},
                {0, 0, 0, 1}
        };
        //when
        Cells cells = new Cells(currentState);
        //then
        Assert.assertEquals(cells.nextState(0, 2), 0);
    }

    @Test
    public void died_cell_with_fewer_than_three_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {1, 1, 0, 1},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
        };
        //when
        Cells cells = new Cells(currentState);
        //then
        Assert.assertEquals(cells.nextState(2, 3), 0);
    }
}
