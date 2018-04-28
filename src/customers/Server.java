package customers;
import implementations.LinkedQueue;

public class Server {
private LinkedQueue<Customer> lineQueue;
	
	public Server(){
		lineQueue = new LinkedQueue<Customer>();
	}
	
	public boolean isServing(){
		return lineQueue.size()==1;
	}
	
	public void add(Customer client){
		lineQueue.enqueue(client);
	}
	
	public Customer attending(){
		return lineQueue.first();
	}

	public Customer nextCustomer(){
		return lineQueue.dequeue();
	}
	
	public int lineLength(){
		return lineQueue.size();
	}
	
	public long getSumOfWaiting(){
		LinkedQueue<Customer> tempQueue = lineQueue;
		long sum = 0;
		
		while(!tempQueue.isEmpty()){
			sum += tempQueue.dequeue().getTimeWaiting();
		}
		return sum;
	}
}