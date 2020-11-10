package gg.AntiHax.data;

import org.bukkit.entity.Player;

public class PlayerData {
    public boolean isSprinting;
    public boolean isAttacking;
    public boolean isOnFire;
    public boolean isInventoryOpen;
    public float currentSpeed, speedTicks;

    public boolean isInventoryOpen() {
        return isInventoryOpen;
    }

    public void setInventoryOpen(boolean inventoryOpen) {
        isInventoryOpen = inventoryOpen;
    }

    public boolean isSprinting() {
        return isSprinting;
    }

    public void setSprinting(boolean sprinting) {
        isSprinting = sprinting;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public boolean isOnFire(Player p) {
        if (p.getFireTicks() > 0) {
            return true;
        }
        return false;
    }

    public void setOnFire(boolean onFire) {
        isOnFire = onFire;
    }

    public float getCurrentSpeed(Player p) {
        return p.getWalkSpeed();
    }

    public void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public float getSpeedTicks(Player p) {
        if (p.getWalkSpeed() > DataPlayer.MAX_XZ_SPEED) {
            setSpeedTicks(speedTicks++);
        }
        return speedTicks;
    }

    public void setSpeedTicks(float speedTicks) {
        this.speedTicks = speedTicks;
    }


}
