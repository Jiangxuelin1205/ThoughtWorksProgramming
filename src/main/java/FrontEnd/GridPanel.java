package FrontEnd;

import Backend.Cells;
import Util.CenteredDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GridPanel extends JPanel {

    private BufferedImage image;

    private static final Color LIVING_COLOR = Color.BLACK;
    private static final Color DEAD_COLOR = Color.WHITE;
    private static final Color BACKGROUND = Color.GRAY;

    public void display(Cells cells) {
        if (cells == null) {
            this.image = null;
        } else {
            this.image = createBoard(cells);
        }
        repaint();
    }

    BufferedImage createBoard(Cells cells) {
        image = new BufferedImage(cells.rowCount(), cells.columnCount(), BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < cells.rowCount(); row++) {
            for (int column = 0; column < cells.columnCount(); column++) {
                if (cells.cellCurrentState(row, column) == Cells.LIVING_STATE) {
                    image.setRGB(row, column, LIVING_COLOR.getRGB());
                } else {
                    image.setRGB(row, column, DEAD_COLOR.getRGB());
                }
            }
        }
        return image;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image == null) {
            return;
        }

        Rectangle displayArea = CenteredDisplay.getDisplayArea(
                new Dimension(image.getWidth(), image.getHeight()),
                new Dimension(getWidth(), getHeight())
        );
        //noinspection SuspiciousNameCombination
        g.drawImage(image, displayArea.x, displayArea.y, displayArea.width, displayArea.height, null);
        g.setColor(BACKGROUND);
        drawGrid(g, displayArea);
    }

    private void drawGrid(Graphics g, Rectangle displayArea) {
        assert image != null;

        for (int column = 0; column < image.getWidth(); column++) {
            drawVerticalLine(g, displayArea, displayArea.x + displayArea.width * column / image.getWidth());
        }
        drawVerticalLine(g, displayArea, displayArea.x + displayArea.width - 1);

        /* 绘制水平线*/
        for (int row = 0; row < image.getHeight(); row++) {
            drawHorizontalLine(g, displayArea, displayArea.y + displayArea.height * row / image.getHeight());
        }
        drawHorizontalLine(g, displayArea, displayArea.y + displayArea.height - 1);
    }

    private void drawHorizontalLine(Graphics g, Rectangle displayArea, int y) {
        g.drawLine(displayArea.x, y, displayArea.x + displayArea.width, y);
    }

    private void drawVerticalLine(Graphics g, Rectangle displayArea, int x) {
        g.drawLine(x, displayArea.y, x, displayArea.y + displayArea.height);
    }

}
