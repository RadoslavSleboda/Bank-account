package bankAccount;
import java.util.Scanner;
public class Account {
	
	Scanner key = new Scanner(System.in);
	
	Node head;
	Node tail;
	int year = 2000;
	double interestRate = 0.025;
	
	public Account() {
	this.head = null;
	this.tail = null;
	}
	public void addToHead(String name, int pin, int money, int loan, int loanLength, int maxMinus) {
		
		Node newHead = new Node(name, pin, money, loan, loanLength, maxMinus);
		if (this.tail == null) {
			this.tail = newHead;
		}
		if (this.head != null) {
			this.head.setPreviousNode(newHead);
			newHead.setNextNode(this.head);
	 }
		this.head = newHead;
	}
	
	public void remove(String name) {
		Node nodeToRemove = this.head;
		while (!nodeToRemove.name.equals(name)) {
			nodeToRemove = nodeToRemove.getNextNode();
			if (nodeToRemove == null) {
				System.out.println("Account does not exist!");
				return;
			}
		}
		if (nodeToRemove == this.head) {
			this.head = nodeToRemove.getNextNode();
			nodeToRemove.getNextNode().setPreviousNode(null);
			nodeToRemove.setNextNode(null);
		} else if (nodeToRemove == this.tail) {
			this.tail = nodeToRemove.getPreviousNode();
			nodeToRemove.getPreviousNode().setNextNode(null);
			nodeToRemove.setPreviousNode(null);
		} else {
			nodeToRemove.getNextNode().setPreviousNode(nodeToRemove.getPreviousNode());
			nodeToRemove.getPreviousNode().setNextNode(nodeToRemove.getNextNode());
			nodeToRemove.setNextNode(null);
			nodeToRemove.setPreviousNode(null);
		} 
		System.out.println("You have canceled your account");
		int repay = nodeToRemove.money - nodeToRemove.loan;
		if (repay >= 0) {
			System.out.println("You will get " + repay + " euros!");
		} else {
			System.out.println("You have to repay " + (0 - repay) + " euros");
		}
	}
	
	public void login() {
		Node currentNode = this.head;
		System.out.println("Enter your name:");
		String account = key.next();
		while (!account.equals(currentNode.name)) {
			currentNode = currentNode.getNextNode();
			if (currentNode == null) {
				System.out.println("Invalid name!");
				return;
			}
		}
	
		System.out.println("Enter your pin: ");
		for (int i = 3; i >= 0; i--) {
		int enterPin = key.nextInt();
		if (enterPin == currentNode.getPin()) {
			System.out.println("You have " + currentNode.money + " euros!");
			if (currentNode.loanLength > 0) {
			System.out.println("Your debt is " + currentNode.loan + " and you will be paying it for " + currentNode.loanLength + " years!");
			}
			menu(currentNode);
			break;
		} else {
			System.out.println("Try again!");
			System.out.println("You have " + i + " more attempt!");
		}
		if (i == 0) {
			System.out.println("Access denied!");
		}
		}
	}
	
	public void menu(Node node) {
		System.out.println("For deposit press 1!");
		System.out.println("For withdraw press 2!");
		System.out.println("For taking a loan press 3!");
		System.out.println("For logout press 4!");
		int choice = key.nextInt();
		 if (choice < 4 && choice > 0) {
		  if (choice == 1) {
			deposit(node);
		} else if (choice == 2) {
			withdraw(node);
		} else if (choice == 3) {
			loan(node);
		} else {
			return;
		}
		
	}
	}
	
	public void deposit(Node node) {
		System.out.println("Ammout to deposit: ");
		int toDeposit = key.nextInt();
		node.money += toDeposit;
		System.out.println("Your updated balance is: " + node.money);
		menu(node);
		
	}
	
	public void withdraw(Node node) {
		System.out.println("You can withdraw " + (node.money - node.maxMinus) + "euros!");
		System.out.println("Ammout to withdraw: ");
		int toWithdraw = key.nextInt();
		if (node.money - toWithdraw >= node.maxMinus) {
			node.money -= toWithdraw;
			System.out.println("Your updated balance is: " + node.money);
		} else {
			System.out.println("You cant withdraw that much money!");
			
		}
		menu(node);
	}
	
	public void loan(Node node) {
		System.out.println("Ammout to borrow: ");
		int toBorrow = key.nextInt();
		System.out.println("Number of years until loan is payd: ");
		int repayLength = key.nextInt();
		node.loan += toBorrow * (1 + repayLength * interestRate * 1.5);
		if (repayLength > node.loanLength) {
		node.loanLength = repayLength;
		}
		System.out.println("You gonna repay " + node.loan + " euros in " + node.loanLength + " years");
		menu(node);
	}
	
	public void time() {
		
		Node currentNode = this.head;
		System.out.println("Set how many years forward you wish to go:");
		int years = key.nextInt();
		for (int i = 1; i <= years; i++) {
		currentNode = this.head;
			while (currentNode != null) {	
			int yearPayment = 0;
			if (currentNode.loanLength > 0) {
			yearPayment = currentNode.loan / currentNode.loanLength;
			} 
			if (currentNode.money < 0) {
				currentNode.money = (int) (currentNode.money + (currentNode.money * 4 * interestRate) - (yearPayment));
			} else {
				currentNode.money += (int) ((currentNode.money * interestRate) - (yearPayment));
				}
				if (currentNode.loanLength > 0) {
					currentNode.loanLength--;
					currentNode.loan -= yearPayment;
				} 
		currentNode = currentNode.getNextNode();
		}
		interestUpdate();
		this.year++;
	}
		System.out.println("Year " + this.year);
		System.out.println("Current interest rate is " + 100 * interestRate + " %");
		}
	public void interestUpdate() {
		interestRate += 0.03 * Math.pow(Math.random(), 2);
		interestRate -= 0.015 * Math.random();
		interestRate = 0.5 * (interestRate + 0.025);
	}
	
	}

