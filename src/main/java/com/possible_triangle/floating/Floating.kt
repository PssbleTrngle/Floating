package com.possible_triangle.floating

import net.minecraftforge.fml.common.Mod

@Mod(Floating.MODID)
class Floating {

    companion object {
        const val MODID = "floating"
    }

    init {
        FloatingWorldType()
    }

}