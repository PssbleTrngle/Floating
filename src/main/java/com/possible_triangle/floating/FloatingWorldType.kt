package com.possible_triangle.floating

import net.minecraft.world.biome.provider.OverworldBiomeProvider
import net.minecraft.world.gen.DimensionSettings
import net.minecraft.world.gen.NoiseChunkGenerator
import net.minecraftforge.common.world.ForgeWorldType
import net.minecraftforge.common.world.ForgeWorldType.IBasicChunkGeneratorFactory

class FloatingWorldType : ForgeWorldType(IBasicChunkGeneratorFactory { biomeReg, settingsReg, seed ->
    val biomes = OverworldBiomeProvider(seed, false, false, biomeReg)
    NoiseChunkGenerator(biomes, seed) { settingsReg.getOrThrow(DimensionSettings.field_242739_h) }
})