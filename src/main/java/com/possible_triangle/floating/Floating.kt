package com.possible_triangle.floating

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT

@Mod(Floating.MODID)
class Floating {

    companion object {
        const val MODID = "floating"
        private val LOGGER = LogManager.getLogger()

        val WORLD_TYPES = DeferredRegister.create(ForgeRegistries.WORLD_TYPES, MODID)!!
        val FLOATING_WORLD_TYPE = WORLD_TYPES.register("floating") { FloatingWorldType() }!!
    }

    init {
        WORLD_TYPES.register(MOD_CONTEXT.getKEventBus())
    }

}