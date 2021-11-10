package bankAccount;
import java.util.Scanner;
public class bank {
	
	
	public static void main(String[]args) {
		Scanner key = new Scanner(System.in);
		
		Account list = new Account();
		
		list.addToHead("mike", 1185, 5000, 10000, 20, -5000);
		list.addToHead("dave", 7385, 15000, 0, 0, -30000);
		list.addToHead("andrea", 2259, 40000, 20000, 10, -45000);
		list.addToHead("luke", 3836, 1500, 8000, 4, -3000);
		list.addToHead("sansa", 7758, -1800, 5000, 30, -2000);
		
		while(true) {
		System.out.println("If you wish to login type: login");
		System.out.println("If you wish to move time forward type: time");
		System.out.println("If you wish to create account type: create");
		System.out.println("If you wish to cancel account type: cancel");
		String account = key.next();
		if (account.equals("time")) {
			list.time();
		} else if (account.equals("create")) {
			System.out.println("Enter your name:");
			String name = key.next();
			System.out.println("Choose your pin:");
			int pin = key.nextInt();
			System.out.println("Choose you credit card limit: ");
			int limit =  0 - key.nextInt();
			list.addToHead(name, pin, 0, 0, 0, limit);
		} else if (account.equals("cancel")) {
			System.out.println("Enter your name:");
			String name = key.next();
			list.remove(name);
		} else if (account.equals("login")) {
			list.login();
		} else {
			System.out.println("Invalid command!");
		}
		}
		
	}
	
	
}
