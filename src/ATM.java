import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ATM {

	static Scanner sc = new Scanner(System.in);

	static boolean isAccountLocked = false;

	// program execution starts from here
	public static void main(String[] args) {

		// creating 4 cards
		Card card1 = new Card("1111222233334444", "1111", 1000);
		Card card2 = new Card("2222111133334444", "2222", 0);
		Card card3 = new Card("3333111122224444", "3333", 250);
		Card card4 = new Card("4444111122223333", "4444", 500);

		// creating 4 users
		User user1 = new User(card1, 2);
		User user2 = new User(card2, 0);
		User user3 = new User(card3, 1);
		User user4 = new User(card4, 0);

		// creating list of 4 users
		List<User> users = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));

		// adding users to bank
		Bank bank = new Bank(users);

		System.out.println("You are connected to " + Bank.getConnectedBankName() + "\n");
		System.out.print("Enter card id: ");
		String id = sc.next();

		Card card = null;

		int numberOfErrors = bank.getNumberOfErrors(id);

		if (numberOfErrors > 0) {
			System.out.println("You have [" + numberOfErrors + "] previous unsuccessful attempts. You have ["
					+ (3 - numberOfErrors) + "] chance(s) left.\n");
		}

		while (true) {
			System.out.print("Enter card pin: ");
			String pin = sc.next();
			card = new Card(id, pin);

			boolean isAuthenticated = bank.authenticateUser(card);

			numberOfErrors++;

			if (isAuthenticated) {
				break;
			}
			if (numberOfErrors == 3) {
				System.out.println("\nIncorrect pin entered 3 times, account locked.");
				isAccountLocked = true;
				break;
			} else {
				System.out.println("\nYou have entered incorrect PIN [" + numberOfErrors + "] time(s). You have ["
						+ (3 - numberOfErrors) + "] chance(s) left.");
				continue;
			}

		}

		if (!isAccountLocked) {

			System.out.println("\nLogin Successful!");

			System.out.print("\nCurrent bank balance: " + bank.checkAmount(card));

			System.out.print("\n\nEnter amount to withdraw: ");
			int withdrawAmount = sc.nextInt();

			if (!bank.withdrawAmount(card, withdrawAmount)) {
				System.out.println(
						"There is not that much money in the bank. Current balance is: " + bank.checkAmount(card));
			} else {
				System.out.println("Withdraw successful for amount: " + withdrawAmount);
			}

			System.out.print("\nEnter amount to deposit: ");
			int depositAmount = sc.nextInt();

			if (!bank.depositAmount(card, depositAmount)) {
				System.out.println("Deposit amount can't be negative.");
			} else {
				System.out.println("Deposit successful for amount: " + depositAmount);
			}

			System.out.print("\nCurrent bank balance: " + bank.checkAmount(card));

		} else {
			System.out.println("Thank you!");
		}
	}

}
