package police_theif.vault;

/**
 * 금고 클래스 - 고정 위치 (10, 10) - 잔액 관리 (초기 10만원) - synchronized로 동시 접근 제어
 */
public class Vault {
	// === 변수 ===
	private int balance; // 현재 잔액
	private int totalStolen; // 총 도난액 누적
	private int totalRecovered; // 총 회수액 누적
	private final int x = 10; // 금고 X 좌표 (불변)
	private final int y = 10; // 금고 Y 좌표 (불변)

	// === 생성자 ===
	/**
	 * 금고 초기화
	 * 
	 * @param initialBalance 초기 잔액 (예: 100000)
	 */
	public Vault(int initialBalance) {
		if (initialBalance < 0)
			initialBalance = 0;
		this.balance = initialBalance;
		this.totalStolen = 0;
		this.totalRecovered = 0;
	}

	// === 메서드 ===
	/**
	 * 금고에서 돈 훔치기 - synchronized: 한 번에 1명만 접근 가능 - balance >= amount일 때만 훔침
	 * -totalStolen 누적
	 * 
	 * @param amount 훔칠 금액
	 */
	public synchronized void steal(int amount) {
		if (amount <= 0)
			return;
		if (balance >= amount) {
			balance -= amount;
			totalStolen += amount;
		}
		// 잔액 부족이면 아무 일도 안 함(스펙 유지)
	}

	/**
	 * 체포 시 금액 회수 - synchronized: 안전하게 증가 - totalRecovered 누적
	 * 
	 * @param amount 회수 금액
	 */
	public synchronized void recover(int amount) {
		if (amount <= 0)
			return;
		balance += amount;
		totalRecovered += amount;
	}

	/**
	 * 현재 잔액 조회 - synchronized: 읽기도 동기화 필요
	 * 
	 * @return 현재 잔액
	 */
	public synchronized int getBalance() {
		return balance;
	}

	/**
	 * 총 도난액 조회
	 */
	public synchronized int getTotalStolen() {
		return totalStolen;
	}

	/**
	 * 총 회수액 조회
	 */
	public synchronized int getTotalRecovered() {
		return totalRecovered;
	}

	/**
	 * 금고 X 좌표
	 */
	public int getX() {
		return x;
	}

	/**
	 * 금고 Y 좌표
	 */
	public int getY() {
		return y;
	}
}
