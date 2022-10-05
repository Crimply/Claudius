package me.crimp.claudius.mod.modules.misc;

import com.mojang.authlib.GameProfile;
import me.crimp.claudius.event.events.TotemPopEvent;
import me.crimp.claudius.mod.modules.Module;
import me.crimp.claudius.mod.modules.client.ClickGuiModule;
import me.crimp.claudius.mod.modules.pvp.PopLagger;
import me.crimp.claudius.mod.setting.Setting;
import me.crimp.claudius.utils.PopChamsUtil;
import me.crimp.claudius.utils.Timer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PopChams extends Module {
    public static PopChams INSTANCE = new PopChams();

    public  final Setting<Integer> fillred = this.register(new Setting<>("FillRed", 167, 0, 255));
    public  final Setting<Integer> fillgreen = this.register(new Setting<>("FillGreen", 215, 0, 255));
    public  final Setting<Integer> fillblue = this.register(new Setting<>("FillBlue", 233, 0, 255));
    public  final Setting<Integer> fillalpha = this.register(new Setting<>("FillAlpha", 50, 0, 255));
    public  final Setting<Integer> lineRed = this.register(new Setting<>("LineRed", 250, 0, 255));
    public  final Setting<Integer> lineGreen = this.register(new Setting<>("LineGreen", 185, 0, 255));
    public  final Setting<Integer> lineBlue = this.register(new Setting<>("LineBlue", 212, 0, 255));
    public  final Setting<Integer> lineAlpha = this.register(new Setting<>("LineAlpha", 50, 0, 255));
    public  final Setting<Integer> fadestart = this.register(new Setting<>("FadeStart", 1238, 0, 3000));
    public  final Setting<Boolean> Golobal = this.register(new Setting<>("Golobal", false));
    public final Setting<Float> fadeTime = this.register(new Setting<>("FadeTime", 0.4f, 0.0f, 2.0f));
    public  final Setting<Boolean> smallArms = this.register(new Setting<>("SmallArms", false));
    public  final Setting<Boolean> self = this.register(new Setting<>("Self", false));
    EntityOtherPlayerMP player;
    ModelPlayer playerModel;
    long startTime;
    double alphaFill;
    double alphaLine;
    Timer followTimer = new Timer();

    public PopChams() {
        super("PopChams", "Cool pop render", Category.RENDER,true,false,false);
        INSTANCE = this;
    }

    @Override
    public void onUpdate() {
        if (Golobal.equals(true)) {
           // lineRed = ClickGuiModule.INSTANCE.red;
        }
    }


    @SubscribeEvent
    public void onPop(TotemPopEvent event) {
        followTimer.reset();
        if (self.getValue() || event.getEntity().getEntityId() != PopChams.mc.player.getEntityId()) {
                GameProfile profile = new GameProfile(PopChams.mc.player.getUniqueID(), "");
                this.player = new EntityOtherPlayerMP(PopChams.mc.world, profile);
                this.player.copyLocationAndAnglesFrom(event.getEntity());
                this.playerModel = new ModelPlayer(0.0f, smallArms.getValue());
                this.startTime = System.currentTimeMillis();
                this.playerModel.bipedHead.showModel = false;
                this.playerModel.bipedBody.showModel = false;
                this.playerModel.bipedLeftArmwear.showModel = false;
                this.playerModel.bipedLeftLegwear.showModel = false;
                this.playerModel.bipedRightArmwear.showModel = false;
                this.playerModel.bipedRightLegwear.showModel = false;
                this.alphaFill = fillalpha.getValue();
                this.alphaLine = lineAlpha.getValue();
                PopChamsUtil popChamsUtil = new PopChamsUtil(this.player, this.playerModel, this.startTime, this.alphaFill, this.alphaLine);
            }
        }
    }