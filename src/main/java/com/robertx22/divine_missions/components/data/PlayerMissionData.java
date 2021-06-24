package com.robertx22.divine_missions.components.data;

import com.robertx22.divine_missions.components.PlayerMissions;
import com.robertx22.divine_missions.configs.MissionsConfig;
import com.robertx22.divine_missions.main.DivineMissions;
import com.robertx22.divine_missions.util.MissionUtil;
import com.robertx22.library_of_exile.utils.RandomUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.HashMap;

@Storable
public class PlayerMissionData {

    @Store
    public HashMap<Integer, ItemStack> missions = new HashMap<>();
    @Store
    public int picks = 3;
    @Store
    public int mission_cd = 10000;

    public void onTick(int ticks, PlayerEntity player) {

        mission_cd -= ticks;

        if (mission_cd < 0) {
            generateNew(player);
        }

    }

    public void generateNew(PlayerEntity player) {

        this.missions = new HashMap<>();
        this.picks = RandomUtils.RandomRange(2, 4);

        for (int i = 0; i < 9; i++) {
            ItemStack stack = MissionUtil.createRandomMissionItem(player);

            missions.put(i, stack);
        }

        mission_cd = MissionsConfig.get().MINUTES_OF_COOLDOWN_TO_REFRESH_MISSIONS * 60 * 20;

        PlayerMissions.KEY.sync(player);

        player.sendMessage(new TranslatableText(DivineMissions.MODID + ".new_missions_arrived").formatted(Formatting.GOLD), false);

    }

}
