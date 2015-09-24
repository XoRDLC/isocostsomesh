package school;

public class StartForAll {
	public static void main(String[] args) {
	    WaitNotify q = new WaitNotify();
	    new Producer(q);
	    new Consumer(q);
	}
}
