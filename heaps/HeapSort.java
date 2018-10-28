import java.util.*;

public class HeapSort {
    /**
     * 
     * Build the max heap from the bottom up
     * floor(heap.length/2) is the parent of the last 2 nodes
     * 
     */
    public static void buildMaxHeap(int array[]) {
        for (int i = array.length / 2; i >= 0; i--) {
            maxHeapify(array, i, array.length);
        }
    }

    /**
     * 
     * Makes the subtree rooted at index i obey the max-heap property (all parents are greater than their children)
     * Floats the value at index down until it finds the place it belongs
     * 
     * @param index
     */
    public static void maxHeapify(int array[], int index, int heapSize) {
        int left = left(index);
        int right = right(index);
        int largest = index;
        if (left < heapSize && array[left] > array[largest]) {
            // left is the largest value
            largest = left;
        } 
        if (right < heapSize && array[right] > array[largest]) {
            // right is the largest value
            largest = right;
        }
        if (largest != index) {
            int temp = array[index];
            array[index] = array[largest];
            if (largest == left) {
                array[left] = temp;
            } else {
                array[right] = temp;
            }
            maxHeapify(array, largest, heapSize);
        }
        
    }

    public static void heapSort(int array[]) {
        buildMaxHeap(array);
        int heapSize = array.length;
        for (int i = array.length - 1; i >= 1; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapSize--;
            maxHeapify(array, 0, heapSize);
        }
    }

    /**
     * 
     * @param i index of element in array
     * @return index of element i's parent in the array
     */
    public static int parent(int i) {
        return (int)((i - 1) / 2);
    }
    
    /**
     * 
     * @param i index of element in array
     * @return index of element i's left child in the array
     */
    public static int left(int i) {
        return ((i * 2) + 1);
    }

    /**
     * 
     * @param i index of element in array
     * @return index of element i's right child in the array
     */
    public static int right(int i) {
        return ((i + 1) * 2);
    }

    public static void print(int array[]) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int data[] = { 4, 2, 5, 1, 6, 10, 22, 13, 14 };
        print(data);
        buildMaxHeap(data);
        print(data);
        heapSort(data);
        print(data);
    }
}