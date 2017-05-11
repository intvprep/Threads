import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class DeadLockForThreadDump {
	private static MyQueue producerQueue = new MyQueue(10);
	private static MyQueue consumerQueue = new MyQueue(10);
	private static String[] arr = {"ramdas","sawant","amita","gunjal"};

	public static void main(String[] args) {
		Thread t1 = new Thread(new Producer(producerQueue,consumerQueue));
		
		Thread t2 = new Thread(new Consumer(producerQueue,consumerQueue));
		
		t1.start();
		
		t2.start();
		
	}
	
	private static class Producer implements Runnable{
		
		MyQueue queue1;
		MyQueue queue2;
		Random random = new Random();
		public Producer(MyQueue queue1,MyQueue queue2) {
			this.queue1 = queue1;
			this.queue2 = queue2;
		}
		@Override
		public void run() {
			while(true){
				queue2.offer("Ramdas");
				
				queue1.poll();
				String producing = arr[random.nextInt(arr.length)];
				System.out.println("Producing: "+producing);
			}
		}
		
	}
	
	private static class Consumer implements Runnable{
		
		MyQueue queue1;
		MyQueue queue2;

		public Consumer(MyQueue queue1,MyQueue queue2) {
			this.queue1 = queue1;
			this.queue2 = queue2;
		}
		@Override
		public void run() {
			while(true){
					
					String string = queue1.poll();
					System.out.println("Consuming "+string);
					queue2.offer(string);

			}
		}
	}
	
	private static class MyQueue{
		Queue<String> queue;
		
		int size = 0;
		int capacity;
		
		public MyQueue(int capacity) {
			queue = new LinkedList<>();
			this.capacity = capacity;
		}
		
		public synchronized boolean offer(String str){
			simulateLongWork();
			if(!isFull()) {
				size++;
				queue.offer(str);
				return true;
			}
			return false;
		}
		
		public synchronized String poll(){
			simulateLongWork();
			if(!isEmpty()){
				
				size--;
				return queue.poll();
			}
			return null;
			
		}
		
		public boolean isEmpty(){
			if(size == 0) return true;
			return false;
		}
		
		public boolean isFull(){
			if(size == capacity) return true;
			return false;
		}
		
		public int size(){
			return size;
		}
		
		public void simulateLongWork(){
			Random random = new Random();
			//int[] a = new int[Integer.MAX_VALUE];
			for(int i=0;i<Integer.MAX_VALUE;i++){
				random.nextInt(100000);
			}
		}
	}
	
}
