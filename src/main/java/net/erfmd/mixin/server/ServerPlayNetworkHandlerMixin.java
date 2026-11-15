package net.erfmd.mixin.server;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @ModifyConstant(method = "onPlayerMove", constant = @Constant(floatValue = 100.0F), require = 0)
    private float PlayerMaxSpeed(float speed) {
        return Float.MAX_VALUE;
    }

    @ModifyConstant(method = "onPlayerMove", constant = @Constant(floatValue = 300.0F), require = 0)
    private float ElytraMaxSpeed(float speed) {
        return Float.MAX_VALUE;
    }

    @ModifyConstant(method = "onVehicleMove", constant = @Constant(doubleValue = 100.0), require = 0)
    private double VehicleMaxSpeed(double speed) {
        return Double.MAX_VALUE;
    }

    @ModifyConstant(method = "onPlayerMove", constant = @Constant(doubleValue = 0.0625), require = 0)
    private double MovedWrong(double speed) {
        return Double.MAX_VALUE;
    }

    @ModifyConstant(method = "onVehicleMove", constant = @Constant(doubleValue = 0.0625), require = 0)
    private double VehicleMovedWrong(double speed) {
        return Double.MAX_VALUE;
    }
}
