package com.barnacle.foregone_shack.registry;

import com.barnacle.foregone_shack.ForegoneShack;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class FSItems {
    public static final DeferredRegister.Items ITEM = DeferredRegister.createItems(ForegoneShack.MOD_ID);
    public static final List<DeferredItem<? extends Item>> ITEM_TRANSLATIONS = new ArrayList<>();

    public static final DeferredItem<Item> POPIM_SPAWN_EGG = registerSpawnEggItem("popim", FSEntities.POPIM, 0x8b1f1f, 0xb2c329);
    public static final DeferredItem<Item> RED_POPIM = registerItem ("red_popim", () -> new Item(foodItem(FSFoodProperties.POPIM)));
    public static final DeferredItem<Item> BLUE_POPIM = registerItem ("blue_popim", () -> new Item(foodItem(FSFoodProperties.POPIM)));
    public static final DeferredItem<Item> ORANGE_POPIM = registerItem ("orange_popim", () -> new Item(foodItem(FSFoodProperties.POPIM)));



    private static <I extends Item> DeferredItem<I> registerItem(String name, Supplier<? extends I> supplier) {
        DeferredItem<I> item = ITEM.register(name, supplier);
        ITEM_TRANSLATIONS.add(item);
        return item;
    }

    private static <I extends Item> DeferredItem<I> registerItemNoLang(String name, Supplier<? extends I> supplier) {
        return ITEM.register(name, supplier);
    }

    private static DeferredItem<Item> registerSpawnEggItem(String name, Supplier<? extends EntityType<? extends Mob>> type, int baseColor, int spotColor) {
        return registerItem(name + "_spawn_egg", () -> new DeferredSpawnEggItem(type, baseColor, spotColor, new Item.Properties()));
    }

    private static DeferredItem<Item> registerSpawnEggItemNoLang(String name, Supplier<? extends EntityType<? extends Mob>> type, int baseColor, int spotColor) {
        return registerItemNoLang(name + "_spawn_egg", () -> new DeferredSpawnEggItem(type, baseColor, spotColor, new Item.Properties()));
    }

    public static Item.Properties foodItem(FoodProperties food) {
        return new Item.Properties().food(food);
    }
}
