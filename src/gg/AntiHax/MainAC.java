package gg.AntiHax;

import gg.AntiHax.checks.CheckManager;
import gg.AntiHax.data.DataManager;
import gg.AntiHax.events.JoinQuitEvents;
import gg.AntiHax.events.MoveEvents;
import gg.AntiHax.utils.BukkitEventUtils;
import gg.AntiHax.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class MainAC extends JavaPlugin {

    private static gg.AntiHax.MainAC instance;
    private CheckManager checkManager;
    private DataManager dataManager;
    public boolean enabled;

    @Override
    public void onEnable() {
        instance = this;
        checkManager = new CheckManager();
        dataManager = new DataManager();

        new ReflectionUtils();

        Bukkit.getPluginManager().registerEvents(new MoveEvents(), this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitEvents(), this);
        Bukkit.getPluginManager().registerEvents(new BukkitEventUtils(), this);
        enabled = true;
    }

    public void onDisable() {
        enabled = false;
        HandlerList.unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static gg.AntiHax.MainAC getInstance() {
        return instance;
    }

    public CheckManager getCheckManager() {
        return checkManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
