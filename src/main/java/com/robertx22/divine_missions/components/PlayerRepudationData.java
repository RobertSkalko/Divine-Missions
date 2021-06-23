package com.robertx22.divine_missions.components;

import com.robertx22.divine_missions.database.db_types.God;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.HashMap;

@Storable
public class PlayerRepudationData {

    @Store
    private HashMap<String, Integer> map = new HashMap<>();

    public int getReputation(God god) {
        return map.getOrDefault(god.GUID(), 0);
    }

    public void addReputation(int rep, God god) {

        int points = map.getOrDefault(god.GUID(), 0) + rep;

        map.put(god.GUID(), rep);

    }
}
