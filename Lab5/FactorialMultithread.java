package Lab5;

import java.util.Scanner;

public class FactorialMultithread {
    static class FactorialThread extends Thread {

        private final int number; // number to compute factorial for
        private final int[] results; // shared array to store results
        private final int index; // index in the results array

        // Thread constructor
        public FactorialThread(int number, int[] results, int index) {
            this.number = number;
            this.results = results;
            this.index = index;
        }

        // thread function
        @Override
        public void run() {
            results[index] = factorial(number);
        }

        // factorial computation
        private int factorial(int n) {
            int res = 1;
            for (int i = 2; i <= n; i++) {
                res *= i;
            }
            return res;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        int[] numbers = new int[5];
        // ask user for 5 numbers
        System.out.print("Enter 5 numbers [1-10]: ");
        for (int i = 0; i < 5; i++) {
            numbers[i] = sc.nextInt();
        }

        sc.close();

        // create result array and threads
        int[] results = new int[5];
        FactorialThread[] threads = new FactorialThread[5];

        // start threads
        for (int i = 0; i < 5; i++) {
            threads[i] = new FactorialThread(numbers[i], results, i);
            threads[i].start();
        }

        // join all threads to main ;
        // so that main waits for their completion
        for (int i = 0; i < 5; i++) {
            threads[i].join();
        }

        // after all threads complete,
        // print results
        System.out.print("The factorials for ");
        for (int n : numbers) {
            System.out.print(n + " ");
        }
        System.out.print("are ");
        for (int r : results) {
            System.out.print(r + " ");
        }
        System.out.println();
    }
}
