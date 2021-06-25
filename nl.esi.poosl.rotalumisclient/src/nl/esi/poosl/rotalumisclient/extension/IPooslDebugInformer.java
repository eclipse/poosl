package nl.esi.poosl.rotalumisclient.extension;

public interface IPooslDebugInformer {
	void debugSelectionChanged(ExternDebugItem object, ExternDebugMessage message);

	void launchStopped(String launchID);

	void lastMessageChanged(ExternDebugMessage lastMessage);

	void launchStart(ExternLaunchStartMessage object);
}
