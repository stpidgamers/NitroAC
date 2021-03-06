package gg.AntiHax.checks;

import gg.AntiHax.MainAC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.WeakHashMap;

public abstract class Check implements Listener {

    protected String name;
    protected CheckType type;
    protected boolean enabled;
    protected boolean punishable;
    protected int max;

    public Map<Player, Integer> violations = new WeakHashMap<>();

    public Check(String name, CheckType type, boolean enabled, boolean punishable, int max) {
        this.name = name;
        this.type = type;
        this.enabled = enabled;
        this.punishable = punishable;
        this.max = max;

        Bukkit.getPluginManager().registerEvents(this, MainAC.getInstance());
    }

    protected void flag(Player player, String... information) {
        int violations = this.violations.getOrDefault(player, 0) + 1;
        if (information != null) {
            StringBuilder formattedInfo = new StringBuilder();
            for (String string : information) {
                formattedInfo.append(string).append(", ");
            }
            for (Player staff : Bukkit.getOnlinePlayers()) {
                if (staff.hasPermission("anticheat.staff")) {
                    staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c&lAntiHax&8] &e" + player.getName() + " &7has been detected for &e" + name + " &8[" + formattedInfo.toString() + "&8]" + "&8(&c" + violations + "&8)"));
                }
            }
        } else {
            for (Player staff : Bukkit.getOnlinePlayers()) {
                if (staff.hasPermission("anticheat.staff")) {
                    staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c&lAntiHax&8] &e" + player.getName() + " &7has been detected for &e" + name + "&8(&c" + violations + "&8)"));
                }
            }
        }
        if (violations > max) {
            player.kickPlayer("§4You Have Been Banned!\n\nReason : §7Unfair Advantage");
            player.setBanned(true);
            Bukkit.broadcastMessage("§4§lAntiHax : I Have Banned " + name + " For : §7Unfair Advantage");
        }
        this.violations.put(player, violations);
    }
}