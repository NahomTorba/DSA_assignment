import java.util.Scanner;
import java.util.Stack;

class Bid {
    String user;
    int amount;

   
    public Bid(String user, int amount) {
        this.user = user;
        this.amount = amount;
    }
}

public class Auctioneer {
    public static void main(String[] args) {
        Stack<Bid> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Auction started. Enter commands:");
        while (true) {
            String input = sc.nextLine();
            String[] parts = input.split(" ");
            if (parts[0].equalsIgnoreCase("BID")) {
                String user = parts[1];
                int amount = Integer.parseInt(parts[2]);

                if (stack.isEmpty() || amount > stack.peek().amount) {
                    stack.push(new Bid(user, amount));
                    System.out.println("Current: " + user + " (" + amount + ")");
                    printStack(stack);
                } else {
                    System.out.println("Error: Too low.");
                }
            } else if (parts[0].equalsIgnoreCase("WITHDRAW")) {
                if (!stack.isEmpty()) {
                    Bid removed = stack.pop();
                    System.out.println(removed.user + " retracted.");

                    if (!stack.isEmpty()) {
                        Bid current = stack.peek();
                        System.out.println("Reverted to " + current.user + " (" + current.amount + ").");
                    } else {
                        System.out.println("No bids left.");
                    }
                    printStack(stack);
                }
            } else if (parts[0].equalsIgnoreCase("CURRENT")) {
                if (!stack.isEmpty()) {
                    Bid current = stack.peek();
                    System.out.println("Current: " + current.user + " (" + current.amount + ")");
                } else {
                    System.out.println("No current bids.");
                }
            } else if (parts[0].equalsIgnoreCase("EXIT")) {
                break;
            }
        }
        sc.close();
    }
    static void printStack(Stack<Bid> stack) {
        System.out.print("Stack: [");
        for (int i = 0; i < stack.size(); i++) {
            System.out.print(stack.get(i).amount);
            if (i < stack.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}