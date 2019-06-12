import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class CellsFrame extends JFrame {

    private JButton selectFileButton = new JButton("选择文件");
    private JButton startOrStopButton = new JButton("开始游戏");
    private JButton durationPromtLabel = new JButton("动画间隔设置(ms为单位)");
    private JTextField durationTextField = new JTextField();

    private JPanel gridPanel = new JPanel();
    private JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
    private JTextField[][] board;

    private boolean stop = false;
    private boolean isStart = false;

    private Cells cells;

    private static final int DEFAULT_DURATION = 200;


    CellsFrame() throws HeadlessException {
        setTitle("生命游戏");

        selectFileButton.addActionListener(new OpenFileListener());
        startOrStopButton.addActionListener(new StartOrStopGameListener());

        buttonPanel.add(selectFileButton);
        buttonPanel.add(startOrStopButton);
        buttonPanel.add(durationPromtLabel);
        buttonPanel.add(durationTextField);
        gridPanel.setBackground(Color.WHITE);

        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().add("North", buttonPanel);
        getContentPane().add(gridPanel);
    }

    private class OpenFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fcDlg = new JFileChooser(".");
            fcDlg.setDialogTitle("请选择初始配置文件");
            int returnVal = fcDlg.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                isStart = false;
                stop = true;
                startOrStopButton.setText("开始游戏");

                String filepath = fcDlg.getSelectedFile().getPath();
                try {
                    cells = new Cells(filepath);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                initGridLayout();
                showBoard();
                gridPanel.updateUI();
            }
        }
    }

    private void showBoard() {
        int[][] currentState = cells.currentState();
        for (int row = 0; row < currentState.length; row++) {
            for (int column = 0; column < currentState[0].length; column++) {
                if (currentState[row][column] == 1) {
                    board[row][column].setBackground(Color.BLACK);
                } else {
                    board[row][column].setBackground(Color.WHITE);
                }
            }
        }
    }

    private void initGridLayout() {
        int rowCount = cells.row();
        int columnCount = cells.column();
        gridPanel.setLayout(new GridLayout(rowCount, columnCount));
        board = new JTextField[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                JTextField text = new JTextField();
                board[row][column] = text;
                gridPanel.add(text);
            }
        }
        add("Center", gridPanel);
    }

    private class StartOrStopGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
