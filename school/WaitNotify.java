package school;

public class WaitNotify{
    int n;
    boolean bValue = false;

    synchronized public int get(){
        while(!bValue){
            try{wait();}catch(InterruptedException e){}}
        System.out.println("Get " +n);
        bValue = false;
        notify();
        return n;
    }
    
    synchronized public void put(int n) {
        while (bValue) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.n = n;
        bValue = true;
        System.out.println("Put " + n);
        notify();

    }

}

class Consumer implements Runnable{
    WaitNotify q;
    Consumer(WaitNotify q){
        this.q = q;
        new Thread(this, "Consumer").start();
    }
    public void run(){
        while(true) q.get(); 
    }
}

class Producer implements Runnable{
    WaitNotify q;
    Producer(WaitNotify q){
        this.q = q;
        new Thread(this, "Producer").start();
   }
    public void run(){
        int i = 0;
        while(i<10) q.put(i++);
    }
}

//for main;
/*
WaitNotify q = new WaitNotify();
new Producer(q);
new Consumer(q);
*/