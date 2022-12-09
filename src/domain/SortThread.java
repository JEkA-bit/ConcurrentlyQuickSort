package domain;

import java.util.Arrays;

public class SortThread extends Thread{

    private int[] array;
    private int start;
    private int end;

    public SortThread(
            int[] array,
            int start,
            int end
    ) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        super.run();
        this.sort();
    }

    public void sort() {
        if(this.end <= this.start) return;

        int pivot = partition(array, this.start, this.end);

        SortThread left = new SortThread(array, this.start, pivot - 1);
        SortThread right = new SortThread(array, pivot + 1, this.end);

        left.start();
        right.start();

        try {
            left.join();
            right.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int partition(int[] arr, int start, int end) {

        int pivot = arr[end];
        int i = start - 1;

        for (int j = start; j <= end - 1; j++) {

            if(arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;

        return i;
    }

    public int[] getArray() {
        return this.array;
    }
}
