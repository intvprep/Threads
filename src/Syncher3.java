public class Syncher3 {

	static boolean flag = true;

	private static void pause() {
		while (flag) {
			System.out.println("Still can't see value of flag");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread runner = new Thread() {
			public void run() {
				pause();
				System.out.println(flag);
			}
		};
		runner.start();
		Thread.sleep(5000);
		flag = false;
	}
}