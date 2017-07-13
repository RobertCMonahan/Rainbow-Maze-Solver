
class Timer {
    private long startTime;

    void start() {
        this.startTime = System.nanoTime();
    }

    double stopAndReturnTime() {
        long elapsedTime = System.nanoTime() - this.startTime;
        double elapsedTimeInSeconds = (double) elapsedTime / 1000000000.0;
        System.out.println(elapsedTimeInSeconds + " seconds");
        return elapsedTimeInSeconds;
    }
}
