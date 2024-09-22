package ru.neoruzzz.terrariasoundtracks.listeners;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import ru.neoruzzz.terrariasoundtracks.event.Event;
import ru.neoruzzz.terrariasoundtracks.event.EventHandler;
import ru.neoruzzz.terrariasoundtracks.event.TickEvent;
import ru.neoruzzz.terrariasoundtracks.util.Globals;

public class playSoundtrack implements Listener, Globals {

    private boolean played = false;
    private String biome = "";
    private String lastBiome = "";

    private String getBiome() {
        return biome;
    }

    public void setBiome(String biome) {
        this.biome = biome;
    }

    public String getLastBiome() {
        return lastBiome;
    }

    public void setLastBiome(String lastBiome) {
        this.lastBiome = lastBiome;
    }

    @EventHandler(TickEvent.class)
    public void onTick(Event event) {
        if (mc.player != null & mc.world != null) {
            setBiome(mc.world.getBiome(new BlockPos((int) mc.player.getX(), (int) mc.player.getY(), (int) mc.player.getZ())).getKey().get().getValue().toString());
            if (!played) {
                setLastBiome(mc.world.getBiome(new BlockPos((int) mc.player.getX(), (int) mc.player.getY(), (int) mc.player.getZ())).getKey().get().getValue().toString());
                mc.player.sendMessage(Text.of("Проигрывается саундтрек " + getLastBiome()));
                mc.getMusicTracker().stop();
                mc.getMusicTracker().play(new MusicSound(RegistryEntry.of(SoundEvent.of(Identifier.of("terraria-soundtracks", "overworld_day"))), 100, 100, true));
                played = true;
            }
            else {
                if (!getBiome().equals(getLastBiome())) {
                    setLastBiome(mc.world.getBiome(new BlockPos((int) mc.player.getX(), (int) mc.player.getY(), (int) mc.player.getZ())).getKey().get().getValue().toString());
                    mc.player.sendMessage(Text.of("Проигрывается саундтрек " + getLastBiome()));
                    mc.getMusicTracker().stop();
                    mc.getMusicTracker().play(new MusicSound(RegistryEntry.of(SoundEvent.of(Identifier.of("terraria-soundtracks", "overworld_day"))), 100, 100, true));
                    played = true;
                }
            }
        }
        else {
            setBiome("");
            mc.getMusicTracker().play(new MusicSound(RegistryEntry.of(SoundEvent.of(Identifier.of("terraria-soundtracks", "title_screen"))), 100, 100, true));
            played = true;
        }
    }
}
