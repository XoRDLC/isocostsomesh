package school;

public class LThread implements Runnable{
	
	private String name;
	private Thread t;
	private int sleepTime = 250;
	
	LThread(String name){
		this.name =name;
		t = new Thread(this, name);
		System.out.println("Thread \"" + name + "\" started.");
		t.start();
	}
	
	LThread(String name, int sleepTime){
		this.name =name;
		this.sleepTime = sleepTime;
		t = new Thread(this, name);
		System.out.println("Thread \"" + name + "\" started.");
		t.start();
	}
	
	public void run() {
		try{
			for(int i =1; i<6; i++) {Thread.sleep(sleepTime);
			System.out.println("Thread \"" + name + "\" iteration: " + i);}
		}
		catch(InterruptedException e){	System.out.println("...interrupted..." + e);}
	}
	
	public void check() throws InterruptedException{
		t.join();
		System.out.println("Wait for thread \"" + name + "\"");
	}
	
	public boolean isAlive(){	return t.isAlive();}
	
	public String getName(){	return t.getName();}

}
//for main
/*
LThread tr1 = new LThread("One");
LThread tr2 = new LThread("Two", 50);
LThread tr3 = new LThread("States", 30);

LThread[] threadNames = {tr1, tr2, tr3};

for(LThread a: threadNames)
	{System.out.println("Thread \"" + a.getName() + "\" running: " + a.isAlive());}

try{
	for(LThread a: threadNames){a.check();}
	Thread.currentThread().sleep(500);
	System.out.println("Pause 1.");
	Thread.sleep(500);
	System.out.println("Pause 2.");
	Thread.currentThread().sleep(500);
	System.out.println("Main Thread ended.");
}
catch(InterruptedException e){System.out.println("Main thread interrupted." + e);}

System.out.println("Thread \"" + tr1.isAlive());
System.out.println("Thread \"" + tr2.isAlive());
*/