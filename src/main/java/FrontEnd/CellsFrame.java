package FrontEnd;

import Backend.Cells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CellsFrame extends JFrame {

    private JButton startButton = new JButton("开始游戏");
    private JTextField durationTextField = new JTextField();

    private GridPanel gridPanel = new GridPanel();


    private boolean isStart = false;
    private static final int THREAD_STATE_DEAD = 0;
    private static final int THREAD_STATE_RUNNING = 1;
    private static final int THREAD_STATE_PAUSE = 2;
    private static final int DEFAULT_DURATION = 200;

    //游戏状态
    private Thread updateCells;
    private Cells cells;
    private int duration = DEFAULT_DURATION;
    private int threadState = THREAD_STATE_DEAD;

    public CellsFrame() throws HeadlessException {
        setTitle("生命游戏");
        JButton selectFileButton = new JButton("选择文件");
        selectFileButton.addActionListener(new ReadFromFile());
        startButton.addActionListener(new StartOrStopGame());
        JButton randomButton = new JButton("随机生成");
        randomButton.addActionListener(new RandomCreateCells());
        JButton durationSetter = new JButton("间隔(ms)");
        durationSetter.addActionListener(new TimeSetter());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        buttonPanel.add(selectFileButton);
        buttonPanel.add(startButton);
        buttonPanel.add(randomButton);
        buttonPanel.add(durationSetter);
        buttonPanel.add(durationTextField);

        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().add("North", buttonPanel);
        getContentPane().add(gridPanel);
    }

    /**
     * 随机生成细胞初始状态
     */
    private class RandomCreateCells implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            cells = new Cells(50, 50);
            startButton.setText("开始游戏");
            isStart = false;
            threadState = THREAD_STATE_DEAD;
            gridPanel.createBoard(cells);
            add("Center", gridPanel);
            gridPanel.updateUI();
        }
    }

    /**
     * 从文件中读取细胞初始状态
     * 初始状态包括行，列和每个细胞的状态
     * 文件格式形如：
     * 3 6
     * 0 0 0 0 0 0
     * 0 1 0 0 0 0
     * 1 0 0 1 0 1
     */
    private class ReadFromFile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent lister) {
            JFileChooser chooser = new JFileChooser(".");
            chooser.setDialogTitle("请选择初始配置文件");
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                startButton.setText("开始游戏");

                //点击按钮，重置游戏状态
                isStart = false;
                threadState = THREAD_STATE_DEAD;

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


    private class StartOrStopGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (updateCells == null) {
                updateCells = new Thread(new gameTask());
                updateCells.start();
                isStart = true;
                threadState = THREAD_STATE_RUNNING;
                startButton.setText("暂停游戏");
            } else {
                if (isStart) {
                    startButton.setText("开始游戏");
                    isStart = false;
                    threadState = THREAD_STATE_PAUSE;
                } else {
                    startButton.setText("暂停游戏");
                    isStart = true;
                    threadState = THREAD_STATE_RUNNING;
                }
            }
        }
    }


    private class gameTask implements Runnable {

        @Override
        public void run() {
            //die
            while (threadState != THREAD_STATE_DEAD) {
                if (threadState == THREAD_STATE_PAUSE) {//pause
                    while (threadState == THREAD_STATE_PAUSE) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                cells.nextState();
                gridPanel.display(cells);

                try {
                    TimeUnit.MILLISECONDS.sleep(duration);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            updateCells = null;
        }
    }

    private class TimeSetter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                duration = Integer.parseInt(durationTextField.getText().trim());

            } catch (NumberFormatException exception) {
                duration = DEFAULT_DURATION;
            }
        }
    }
}
