package gg.AntiHax.checks.movement.Fly;

import gg.AntiHax.MainAC;
import gg.AntiHax.checks.Check;
import gg.AntiHax.checks.CheckType;
import gg.AntiHax.data.DataPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

public class FlyA extends Check {
    public FlyA(String name, CheckType type, boolean enabled, boolean punishable, int max) {
        super(name, type, enabled, punishable, max);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveEvent event) {
        DataPlayer data = MainAC.getInstance().getDataManager().getDataPlayer(event.getPlayer());

        if(data == null
                || event.getPlayer().getAllowFlight()
                || event.getPlayer().getVehicle() != null
                || data.inLiquid
                || data.onClimbable
                || System.currentTimeMillis() - data.lastVelocityTaken < 200L) return;

        float deltaY = (float) (event.getTo().getY() - event.getFrom().getY());


        if (data.airTicks > 4 && !event.getPlayer().isFlying() && data.)



    }




}
