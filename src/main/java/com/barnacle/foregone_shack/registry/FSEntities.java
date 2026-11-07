package com.barnacle.foregone_shack.registry;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.entity.release.Popim;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FSEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ForegoneShack.MOD_ID);

    public static List<DeferredHolder<EntityType<?>, ? extends EntityType<?>>> ENTITY_TRANSLATIONS = new ArrayList<>();

    public static final DeferredHolder<EntityType<?>, EntityType<Popim>> POPIM = registerEntity("popim", Popim::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.8F, 0.3F).eyeHeight(0.3F).clientTrackingRange(10));

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerEntity(String name, EntityType.EntityFactory<E> factory, MobCategory entityClassification, Consumer<EntityType.Builder<E>> builderConsumer) {
        if (entityClassification != MobCategory.MISC) {
            FSMobVariants.register(name);
        }
        return ENTITY_TYPE.register(name, () -> {
            var builder = EntityType.Builder.of(factory, entityClassification);
            builderConsumer.accept(builder);
            return builder.build(ForegoneShack.MOD_ID + ":" + name);
        });
    }
}
