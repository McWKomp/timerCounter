package counterPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterTask extends JFrame {
    private JLabel stopwatchLabel;
    private Timer stopwatchTimer;
    private int stopwatchSeconds = 0;
    private boolean isRunning = false;

    public CounterTask() {
        setTitle("Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new FlowLayout());

        stopwatchLabel = new JLabel("Counter: 0 seconds");

        JButton startStopButton = new JButton("Start / Stop");
        JButton resetButton = new JButton("Reset");

        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    startStopwatch();
                    isRunning = true;
                } else {
                    stopStopwatch();
                    isRunning = false;
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetStopwatch();
                isRunning = false;
            }
        });

        add(stopwatchLabel);
        add(startStopButton);
        add(resetButton);

        stopwatchTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatchSeconds++;
                stopwatchLabel.setText("Counter: " + stopwatchSeconds + " seconds");
            }
        });

    }

    private void startStopwatch() {
        stopwatchTimer.start();
    }

    private void stopStopwatch() {
        stopwatchTimer.stop();
    }

    private void resetStopwatch() {
        stopwatchSeconds = 0;
        stopwatchLabel.setText("Counter: 0 seconds");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new counterPack.CounterTask().setVisible(true);
            }
        });
    }
}