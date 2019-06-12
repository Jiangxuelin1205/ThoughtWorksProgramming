import org.junit.Assert;
import org.junit.Test;

public class SingleCellTest {

    @Test
    public void living_cell_with_fewer_than_two_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {0, 0, 1},
                {0, 1, 0},
                {0, 0, 0}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(1, 1), 0);


    }

   @Test
    public void living_cell_with_more_than_three_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {0, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };
       Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(1, 1), 0);
    }

    @Test
    public void living_cell_with_two_neighbours_will_live_on(){
        //given
        int[][] currentState = new int[][]{
                {0, 0, 1},
                {0, 1, 0},
                {1, 0, 0}
        };
        //when
        Cells cells = new Cells(currentState);
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(1, 1), 1);
    }

    @Test
    public void living_cell_with_three_neighbours_will_live_on() {
        //given
        int[][] currentState = new int[][]{
                {0, 0, 1},
                {0, 1, 0},
                {1, 0, 1}
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
                {0, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(1, 1), 1);
    }

    @Test
    public void died_cell_with_more_than_three_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {0, 0, 1},
                {0, 0, 0},
                {1, 1, 1}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(1, 1), 0);
    }

    @Test
    public void died_cell_with_fewer_than_three_neighbours_will_die() {
        //given
        int[][] currentState = new int[][]{
                {0, 0, 1},
                {0, 0, 0},
                {0, 0, 1}
        };
        Cells cells = new Cells(currentState);
        //when
        cells.nextState();
        //then
        Assert.assertEquals(cells.singleState(1, 1), 0);
    }
}
