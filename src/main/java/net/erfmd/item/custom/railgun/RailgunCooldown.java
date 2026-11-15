package net.erfmd.item.custom.railgun;

import net.erfmd.item.EMDItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.scoreboard.ReadableScoreboardScore;
import net.minecraft.scoreboard.ScoreHolder;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public final class RailgunCooldown {
    // Время перезарядки после выстрела. 20 тиков = 1 секунда
    public static final int TICKS = 200;
    // Название хранилища перезарядки.
    private static final String OBJECTIVE = "railgun_cd";

    private RailgunCooldown() {}

    // Регистрация системы перезарядки.
    public static void register() {
        // Отсчитываем время только для игроков, находящихся на сервере.
        ServerTickEvents.END_SERVER_TICK.register(server -> {

            Scoreboard scoreboard = server.getScoreboard();
            ScoreboardObjective objective = scoreboard.getNullableObjective(OBJECTIVE);

            if (objective == null) {
                return;
            }

            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                ScoreHolder holder = holder(player);

                ReadableScoreboardScore current = scoreboard.getScore(holder, objective);

                if (current != null && current.getScore() > 0) {
                    scoreboard.getOrCreateScore(holder, objective).setScore(current.getScore() - 1);
                }
            }
        });

        // Восстанавливаем перезарядку при входе.
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> restore(handler.getPlayer(), server));

        // Восстанавливаем перезарядку после смерти.
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> restore(newPlayer, newPlayer.getServer()));
    }

    // Проверяем, перезаряжается ли рельсотрон.
    public static boolean isCoolingDown(ServerPlayerEntity player) {
        return getRemaining(player.getServer(), player) > 0 || player.getItemCooldownManager()
                .isCoolingDown(EMDItems.RAILGUN);
    }

    // Запускаем перезарядку после выстрела.
    public static void start(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();

        if (server == null) {
            return;
        }

        Scoreboard scoreboard = server.getScoreboard();

        scoreboard.getOrCreateScore(holder(player), objective(scoreboard)).setScore(TICKS);

        player.getItemCooldownManager().set(EMDItems.RAILGUN, TICKS);
    }

    // Восстанавливаем оставшееся время.
    private static void restore(ServerPlayerEntity player, MinecraftServer server) {
        if (server == null) {
            return;
        }

        int remaining = getRemaining(server, player);

        if (remaining > 0) {
            player.getItemCooldownManager().set(EMDItems.RAILGUN, remaining);
        }
    }

    // Получаем оставшееся время перезарядки.
    private static int getRemaining(MinecraftServer server, ServerPlayerEntity player) {
        if (server == null) {
            return 0;
        }

        Scoreboard scoreboard = server.getScoreboard();

        ScoreboardObjective objective = scoreboard.getNullableObjective(OBJECTIVE);

        if (objective == null) {
            return 0;
        }

        ReadableScoreboardScore score = scoreboard.getScore(holder(player), objective);

        return score == null ? 0 : Math.max(0, score.getScore());
    }

    // Используем UUID игрока вместо его никнейма.
    private static ScoreHolder holder(ServerPlayerEntity player) {
        return ScoreHolder.fromName(player.getUuidAsString());
    }

    // Создаём хранилище перезарядки.
    private static ScoreboardObjective objective(Scoreboard scoreboard) {
        ScoreboardObjective existing = scoreboard.getNullableObjective(OBJECTIVE);

        if (existing != null) {
            return existing;
        }

        return scoreboard.addObjective(OBJECTIVE, ScoreboardCriterion.DUMMY, Text.literal("Railgun Recharge (Ticks)"),
                ScoreboardCriterion.RenderType.INTEGER, false, null);
    }
}