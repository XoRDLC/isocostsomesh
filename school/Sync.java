package school;

class CallMe{
	synchronized void call(String msg){
		System.out.print("[" + msg);
		try{
			Thread.sleep(100);
		}
		catch(InterruptedException e){
			System.out.println("Interrupted thread: " + e);
		}
		System.out.print("]");
	}
}

public class Sync implements Runnable {
	
	String msg;
	CallMe target;
	Thread t;
	
	Sync(CallMe target1, String msg1){
		target = target1;
		msg = msg1;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		target.call(msg);
	}
}

/*
for(int i =1; i<100; i++)   {
    CallMe target = new CallMe();
    Sync s1 = new Sync(target, "Good");
    Sync s2 = new Sync(target, "news");
    Sync s3 = new Sync(target, "for");
    Sync s4 = new Sync(target, "everyone!");
    
    try{
        s1.t.join();
        s2.t.join();
        s3.t.join();
        s4.t.join();
    }
    catch(InterruptedException e){
        System.out.println("Interrupted: " + e);
    }
    System.out.println();
}
*/