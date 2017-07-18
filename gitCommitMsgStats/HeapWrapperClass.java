package gitStatistics;

import java.util.PriorityQueue;

/* Min and max heap class and method definitions*/

public class HeapWrapperClass {
	
	//Inserts an element into the specified "heapType"
	public void addElement(int element, PriorityQueue<Integer> heap){
		
		heap.add(element);
	}
	
	
	//get size of a heap
	private int getHeapSize(PriorityQueue<Integer> heap){
		
		return heap.size();
	}
	
	//Compare minHeap and maxHeap sizes
	public int compareHeapSize(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap){
		int minHeapSize = getHeapSize(minHeap);
		int maxHeapSize = getHeapSize(maxHeap);
		
		return (minHeapSize == maxHeapSize) ? 0 : (maxHeapSize > minHeapSize) ? 1 : -1;
	}
	
	
	//Get minimum element from minHeap
	public int getMinElement(PriorityQueue<Integer> heap){
		return heap.peek();
	}
	
	//Get maximum element from maxHeap
	public int getMaxElement(PriorityQueue<Integer> heap){
		return heap.peek();
	}
   
	//Delete maximum element from maxHeap
	public int deleteMaxElement(PriorityQueue<Integer> heap){
		return heap.poll();
	}
	
	//Delete minimum element from minHeap
	public int deleteMinElement(PriorityQueue<Integer> heap){
		return heap.poll();
	}
	
	
	//Get average of top elements of minHeap and maxHeap
	public double getAverage(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap){
		return (getMinElement(minHeap) + getMaxElement(maxHeap)) / 2.0; 
	}

}
