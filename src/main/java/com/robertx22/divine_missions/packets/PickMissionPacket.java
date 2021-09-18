package com.robertx22.divine_missions.packets;

import com.robertx22.divine_missions.components.PlayerMissions;
import com.robertx22.divine_missions.components.PlayerReputation;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.saving.MissionItemData;
import com.robertx22.library_of_exile.main.MyPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent.Context;

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
    public void onReceived(Context ctx) {

        PlayerEntity player = ctx.getPlayer();

        PlayerMissions missions = PlayerMissions.KEY.get(player);

        if (missions.data.picks > 0) {
            ItemStack stack = missions.data.missions.getOrDefault(num, ItemStack.EMPTY);
            if (stack != null && !stack.isEmpty()) {

                MissionItemData data = MissionItemData.SAVER.loadFrom(stack);
                PlayerReputation.KEY.get(player)
                    .addReputation(-data.rep, data.getGod()); // upfront cost

                missions.data.picks--;
                player.inventory.placeItemBackInInventory(player.level, stack);
                missions.data.missions.remove(num);

                PlayerMissions.KEY.sync(player);
            }
        } else {

        }

    }

    @Override
    public MyPacket newInstance() {
        return new PickMissionPacket(0);
    }
}
