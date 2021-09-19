package com.robertx22.divine_missions.packets;

import com.robertx22.divine_missions.components.PlayerMissionCap;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.MissionItemData;
import com.robertx22.library_of_exile.main.MyPacket;
import com.robertx22.library_of_exile.packets.ExilePacketContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public class PickMissionPacket extends MyPacket<PickMissionPacket> {
    int num = 0;

    public PickMissionPacket(int num) {
        this.num = num;
    }

    @Override
    public ResourceLocation getIdentifier() {
        return DivineMissions.id("pick_mission");
    }

    @Override
    public void loadFromData(PacketBuffer buf) {
        this.num = buf.readInt();
    }

    @Override
    public void saveToData(PacketBuffer buf) {
        buf.writeInt(num);
    }

    @Override
    public void onReceived(ExilePacketContext ctx) {

        PlayerEntity player = ctx.getPlayer();

        PlayerMissionCap cap = PlayerMissionCap.get(player);

        //if (cap.missionData.picks > 0) {
        ItemStack stack = cap.missionData.missions.getOrDefault(num, ItemStack.EMPTY);
        if (stack != null && !stack.isEmpty()) {

            MissionItemData data = MissionItemData.SAVER.loadFrom(stack);
            PlayerMissionCap.get(player)
                .addReputation(-data.rep, data.getGod()); // upfront cost

            //cap.missionData.picks--;
            player.inventory.placeItemBackInInventory(player.level, stack);
            cap.missionData.missions.remove(num);

            cap.syncToClient(player);
        }
    }

    @Override
    public MyPacket newInstance() {
        return new PickMissionPacket(0);
    }
}
