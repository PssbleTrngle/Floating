package com.possible_triangle.floating

import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens
import net.minecraft.util.ResourceLocation
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.DimensionSettings

open class ModdedWorldType(
        name: String,
        private val generator: (biomeReg: Registry<Biome>, settingsReg: Registry<DimensionSettings>, seed: Long) -> ChunkGenerator
) : BiomeGeneratorTypeScreens(ResourceLocation(Floating.MODID, name).toString()) {

    init {
        field_239068_c_.add(this)
    }

    override fun func_241869_a(biomeReg: Registry<Biome>, settingsReg: Registry<DimensionSettings>, seed: Long): ChunkGenerator {
        return generator(biomeReg, settingsReg, seed)
    }

}