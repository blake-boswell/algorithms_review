import java.util.*;

/**
 * Properties:
 * Tree is completely filled on all but last level
 * fill from left to right
 * 
 */

public class MaxHeap<T extends Comparable<T>>{
    private int size;
    private ArrayList<T> heap;
    MaxHeap(T array[]) {
        this.size = array.length;
        this.heap =  new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            this.heap.add(array[i]);
        }
        buildMaxHeap();
    }

    /**
     * 
     * Build the max heap from the bottom up
     * floor(heap.length/2) is the parent of the last 2 nodes
     * 
     */
    private void buildMaxHeap() {
        for (int i = this.heap.size() / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    /**
     * 
     * Makes the subtree rooted at index i obey the max-heap property (all parents are greater than their children)
     * Floats the value at index down until it finds the place it belongs
     * 
     * @param index
     */
    private void maxHeapify(int index) {
        int left = left(index);
        int right = right(index);
        int largest = index;
        if (left < this.heap.size() && this.heap.get(left).compareTo(this.heap.get(largest)) == 1) {
            // left is the largest value
            largest = left;
        } 
        if (right < this.heap.size() && this.heap.get(right).compareTo(this.heap.get(largest)) == 1) {
            // right is the largest value
            largest = right;
        }
        if (largest != index) {
            T temp = this.heap.get(index);
            this.heap.set(index, this.heap.get(largest));
            if (largest == left) {
                this.heap.set(left, temp);
            } else {
                this.heap.set(right, temp);
            }
            maxHeapify(largest);
        }
        
    }

    public void insert(T element) {
        this.size++;
        this.heap.add(element);
        int index = this.heap.size() - 1;
        while (index > 0 && this.heap.get(parent(index)).compareTo(this.heap.get(index)) == -1) {
            // Swap with parent
            T temp = this.heap.get(parent(index));
            this.heap.set(parent(index), this.heap.get(index));
            this.heap.set(index, temp);
            index = parent(index);
        }
    }

    public void delete(T element) {

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
        for (int i = 0; i < this.heap.size(); i++) {
            System.out.print(this.heap.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer data[] = { 4, 2, 5, 1, 6, 10, 22, 13, 14 };
        MaxHeap<Integer> maxHeap = new MaxHeap(data);
        maxHeap.print();
        maxHeap.insert(25);
        maxHeap.print();
        maxHeap.insert(0);
        maxHeap.print();
    }
}