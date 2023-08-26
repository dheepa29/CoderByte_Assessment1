import java.util.ArrayList;
import java.util.Scanner;

class Trade {
    String currencyPair;
    String customerName;
    double amount;
    double rate;

    public Trade(String currencyPair, String customerName, double amount, double rate) {
        this.currencyPair = currencyPair;
        this.customerName = customerName;
        this.amount = amount;
        this.rate = rate;
    }
}

public class FXTrading {
    static ArrayList<Trade> trades = new ArrayList<>();
    static double usdToInrRate = 66.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Book Trade");
            System.out.println("2. Print Trades");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookTrade(scanner);
                    break;
                case 2:
                    printTrades();
                    break;
                case 3:
                    System.out.print("Do you really want to exit (y/n): ");
                    String confirmExit = scanner.next();
                    if (confirmExit.equalsIgnoreCase("y")) {
                        System.out.println("Bye - have a good day");
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (true);
    }

    static void bookTrade(Scanner scanner) {
        System.out.print("Enter customer Name: ");
        String customerName = scanner.next();

        System.out.print("Enter Currency Pair: ");
        String currencyPair = scanner.next();

        if (!currencyPair.equalsIgnoreCase("USDINR")) {
            System.out.println("Invalid currency pair. Only USDINR is supported.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        System.out.print("Do you want to get Rate (Yes/No): ");
        String getRate = scanner.next();

        double rate = usdToInrRate;
        if (getRate.equalsIgnoreCase("Yes")) {
            System.out.println("You are transferring INR "+ amount*usdToInrRate + "to " + customerName + ".(Assuming that rate was " + (int) usdToInrRate + "00)" );
            // rate = scanner.nextDouble();
        }

        double inrAmount = amount * rate;
        trades.add(new Trade(currencyPair, customerName, inrAmount, rate));

        // System.out.println("You are transferring INR " + inrAmount + " to " + customerName +
                        //   " (Assuming that rate was " + rate + ")");
        System.out.print("Book/Cancel this trade? (Book/Cancel): ");
        String tradeAction = scanner.next();

        if (tradeAction.equalsIgnoreCase("Book")) {
            System.out.println("Trade for " + currencyPair + " has been booked with rate " + rate +
                               ". The amount of Rs " + inrAmount +
                               " will be transferred in 2 working days to " + customerName + ".");
        } else if (tradeAction.equalsIgnoreCase("Cancel")) {
            System.out.println("Trade is Canceled.");
        } else {
            System.out.println("Invalid action. Trade is Canceled.");
        }
    }

    static void printTrades() {
        System.out.println("TradeNo\tCurrencyPair\tCustomerName\tAmount\t\tRate");
        for (int i = 0; i < trades.size(); i++) {
            Trade trade = trades.get(i);
            System.out.println((i + 1) + "\t" + trade.currencyPair + "\t\t" + trade.customerName +
                               "\t\tINR " + trade.amount + "\t" + trade.rate);
        }
    }
}
