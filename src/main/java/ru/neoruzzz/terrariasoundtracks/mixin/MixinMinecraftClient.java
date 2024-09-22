package ru.neoruzzz.terrariasoundtracks.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.neoruzzz.terrariasoundtracks.TerrariaSoundtracks;
import ru.neoruzzz.terrariasoundtracks.event.TickEvent;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void onTick(CallbackInfo ci) {
        TerrariaSoundtracks.EVENT_BUS.dispatch(new TickEvent());
    }
}