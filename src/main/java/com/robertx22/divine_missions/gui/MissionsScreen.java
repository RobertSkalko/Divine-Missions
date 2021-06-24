package com.robertx22.divine_missions.gui;

import com.robertx22.divine_missions.components.PlayerMissions;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.packets.PickMissionPacket;
import com.robertx22.library_of_exile.gui.ItemSlotButton;
import com.robertx22.library_of_exile.main.Packets;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Map;

public class MissionsScreen extends BaseScreen {

    public MissionsScreen() {
        super(146, 180);
    }

    static Identifier BACKGROUND = DivineMissions.id("textures/gui/background.png");

    @Override
    public void init() {
        super.init();

        PlayerMissions missions = PlayerMissions.KEY.get(mc.player);

        int startX = 25;

        int x = guiLeft + startX;
        int y = guiTop + startX;

        int i = 0;

        for (Map.Entry<Integer, ItemStack> entry : missions.data.missions.entrySet()) {

            ItemSlotButton itemslot = new ItemSlotButton(entry.getValue(), x, y);
            itemslot.renderFancyBorder = true;
            this.addButton(itemslot);

            this.addButton(new PickMissionButton(entry.getKey(), x + 1, y + 20, action -> {
                Packets.sendToServer(new PickMissionPacket(entry.getKey()));
            }));

            x += 40;

            i++;

            if (i % 3 == 0) {
                y += 40;
                x = guiLeft + startX;
            }
        }

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(MatrixStack matrix, int x, int y, float ticks) {

        renderBackground(matrix, BACKGROUND);

        super.render(matrix, x, y, ticks);

        buttons.forEach(b -> b.renderToolTip(matrix, x, y));

    }
}
