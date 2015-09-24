package school;

public class HiLoPriority implements Runnable {

	long click = 0;
	Thread t;
	private volatile boolean running = true;
	
	HiLoPriority(int p){
		t = new Thread(this);
		t.setPriority(p);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running)
			click++;
	}
	
	public void stop(){
		running = false;
	}
	
	public void start(){
		t.start();
	}

}
//For main: 
/*
HiLoPriority t1 = new HiLoPriority(Thread.NORM_PRIORITY +2);
HiLoPriority t2 = new HiLoPriority(Thread.NORM_PRIORITY -2);
t1.start();
t2.start();

try{
	Thread.sleep(10000);
}
catch(InterruptedException e){
	System.out.println("Interrupted 1 " + e);
}
t1.stop();
t2.stop();

try{
	t1.t.join();
	t2.t.join();
}
catch(InterruptedException e){
	System.out.println("Interrupted 2 "+ e);
}

System.out.println("HIPR: " + t1.click);
System.out.println("LOPR: " + t2.click);
*/
