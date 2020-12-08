package com.possible_triangle.floating

import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.provider.OverworldBiomeProvider
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.DimensionSettings
import net.minecraft.world.gen.NoiseChunkGenerator
import net.minecraftforge.common.world.ForgeWorldType

class FloatingWorldType : ForgeWorldType({ biomeReg, settingsReg, seed ->
    val biomes = OverworldBiomeProvider(seed, false, false, biomeReg)
    NoiseChunkGenerator(biomes, seed) { settingsReg.getOrThrow(DimensionSettings.field_242739_h) }
})