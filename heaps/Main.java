import MaxHeap;

public class Main {
    public static void main(String[] args) {
        Integer data[] = { 4, 2, 5, 1, 6, 10, 22, 13, 14 };
        MaxHeap<Integer> maxHeap = new MaxHeap(data);
        maxHeap.print();
    }
}