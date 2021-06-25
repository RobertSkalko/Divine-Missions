package com.robertx22.divine_missions.db_init;

import com.robertx22.divine_missions.main.DivineMissions;

public class RegistryInit {

    public static void init() {

        RegistryTypes.init();
        AddRegistryContainers.addAll();
        RegisterNonDatapackEntries.register();
        if (DivineMissions.RUN_DEV_TOOLS()) {
            AddToDataGen.addAll();
        }
    }
}
