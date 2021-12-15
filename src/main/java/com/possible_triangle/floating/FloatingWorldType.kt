package com.possible_triangle.floating

import biomesoplenty.api.enums.BOPClimates
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.provider.BiomeProvider
import net.minecraft.world.gen.DimensionSettings
import net.minecraft.world.gen.NoiseChunkGenerator
import net.minecraftforge.common.world.ForgeWorldType

class FloatingWorldType(biomes: (Long, Registry<Biome>) -> BiomeProvider) : ForgeWorldType({ biomeReg, settingsReg, seed ->
    NoiseChunkGenerator(biomes(seed, biomeReg), seed) { settingsReg.getOrThrow(DimensionSettings.FLOATING_ISLANDS) }
}) {

    companion object {

        val VANILLA = FloatingWorldType(::DryBiomeProvider)
        val BOP = FloatingWorldType { seed, biomes ->
            DryBiomeProvider(seed, biomes) {
                BOPClimates.getOverworldBiomes().asList()
            }
        }

    }

}