import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Phase 1 - Adapter
        LegacyFirewall legacyFirewall = new LegacyFirewall();
        SecurityLog firewall = new FirewallAdapter(legacyFirewall);

        firewall.logEvent("Suspicious network activity detected.");
        firewall.setSeverity(5);

        System.out.println();

        // Phase 2 - Create the security subsystems
        NetworkTrafficController network = new NetworkTrafficController();
        UserAccessManager users = new UserAccessManager();
        EncryptionService encryption = new EncryptionService();

        List<String> compromisedUsers = List.of(
                "admin_temp",
                "guest_user_1",
                "service_acct"
        );

        // Emergency Breach
        System.out.println("=== EMERGENCY BREACH ===");

        network.blockPort(8080);
        network.blockPort(443);

        users.lockUserAccounts(compromisedUsers);

        encryption.encryptDatabase("Customer_Records");

        network.divertTraffic();

        System.out.println();

        // All-Clear
        System.out.println("=== ALL-CLEAR ===");

        network.unblockPort(8080);
        network.unblockPort(443);

        users.unlockUserAccounts(compromisedUsers);

        encryption.decryptDatabase("Customer_Records");
    }
}
