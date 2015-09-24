package school;

class ThreadLearning implements Help, Runnable {

	Thread thr;
	public ThreadLearning() {
		
		thr = new Thread(this, "TL Thread");
		System.out.println("Thread TL started");
		thr.start();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			for(int i=1; i<6;i++){
				System.out.println("Значение: " + i);
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e){
			System.out.println("Thread stopped.");
		}
		System.out.println("THread TL ended.");
		
	}


	
	@Override
	public void help() {
		// TODO Auto-generated method stub

	}

	}
