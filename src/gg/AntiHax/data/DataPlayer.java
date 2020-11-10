package gg.AntiHax.data;

import gg.AntiHax.MainAC;
import gg.AntiHax.utils.PastLocation;
import com.google.common.collect.Lists;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class DataPlayer {

    public Player player;
    public Object boundingBox;
    public boolean onGround, inLiquid, onStairSlab, onIce, onClimbable, underBlock, onSlime, nearGround;
    public int airTicks, groundTicks, iceTicks, liquidTicks, blockTicks, slimeTicks, velXTicks, velYTicks, velZTicks;
    public long lastVelocityTaken, lastAttack, lastServerKP, ping;
    public LivingEntity lastHitEntity;
    public PastLocation entityPastLocations = new PastLocation();
    public static double MAX_XZ_SPEED = 0.66D;

    public boolean inventoryOpen = false;

    /** Killaura **/
    public int killauraAVerbose;
    public long lastFlying;

    /** Pattern **/
    public List<Float> patterns = Lists.newArrayList();
    public float lastRange;

    /** Fly **/
    public float lastDeltaY, lastAccel;

    /**
     * Thresholds
     **/
    public int speedThreshold;
    public int reachThreshold;
    public float flyThreshold;

    public DataPlayer(Player player) {
        this.player = player;

        new BukkitRunnable() {
            public void run() {
                if(lastHitEntity != null) {
                    entityPastLocations.addLocation(lastHitEntity.getLocation());
                }
            }
        }.runTaskTimer(MainAC.getInstance(), 0L, 1L);
    }

    public boolean isVelocityTaken() {
        return velXTicks > 0 || velYTicks > 0 || velZTicks > 0;
    }

    public void reduceVelocity() {
        velXTicks = Math.max(0, velXTicks - 1);
        velYTicks = Math.max(0,velYTicks - 1);
        velZTicks = Math.max(0, velZTicks - 1);
    }



}