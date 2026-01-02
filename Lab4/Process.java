package Lab4;

public class Process implements Comparable<Process> {

    private char processName;
    private int arrivalTime;
    private int burstTime;
    private int waitingTime;
    private int finishTime;
    private int turnAroundTime;
    private String state;

    public Process(char pName, int aT, int bT) {
        this.processName = pName;
        this.arrivalTime = aT;
        this.burstTime = bT;
    }

    public Process(int bT) {
        this.processName = '-';
        this.arrivalTime = 0;
        this.burstTime = bT;
    }

    @Override
    public int compareTo(Process anotherProcess) {
        return this.arrivalTime - anotherProcess.arrivalTime;
    }

    public int getBurstTime() {
        return this.burstTime;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int calculateTurnAroundTime() {
        this.turnAroundTime = this.finishTime - this.arrivalTime;
        return this.turnAroundTime;
    }

    public int calculateWaitingTime() {
        this.waitingTime = this.turnAroundTime - this.burstTime;
        return this.waitingTime;
    }

    public String toString() {
        String rtnString = String.format("%s \t %d \t %d \t %d \t %d", processName, arrivalTime, burstTime, waitingTime,
                turnAroundTime);
        return rtnString;
    }

}
