package customers;

public class Monitor {
	
	private Server[] servers;
	
	public Monitor( Server[] servers) {
		this.servers = servers;
	}
	
	public  boolean isBalanced() {
		Server s = servers[0];
		for(int i = 1 ; i< servers.length; i++) {
			if(s.lineLength() != servers[i].lineLength()) {
				return false;
			}
		}
		return true;
	}
	
	public int findServerLineWithMostCustomer() {
		int index = 0 ;
		Server s = servers[0];
		for(int i = 1; i < servers.length; i++) {
			if(servers[i].lineLength() >= s.lineLength()) {
				s = servers[i];
				index = i;
			}
		}
		return index;
	}
	
	public  int numOfCostumersWaiting() {
		int x = 0;
		
		for(int i =0; i < servers.length;i++) {
			x = x + servers[i].lineLength();
		}
		
		return x;
		
	}
	public boolean allAreServing() {
		for(int i = 0 ; i<servers.length;i++) {
			if(!servers[i].isServing()) {
				return false;
			}
		}
		return true;
	}
	
	public int countNumberofMaxCusLines() {
		int n = this.findServerLineWithMostCustomer();
		int x = 0;
		for(int i = 1; i < servers.length ; i++) {
			if(servers[n].lineLength() == servers[(n+1)%servers.length].lineLength()) {
				x++;
			}
			
		}
		return x;
	}
	
	public int findServerLineWithLeastCustomers() {
		Server s = servers[0];
		int index = 0;
		for(int i =0; i<servers.length;i++) {
			if(servers[i].lineLength()<s.lineLength()) {
				s = servers[i];
				index = i;
			}
		}
		return index;
	}
	

}
