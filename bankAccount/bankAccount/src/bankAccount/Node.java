package bankAccount;

public class Node {
	
	String name;
	private int pin;
	int money;
	int loan;
	int loanLength;
	int maxMinus;
	Node next;
	Node previous;
	
	public Node(String name, int password, int money, int loan, int loanLength, int maxMinus) {
		this.name = name;
		this.pin = password;
		this.money = money;
		this.loan = loan;
		this.loanLength = loanLength;
		this.maxMinus = maxMinus;
		next = null;
		previous = null;
	}
	
	public int getPin() {
		return pin;
	}
	
	public void setNextNode(Node node) {
		this.next = node;
	}
	public Node getNextNode() {
		return next;
	}
	public void setPreviousNode(Node node) {
		this.previous = node;
	}
	public Node getPreviousNode() {
		return previous;
	}
	
	
}
