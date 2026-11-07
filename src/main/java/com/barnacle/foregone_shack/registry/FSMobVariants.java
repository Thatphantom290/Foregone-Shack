package com.barnacle.foregone_shack.registry;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.entity.variant.FSMobVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
public class FSMobVariants {

    public static final Set<ResourceKey<Registry<FSMobVariant>>> REGISTRIES = new HashSet<>();

    public static ResourceKey<Registry<FSMobVariant>> register(String name) {
        ResourceKey<Registry<FSMobVariant>> key = registryFor(name);
        REGISTRIES.add(key);
        return key;
    }

    public static void registerVariantRegistries(DataPackRegistryEvent.NewRegistry event) {
        REGISTRIES.forEach(registry -> event.dataPackRegistry(registry,FSMobVariant.DIRECT_CODEC, FSMobVariant.DIRECT_CODEC));
    }

    public static ResourceKey<Registry<FSMobVariant>> registryFor(String mobName) {
        return ResourceKey.createRegistryKey(ForegoneShack.location("mob_variant/" + mobName));
    }

    public static ResourceKey<Registry<FSMobVariant>> registryFor(EntityType<?> type) {
        return registryFor(EntityType.getKey(type).getPath());
    }

    public static Optional<ResourceKey<Registry<FSMobVariant>>> findRegistry(EntityType<?> type) {
        ResourceLocation id = EntityType.getKey(type);

        if (!id.getNamespace().equals(ForegoneShack.MOD_ID)) {
            return Optional.empty();
        }

        ResourceKey<Registry<FSMobVariant>> registry = registryFor(id.getPath());
        return REGISTRIES.contains(registry) ? Optional.of(registry) : Optional.empty();
    }

    public static ResourceKey<FSMobVariant> defaultFor(EntityType<?> type) {
        String name = EntityType.getKey(type).getPath();
        return createKey(registryFor(name), name);
    }

    public static ResourceKey<FSMobVariant> createKey(ResourceKey<Registry<FSMobVariant>> registry, String name) {
        return ResourceKey.create(registry, ForegoneShack.location(name));
    }
}