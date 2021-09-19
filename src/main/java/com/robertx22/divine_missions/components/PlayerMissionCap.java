package com.robertx22.divine_missions.components;

import com.robertx22.divine_missions.components.data.PlayerMissionData;
import com.robertx22.divine_missions.components.data.PlayerRepudationData;
import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.db_types.God;
import com.robertx22.divine_missions.database.db_types.ReputationLevel;
import com.robertx22.library_of_exile.components.forge.BaseProvider;
import com.robertx22.library_of_exile.components.forge.BaseStorage;
import com.robertx22.library_of_exile.components.forge.IPlayerCap;
import com.robertx22.library_of_exile.main.Ref;
import com.robertx22.library_of_exile.utils.LoadSave;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Mod.EventBusSubscriber
public class PlayerMissionCap implements IPlayerCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "player_missions");

    @CapabilityInject(PlayerMissionCap.class)
    public static final Capability<PlayerMissionCap> Data = null;

    public static PlayerMissionCap get(PlayerEntity entity) {
        return entity.getCapability(Data)
            .orElse(null);
    }

    @Override
    public String getCapIdForSyncing() {
        return "player_missions";
    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof PlayerEntity) {
                event.addCapability(RESOURCE, new Provider((PlayerEntity) event.getObject()));
            }
        }
    }

    public static class Provider extends BaseProvider<PlayerMissionCap, PlayerEntity> {
        public Provider(PlayerEntity owner) {
            super(owner);
        }

        @Override
        public PlayerMissionCap newDefaultImpl(PlayerEntity owner) {
            return new PlayerMissionCap(owner);
        }

        @Override
        public Capability<PlayerMissionCap> dataInstance() {
            return Data;
        }
    }

    public static class Storage implements BaseStorage<PlayerMissionCap> {

    }

    public PlayerMissionData missionData = new PlayerMissionData();
    public PlayerRepudationData repData = new PlayerRepudationData();

    public PlayerEntity owner;

    public PlayerMissionCap(PlayerEntity en) {
        this.owner = en;
    }

    static String MISSIN_DATA_LOC = "mission_data";
    static String REP_LOC = "rep";

    public ReputationLevel getReputationLevel(God god) {

        List<ReputationLevel> can = new ArrayList<>();

        int rep = repData.getReputation(god);

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

        if (can.isEmpty()) {
            can.add(MissionsDB.ReputationLevels()
                .get("neutral"));
        }

        ReputationLevel repLevel = can.stream()
            .max(Comparator.comparingInt(x -> x.rank))
            .get();

        return repLevel;
    }

    public void addReputation(int amount, God god) {

        ReputationLevel level = getReputationLevel(god);

        repData.addReputation(amount, god);
        ReputationLevel after = getReputationLevel(god);

        if (level.GUID()
            .equals(after.GUID()) == false) {

            owner.displayClientMessage(new TranslationTextComponent("divine_missions.new_reputation_message",
                after.getTranslatable()
                    .withStyle(after.getFormat()),
                god.getTranslatable()
                    .withStyle(god.getFormat())), false);
        }
    }

    @Override
    public CompoundNBT saveToNBT() {

        CompoundNBT nbt = new CompoundNBT();
        try {
            if (missionData != null) {
                LoadSave.Save(missionData, nbt, MISSIN_DATA_LOC);
            }
            if (repData != null) {
                LoadSave.Save(repData, nbt, REP_LOC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nbt;

    }

    @Override
    public void loadFromNBT(CompoundNBT nbt) {
        try {
            this.missionData = LoadSave.Load(PlayerMissionData.class, new PlayerMissionData(), nbt, MISSIN_DATA_LOC);
            if (missionData == null) {
                missionData = new PlayerMissionData();
            }
            this.repData = LoadSave.Load(PlayerRepudationData.class, new PlayerRepudationData(), nbt, REP_LOC);
            if (repData == null) {
                repData = new PlayerRepudationData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
