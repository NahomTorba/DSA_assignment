import java.util.*;

public class ResourceScheduler {

    static class Request {
        String id;
        int startTime;
        int endTime;
        int priority;

        Request(String id, int startTime, int endTime, int priority) {
            this.id = id;
            this.startTime = startTime;
            this.endTime = endTime;
            this.priority = priority;
        }
    }

    static class Resource {
        int resourceId;
        int nextFreeTime;

        Resource(int resourceId, int nextFreeTime) {
            this.resourceId = resourceId;
            this.nextFreeTime = nextFreeTime;
        }
    }
    // 3. GLOBAL STORAGE
    static List<Request> requests = new ArrayList<>();
    static Map<String, Integer> assignment = new LinkedHashMap<>();

    static int totalResources = 0;
    static int peakUsage = 0;

    // 4. HANDLE REQUEST COMMAND
    static void addRequest(String input) {
        String[] parts = input.split("\\s+");

        String id = parts[1];
        int start = Integer.parseInt(parts[2]);
        int end = Integer.parseInt(parts[3]);

        int priority = 0;
        if (parts.length == 5) {
            priority = Integer.parseInt(parts[4]);
        }

        requests.add(new Request(id, start, end, priority));
        System.out.println("Request " + id + " added.");
    }

    //SCHEDULING LOGIC
    static void scheduleRequests() {
        if (requests.isEmpty()) {
            System.out.println("No requests to schedule.");
            return;
        }

        assignment.clear();
        totalResources = 0;
        peakUsage = 0;

        // Convert list to array (needed for QuickSort)
        Request[] requestArray = requests.toArray(new Request[0]);

        // Sort requests
        quickSort(requestArray, 0, requestArray.length - 1);

        // Min-Heap based on earliest free resource
        PriorityQueue<Resource> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(r -> r.nextFreeTime));

        System.out.println("Processing requests...");

        for (Request req : requestArray) {

            // If a resource is free, reuse it
            if (!minHeap.isEmpty() && minHeap.peek().nextFreeTime <= req.startTime) {

                Resource res = minHeap.poll();
                assignment.put(req.id, res.resourceId);

                System.out.println("- " + req.id + " (" + req.startTime + "-" + req.endTime +
                        ") → Reused Resource " + res.resourceId);

                res.nextFreeTime = req.endTime;
                minHeap.offer(res);

            } else {
                // Otherwise create a new resource
                totalResources++;
                Resource newRes = new Resource(totalResources, req.endTime);
                minHeap.offer(newRes);
                assignment.put(req.id, totalResources);

                System.out.println("- " + req.id + " (" + req.startTime + "-" + req.endTime +
                        ") → Created Resource " + totalResources);
            }

            // Track maximum concurrent usage
            peakUsage = Math.max(peakUsage, minHeap.size());
        }
    }

    // 6. PRINT STATISTICS
    static void printStats() {
        System.out.println("Total Resources Created: " + totalResources);
        System.out.println("Peak Concurrent Usage: " + peakUsage);
    }

    // 7. QUICKSORT IMPLEMENTATION
    static void quickSort(Request[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    static int partition(Request[] arr, int low, int high) {
        Request pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareRequests(arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    static int compareRequests(Request a, Request b) {
        if (a.startTime != b.startTime)
            return a.startTime - b.startTime;       // earlier start first
        if (a.priority != b.priority)
            return b.priority - a.priority;         // higher priority first
        return a.id.compareTo(b.id);                // tie breaker
    }

    static void swap(Request[] arr, int i, int j) {
        Request temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // main method
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Resource Allocation Scheduler ===");
        System.out.println("Commands:");
        System.out.println("REQUEST <id> <start> <end> [priority]");
        System.out.println("SCHEDULE");
        System.out.println("STATS");
        System.out.println("EXIT");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                break;
            } else if (input.startsWith("REQUEST")) {
                addRequest(input);
            } else if (input.equalsIgnoreCase("SCHEDULE")) {
                scheduleRequests();
            } else if (input.equalsIgnoreCase("STATS")) {
                printStats();
            } else {
                System.out.println("Unknown command.");
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }
}
