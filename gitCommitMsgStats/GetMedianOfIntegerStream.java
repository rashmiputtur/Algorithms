package gitStatistics;

import java.util.Comparator;
import java.util.PriorityQueue;
import gitStatistics.HeapWrapperClass;
import gitStatistics.MinHeapComparator;
import gitStatistics.MaxHeapComparator;

/* Compute median of an integer stream*/
public class GetMedianOfIntegerStream {

	// Instantiation of the heap implementation
	HeapWrapperClass heapClass = new HeapWrapperClass();

	/*
	 * A custom comparator to define the ordering of elements in min and max
	 * heaps
	 */
	Comparator<Integer> minHeapComparator = new MinHeapComparator();
	Comparator<Integer> maxHeapComparator = new MaxHeapComparator();

	
// Minheap and Maxheap implementations
	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(1,
			minHeapComparator);
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1,
			maxHeapComparator);

	
	/*
	 * Method to update the median when a new integer is encountered
	 * @param: number : The current number encountered in the stream
	 * @param: median : The median of numbers encountered before the current number 
	 */
	public double getMeadian(int number, double median) {

		double newMedian = 0;
		
		//Difference between the sizes of minHeap and maxHeap
		int getHeapSizeDiff = heapClass.compareHeapSize(minHeap, maxHeap);
		

		switch (getHeapSizeDiff) {
		case 1: // There are more elements in the maxHeap

			// Current element fits in the maxHeap
			if (number < median) {
				// Remove top element from maxHeap and insert into minHeap
				int topElement = heapClass.deleteMaxElement(maxHeap);
				heapClass.addElement(topElement, minHeap);

				// Insert current element into maxHeap
				heapClass.addElement(number, maxHeap);
			} else {
				// Current element fits into minHeap

				heapClass.addElement(number, minHeap);
			}

			// Both heaps are balanced, get average of top elements as median
			newMedian = heapClass.getAverage(minHeap, maxHeap);
			break;

		case 0: // minHeap and maxHeap have equal elements

			// Current element fits in the maxHeap
			if (number < median) {
				heapClass.addElement(number, maxHeap);
				newMedian = heapClass.getMaxElement(maxHeap);
			} else {
				// Current element fits into minHeap

				heapClass.addElement(number, minHeap);
				newMedian = heapClass.getMinElement(minHeap);
			}

			break;

		case -1: // There are more elements in the minHeap

			// Current element fits in the maxHeap
			if (number < median) {
				heapClass.addElement(number, maxHeap);
			} else {
				// Remove top element from minHeap and insert into maxHeap
				int topElement = heapClass.deleteMinElement(minHeap);
				heapClass.addElement(topElement, maxHeap);

				// Current element is inserted into minHeap
				heapClass.addElement(number, minHeap);
			}
			// Both heaps are balanced, get average of top elements as median
			newMedian = heapClass.getAverage(minHeap, maxHeap);
			break;
		} // End of switch

		return newMedian;
	}

}
