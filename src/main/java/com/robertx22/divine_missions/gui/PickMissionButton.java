package com.robertx22.divine_missions.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.divine_missions.components.PlayerMissions;
import com.robertx22.divine_missions.main.DivineMissions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;

public class PickMissionButton extends ImageButton {

    public int missionNumber;

    static ResourceLocation TEX = DivineMissions.id("textures/gui/pick.png");

    public PickMissionButton(int missionNumber, int x, int y, OnPress pressAction) {
        super(x, y, 16, 16, 0, 0, 0, DivineMissions.id("textures/gui/pick.png"), pressAction);
        this.missionNumber = missionNumber;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {

        Minecraft mc = Minecraft.getInstance();

        if (PlayerMissions.KEY.get(mc.player).data.picks > 0 && PlayerMissions.KEY.get(mc.player).data.missions.containsKey(missionNumber)) {

            mc.getTextureManager()
                .bind(TEX);

            RenderSystem.enableDepthTest();
            blit(matrices, this.x, this.y, 0, 0, this.width, this.height, 16, 16);

            if (this.isHovered()) {
                this.renderToolTip(matrices, mouseX, mouseY);
            }
        }

    }
}
