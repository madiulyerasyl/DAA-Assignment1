package daa;

public class Metrics {
    public long comparisons = 0;
    public int maxDepth = 0;
    public long timeNano = 0;

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
        timeNano = 0;
    }

    public void updateDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public double getTimeMs() {
        return timeNano / 1_000_000.0;
    }
}