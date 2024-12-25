import java.util.concurrent.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nTask 1: Mathematical computations, first to complete will be shown");
        long startTask1 = System.currentTimeMillis();

        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            return performComputation(45);
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            return performComputation(40);
        });

        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            return performComputation(42);
        });

        CompletableFuture<Object> firstTask = CompletableFuture.anyOf(task1, task2, task3);

        firstTask.thenAccept(result -> {
            long endTask1 = System.currentTimeMillis();
            System.out.println("First completed: " + result + " (Time taken: " + (endTask1 - startTask1) + " ms)\n");
        }).join();

        System.out.println("\nTask 2: Flight booking simulation");
        long startTask2 = System.currentTimeMillis();

        CompletableFuture<Double> price1 = CompletableFuture.supplyAsync(() -> {
            sleep(2200);
            return 250.0;
        });

        CompletableFuture<Double> price2 = CompletableFuture.supplyAsync(() -> {
            sleep(3000);
            return 260.0;
        });

        CompletableFuture<Double> price3 = CompletableFuture.supplyAsync(() -> {
            sleep(1800);
            return 255.0;
        });

        CompletableFuture<Object> bestPrice = CompletableFuture.anyOf(price1, price2, price3);

        bestPrice.thenAccept(price -> {
            long endTask2 = System.currentTimeMillis();
            System.out.println("Booking status: Flight booked at price: " + price + " (Time taken: " + (endTask2 - startTask2) + " ms)");
        }).join();
    }

    private static String performComputation(int n) {
        long result = fibonacci(n);
        return "Fibonacci(" + n + ") = " + result;
    }

    private static long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
