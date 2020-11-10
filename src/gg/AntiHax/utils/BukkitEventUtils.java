package gg.AntiHax.utils;

import gg.AntiHax.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;

public class BukkitEventUtils implements Listener {

    @EventHandler
    public void isAttacking(EntityDamageByEntityEvent event){
        PlayerData data = new PlayerData();
        if (event instanceof Player){
            data.setAttacking(true);
        }
    }

    @EventHandler
    public void isInventoryOpen(Inventory e){
        PlayerData data = new PlayerData();
        data.setInventoryOpen(true);
    }
}
