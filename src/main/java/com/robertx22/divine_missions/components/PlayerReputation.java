package com.robertx22.divine_missions.components;

import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.db_types.God;
import com.robertx22.divine_missions.database.db_types.ReputationLevel;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.library_of_exile.utils.LoadSave;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerReputation implements PlayerComponent, AutoSyncedComponent {

    public static final ComponentKey<PlayerReputation> KEY = ComponentRegistry.getOrCreate(DivineMissions.id("reputation"), PlayerReputation.class);

    public static String ID = "reputation";
    static String LOC = "data";

    public PlayerEntity player;

    public PlayerRepudationData data = new PlayerRepudationData();

    public PlayerReputation(PlayerEntity player) {
        this.player = player;
    }

    public ReputationLevel getReputationLevel(God god) {

        List<ReputationLevel> can = new ArrayList<>();

        int rep = data.getReputation(god);

        MissionsDB.ReputationLevels()
            .getList()
            .forEach(x -> {
                if (x.rep_needed >= 0) {
                    if (rep >= x.rep_needed) {
                        can.add(x);
                    }
                } else {
                    if (rep <= x.rep_needed) {
                        can.add(x);
                    }
                }
            });

        ReputationLevel repLevel = can.stream()
            .max(Comparator.comparingInt(x -> x.rank))
            .get();

        return repLevel;
    }

    public void addReputation(int amount, God god) {
        data.addReputation(amount, god);
        KEY.sync(player);
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

        this.data = LoadSave.Load(PlayerRepudationData.class, new PlayerRepudationData(), tag, LOC);

        if (data == null) {
            data = new PlayerRepudationData();
        }
    }

    @Override
    public void writeToNbt(NbtCompound tag) {

        if (data != null) {
            LoadSave.Save(data, tag, LOC);
        }

    }
}
