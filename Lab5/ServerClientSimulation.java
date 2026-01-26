package Lab5;

import java.util.concurrent.Semaphore;

public class ServerClientSimulation {

    // shared data structure
    static class SharedData {
        public int clientId = 0;
        public int pingCount = 0;
        public int nextClientId = 1; // whose turn (1 or 2)
    }

    // server thread object
    static class Server extends Thread {
        private final SharedData shared;
        private final Semaphore serverSem; // semaphore to signal server
        private final Semaphore[] clientSems; // semaphores to signal clients
        private final int totalPings; // total number of pings to process

        // server constructor
        public Server(SharedData shared, Semaphore serverSem, Semaphore[] clientSems, int totalPings) {
            this.shared = shared;
            this.serverSem = serverSem;
            this.clientSems = clientSems;
            this.totalPings = totalPings;
        }

        @Override
        public void run() {
            int pongCount = 0; // number of pongs sent

            // process pings
            while (pongCount < totalPings) {
                try {
                    // wait for a ping
                    serverSem.acquire();

                    // read shared data
                    int cid = shared.clientId;
                    int ping = shared.pingCount;

                    System.out.println("Server pong " + ping + " to client " + cid);
                    pongCount++;

                    // signal the corresponding client
                    clientSems[cid - 1].release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Simulation ends");
        }
    }

    // client thread object
    static class Client extends Thread {
        private final int id;
        private final SharedData shared;
        private final Semaphore serverSem; // semaphore to signal server
        private final Semaphore mySem; // semaphore to signal this client
        private final int pings;

        // client constructor
        public Client(int id, SharedData shared, Semaphore serverSem, Semaphore mySem, int pings) {
            this.id = id;
            this.shared = shared;
            this.serverSem = serverSem;
            this.mySem = mySem;
            this.pings = pings;
        }

        @Override
        public void run() {
            // send pings
            for (int i = 1; i <= pings; i++) {
                try {
                    // Wait for my turn
                    while (true) {
                        synchronized (shared) {
                            if (shared.nextClientId == id) {
                                shared.clientId = id;
                                shared.pingCount = i;
                                System.out.println("Client " + id + " ping " + i);
                                break;
                            }
                        }
                        Thread.sleep(1); // avoid busy wait
                    }
                    serverSem.release(); // signal server
                    mySem.acquire(); // wait for server response

                    // After pong, pass the turn
                    synchronized (shared) {
                        shared.nextClientId = (id == 1) ? 2 : 1;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // simulation parameters
        final int numClients = 2;
        final int pingsPerClient = 10;

        // initialize shared data and semaphores
        SharedData shared = new SharedData();
        Semaphore serverSem = new Semaphore(0);
        Semaphore[] clientSems = { new Semaphore(0), new Semaphore(0) };

        // create and start server and client threads
        Server server = new Server(shared, serverSem, clientSems, numClients * pingsPerClient);
        Client client1 = new Client(1, shared, serverSem, clientSems[0], pingsPerClient);
        Client client2 = new Client(2, shared, serverSem, clientSems[1], pingsPerClient);
        server.start();
        client1.start();
        client2.start();

        // wait for all threads to finish
        try {
            client1.join();
            client2.join();
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
