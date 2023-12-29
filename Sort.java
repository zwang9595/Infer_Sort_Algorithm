/**
 * Sort algorithm used for analysis with Infer Analyzer by Zhao Wang
 * Supports different type of data
 * Version: 1.6
 * Date: 4/24/2022
 */
package cs6110;

import java.util.Arrays;
import java.util.Comparator;

public class Sort<T> {
	
	// Initial values
	private T[] sorted, unsorted;
	private Comparator<T> comparator;
	private int comparisonCount, length;
	private boolean isSorted;
	private String type;
	
	/**
	 * Constructor that imports data array and copy data (avoid
	 * reference leak)
	 */
	@SuppressWarnings("unchecked")
	protected Sort(T[] unsorted, Comparator<T> comparator) {
		this.comparator = comparator;
		this.length = unsorted.length;
		this.isSorted = false;
		this.unsorted = (T[]) new Object[this.length];
		this.sorted = (T[]) new Object[this.length];
		this.type = "none";
		// Copy array
		copy(this.sorted, unsorted);
		copy(this.unsorted, unsorted);
	}
	
	/**
	 * Simple bubblesort with garanteed O(n^2)
	 */
	protected void bubbleSort() {
        int i, j;
        int n = this.length;
        for(i = 0; i < n - 1; i++) {
            for(j = 0; j < n - i - 1; j++) {
                if(count(comparator.compare(this.sorted[j], this.sorted[j + 1])) > 0) {
                    swap(this.sorted, j, j + 1);
                }
            }
        }
        this.isSorted = true;
        this.type = "Bubble";
    }
	
	/**
	 * Non-recursive heapsort (Infer does not support recursive function)
	 */
	protected void heapSort() {
		int i, j;
		for (i = 1; i < this.length; i++) {
			if(count(comparator.compare(this.sorted[i], this.sorted[(i - 1) / 2])) > 0) {
				j = i;
				while (count(comparator.compare(this.sorted[j], this.sorted[(j - 1) / 2])) > 0) {
					swap(this.sorted, j, (j - 1) / 2);
					j = (j - 1) / 2;
		    	}
		    }
		}
		
		for (i = this.length - 1; i > 0; i--) {
	    	int pos = 1;
	    	swap(this.sorted, 0, i);
	    	j = 0;
	    	
	    	while (pos < i) {
	    		pos = (2 * j + 1);
		        if (pos < (i - 1) && count(comparator.compare(this.sorted[pos], this.sorted[pos + 1])) < 0) {
		        	pos++;
		        }
		        if (pos < i && count(comparator.compare(this.sorted[j], this.sorted[pos])) < 0) {
		        	swap(this.sorted, j, pos);
		        }
		        j = pos;
	    	}
		}
		this.isSorted = true;
		this.type = "Heap";
	}
	
	/**
	 * Simple insertionsort
	 */
	protected void insertionSort() {
		int i, j;
        for (i = 1; i < this.length; ++i) {
            T pivot = this.sorted[i];
            j = i - 1;
            
            while (j >= 0 && count(comparator.compare(this.sorted[j], pivot)) > 0) {
            	this.sorted[j + 1] = this.sorted[j];
                j = j - 1;
            }
            this.sorted[j + 1] = pivot;
        }
        this.isSorted = true;
        this.type = "Insertion";
	}
	
