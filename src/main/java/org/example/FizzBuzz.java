package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class FizzBuzz {
    final private int N;
    private BlockingQueue<String> queue;
    private int current;

    public FizzBuzz(int n){
        this.N = n;
        this.queue = new LinkedBlockingQueue<>();
        this.current = 1;
    }

    public synchronized void fizz() {
        while (current <= N) {
            if (current % 3 == 0 && current % 5 != 0) {
                queue.add("fizz");
                current++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void buzz() {
        while (current <= N) {
            if (current % 5 == 0 && current % 3 != 0) {
                queue.add("buzz");
                current++;
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public synchronized void fizzBuzz(){
        while(current <= N) {
            if (current % 5 == 0 && current % 3 == 0){
                queue.add("fizzbuzz");
                current++;
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void numberAndPrint(){
        while (current <= N){
            if (current % 3 != 0 && current % 5 != 0){
                queue.add(String.valueOf(current));
                current++;
                notifyAll();
            }else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

                System.out.println(queue.poll());

        }

    }



    public static void main(String[] args) {

        int n = 15;

        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadA = new Thread(fizzBuzz::fizz);
        Thread threadB = new Thread(fizzBuzz::buzz);
        Thread threadC = new Thread(fizzBuzz::fizzBuzz);
        Thread threadD = new Thread(fizzBuzz::numberAndPrint);

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();





    }
}
