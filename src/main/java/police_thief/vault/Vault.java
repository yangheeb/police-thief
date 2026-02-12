package police_thief.vault;

public class Vault {
	private int balance; // 현재 잔액
	private int totalStolen; // 총 누적 도난액
	private int totalRecovered; // 총 누적 회수액
	private final int x = 10; // 금고 X 좌표
	private final int y = 10; // 금고 Y 좌표

	// 생성자
	public Vault(int initialBalance) {
		if (initialBalance < 0)
			initialBalance = 0;
		this.balance = initialBalance;
		this.totalStolen = 0;
		this.totalRecovered = 0;
	}

	/*
	 * 도둑이 돈을 훔칠 때 호출하는 메서드 동시에 여러 스레드가 들어오지 못하게 잠금(lock) 걸기! -> synchronized
	 * 
	 * 현재 잔액이 훔칠 금액보다 충분할 때만 실행
	 */
	public synchronized void steal(int amount) {
		if (amount <= 0)
			return;
		if (balance >= amount) {
			balance -= amount;
			totalStolen += amount;
		}
	}

	/*
	 * 경찰이 체포해서 돈을 되찾을 때 호출 동시성 안전하게 처리 -> synchronized
	 */
	public synchronized void recover(int amount) {
		if (amount <= 0)
			return;
		balance += amount;
		totalRecovered += amount;
	}

	/*
	 * 현재 잔액 조회 읽는 순간에도 다른 스레드가 바꾸면 값이 엇갈릴 수 있어서 synchronized로 보호해 일관된 값을 보장
	 */
	public synchronized int getBalance() {
		return balance;
	}

	// 총 도난액 조회
	public synchronized int getTotalStolen() {
		return totalStolen;
	}

	// 총 회수액 조회
	public synchronized int getTotalRecovered() {
		return totalRecovered;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
