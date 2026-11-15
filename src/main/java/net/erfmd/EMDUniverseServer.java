package net.erfmd;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class EMDUniverseServer implements DedicatedServerModInitializer {
    /**
     * Серверный регистратор
     */
    @Override
    public void onInitializeServer() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            EMDUniverse.setGameRules(server.getGameRules());
        });
    }
}
