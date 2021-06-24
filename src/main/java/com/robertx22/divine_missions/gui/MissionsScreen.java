package com.robertx22.divine_missions.gui;

import com.robertx22.divine_missions.components.PlayerMissions;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.packets.PickMissionPacket;
import com.robertx22.library_of_exile.gui.ItemSlotButton;
import com.robertx22.library_of_exile.main.Packets;
import com.robertx22.library_of_exile.utils.CLOC;
import com.robertx22.library_of_exile.utils.GuiUtils;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Map;

public class MissionsScreen extends BaseScreen {

    static int X = 146;
    static int Y = 180;

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

        int picks = PlayerMissions.KEY.get(mc.player).data.picks;
        super.render(matrix, x, y, ticks);
        String txt = CLOC.translate(DivineMissions.ofTranslation("picks"))
            + ": " + picks;

        if (picks == 0) {
            txt = CLOC.translate(DivineMissions.ofTranslation("come_back_later"));
        }

        GuiUtils.renderScaledText(matrix, guiLeft + X / 2, guiTop + Y + 10, 1F, txt, Formatting.GREEN);

        buttons.forEach(b -> b.renderToolTip(matrix, x, y));

    }
}
