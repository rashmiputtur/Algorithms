package gitStatistics;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

/*
 * Class to collect the updated mean and median and the timestamp of every message
 */
public class CollectStatistics {
	static BigInteger mean = null;
	static Double median = 0.0;
	static String timestamp = "";
	
	public void getMeanAndMedian(BigInteger mean, Double median){
		CollectStatistics.mean = mean;
		CollectStatistics.median = median;
		
	}
	
	public void getLastRecordTimeStamp(String currentMsgTimestamp){
		CollectStatistics.timestamp = currentMsgTimestamp;
		displayResults();
		
	}
	
	private void displayResults(){
		if(timestamp!=""){
			System.out.println("Time until " + timestamp);
			System.out.println("Mean " + mean);
			System.out.println("Median " + median);
		}
	}

}
