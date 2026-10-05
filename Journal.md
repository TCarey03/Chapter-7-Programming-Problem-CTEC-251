Phase 1

Adapter Pattern

The Adapter pattern helps follow the Principle of Least Knowledge because the main program only needs to know about the SecurityLog interface. It does not need to know the specific method names or implementation details of the LegacyFirewall.

The FirewallAdapter acts as a middle layer between the modern interface and the older firewall. When the main program calls logEvent(), the adapter translates that call into the legacy firewall's recordActivity() method. The same thing happens with setSeverity() and setAlertLevel().

This is better than having the main dashboard directly work with LegacyFirewall because it keeps the old firewall's details out of the main program. If the legacy firewall changes, the adapter can handle those changes without requiring the dashboard code to know about them.
