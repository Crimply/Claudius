package me.crimp.claudius.mod.modules.render;

import me.crimp.claudius.event.events.PacketEvent;
import me.crimp.claudius.event.events.Render3DEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.modules.client.ClickGuiModule;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.ColorUtil;
import me.crimp.claudius.utils.RenderUtil;
import me.crimp.claudius.utils.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class ExplosionChams extends Module {
    public final Setting<Integer> red = this.register(new Setting<>("Red", 30, 0, 255));
    public final Setting<Integer> green = this.register(new Setting<>("Green", 167, 0, 255));
    public final Setting<Integer> blue = this.register(new Setting<>("Blue", 255, 0, 255));
    public final Setting<Integer> alpha = this.register(new Setting<>("Alpha", 150, 0, 255));
    public final Setting<Float> increase = this.register(new Setting<>("Increase Size", 0.5f, 0.1f, 5.0f));
    public final Setting<Integer> riseSpeed = this.register(new Setting<>("Rise Time", 5, 1, 50));
    public Map<EntityEnderCrystal, BlockPos> explodedCrystals = new HashMap<>();
    public BlockPos crystalPos = null;
    public int aliveTicks = 0;
    public double speed = 0.0;
    public Timer timer = new Timer();

    public ExplosionChams() {
        super("ExplosionChams", "Draws a cham when a crystal explodes", Category.Render, false, false);
    }

    @Override
    public void onEnable() {
        this.explodedCrystals.clear();
    }

    @Override
    public void onUpdate() {
        if (ExplosionChams.fullNullCheck()) {
            return;
        }
        ++this.aliveTicks;
        if (this.timer.passedMs(5L)) {
            this.speed += 0.5;
            this.timer.reset();
        }
        if (this.speed > (double) this.riseSpeed.getValue()) {
            this.speed = 0.0;
            this.explodedCrystals.clear();
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        try {
            for (Entity crystal : ExplosionChams.mc.world.loadedEntityList) {
                if (!(crystal instanceof EntityEnderCrystal) || !(event.getPacket() instanceof SPacketExplosion)) continue;
                this.crystalPos = new BlockPos(crystal.posX, crystal.posY, crystal.posZ);
                this.explodedCrystals.put((EntityEnderCrystal)crystal, this.crystalPos);
            }
        }
        catch (Exception ignore) {
        }
    }
    int color = ColorUtil.toRGBA(ClickGuiModule.INSTANCE.red.getValue(), ClickGuiModule.INSTANCE.green.getValue(), ClickGuiModule.INSTANCE.blue.getValue());
    @Override
    @SubscribeEvent
    public void onRender3D(Render3DEvent event) {
        if (!this.explodedCrystals.isEmpty()) {
            RenderUtil.drawCircle(this.crystalPos.getX(), (float)this.crystalPos.getY() + (float)this.speed / 3.0f + 0.7f, this.crystalPos.getZ(), 0.6f + this.increase.getValue(), color - 60);
            RenderUtil.drawCircle(this.crystalPos.getX(), (float)this.crystalPos.getY() + (float)this.speed / 3.0f + 0.6f, this.crystalPos.getZ(), 0.5f + this.increase.getValue(), color - 50);
            RenderUtil.drawCircle(this.crystalPos.getX(), (float)this.crystalPos.getY() + (float)this.speed / 3.0f + 0.5f, this.crystalPos.getZ(), 0.4f + this.increase.getValue(), color - 40);
            RenderUtil.drawCircle(this.crystalPos.getX(), (float)this.crystalPos.getY() + (float)this.speed / 3.0f + 0.4f, this.crystalPos.getZ(), 0.3f + this.increase.getValue(), color - 30);
            RenderUtil.drawCircle(this.crystalPos.getX(), (float)this.crystalPos.getY() + (float)this.speed / 3.0f + 0.3f, this.crystalPos.getZ(), 0.2f + this.increase.getValue(), color - 20);
            RenderUtil.drawCircle(this.crystalPos.getX(), (float)this.crystalPos.getY() + (float)this.speed / 3.0f + 0.2f, this.crystalPos.getZ(), 0.1f + this.increase.getValue(), color - 10);
            RenderUtil.drawCircle(this.crystalPos.getX(), (float)this.crystalPos.getY() + (float)this.speed / 3.0f + 0.1f, this.crystalPos.getZ(), 0.0f + this.increase.getValue(), color);
        }
    }
}
