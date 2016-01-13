package Objects;

public class Balance {
	
	//this is a comment on GitHub
	
	double amount;
	long lastWorked; 
	
	public Balance(){
		amount = 0;
		lastWorked = 0;
		
	}

	public double getAmount() {
		return Math.round(amount*100)/100;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getLastWorked() {
		return lastWorked;
	}

	public void setLastWorked(long lastWorked) {
		this.lastWorked = lastWorked;
	}
	
	public void subtractLateFees(long timeOverdue){ //not sure
		long lateFee = timeOverdue*1;
		amount = amount - lateFee;
	}
	
	public boolean canWork(long time){
		//lastWorked = current time - time button pressed;
		if (time - lastWorked > 10000) return true; 
		else return false; 
	}
	
	public String earnMoney(long time){
		String tmp = "";
		if(canWork(time)){ 
			amount = amount + 5;
			lastWorked = time; 
			tmp = "did some work at the library and earned $5";
		}
		
		else tmp = "can not do a double shift! Wait until the first job is done";
		return tmp;
	}
	
	

}

