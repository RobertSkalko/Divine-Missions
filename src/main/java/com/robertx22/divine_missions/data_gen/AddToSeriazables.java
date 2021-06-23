package com.robertx22.divine_missions.data_gen;

import com.robertx22.divine_missions.data_gen.adders.*;

public class AddToSeriazables {

    public static void addAll() {

        RewardsAdder.init();
        TaskEntryAdder.init();

        new RarityAdders().registerAll();
        new ReputationAdders().registerAll();
        new PoolsAdder().registerAll();

        new GodsAdder().registerAll();

    }
}
