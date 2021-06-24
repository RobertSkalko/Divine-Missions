package com.robertx22.divine_missions.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.divine_missions.components.PlayerMissions;
import com.robertx22.divine_missions.main.DivineMissions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PickMissionButton extends TexturedButtonWidget {

    public int missionNumber;

    static Identifier TEX = DivineMissions.id("textures/gui/pick.png");

    public PickMissionButton(int missionNumber, int x, int y, PressAction pressAction) {
        super(x, y, 16, 16, 0, 0, 0, DivineMissions.id("textures/gui/pick.png"), pressAction);
        this.missionNumber = missionNumber;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        MinecraftClient mc = MinecraftClient.getInstance();

        if (PlayerMissions.KEY.get(mc.player).data.picks > 0 && PlayerMissions.KEY.get(mc.player).data.missions.containsKey(missionNumber)) {

            mc.getTextureManager()
                .bindTexture(TEX);

            RenderSystem.enableDepthTest();
            drawTexture(matrices, this.x, this.y, 0, 0, this.width, this.height, 16, 16);

            if (this.isHovered()) {
                this.renderToolTip(matrices, mouseX, mouseY);
            }
        }

    }
}
