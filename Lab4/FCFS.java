package Lab4;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

public class FCFS {
    public void main(String[] args) {
        int currentIndex = 0;

        // Sample processes
        List<Process> processes = new ArrayList<>(List.of(
                new Process('B', 2, 4),
                new Process('A', 0, 6),
                new Process('D', 6, 3),
                new Process('C', 5, 1),
                new Process('E', 9, 5)));

        // sort the queue based on arrival time and enqueue processes
        Queue<Process> processQ = Process.enqueueProcesses(processes);

        // FCFS Scheduling
        // if there are processes in the queue
        while (processQ.size() > 0) {

            // dequeue the first process
            Process p = processQ.poll();

            // run the process till it finish
            currentIndex += p.RunProcess(p.getBurstTime(), currentIndex);

        }

        // Print the results
        Process.printProcesses(processes);
    }
}