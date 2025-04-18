import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

class Clock extends JLabel implements Runnable {
    private Thread clockThread;

    public Clock() {
        setFont(new Font("Serif", Font.PLAIN, 40));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setPreferredSize(new Dimension(300, 100));
    }

    public void startClock() {
        clockThread = new Thread(this);
        clockThread.start();
    }

    @Override
    public void run() {
        while (true) {
            LocalTime currentTime = LocalTime.now();
            String timeString = currentTime.toString();  // Format: HH:mm:ss
            setText(timeString);
            try {
                Thread.sleep(1000);  // Update the time every 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class DigitalClockApp16 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Digital Clock");
        Clock clock = new Clock();

        frame.setLayout(new BorderLayout());
        frame.add(clock, BorderLayout.CENTER);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // Center the window
        frame.setVisible(true);

        clock.startClock();  // Start updating the clock
    }
}

