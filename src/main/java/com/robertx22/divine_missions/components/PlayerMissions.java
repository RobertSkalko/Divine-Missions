package com.robertx22.divine_missions.components;

import com.robertx22.divine_missions.components.data.PlayerMissionData;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.library_of_exile.utils.LoadSave;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerMissions implements PlayerComponent, AutoSyncedComponent {

    public static final ComponentKey<PlayerMissions> KEY = ComponentRegistry.getOrCreate(DivineMissions.id("missions"), PlayerMissions.class);

    static String LOC = "data";

    public PlayerEntity player;

    public PlayerMissionData data = new PlayerMissionData();

    public PlayerMissions(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.player;
    }

    @Override
    public boolean shouldCopyForRespawn(boolean lossless, boolean keepInventory) {
        return true;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.data = LoadSave.Load(PlayerMissionData.class, new PlayerMissionData(), tag, LOC);
        if (data == null) {
            data = new PlayerMissionData();
        }

        KEY.sync(player);
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        if (data != null) {
            LoadSave.Save(data, tag, LOC);
        }
    }
}