	/**
	 * Helper method for mergesort
	 */
	@SuppressWarnings("unchecked")
	private void merge(int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;
  
        T L[] = (T[]) new Object[n1];
        T R[] = (T[]) new Object[n2];
  
        for (int i = 0; i < n1; ++i)
            L[i] = this.sorted[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = this.sorted[mid + 1 + j];
  
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
  
        // Initial index of merged subarray array
        int k = left;
        while (i < n1 && j < n2) {
            if (count(comparator.compare(L[i], R[j])) <= 0) {
            	this.sorted[k] = L[i];
                i++;
            }
            else {
            	this.sorted[k] = R[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
        	this.sorted[k] = L[i];
            i++;
            k++;
        }
  
        while (j < n2) {
        	this.sorted[k] = R[j];
            j++;
            k++;
        }
    }
	
	/**
	 * Recursive helper method for mergesort
	 */
    private void sort_help(int left, int right)
    {
        if (left < right) {
        	int mid = (left + right) / 2;
        	
        	sort_help(left, mid);
            sort_help(mid + 1, right);
  
            merge(left, mid, right);
        }
    }
    
    /**
     * Recursive mergesort
     */
    protected void mergeSort() {
    	sort_help(0, this.length - 1);
    	this.isSorted = true;
		this.type = "Merge";
    }
	
    /**
     * Helper method for quicksort
     */
	private int partition(int left, int right) {
		
	    T pivot = this.sorted[right];
	    int i = (left - 1);
	    int j;
	    
	    for(j = left; j <= right - 1; j++) {	    	
	        if (count(comparator.compare(this.sorted[j], pivot)) < 0) {
	            i++;
	            swap(this.sorted, i, j);
	        }
	    }
	    swap(this.sorted, i + 1, right);
	    return (i + 1);
	}
	
	/**
     * Helper method for quicksort
     */
	private void quickSort_h(int left, int right) {
	    if (left < right) {
	        int pos = partition(left, right);
	        quickSort_h(left, pos - 1);
	        quickSort_h(pos + 1, right);
	    }
	}
	
	/**
     * Recursive quicksort (manual complexity analysis)
     */
	protected void quickSort() {
		quickSort_h(0, this.length - 1);
		this.isSorted = true;
		this.type = "Quick";
	}
	
	/**
	 * Simple selectionsort with garanteed O(n^2)
	 */
	protected void selectionSort() {
		int len = this.length;
		int i, j;
        for (i = 0; i < len - 1; i++) {
            int min = i;
            for (j = i + 1; j < len; j++) {
                if (count(comparator.compare(sorted[j], sorted[min])) < 0) {
                	min = j;
                }
            }
            swap(this.sorted, min, i);
        }
        this.isSorted = true;
        this.type = "Selection";
	}
	
	/**
	 * Get count for manual analysis
	 */
	protected int getCount() {
		return this.comparisonCount;
	}
	
	/**
	 * Get length for manual analysis
	 */
	protected int getLength() {
		return this.length;
	}
	
	/**
	 * Get sorted array for test
	 */
	protected T[] getSorted() {
		return this.sorted;
	}
	
	/**
	 * Get sort type for manual analysis
	 */
	protected String getType() {
		return this.type;
	}
	
	/**
	 * Setter for changing comparator
	 */
	protected void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
		check();
	}
	
	/**
	 * Clear the sorted array and reference (avoid reference leak)
	 */
	protected void clear() {
		check();
	}
	
	/**
	 * Printer for manual analysis
	 */
	protected void print() {
		bubbleSort();
		System.out.printf("%9s: %s count: %d, length: %d%n", this.type, Arrays.toString(this.sorted), getCount(), getLength());
		clear();
		
		heapSort();
		System.out.printf("%9s: %s count: %d, length: %d%n", this.type, Arrays.toString(this.sorted), getCount(), getLength());
		clear();
		
		insertionSort();
		System.out.printf("%9s: %s count: %d, length: %d%n", this.type, Arrays.toString(this.sorted), getCount(), getLength());
		clear();
		
		mergeSort();
		System.out.printf("%9s: %s count: %d, length: %d%n", this.type, Arrays.toString(this.sorted), getCount(), getLength());
		clear();
		
		quickSort();
		System.out.printf("%9s: %s count: %d, length: %d%n", this.type, Arrays.toString(this.sorted), getCount(), getLength());
		clear();
		
		selectionSort();
		System.out.printf("%9s: %s count: %d, length: %d%n", this.type, Arrays.toString(this.sorted), getCount(), getLength());
		clear();
		
		System.out.println();
	}
	
	/**
	 * Helper method swapper
	 */
	private void swap(T[] data, int first, int second) {
    	T temp = data[first];
    	data[first] = data[second];
    	data[second] = temp;
    }
	
	/**
	 * Helper method for copying data
	 */
	private void copy(T[] sorted, T[] unsorted) {
		System.arraycopy(unsorted, 0, sorted, 0, unsorted.length);
	}
	
	/**
	 * Counting for manual time complexity analysis
	 */
	private int count (int v) {
        this.comparisonCount++;
        return v;
    }
	
	/**
	 * Check if the array is sorted before second sort and clear all
	 */
	private void check() {
		if(isSorted) {
			//this.sorted = null; // Uncomment it for --eradicate test
			//this.sorted = (T[]) new Object[this.unsorted.length]; // Uncomment it for buffer overrun analysis (--bufferoverrun)
			copy(this.sorted, this.unsorted);
			this.comparisonCount = 0;
			this.type = "none";
			this.isSorted = false;
		}
	}

}
