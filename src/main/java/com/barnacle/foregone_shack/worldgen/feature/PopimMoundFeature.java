package com.barnacle.foregone_shack.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;

public class PopimMoundFeature extends Feature<NoneFeatureConfiguration> {
    public PopimMoundFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        BlockPos surfacePos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, origin);

        MinecraftServer server = level.getServer();
        if (server == null) return false;

        StructureTemplateManager templateManager = server.getStructureManager();
        Optional<StructureTemplate> templateOpt = templateManager.get(
                ResourceLocation.fromNamespaceAndPath("foregone_shack", "popim_mound")
        );

        if (templateOpt.isEmpty()) {
            System.err.println("❌ Popim Mound structure template not found in resources!");
            return false;
        }

        StructureTemplate template = templateOpt.get();

        BlockPos spawnPos = surfacePos.below(2);

        StructurePlaceSettings settings = new StructurePlaceSettings()
                .setRotation(Rotation.getRandom(random))
                .setMirror(Mirror.NONE)
                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);

        boolean success = template.placeInWorld(level, spawnPos, spawnPos, settings, random, 2);

        if (!success) {
            System.err.println("❌ template.placeInWorld returned false at " + spawnPos);
        }

        return success;
    }
}