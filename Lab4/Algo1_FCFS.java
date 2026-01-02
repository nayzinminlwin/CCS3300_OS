package Lab4;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Algo1_FCFS {
    String[] headers = { "Process", "Arrival", "Burst", "Waiting", "TT" };
    int currentIndex = 0;
    double totalWaitTime = 0;

    List<Process> processes = new ArrayList<>(List.of(
            new Process('A', 0, 6),
            new Process('B', 2, 4),
            new Process('C', 5, 1),
            new Process('D', 6, 3),
            new Process('E', 9, 5)));

    Queue<Process> processQ = new LinkedList<>();

    // already implement compareTo in Process class, now sort them in arrival time

    public void sortProcesses(List<Process> processes) {
        processes.sort(Process::compareTo);
    }

    // enqueue sorted processes into the queue
    public void enqueueProcesses() {
        sortProcesses(processes);
        for (Process p : processes) {
            processQ.add(p);
        }
    }

    public void main(String[] args) {
        Algo1_FCFS fcfs = new Algo1_FCFS();
        fcfs.sortProcesses(fcfs.processes);
        fcfs.enqueueProcesses();

        for (Process p : fcfs.processes) {
            // System.out.println(fcfs.processQ.poll());
            currentIndex += p.getBurstTime();
            p.setFinishTime(currentIndex);
            p.calculateTurnAroundTime();
            totalWaitTime += p.calculateWaitingTime();
        }

        for (String header : fcfs.headers) {
            System.out.print(header + "\t");
        }
        System.out.println();
        for (Process p : fcfs.processes) {
            System.out.println(p.toString());
        }

        System.out.println("Average waiting time : " + totalWaitTime / processes.size());
    }
}