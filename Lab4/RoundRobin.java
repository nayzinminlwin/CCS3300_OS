package Lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        System.out.println("Round Robin Scheduling Algorithm");

        String ganntChart = "";
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        // input arrival times
        System.out.print("Enter arrival times : ");
        int[] arrivalTimes = new int[n];
        for (int i = 0; i < n; i++) {
            arrivalTimes[i] = sc.nextInt();
        }

        // Input burst times
        System.out.print("Burst time : ");
        int[] burstTimes = new int[n];
        for (int i = 0; i < n; i++) {
            burstTimes[i] = sc.nextInt();
        }

        // Input quantum time
        System.out.print("Enter quantum time: ");
        int quantum = sc.nextInt();

        sc.close();

        // Create processes
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            processes.add(new Process((char) (65 + i), arrivalTimes[i], burstTimes[i]));
        }

        // queue for processes
        Queue<Process> processQ;

        // sort the queue based on arrival time and enqueue processes
        processQ = Process.enqueueProcesses(processes);

        int currentIndex = 0;

        // if there are processes in the queue
        while (processQ.size() > 0) {

            // dequeue the first process
            Process currentProcess = processQ.poll();

            // run the process for quantum time
            int runUnits = currentProcess.RunProcess(quantum, currentIndex);

            if (runUnits > 0) {
                // append to gannt chart
                ganntChart += String.valueOf(currentProcess.getProcessName()).repeat(runUnits);

                // update current time
                currentIndex = currentIndex + runUnits;
            } else {
                // move time forward if no process is running
                currentIndex++;
            }

            // if process is not finished, enqueue it to the back of the queue
            if (!currentProcess.isFinished()) {
                processQ.add(currentProcess);
            } else {
                // print finish time
                System.out.println("Finish Time of Process " + currentProcess.getProcessName() + " : "
                        + currentProcess.finishTime);
            }
        }

        // print all processes info
        Process.printProcesses(processes);

        System.out.println("Gannt Chart: " + ganntChart);
    }

}
