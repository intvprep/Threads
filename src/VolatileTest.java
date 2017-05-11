public class VolatileTest extends Thread {
	
	// if keepRunning is not volatile, the other thread will not see the changed value in main thread
	volatile boolean keepRunning = true;
	//boolean keepRunning = true; 

	public static void main(String[] args) throws InterruptedException {
		VolatileTest t = new VolatileTest();
		t.start();
		Thread.sleep(1000);
		t.keepRunning = false;
		System.out.println(System.currentTimeMillis() + ": keepRunning is false");
	}

	public void run() {
		while (keepRunning) {

		}
	}
}