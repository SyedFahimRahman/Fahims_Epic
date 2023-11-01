package Epic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class LeaderboardGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel meanLabel;
    private JLabel medianLabel;
    private JLabel stdDeviationLabel;

    public LeaderboardGUI() {
        // Create the GUI components
        frame = new JFrame("Quiz Leaderboard");
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("Rank");
        tableModel.addColumn("Name");
        tableModel.addColumn("Score");

        meanLabel = new JLabel("Mean: ");
        medianLabel = new JLabel("Median: ");
        stdDeviationLabel = new JLabel("Std Deviation: ");

        // Set up the layout of the JFrame
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(meanLabel, BorderLayout.PAGE_START);
        frame.add(medianLabel, BorderLayout.PAGE_END);
        frame.add(stdDeviationLabel, BorderLayout.LINE_START);
        frame.setSize(800, 600);
        frame.setBackground(Color.blue);
        frame.setForeground(Color.cyan);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void updateLeaderboard(String name, int score) {
        // Update the leaderboard with a new entry
        tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, name, score});

        // Update mean, median, and standard deviation labels
        double[] scores = getScoresArray();
        double mean = calculateMean(scores);
        double median = calculateMedian(scores);
        double stdDeviation = calculateStdDeviation(scores);

        meanLabel.setText("Mean: " + mean);
        medianLabel.setText("Median: " + median);
        stdDeviationLabel.setText("Std Deviation: " + stdDeviation);
    }

    private double[] getScoresArray() {
        // Extract scores from the JTable and store them in an array
        int rowCount = tableModel.getRowCount();
        double[] scores = new double[rowCount];

        for (int i = 0; i < rowCount; i++) {
            scores[i] = Double.parseDouble(tableModel.getValueAt(i, 2).toString());
        }

        return scores;
    }

    private double calculateMean(double[] scores) {
        // Calculate the mean of the scores
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.length;
    }

    private double calculateMedian(double[] scores) {
        // Calculate the median of the scores
        Arrays.sort(scores);
        int n = scores.length;
        if (n % 2 == 0) {
            return (scores[n / 2 - 1] + scores[n / 2]) / 2.0;
        } else {
            return scores[n / 2];
        }
    }

    private double calculateStdDeviation(double[] scores) {
        // Calculate the standard deviation of the scores
        double mean = calculateMean(scores);
        double sum = 0;

        for (double score : scores) {
            sum += Math.pow(score - mean, 2);
        }

        return Math.sqrt(sum / scores.length);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create an instance of LeaderboardGUI
            new LeaderboardGUI();
        });
    }
}
