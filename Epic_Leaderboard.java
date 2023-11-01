package Epic;

import java.util.*;

public class Epic_Leaderboard {
    List<QuizResult> results = new ArrayList<>();

    static class QuizResult {
        String name;
        int score;

        public QuizResult(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    public void recordResult(String name, int score) {
        results.add(new QuizResult(name, score)); // Record the result
    }

    public void displayLeaderboard() {
        // Sort the results in descending order by score
        Collections.sort(results, (a, b) -> Integer.compare(b.score, a.score));

        // Display the leaderboard
        System.out.println("\nLeaderboard: ");
        for (int i = 0; i < results.size(); i++) {
            QuizResult result = results.get(i);
            System.out.println((i + 1) + ". " + result.name + " - Score: " + result.score);
        }

        // Calculate and display mean, median, and standard deviation
        int[] scores = results.stream().mapToInt(result -> result.score).toArray();
        double mean = calculateMean(scores);
        int median = calculateMedian(scores);
        double stdDev = calculateStandardDeviation(scores);

        System.out.println("Mean score: " + mean);
        System.out.println("Median score: " + median);
        System.out.println("Standard Deviation: " + stdDev);
    }

    // Existing code for calculateMean, calculateMedian, and calculateStandardDeviation...

    // Calculate the mean of an array of scores
    public static double calculateMean(int[] scores) {
        return Arrays.stream(scores).average().orElse(0.0);
    }

    // Calculate the median of an array of scores
    public static int calculateMedian(int[] scores) {
        Arrays.sort(scores);
        int n = scores.length;
        if (n % 2 == 0) {
            return (scores[n / 2 - 1] + scores[n / 2]) / 2;
        } else {
            return scores[n / 2];
        }
    }

    // Calculate the standard deviation of an array of scores
    public static double calculateStandardDeviation(int[] scores) {
        double mean = calculateMean(scores);
        double sumOfSquaredDifferences = 0.0;

        for (int score : scores) {
            double diff = score - mean;
            sumOfSquaredDifferences += diff * diff;
        }

        double variance = sumOfSquaredDifferences / scores.length;
        return Math.sqrt(variance);
    }
}
