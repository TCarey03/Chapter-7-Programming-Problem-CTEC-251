Phase 1

Adapter Pattern

The Adapter pattern helps follow the Principle of Least Knowledge because the main program only needs to know about the SecurityLog interface. It does not need to know the specific method names or implementation details of the LegacyFirewall.

The FirewallAdapter acts as a middle layer between the modern interface and the older firewall. When the main program calls logEvent(), the adapter translates that call into the legacy firewall's recordActivity() method. The same thing happens with setSeverity() and setAlertLevel().

This is better than having the main dashboard directly work with LegacyFirewall because it keeps the old firewall's details out of the main program. If the legacy firewall changes, the adapter can handle those changes without requiring the dashboard code to know about them.

--------------------------

Phase 2

Subsystem Setup & Direct Interaction

In Phase 2, my Main class had to keep track of which ports and users were affected during the emergency. I stored the compromised users in a list so I could use the same list when locking and unlocking the accounts. I also had to remember the two ports, 8080 and 443, so I could unblock them during the All-Clear sequence.

This manual approach could become dangerous if the system grew to handle 1,000 ports and 10,000 users. It would be easy to forget to restore one of the ports or accounts. The Main class would also become very large and difficult to maintain. Having to remember every action and its reverse action increases the chance of making a mistake during a real emergency.

--------------------------

Phase 3

Facade Pattern & Integration

In Phase 2, the Main class had to directly interact with the NetworkTrafficController, UserAccessManager, and EncryptionService. This made the client code more complicated because Main had to know every step required for an emergency lockdown and the All-Clear.

In Phase 3, I created the CommandCenterFacade to provide a simpler interface. Instead of manually calling each subsystem method, Main can call methods such as initiateEmergencyLockdown() and liftEmergencyLockdown(). The Facade handles all of the individual steps.

If the NetworkTrafficController were replaced with a different vendor's version, the Main class would not necessarily need to change. The Facade could be updated to work with the new controller while keeping the same high-level methods that Main uses. This reduces the amount of system-specific knowledge that the client needs to have.
