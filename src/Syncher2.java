public class Syncher2 {
	final static int[] intArray = new int[2];

	private static void pause() {
		while (intArray[0] == 0) {
//			try {
//				intArray.wait();
//			} catch (InterruptedException ie) {
//				System.out.println(Thread.currentThread() + " interrupted.");
//			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread runner = new Thread() {
			public void run() {
				synchronized (intArray) {
					pause();
					System.out.println(intArray[0] + intArray[1]);
				}
			}
		};
		runner.start();
		Thread.sleep(3000);
		
		synchronized (intArray) {
			intArray[0] = intArray[1] = 10;
			//intArray.notify();
		}
	}
}