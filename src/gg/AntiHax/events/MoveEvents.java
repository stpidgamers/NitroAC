package gg.AntiHax.events;

import gg.AntiHax.data.DataPlayer;
import gg.AntiHax.utils.PlayerUtils;
import gg.AntiHax.utils.ReflectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

public class MoveEvents implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getFrom().getX() != event.getTo().getX()
                || event.getFrom().getY() != event.getTo().getY()
                || event.getFrom().getZ() != event.getTo().getZ()) {
            DataPlayer data = gg.AntiHax.MainAC.getInstance().getDataManager().getDataPlayer(player);

            if (data != null) {
                data.onGround = PlayerUtils.isOnGround(player);
                data.onStairSlab = PlayerUtils.isInStairs(player);
                data.inLiquid = PlayerUtils.isInLiquid(player);
                data.onIce = PlayerUtils.isOnIce(player);
                data.onClimbable = PlayerUtils.isOnClimbable(player);
                data.underBlock = PlayerUtils.inUnderBlock(player);
                data.onSlime = PlayerUtils.isOnSlime(player);

                data.boundingBox = ReflectionUtils.getBoundingBox(event.getPlayer());
                data.nearGround = ReflectionUtils.getCollidingBlocks(event.getPlayer(), ReflectionUtils
                        .modifyBoundingBox(data.boundingBox, 0, -1,0,0,0,0))
                        .size() > 0;

                if(event.getPlayer().isOnGround()) {
                    data.groundTicks++;
                    data.airTicks = 0;
                } else {
                    data.airTicks++;
                    data.groundTicks = 0;
                }

                data.reduceVelocity();

                data.iceTicks = Math.max(0, data.onIce ? Math.min(60, data.iceTicks + 3) : data.iceTicks - 1);
                data.liquidTicks = Math.max(0, data.inLiquid ? Math.min(40, data.liquidTicks + 1)  : data.liquidTicks - 1);
                data.blockTicks = Math.max(0, data.underBlock ? Math.min(60, data.blockTicks + 3)  : data.blockTicks - 1);
                data.slimeTicks = Math.max(0, data.onSlime ? Math.min(data.slimeTicks + 8, 60) : data.slimeTicks - 1);
            }
        }
    }

    @EventHandler
    public void onVelocity(PlayerVelocityEvent event) {
        DataPlayer data = gg.AntiHax.MainAC.getInstance().getDataManager().getDataPlayer(event.getPlayer());

        if(data == null) {
            return;
        }
        if(event.getVelocity().getY() > -0.078 || event.getVelocity().getY() < -0.08) {
            data.lastVelocityTaken = System.currentTimeMillis();
        }

        data.velXTicks = (int) Math.round(event.getVelocity().getX() * 100);
        data.velXTicks = (int) Math.round(event.getVelocity().getY() * 100);
        data.velXTicks = (int) Math.round(event.getVelocity().getZ() * 100);
    }
}