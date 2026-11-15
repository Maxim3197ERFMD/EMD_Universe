package net.erfmd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

public class Experiment_803S_Block extends CakeBlock {

    public Experiment_803S_Block(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            if (tryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return tryEat(world, pos, state, player);
    }

    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        }
        player.incrementStat(Stats.EAT_CAKE_SLICE);
        player.getHungerManager().add(3, 0.2f);
        int CakeEffectType;
        if (world.getRandom().nextFloat() >= 0.035f && world.getRandom().nextFloat() <= 0.015f) {
            CakeEffectType = 5;
        } else if (world.getRandom().nextFloat() >= 0.1f && world.getRandom().nextFloat() <= 0.035f) {
            CakeEffectType = 4;
        } else if (world.getRandom().nextFloat() >= 0.15f && world.getRandom().nextFloat() <= 0.1f) {
            CakeEffectType = 3;
        } else if (world.getRandom().nextFloat() >= 0.5f && world.getRandom().nextFloat() <= 0.15f) {
            CakeEffectType = 2;
        } else if (world.getRandom().nextFloat() <= 0.5f) {
            CakeEffectType = 1;
        } else {
            CakeEffectType = 0;
        }
        switch (CakeEffectType) {
            case 1 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600));
            case 2 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 600));
            case 3 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 1000));
            case 4 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1800));
            case 5 -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 6000));
            default -> {}
        }
//        if (world.getRandom().nextFloat() <= 0.5f) {
//            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600));
//        }
//        else if (world.getRandom().nextFloat() >= 0.5f && world.getRandom().nextFloat() <= 0.15f) {
//            player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 600));
//        }
//        else if (world.getRandom().nextFloat() >= 0.15f && world.getRandom().nextFloat() <= 0.1f) {
//            player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 1000));
//        }
//        else if (world.getRandom().nextFloat() >= 0.1f && world.getRandom().nextFloat() <= 0.035f) {
//            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 1800));
//        }
//        else if (world.getRandom().nextFloat() >= 0.035f && world.getRandom().nextFloat() <= 0.015f) {
//            player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 6000));
//        }
        int i = state.get(BITES);
        world.emitGameEvent(player, GameEvent.EAT, pos);
        if (i < 6) {
            world.setBlockState(pos, state.with(BITES, i + 1), Block.NOTIFY_ALL);
        } else {
            world.removeBlock(pos, false);
            world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }
        return ActionResult.SUCCESS;
    }
}