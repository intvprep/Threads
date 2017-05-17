import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		
		Thread t1 = new Thread(new Task(latch, "Thread1",1000));
		t1.setDaemon(true);
		t1.start();
		Thread t2 = new Thread(new Task(latch, "Thread2",5000));
		t2.setDaemon(true);
		t2.start();
		t1.join(1000);
		t2.join();
		
		//latch.await();
		
		
	}
	
	
	private static class Task implements Runnable{
		CountDownLatch latch;
		String name;
		int wait;
		public Task(CountDownLatch latch,String name, int wait) {
			this.latch = latch;
			this.name = name;
			this.wait = wait;
		}
		@Override
		public void run() {
			System.out.println("Minding my own business: "+name);
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+" done !!");
			latch.countDown();
		}
	}
}
