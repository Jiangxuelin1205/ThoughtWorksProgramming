import Backend.Cells;
import FrontEnd.GridPanel;
import org.junit.Test;

import javax.swing.*;

public class GridPanelTest {

    @Test
    public void displayGrid() {
        int[][] currentState = new int[][]{
                {1, 1, 0, 1},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
        };
        Cells cells = new Cells(currentState);
        JFrame window = new JFrame();
        window.setSize(500, 500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        GridPanel gridPanel=new GridPanel();
        window.add(gridPanel);

        gridPanel.display(cells);

    }
}
