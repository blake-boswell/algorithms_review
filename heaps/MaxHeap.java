import java.util.*;

/**
 * Properties:
 * Tree is completely filled on all but last level
 * fill from left to right
 * 
 */

public class MaxHeap<T extends Comparable<T>>{
    private int size;
    private T array[];
    MaxHeap(T array[]) {
        this.size = array.length;
        this.array =  (T[])new Comparable[this.size];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
        buildMaxHeap(this.array);
    }

    /**
     * 
     * Build the max heap from the bottom up
     * floor(array.length/2) is the parent of the last 2 nodes
     * 
     * @param array
     */
    private void buildMaxHeap(T array[]) {
        for (int i = array.length / 2; i >= 0; i--) {
            maxHeapify(array, i);
        }
    }

    /**
     * 
     * Makes the subtree rooted at index i obey the max-heap property (all parents are greater than their children)
     * Floats the value at index down until it finds the place it belongs
     * 
     * @param array
     * @param index
     */
    private void maxHeapify(T array[], int index) {
        int left = left(index);
        int right = right(index);
        int largest = index;
        if (left < this.size && array[left].compareTo(array[largest]) == 1) {
            // left is the largest value
            largest = left;
        } 
        if (right < this.size && array[right].compareTo(array[largest]) == 1) {
            // right is the largest value
            largest = right;
        }
        if (largest != index) {
            T temp = array[index];
            array[index] = array[largest];
            if (largest == left) {
                array[left] = temp;
            } else {
                array[right] = temp;
            }
            maxHeapify(array, largest);
        }
        
    }

    /**
     * 
     * @param i index of element in array
     * @return index of element i's parent in the array
     */
    private int parent(int i) {
        return (int)((i - 1) / 2);
    }
    
    /**
     * 
     * @param i index of element in array
     * @return index of element i's left child in the array
     */
    private int left(int i) {
        return ((i * 2) + 1);
    }

    /**
     * 
     * @param i index of element in array
     * @return index of element i's right child in the array
     */
    private int right(int i) {
        return ((i + 1) * 2);
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.array[i] + " ");
        }
    }

    public static void main(String[] args) {
        Integer data[] = { 4, 2, 5, 1, 6, 10, 22, 13, 14 };
        MaxHeap<Integer> maxHeap = new MaxHeap(data);
        maxHeap.print();
    }
}