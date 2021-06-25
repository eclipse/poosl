package nl.esi.poosl.rotalumisclient.debug.credit;

public class PooslMessageCreditor {
	private static final int WINDOW = 50;
	private static final int THRESHOLD = 5;

	private int availableCredits = WINDOW;

	private final IPooslCreditReceiver receiver;

	public PooslMessageCreditor(IPooslCreditReceiver receiver) {
		this.receiver = receiver;
	}

	public void useCredit() {
		availableCredits--;
		if (availableCredits < THRESHOLD) {
			availableCredits += WINDOW;
			receiver.receiveCredits(WINDOW);
		}
	}

	public int getCurrentMax() {
		return WINDOW;
	}
}
