package gg.AntiHax.events;

import gg.AntiHax.data.DataPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        gg.AntiHax.MainAC.getInstance().getDataManager().add(e.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent e) {
       gg.AntiHax.MainAC.getInstance().getDataManager().remove(e.getPlayer());
    }
}