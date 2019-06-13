package FrontEnd;

import Backend.Cells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CellsFrame extends JFrame {

    private JButton selectFileButton = new JButton("选择文件");
    private JButton startButton = new JButton("开始游戏");
    private JButton durationPromtLabel = new JButton("动画间隔设置(ms为单位)");
    private JTextField durationTextField = new JTextField();

    //private JPanel gridPanel = new JPanel();
    private GridPanel gridPanel=new GridPanel();
    private JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
    private JTextField[][] board;

    private boolean stop = false;
    private boolean isStart = false;

    private Cells cells;

    private static final int DEFAULT_DURATION = 200;


    public CellsFrame() throws HeadlessException {
        setTitle("生命游戏");

        selectFileButton.addActionListener(new OpenFileListener());
        startButton.addActionListener(new StartOrStopGameListener());

        buttonPanel.add(selectFileButton);
        buttonPanel.add(startButton);
        buttonPanel.add(durationPromtLabel);
        buttonPanel.add(durationTextField);

        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().add("North", buttonPanel);
        getContentPane().add(gridPanel);
    }

    private class OpenFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent lister) {
            JFileChooser chooser = new JFileChooser(".");
            chooser.setDialogTitle("请选择初始配置文件");
            //choose file
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {

                startButton.setText("开始游戏");
                String filePath = chooser.getSelectedFile().getPath();
                try {
                    cells = new Cells(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gridPanel.createBoard(cells);

                add("Center", gridPanel);
                gridPanel.updateUI();

            }
        }
    }

    private class StartOrStopGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
