package timerPack;

//Necessary utilities
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerTask extends JFrame {

    //Label for displaying left time
    private JLabel timerLabel;

    //Timer object
    private Timer timerTimer;

    //Countable seconds
    private int timerSeconds = 0;

    //Checks if timer is running
    private boolean isRunning = false;

    public TimerTask() {
        //Title of display
        setTitle("Timer");

        //Close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Size of display
        setSize(300, 150);

        //Layout
        setLayout(new FlowLayout());

        //Label for displaying left time
        timerLabel = new JLabel("Timer: 0 seconds left");

        //Start/stop button
        JButton startStopButton = new JButton("Start / Stop");

        //Reset button
        JButton resetButton = new JButton("Reset");

        //Toggle for start/stop button
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks if isRunning == False
                if (!isRunning) {
                    //Starts timer
                    startTimer();
                    //Sets isRunning as True
                    isRunning = true;

                    //If isRunning == True
                } else {
                    //Stops timer
                    stopTimer();
                    //Sets isRunning as False
                    isRunning = false;
                }
            }
        });

        //Reset button function
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Resets timer to 0
                resetTimer();
                //Sets isRunning as False
                isRunning = false;
            }
        });

        //Adds UI elements
        add(timerLabel);
        add(startStopButton);
        add(resetButton);

        timerTimer = new Timer(1000, new ActionListener() {
            @Override
            //While delay != 0 action below performs
            public void actionPerformed(ActionEvent e) {
                //While seconds on timer bigger than 0
                if (timerSeconds > 0) {
                    //Decrement
                    timerSeconds--;
                    //After decrementation need to set new value on label
                    timerLabel.setText("Timer: " + timerSeconds + " seconds left");
                    //If seconds on timer == 0
                } else {
                    //Stop timer
                    stopTimer();
                }
            }
        });
    }

    //Function that starts timer count
    private void startTimer() {
        //Ask to input time of running of timer
        String input = JOptionPane.showInputDialog("Enter running time for timer (in seconds):");
        //try/catch surround
        try {
            //Sets new value of timerSecond as input variable converted to integer
            timerSeconds = Integer.parseInt(input);
            //Sets new label
            timerLabel.setText("Timer: " + timerSeconds + " seconds left");
            //Starts timer
            timerTimer.start();
        } catch (NumberFormatException e) {
            //Throws exception
            JOptionPane.showMessageDialog(null, "Unrecognizable input format. Please enter a number.");
        }
    }

    //Stops timer
    private void stopTimer() {
        timerTimer.stop();
        //Notification if time is out
        JOptionPane.showMessageDialog(null, "Time is out!");
    }

    //Resets timer
    private void resetTimer() {
        timerSeconds = 0;
        timerLabel.setText("Timer: 0 seconds left");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new timerPack.TimerTask().setVisible(true);
            }
        });
    }
}
