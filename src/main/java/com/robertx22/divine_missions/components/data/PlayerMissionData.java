package com.robertx22.divine_missions.components.data;

import com.robertx22.divine_missions.components.PlayerMissionCap;
import com.robertx22.divine_missions.configs.MissionsConfig;
import com.robertx22.divine_missions.mission_gen.MissionUtil;
import com.robertx22.library_of_exile.utils.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

@Storable
public class PlayerMissionData {

    @Store
    public HashMap<Integer, ItemStack> missions = new HashMap<>();

    @Store
    public int mission_cd = 10000;

    public void onTick(int ticks, PlayerEntity player) {

        if (mission_cd > 0) {
            mission_cd -= ticks;
        } else {
            generateNew(player);
        }

    }

    public void generateNew(PlayerEntity player) {

        for (int i = 0; i < 9; i++) {
            if (missions.getOrDefault(i, ItemStack.EMPTY)
                .isEmpty()) {
                ItemStack stack = MissionUtil.createRandomMissionItem(player);
                missions.put(i, stack);
                break;
            }
        }

        int cd = MissionsConfig.get().MIN_TO_GAIN_NEW_MISSIONS.get() * 60 * 20;

        int cdmin = cd - (int) (cd * MissionsConfig.get().MISSION_REFRESH_COOLDOWN_VARIATION.get());
        int cdmax = cd + (int) (cd * MissionsConfig.get().MISSION_REFRESH_COOLDOWN_VARIATION.get());

        this.mission_cd = RandomUtils.RandomRange(cdmin, cdmax);

        PlayerMissionCap.get(player)
            .syncToClient(player);

        // player.displayClientMessage(new TranslationTextComponent(DivineMissions.MODID + ".new_missions_arrived").withStyle(TextFormatting.GOLD), false);

    }

}
