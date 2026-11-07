package com.barnacle.foregone_shack.registry;

import com.barnacle.foregone_shack.ForegoneShack;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class FSModelLayers {

    public static final ModelLayerLocation POPIM = register("popim");

    private static ModelLayerLocation register(String id) {
        return new ModelLayerLocation(ForegoneShack.location(id), "main");
    }
}
