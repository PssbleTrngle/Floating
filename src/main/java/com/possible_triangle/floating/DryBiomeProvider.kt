package com.possible_triangle.floating

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.RegistryKey
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryLookupCodec
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.biome.provider.BiomeProvider
import net.minecraft.world.gen.layer.LayerUtil
import net.minecraftforge.common.BiomeManager
import java.util.function.BiFunction

class DryBiomeProvider(
        private val seed: Long, private val biomes: Registry<Biome>,
        private val additional: () -> List<RegistryKey<Biome>> = { BiomeManager.getAdditionalOverworldBiomes() }
) : BiomeProvider(
        mutableListOf(VANILLA_BIOMES, additional())
                .flatten()
                .map { biomes.getOrThrow(it) }
                .filter { it.biomeCategory != Biome.Category.OCEAN }
                .filter { it.biomeCategory != Biome.Category.RIVER }

) {

    companion object {
        private val VANILLA_BIOMES = listOf(Biomes.OCEAN, Biomes.PLAINS, Biomes.DESERT, Biomes.MOUNTAINS, Biomes.FOREST, Biomes.TAIGA, Biomes.SWAMP, Biomes.RIVER, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER, Biomes.SNOWY_TUNDRA, Biomes.SNOWY_MOUNTAINS, Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE, Biomes.BEACH, Biomes.DESERT_HILLS, Biomes.WOODED_HILLS, Biomes.TAIGA_HILLS, Biomes.MOUNTAIN_EDGE, Biomes.JUNGLE, Biomes.JUNGLE_HILLS, Biomes.JUNGLE_EDGE, Biomes.DEEP_OCEAN, Biomes.STONE_SHORE, Biomes.SNOWY_BEACH, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.WOODED_MOUNTAINS, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU, Biomes.BADLANDS_PLATEAU, Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.SUNFLOWER_PLAINS, Biomes.DESERT_LAKES, Biomes.GRAVELLY_MOUNTAINS, Biomes.FLOWER_FOREST, Biomes.TAIGA_MOUNTAINS, Biomes.SWAMP_HILLS, Biomes.ICE_SPIKES, Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, Biomes.DARK_FOREST_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.MODIFIED_BADLANDS_PLATEAU)

        private val CODEC = RecordCodecBuilder.create<DryBiomeProvider> { builder ->
            builder.group(
                    Codec.LONG.fieldOf("seed").stable().forGetter { it.seed },
                    RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter { it.biomes }
            ).apply(builder, builder.stable(BiFunction(::DryBiomeProvider)))
        }

    }

    private val noiseBiomeLayer = LayerUtil.getDefaultLayer(seed, false, 4, 4)

    override fun getNoiseBiome(x: Int, y: Int, z: Int): Biome {
        return this.noiseBiomeLayer.get(this.biomes, x, z)
    }

    override fun codec(): Codec<out BiomeProvider> {
        return CODEC
    }

    override fun withSeed(seed: Long): BiomeProvider {
        return DryBiomeProvider(seed, this.biomes, this.additional)
    }
}