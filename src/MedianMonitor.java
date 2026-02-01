import java.util.*;

public class MedianMonitor {
    private PriorityQueue<Integer> leftMaxHeap;
    private PriorityQueue<Integer> rightMinHeap;

    public MedianMonitor() {
        leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        rightMinHeap = new PriorityQueue<>();
    }

    public void add(int val) {
        leftMaxHeap.add(val);
        rightMinHeap.add(leftMaxHeap.poll());

        if (rightMinHeap.size() > leftMaxHeap.size()) {
            leftMaxHeap.add(rightMinHeap.poll());
        }
    }

    public double getMedian() {
        if (leftMaxHeap.isEmpty()) return 0.0;

        if (leftMaxHeap.size() > rightMinHeap.size()) {
            return (double) leftMaxHeap.peek();
        } else {
            return (leftMaxHeap.peek() + rightMinHeap.peek()) / 2.0;
        }
    }

    public void debug() {
        System.out.println("DEBUG | Left: " + leftMaxHeap);
        System.out.println("DEBUG | Right: " + rightMinHeap);
    }

    public static void main(String[] args) {
        MedianMonitor monitor = new MedianMonitor();
        Scanner sc = new Scanner(System.in);

        System.out.println("Commands: ADD <val>, MEDIAN, DEBUG, EXIT");

        while (true) {
            System.out.print("> ");
            if (!sc.hasNext()) break;

            String input = sc.next().toUpperCase();

            if (input.equals("ADD")) {
                if (sc.hasNextInt()) {
                    int val = sc.nextInt();
                    monitor.add(val);
                    System.out.println("Added " + val);
                }
            } else if (input.equals("MEDIAN")) {
                System.out.println("Current Median: " + monitor.getMedian());
            } else if (input.equals("DEBUG")) {
                monitor.debug();
            } else if (input.equals("EXIT")) {
                break;
            }
        }
        sc.close();
    }
}
