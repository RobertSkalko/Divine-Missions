package com.robertx22.divine_missions.util;

import com.robertx22.divine_missions.components.PlayerReputation;
import com.robertx22.divine_missions.database.MissionsDB;
import com.robertx22.divine_missions.database.db_types.*;
import com.robertx22.divine_missions.item.MissionItem;
import com.robertx22.divine_missions.main.ModItems;
import com.robertx22.divine_missions.saving.MissionItemData;
import com.robertx22.divine_missions.saving.RewardData;
import com.robertx22.divine_missions.saving.TaskData;
import com.robertx22.library_of_exile.utils.RandomUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MissionUtil {

    public static ItemStack createRandomMissionItem(PlayerEntity player) {
        MissionItemData data = createRandom(player);

        ItemStack stack = new ItemStack(ModItems.INSTANCE.MISSION_ITEM);

        MissionItemData.SAVER.saveTo(stack, data);

        int timelimit = data.tasks.stream()
            .mapToInt(x -> x.getTaskEntry().seconds * x.req)
            .sum() * 20;

        stack.getTag()
            .putInt(MissionItem.TICKS, timelimit);

        return stack;
    }

    public static MissionItemData createRandom(PlayerEntity player) {

        MissionItemData data = new MissionItemData();

        PlayerReputation reps = PlayerReputation.KEY.get(player);

        List<GodWeight> weights = new ArrayList<>();
        for (God god : MissionsDB.Gods()
            .getList()) {
            int weight = (int) (god.Weight() * reps.getReputationLevel(god).weight_multi);
            weights.add(new GodWeight(god, weight));
        }
        God god = RandomUtils.weightedRandom(weights).god;
        ReputationLevel rep = reps.getReputationLevel(god);
        MissionRarity rar = RandomUtils.weightedRandom(MissionsDB.MissionRarities()
            .getList());

        if (RandomUtils.roll(rep.chance_for_higher_rar)) {
            int higher = rar.rank + 1;
            Optional<MissionRarity> opt = MissionsDB.MissionRarities()
                .getList()
                .stream()
                .filter(x -> x.rank == higher)
                .findFirst();

            if (opt.isPresent()) {
                rar = opt.get();
            }
        }

        data.rar = rar.id;

        List<Pool> touse = god.getRandomPoolsToUse(god.task_pools, rar);

        int totalWorth = 0;

        for (Pool x : touse) {
            TaskEntry task = RandomUtils.weightedRandom(x.getTaskEntries());

            TaskData taskData = TaskData.createNew(task, (int) (RandomUtils.RandomRange(task.min, task.max) * rar.diff_multi));
            data.tasks.add(taskData);

            totalWorth += taskData.req * task.worth * rar.reward_multi;
        }

        List<Pool> rewardPools = god.getRandomPoolsToUse(god.reward_pools, rar);

        // todo add limit with total worth
        for (Pool x : rewardPools) {
            Reward reward = RandomUtils.weightedRandom(x.getRewards());
            RewardData rewardData = new RewardData();
            rewardData.id = reward.GUID();
            rewardData.count = (int) (RandomUtils.RandomRange(reward.min, reward.max) * rar.reward_multi);
            data.rewards.add(rewardData);
        }

        data.god = god.GUID();

        data.rep = totalWorth / 100;

// TODO
        return data;
    }

    public static List<ItemStack> getCurrentMissions(PlayerEntity player) {
        return player.inventory.main.stream()
            .filter(x -> x.getItem() == ModItems.INSTANCE.MISSION_ITEM)
            .collect(Collectors.toList());
    }
}
