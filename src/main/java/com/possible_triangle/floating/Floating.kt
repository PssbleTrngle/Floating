package com.possible_triangle.floating

import net.minecraftforge.fml.ModList
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT

@Mod(Floating.MODID)
class Floating {

    companion object {
        const val MODID = "floating"
        val WORLD_TYPES = DeferredRegister.create(ForgeRegistries.WORLD_TYPES, MODID)!!
    }

    init {
        WORLD_TYPES.register("floating") { FloatingWorldType() }
        if (ModList.get().isLoaded("biomesoplenty"))
            WORLD_TYPES.register("floating_bop") { FloatingBOPWorldType() }

        WORLD_TYPES.register(MOD_CONTEXT.getKEventBus())
    }

}