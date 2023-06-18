package org.example;

public class TimeTracker {

    public static void main(String[] args) {
        Thread timeThread = new Thread(new timePrintRunnable());
        Thread messageThread = new Thread(new MassagePrintRunnable());
        timeThread.start();
        messageThread.start();

    }
}

    class timePrintRunnable implements Runnable {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                System.out.println("Витрачено часу: " + (elapsedTime/1000) + " c.");

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e ){
                    e.printStackTrace();
                }

            }
        }
    }

    class MassagePrintRunnable implements Runnable {
        @Override
        public void run() {
            while (true){
                System.out.println("Минуло 5 секунд");
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

