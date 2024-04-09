package net.seanomik.tamablefoxes.versions.version_1_20_R3.pathfinding;

import java.util.EnumSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.seanomik.tamablefoxes.versions.version_1_20_R3.EntityTamableFox;

public class FoxPathfinderGoalSleepWhenOrdered extends Goal {
    private final EntityTamableFox mob;
    private boolean orderedToSleep;

    public FoxPathfinderGoalSleepWhenOrdered(EntityTamableFox entitytameableanimal) {
        this.mob = entitytameableanimal;
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    public boolean canContinueToUse() {
        return this.orderedToSleep;
    }

    public boolean canUse() {
        if (!this.mob.isTamed()) {
            return this.orderedToSleep && this.mob.getTarget() == null;
        } else if (this.mob.isInWaterOrBubble()) {
            return false;
        } else if (this.mob.isFallFlying()) {
            return false;
        } else {
            LivingEntity entityliving = this.mob.getOwner();
            return entityliving == null || ((!(this.mob.distanceToSqr(entityliving) < 144.0D) || entityliving.getLastHurtByMob() == null) && this.mob.isOrderedToSleep());
        }
    }

    public void start() {
        this.mob.getNavigation().stop();
        this.mob.setSleeping(true);
        this.orderedToSleep = true;
    }

    public void stop() {
        this.mob.setSleeping(false);
        this.orderedToSleep = false;
    }

    public boolean isOrderedToSleep() { return this.orderedToSleep; }

    public void setOrderedToSleep(boolean flag) {
        this.orderedToSleep = flag;
    }
}
