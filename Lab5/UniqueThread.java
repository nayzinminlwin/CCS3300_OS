package Lab5;

public class UniqueThread extends Thread {
    private static int counter = 0;
    private int id;

    public UniqueThread() {
        this.id = ++counter;
        this.setName("Thread-" + id);
        System.out.println("Thread " + id + " is created");
    }

    @Override
    public void run() {
        // no work for now
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            UniqueThread t = new UniqueThread();
            t.start();
        }
    }
}
