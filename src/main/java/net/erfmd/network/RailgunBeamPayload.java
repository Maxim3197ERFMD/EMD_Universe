package net.erfmd.network;

import net.erfmd.EMDUniverse;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public record RailgunBeamPayload(int shotId, Vec3d start, Vec3d end, int remainingTicks) implements CustomPayload {
    public static final CustomPayload.Id<RailgunBeamPayload> ID =
            new CustomPayload.Id<>(Identifier.of(EMDUniverse.MOD_ID, "railgun_beam"));

    public static final PacketCodec<RegistryByteBuf, RailgunBeamPayload> CODEC =
            PacketCodec.of(RailgunBeamPayload::write, RailgunBeamPayload::read);

    private static void write(RailgunBeamPayload payload, RegistryByteBuf buf) {
        buf.writeInt(payload.shotId());

        buf.writeDouble(payload.start().x);
        buf.writeDouble(payload.start().y);
        buf.writeDouble(payload.start().z);

        buf.writeDouble(payload.end().x);
        buf.writeDouble(payload.end().y);
        buf.writeDouble(payload.end().z);

        buf.writeInt(payload.remainingTicks());
    }

    private static RailgunBeamPayload read(RegistryByteBuf buf) {
        int shotId = buf.readInt();

        Vec3d start = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
        Vec3d end = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());

        int remainingTicks = buf.readInt();

        return new RailgunBeamPayload(shotId, start, end, remainingTicks);
    }

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
