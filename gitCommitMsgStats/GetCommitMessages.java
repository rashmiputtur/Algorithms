package gitStatistics;

import java.math.BigInteger;

public class GetCommitMessages {
	
	//Instantiations of classes computin mean and median
	GetMeanOfIntegerStream meanOfIntStream = new GetMeanOfIntegerStream();
	GetMedianOfIntegerStream medianOfIntStream = new GetMedianOfIntegerStream();

	// Variables to hold mean, median and number of messages encountered until now
	static double median =0;
	static BigInteger mean = new BigInteger("0");
	static BigInteger numberOfMessages = new BigInteger("0");
	
	public void getMessage(String message){
		
		//Trim message and compute length
		int messageLength = (message.trim()).length();
		
		//Update mean for new message length
		mean  = meanOfIntStream.getMean(messageLength, numberOfMessages, mean);
		
		//Update median for new message length
		median = medianOfIntStream.getMeadian(messageLength, median);
		
		//Return updated mean and median to concerned class
		CollectStatistics collectStats = new CollectStatistics();
		collectStats.getMeanAndMedian(mean, median);
	}
}
