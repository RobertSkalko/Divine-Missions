package com.robertx22.divine_missions.main;

import com.robertx22.divine_missions.components.PlayerReputation;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;

public class Components implements EntityComponentInitializer {

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PlayerReputation.KEY, x -> new PlayerReputation(x));
    }
}
