package FrontEnd;

import Backend.Cells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CellsFrame extends JFrame {

    private JButton selectFileButton = new JButton("选择文件");
    private JButton startButton = new JButton("开始游戏");
    private JButton durationPromtLabel = new JButton("动画间隔设置(ms为单位)");
    private JTextField durationTextField = new JTextField();

    private GridPanel gridPanel = new GridPanel();
    private JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

    private int threadState = 0;
    private boolean isStart = false;
    private static final int THREAD_STATE_DEAD=0;
    private static final int THREAD_STATE_RUNNING=1;
    private static final int THREAD_STATE_PAUSE=2;

    private Thread thread;
    private Cells cells;
    private int duration=DEFAULT_DURATION;

    private static final int DEFAULT_DURATION = 200;


    public CellsFrame() throws HeadlessException {
        setTitle("生命游戏");

        selectFileButton.addActionListener(new OpenFileListener());
        startButton.addActionListener(new StartOrStopGameListener());
        durationPromtLabel.addActionListener(new TimeSetter());

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

    private class StartOrStopGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (thread == null) {
                thread = new Thread(new gameTask());
                thread.start();
                threadState = THREAD_STATE_RUNNING;
            }
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

        }
    }

    private class TimeSetter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            duration = Integer.parseInt(durationTextField.getText().trim());
        }
    }
}
