import java.util.List;

public class CommandCenterFacade {

    private NetworkTrafficController network;
    private UserAccessManager users;
    private EncryptionService encryption;

    private List<String> compromisedUsers;
    private final int port1 = 8080;
    private final int port2 = 443;
    private final String database = "Customer_Records";

    public CommandCenterFacade(
            NetworkTrafficController network,
            UserAccessManager users,
            EncryptionService encryption) {

        this.network = network;
        this.users = users;
        this.encryption = encryption;

        compromisedUsers = List.of(
                "admin_temp",
                "guest_user_1",
                "service_acct"
        );
    }

    public void initiateEmergencyLockdown() {
        System.out.println("=== EMERGENCY LOCKDOWN ===");

        network.blockPort(port1);
        network.blockPort(port2);

        users.lockUserAccounts(compromisedUsers);

        encryption.encryptDatabase(database);

        System.out.println("Emergency lockdown complete.");
    }

    public void liftEmergencyLockdown() {
        System.out.println("=== LIFTING EMERGENCY LOCKDOWN ===");

        network.unblockPort(port1);
        network.unblockPort(port2);

        users.unlockUserAccounts(compromisedUsers);

        encryption.decryptDatabase(database);

        System.out.println("Emergency lockdown lifted.");
    }

    public void enableMaintenanceMode() {
        System.out.println("=== MAINTENANCE MODE ===");

        network.divertTraffic();

        users.grantAdminAccess("security_admin");

        encryption.verifyIntegrity();

        System.out.println("Maintenance mode enabled.");
    }
}