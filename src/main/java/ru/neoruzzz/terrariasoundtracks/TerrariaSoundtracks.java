package ru.neoruzzz.terrariasoundtracks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.neoruzzz.terrariasoundtracks.event.EventBus;
import ru.neoruzzz.terrariasoundtracks.listeners.playSoundtrack;

public class TerrariaSoundtracks implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(TerrariaSoundtracks.class);
    public static EventBus EVENT_BUS = new EventBus();

    @Override
    public void onInitialize() {
        Registry.register(Registries.SOUND_EVENT, Identifier.of("terraria-soundtracks", "title_screen"), SoundEvent.of(Identifier.of("terraria-soundtracks", "title_screen")));
        Registry.register(Registries.SOUND_EVENT, Identifier.of("terraria-soundtracks", "overworld_day"), SoundEvent.of(Identifier.of("terraria-soundtracks", "overworld_day")));
        EVENT_BUS.registry(new playSoundtrack());
        LOGGER.info("Initialize!");
    }
}
