import domain.SortThread;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] arr = new Random(10).ints(1000, -10, 10).toArray();

        Arrays.stream(arr).forEach(e -> System.out.print(e + " "));
        System.out.println();

        SortThread thread = new SortThread(arr, 0, arr.length - 1);
        thread.start();

        try {
            LocalDateTime start = LocalDateTime.now();
            thread.join();
            LocalDateTime finish = LocalDateTime.now();

            arr = thread.getArray();
            Arrays.stream(arr).forEach(e -> System.out.print(e + " "));

            System.out.print("\nTime (ms): " + ChronoUnit.MILLIS.between(start, finish));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}