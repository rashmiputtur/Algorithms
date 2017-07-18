package gitStatistics;

import java.math.BigInteger;

/* Compute average of an Integer stream
 */
public class GetMeanOfIntegerStream {
	
	/* 
	 * @param: number : The current number in the stream
	 * @param: numberOfElements : The count of numbers encountered before current number
	 * @param: oldMean : The mean of numbers before the current number
	 */
	public BigInteger getMean(int number, BigInteger numberOfElements, BigInteger oldMean){
		
		//Compute sum of all numbers encountered so far using oldMean and numberOfElements
		BigInteger oldSumOfElements = null;
		oldSumOfElements = (numberOfElements).multiply(oldMean);
		BigInteger newSumOfElements = oldSumOfElements.add(BigInteger.valueOf(number));
		
		//Update count of numbers encountered by one
		BigInteger currentNumberOfElements = numberOfElements.add(new BigInteger("1"));
		
		//Compute new mean
		BigInteger newMean = null;
		newMean = newSumOfElements.divide(currentNumberOfElements);
		
		return newMean;
	}
}
