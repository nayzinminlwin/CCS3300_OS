package Lab4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Process implements Comparable<Process> {

    private char processName;
    private int arrivalTime;
    private int burstTime;
    private int unitsLeft;
    private int waitingTime;
    public int finishTime;
    private int turnAroundTime;
    private boolean isFinished;

    public Process(char pName, int aT, int bT) {
        this.processName = pName;
        this.arrivalTime = aT;
        this.burstTime = bT;
        this.unitsLeft = bT;
        this.isFinished = false;
    }

    public Process(int bT) {
        this.processName = '-';
        this.arrivalTime = 0;
        this.burstTime = bT;
        this.unitsLeft = bT;
        this.isFinished = false;
    }

    @Override
    public int compareTo(Process anotherProcess) {
        return this.arrivalTime - anotherProcess.arrivalTime;
    }

    public int RunProcess(int quantum, int currentIndex) {

        // if process is not arrived yet
        if (currentIndex < this.arrivalTime) {
            System.out.println("Process " + this.processName + " has not arrived yet.");
            return 0;
        }

        int runUnits = Math.min(this.unitsLeft, quantum);
        if (this.unitsLeft <= quantum) {
            this.unitsLeft = 0;
            markFinished(currentIndex + runUnits);
        } else {
            this.unitsLeft = this.unitsLeft - runUnits;
        }

        System.out.println("Process " + this.processName + " is running for " + runUnits + " units.");
        return runUnits;
    }

    public void markFinished(int finishTime) {
        this.isFinished = true;
        setFinishTime(finishTime);
        calculateTurnAroundTime();
        calculateWaitingTime();
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public char getProcessName() {
        return this.processName;
    }

    public int getBurstTime() {
        return this.burstTime;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
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

    public static void printProcesses(List<Process> processes) {

        System.out.println();

        // Printing the results
        String[] headers = { "Process", "Arrival", "Burst", "Waiting", "TT" };

        for (String header : headers) {
            System.out.print(header + "\t");
        }
        System.out.println();

        double totalWaitTime = 0;
        for (Process p : processes) {
            System.out.println(p.toString());
            totalWaitTime += p.calculateWaitingTime();
        }

        System.out.println("Average waiting time : " + totalWaitTime / processes.size());

    }

    // already implement compareTo in Process class, now sort them in arrival time

    public static List<Process> sortProcesses(List<Process> processes) {
        processes.sort(Process::compareTo);
        return processes;
    }

    // enqueue sorted processes into the queue
    public static Queue<Process> enqueueProcesses(List<Process> processes) {
        Queue<Process> processQ = new LinkedList<>();
        processes = sortProcesses(processes);
        for (Process p : processes) {
            processQ.add(p);
        }
        return processQ;
    }

}
