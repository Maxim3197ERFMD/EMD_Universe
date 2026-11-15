package net.erfmd.datagen.lang;

import net.erfmd.block.EMDBlocks;
import net.erfmd.item.EMDItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EMDLangRussianProvider extends FabricLanguageProvider {
    public EMDLangRussianProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "ru_ru", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(EMDItems.RAW_RUBY, "Рудный рубин");
        translationBuilder.add(EMDItems.RUBY, "Рубин");

        translationBuilder.add(EMDItems.PIECE_OF_CAKE, "Кусочек торта");
        translationBuilder.add(EMDItems.PIECE_OF_CHOCOLATE_CAKE, "Кусочек шоколадного торта");
        translationBuilder.add(EMDItems.PIECE_OF_EXPERIMENT_115, "Кусочек \"Эксперимента 115\"");
        translationBuilder.add(EMDItems.PIECE_OF_EXPERIMENT_803S, "Кусочек \"Эксперимента 803-S\"");
        translationBuilder.add(EMDItems.CARAMEL, "Карамель");
        translationBuilder.add(EMDItems.CARAMEL_APPLE, "Карамельное яблоко");

        translationBuilder.add(EMDItems.CHOCOLATE_CAKE, "Шоколадный торт");
        translationBuilder.add(EMDItems.EXPERIMENT_115, "Эксперимент 115");
        translationBuilder.add(EMDItems.EXPERIMENT_803S, "Эксперимент 803-S");

        translationBuilder.add(EMDItems.ROSE_GOLDEN_APPLE, "Розовое золотое яблоко");

        translationBuilder.add(EMDItems.ROSE_GOLD_INGOT, "Розовое золотой слиток");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_SWORD, "Розовый золотой меч");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_AXE, "Розовый золотой топор");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_PICKAXE, "Розовая золотая кирка");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_SHOVEL, "Розовая золотая лопата");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_HOE, "Розовая золотая мотыга");

        translationBuilder.add(EMDItems.ROSE_GOLDEN_HELMET, "Розовый золотой шлем");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_CHESTPLATE, "Розовый золотой нагрудник");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_LEGGINGS, "Розовые золотые поножи");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_BOOTS, "Розовые золотые ботинки");

        translationBuilder.add(EMDItems.COPPER_COIL, "Медная катушка");
        translationBuilder.add(EMDItems.COPPER_WIRE, "Медный провод");
        translationBuilder.add(EMDItems.ACCUMULATOR, "Аккумулятор");
        translationBuilder.add(EMDItems.ENERGY_BALL, "Энергетическая сфера");
        translationBuilder.add(EMDItems.SPECIAL_STORAGE, "Чип-плата");
        translationBuilder.add(EMDItems.EMERALD_DUST, "Изумрудная пыль");
        translationBuilder.add(EMDItems.DIAMOND_DUST, "Алмазная пыль");
        translationBuilder.add(EMDItems.METAL_ALLOY, "Металлический сплав");
        translationBuilder.add(EMDItems.WARPED_NETHERITE_INGOT, "Искажённый незерит");
        translationBuilder.add(EMDItems.HIGH_VOLTAGE_BATTERY, "Высоковольтная батарея");

        translationBuilder.add(EMDItems.DISASSEMBLY_CHAINSAW, "Бензопила Дрона-Демонтажника");
        translationBuilder.add(EMDItems.LM85_BLADE, "Клинок LM-85");
        translationBuilder.add(EMDItems.NANOMACHINES, "Наномашины");

        translationBuilder.add(EMDItems.NETHERITE_BOW, "Незеритовый лук");
        translationBuilder.add(EMDItems.NETHERITE_CROSSBOW, "Незериторый арбалет");

        translationBuilder.add(EMDItems.HAM, "Свиной окорок");
        translationBuilder.add(EMDItems.HAM_BAT, "Мясная дубинка");
        translationBuilder.add(EMDItems.LOLLICHOP, "Кледенец");
        translationBuilder.add(EMDItems.CANDY_CANE, "Карамельная трость");
        translationBuilder.add(EMDItems.WORKER_DRONE_ARM, "Рука Дрона-Рабочего");

        translationBuilder.add(EMDItems.CHERRIES, "Вишня");
        translationBuilder.add(EMDItems.BANANA, "Банан");
        translationBuilder.add(EMDItems.HONEYSUCKLE, "Жимолость");
        translationBuilder.add(EMDItems.CHERRY_PIE, "Вишнёвый пирог");
        translationBuilder.add(EMDItems.HONEYSUCKLE_PIE, "Пирог из жимолости");

        translationBuilder.add(EMDItems.WARHAMMER_SOUP, "Макрагг");

        translationBuilder.add(EMDBlocks.POTATO_BLOCK, "Блок картофели");
        translationBuilder.add(EMDBlocks.POISONOUS_POTATO_BLOCK, "Блок ядовитой картофели");

        translationBuilder.add(EMDBlocks.RUBY_ORE, "Рубиновая руда");
        translationBuilder.add(EMDBlocks.DEEPSLATE_RUBY_ORE, "Рубиноносный глубинный сланец");
        translationBuilder.add(EMDBlocks.NETHER_RUBY_ORE, "Незерская рубиновая руда");
        translationBuilder.add(EMDBlocks.NETHER_IRON_ORE, "Незерская железная руда");
        translationBuilder.add(EMDBlocks.RAW_RUBY_BLOCK, "Блок рудного рубина");
        translationBuilder.add(EMDBlocks.RUBY_BLOCK, "Рубиновый блок");
        translationBuilder.add(EMDBlocks.ROSE_GOLD_BLOCK, "Розовый золотой блок");

        translationBuilder.add(EMDBlocks.STONE_TILES, "Каменный плитняк");
        translationBuilder.add(EMDBlocks.STONE_TILE_SLAB, "Плита из каменного плитняка");
        translationBuilder.add(EMDBlocks.STONE_TILE_STAIRS, "Ступеньки из каменного плитняка");
        translationBuilder.add(EMDBlocks.STONE_TILE_WALL, "Ограда из каменного плитняка");
        translationBuilder.add(EMDBlocks.CRACKED_STONE_TILES, "Потрескавшийся каменный плитняк");

        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILES, "Полированно-чернитный плитняк");
        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILE_SLAB, "Плита из полированно-чернитного плитняка");
        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILE_STAIRS, "Ступеньки из полированно-чернитного плитняка");
        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILE_WALL, "Ограда из полированно-чернитного плитняка");
        translationBuilder.add(EMDBlocks.CRACKED_POLISHED_BLACKSTONE_TILES, "Потрескавшийся полированно-чернитный плитняк");

        translationBuilder.add(EMDBlocks.SMOOTH_END_STONE, "Гладкий эндерняк");
        translationBuilder.add(EMDBlocks.SMOOTH_END_STONE_SLAB, "Плита из гладкого эндерняка");
        translationBuilder.add(EMDBlocks.SMOOTH_END_STONE_STAIRS, "Ступеньки из гладкого эндерняка");
        translationBuilder.add(EMDBlocks.END_STONE_TILES, "Эндерняковый плитняк");
        translationBuilder.add(EMDBlocks.END_STONE_TILE_SLAB, "Плита из эндернякового плитняка");
        translationBuilder.add(EMDBlocks.END_STONE_TILE_STAIRS, "Ступеньки из эндернякового плитняка");
        translationBuilder.add(EMDBlocks.END_STONE_TILE_WALL, "Ограда из эндернякового плитняка");

        translationBuilder.add(EMDBlocks.POLISHED_STONE, "Полированный камень");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_SLAB, "Плита из полированного камня");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_STAIRS, "Ступеньки из полированного камня");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_WALL, "Ограда из полированного камня");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_PILLAR, "Полированная каменная колонна");

        translationBuilder.add(EMDBlocks.SANDSTONE_BRICKS, "Песчаниковые кирпичи");
        translationBuilder.add(EMDBlocks.SANDSTONE_BRICK_SLAB, "Плита из песчаникового кирпича");
        translationBuilder.add(EMDBlocks.SANDSTONE_BRICK_STAIRS, "Ступеньки из песчаникового кирпича");
        translationBuilder.add(EMDBlocks.SANDSTONE_BRICK_WALL, "Ограда из песчаникового кирпича");
        translationBuilder.add(EMDBlocks.CHISELED_SANDSTONE_BRICKS, "Резные песчаниковые кирпичи");

        translationBuilder.add(EMDBlocks.POLISHED_NETHER_BRICKS, "Полированные незерские кирпичи");
        translationBuilder.add(EMDBlocks.POLISHED_NETHER_BRICK_SLAB, "Плита из полированного незерского кирпича");
        translationBuilder.add(EMDBlocks.POLISHED_NETHER_BRICK_STAIRS, "Ступеньки из полированного незерского кирпича");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICKS, "Искажённые незерские кирпичи");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICK_SLAB, "Плита из искажённого незерского кирпича");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICK_STAIRS, "Ступеньки из искажённого незерского кирпича");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICK_WALL, "Ограда из искажённого незерского кирпича");

        translationBuilder.add(EMDBlocks.SNOW_BRICKS, "Снежные кирпичи");
        translationBuilder.add(EMDBlocks.SNOW_BRICK_SLAB, "Плита из снежного кирпича");
        translationBuilder.add(EMDBlocks.SNOW_BRICK_STAIRS, "Ступеньки из снежного кирпича");
        translationBuilder.add(EMDBlocks.SNOW_BRICK_WALL, "Ограда из снежного кирпича");

        translationBuilder.add(EMDBlocks.TERRACOTTA_BRICKS, "Керамиковые кирпичи");
        translationBuilder.add(EMDBlocks.WHITE_TERRACOTTA_BRICKS, "Белые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, "Светло-серые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.GRAY_TERRACOTTA_BRICKS, "Серые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.BLACK_TERRACOTTA_BRICKS, "Чёрные терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.BROWN_TERRACOTTA_BRICKS, "Коричневые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.RED_TERRACOTTA_BRICKS, "Красные терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.ORANGE_TERRACOTTA_BRICKS, "Оранжевые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.YELLOW_TERRACOTTA_BRICKS, "Жёлтые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.LIME_TERRACOTTA_BRICKS, "Лаймовые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.GREEN_TERRACOTTA_BRICKS, "Зелёные терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.CYAN_TERRACOTTA_BRICKS, "Бирюзовые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, "Голубые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.BLUE_TERRACOTTA_BRICKS, "Синие терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.PURPLE_TERRACOTTA_BRICKS, "Фиолетовые терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.MAGENTA_TERRACOTTA_BRICKS, "Пурпурные терракотовые кирпичи");
        translationBuilder.add(EMDBlocks.PINK_TERRACOTTA_BRICKS, "Розовые терракотовые кирпичи");

        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICKS, "Обсидиановые кирпичи");
        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICK_SLAB, "Плита из обсидианового кирпича");
        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICK_STAIRS, "Ступеньки из обсидианового кирпича");
        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICK_WALL, "Ограда из обсидианового кирпича");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN, "Полированный обсидиан");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN_SLAB, "Плита из полированного обсидиана");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN_STAIRS, "Ступеньки из полированного обсидиана");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN_WALL, "Ограда из полированного обсидиана");
        translationBuilder.add(EMDBlocks.CHISELED_OBSIDIAN, "Резной обсидиан");
        translationBuilder.add(EMDBlocks.OBSIDIAN_PILLAR, "Обсидиановая колонна");

        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_BRICKS, "Плачущие обсидиановые кирпичи");
        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_BRICK_SLAB, "Плита из плачущего обсидианового кирпича");
        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_BRICK_STAIRS, "Ступеньки из плачущего обсидианового кирпича");
        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_BRICK_WALL, "Ограда из плачущего обсидианового кирпича");
        translationBuilder.add(EMDBlocks.CRYING_POLISHED_OBSIDIAN, "Полированный плачущий обсидиан");
        translationBuilder.add(EMDBlocks.CRYING_POLISHED_OBSIDIAN_SLAB, "Плита из полированного плачущего обсидиана");
        translationBuilder.add(EMDBlocks.CRYING_POLISHED_OBSIDIAN_STAIRS, "Ступеньки из полированного плачущего обсидиана");
        translationBuilder.add(EMDBlocks.CRYING_POLISHED_OBSIDIAN_WALL, "Ограда из полированного плачущего обсидиана");
        translationBuilder.add(EMDBlocks.CRYING_CHISELED_OBSIDIAN, "Резной плачущий обсидиан");
        translationBuilder.add(EMDBlocks.CRYING_OBSIDIAN_PILLAR, "Плачущая обсидиановая колонна");

        translationBuilder.add(EMDBlocks.IRON_BAR_DOOR, "Сетчатая дверь");
        translationBuilder.add(EMDBlocks.IRON_BAR_BLOCK, "Решётчатый блок");
        translationBuilder.add(EMDBlocks.IRON_BAR_STAIRS, "Решётчатые ступеньки");
        translationBuilder.add(EMDBlocks.IRON_BAR_SLAB, "Решётчатая плита");
        translationBuilder.add(EMDBlocks.IRON_BAR_TRAPDOOR, "Решётчатый люк");
        translationBuilder.add(EMDBlocks.IRON_BAR_WALL, "Решётчатая стена");

        translationBuilder.add(EMDBlocks.REDSTONE_HEART, "Редстоуновое сердце");

        translationBuilder.add(EMDBlocks.BEEKEEPING_TABLE, "Стол пчеловода");

        translationBuilder.add(EMDBlocks.COLORED_REDSTONE_LAMP, "Цветной редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.BLACK_REDSTONE_LAMP, "Чёрный редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.BLUE_REDSTONE_LAMP, "Синий редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.BROWN_REDSTONE_LAMP, "Коричневый редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.CYAN_REDSTONE_LAMP, "Бирюзовый редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.GRAY_REDSTONE_LAMP, "Серый редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.GREEN_REDSTONE_LAMP, "Зелёный редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.LIGHT_BLUE_REDSTONE_LAMP, "Голубой редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.LIGHT_GRAY_REDSTONE_LAMP, "Светло-серый редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.LIME_REDSTONE_LAMP, "Лаймовый редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.MAGENTA_REDSTONE_LAMP, "Пурпурный редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.ORANGE_REDSTONE_LAMP, "Оранжевый редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.PINK_REDSTONE_LAMP, "Розовый редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.PURPLE_REDSTONE_LAMP, "Фиолетовый редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.RED_REDSTONE_LAMP, "Красный редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.WHITE_REDSTONE_LAMP, "Белый редстоуновый фонарь");
        translationBuilder.add(EMDBlocks.YELLOW_REDSTONE_LAMP, "Жёлтый редстоуновый фонарь");

        translationBuilder.add(EMDItems.OIL_SUN_ARMOR_TRIM_SMITHING_TEMPLATE, "Кузнечный шаблон");
        translationBuilder.add(EMDItems.LOVE_HAREM_ARMOR_TRIM_SMITHING_TEMPLATE, "Кузнечный шаблон");
        translationBuilder.add(EMDItems.SCORPION_ARMOR_TRIM_SMITHING_TEMPLATE, "Кузнечный шаблон");

        translationBuilder.add(EMDBlocks.PARSLEY_CROP, "Посаженная петрушка");

        translationBuilder.add(EMDItems.PARSLEY, "Петрушка");
        translationBuilder.add(EMDItems.PARSLEY_SEEDS, "Семена петрушки");

        translationBuilder.add(EMDBlocks.GOLDEN_FLOWER_CROP, "Посаженный золотой цветок");

        translationBuilder.add(EMDItems.GOLDEN_FLOWER, "Золотой цветок");
        translationBuilder.add(EMDItems.GOLDEN_FLOWER_PETALS, "Золотые лютики");

        translationBuilder.add("entity.minecraft.villager.beekeeper", "Пчеловод");

        translationBuilder.add("block.minecraft.banner.absolute_solver", "Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.black", "Чёрный Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.blue", "Синий Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.brown", "Коричневый Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.cyan", "Бирюзовый Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.gray", "Серый Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.green", "Зелёный Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.light_blue", "Голубой Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.light_gray", "Светло-серый Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.lime", "Лаймовый Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.magenta", "Пурпурный Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.orange", "Оранжевый Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.pink", "Розовый Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.purple", "Фиолетовый Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.red", "Красный Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.white", "Белый Absolute Solver");
        translationBuilder.add("block.minecraft.banner.absolute_solver.yellow", "Жёлтый Absolute Solver");

        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull", "Череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.black", "Чёрный череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.blue", "Синий череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.brown", "Коричневый череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.cyan", "Бирюзовый череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.gray", "Серый череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.green", "Зелёный череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.light_blue", "Голубой череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.light_gray", "Светло-серый череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.lime", "Лаймовый череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.magenta", "Пурпурный череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.orange", "Оранжевый череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.pink", "Розовый череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.purple", "Фиолетовый череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.red", "Красный череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.white", "Белый череп Дрона-Демонтажника");
        translationBuilder.add("block.minecraft.banner.disassembly_drone_skull.yellow", "Жёлтый череп Дрона-Демонтажника");

        translationBuilder.add("block.minecraft.banner.jc_jenson_logo", "Логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.black", "Чёрный логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.blue", "Синий логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.brown", "Коричневый логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.cyan", "Бирюзовый логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.gray", "Серый логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.green", "Зелёный логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.light_blue", "Голубой логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.light_gray", "Светло-серый логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.lime", "Лаймовый логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.magenta", "Пурпурный логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.orange", "Оранжевый логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.pink", "Розовый логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.purple", "Фиолетовый логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.red", "Красный логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.white", "Белый логотип JCJenson");
        translationBuilder.add("block.minecraft.banner.jc_jenson_logo.yellow", "Жёлтый логотип JCJenson");

        translationBuilder.add("block.minecraft.banner.worker_drone_avatar", "Значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.black", "Чёрный значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.blue", "Синий значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.brown", "Коричневый значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.cyan", "Бирюзовый значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.gray", "Серый значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.green", "Зелёный значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.light_blue", "Голубой значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.light_gray", "Светло-серый значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.lime", "Лаймовый значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.magenta", "Пурпурный значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.orange", "Оранжевый значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.pink", "Розовый значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.purple", "Фиолетовый значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.red", "Красный значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.white", "Белый значок Дрона-Рабочего");
        translationBuilder.add("block.minecraft.banner.worker_drone_avatar.yellow", "Жёлтый значок Дрона-Рабочего");

        translationBuilder.add("item.minecraft.potion.effect.wither", "Зелье иссушения");
        translationBuilder.add("item.minecraft.potion.effect.long_wither", "Зелье иссушения");
        translationBuilder.add("item.minecraft.potion.effect.strong_wither", "Зелье иссушения");
        translationBuilder.add("item.minecraft.splash_potion.effect.wither", "Взрывное зелье иссушения");
        translationBuilder.add("item.minecraft.splash_potion.effect.long_wither", "Взрывное зелье иссушения");
        translationBuilder.add("item.minecraft.splash_potion.effect.strong_wither", "Взрывное зелье иссушения");
        translationBuilder.add("item.minecraft.lingering_potion.effect.wither", "Туманное зелье иссушения");
        translationBuilder.add("item.minecraft.lingering_potion.effect.long_wither", "Туманное зелье иссушения");
        translationBuilder.add("item.minecraft.lingering_potion.effect.strong_wither", "Туманное зелье иссушения");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.wither", "Стрела иссушения");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.long_wither", "Стрела иссушения");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.strong_wither", "Стрела иссушения");

        translationBuilder.add("item.minecraft.potion.effect.blindness", "Зелье слепоты");
        translationBuilder.add("item.minecraft.potion.effect.long_blindness", "Зелье слепоты");
        translationBuilder.add("item.minecraft.splash_potion.effect.blindness", "Взрывное зелье слепоты");
        translationBuilder.add("item.minecraft.splash_potion.effect.long_blindness", "Взрывное зелье слепоты");
        translationBuilder.add("item.minecraft.lingering_potion.effect.blindness", "Туманное зелье слепоты");
        translationBuilder.add("item.minecraft.lingering_potion.effect.long_blindness", "Туманное зелье слепоты");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.blindness", "Стрела слепоты");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.long_blindness", "Стрела слепоты");

        translationBuilder.add("item.minecraft.potion.effect.unluck", "Зелье невезения");
        translationBuilder.add("item.minecraft.splash_potion.effect.unluck", "Взрывное зелье невезения");
        translationBuilder.add("item.minecraft.lingering_potion.effect.unluck", "Туманное зелье невезения");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.unluck", "Стрела невезения");

        translationBuilder.add("trim_pattern.emd_universe.oil_sun", "Отделка «Солнце»");
        translationBuilder.add("trim_pattern.emd_universe.love_harem", "Отделка «Любовь»");
        translationBuilder.add("trim_pattern.emd_universe.scorpion", "Отделка «Скорпион»");

        translationBuilder.add("advancements.husbandry.warhammer_soup.title", "Жиллиман одобряет!");
        translationBuilder.add("advancements.husbandry.warhammer_soup.description", "Приготовьте Великий Суп по рецепту священного ордена «Ультрамаринов»!");

        translationBuilder.add("trim_material.emd_universe.rose", "Розово-золотая");

        translationBuilder.add("painting.emd_universe.chainsaw_mode.title", "Дрон-Бензопила");
        translationBuilder.add("painting.emd_universe.chainsaw_mode.author", "Chesnok81");

        translationBuilder.add("painting.emd_universe.cosmic_top_secret.title", "Засекреченный проект");
        translationBuilder.add("painting.emd_universe.cosmic_top_secret.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.dushnila.title", "Душнила Эл");
        translationBuilder.add("painting.emd_universe.dushnila.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.extremal_mafia_drones.title", "Экстремальные Дроны Мафии");
        translationBuilder.add("painting.emd_universe.extremal_mafia_drones.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.family.title", "Семейное фото");
        translationBuilder.add("painting.emd_universe.family.author", "Glitch Productions");

        translationBuilder.add("painting.emd_universe.finland_logo.title", "Логотип Легиона");
        translationBuilder.add("painting.emd_universe.finland_logo.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.gigachats.title", "Дроны-Гигачаты");
        translationBuilder.add("painting.emd_universe.gigachats.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.gnida.title", "Сущность \"Ролтон\"");
        translationBuilder.add("painting.emd_universe.gnida.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.gon.title", "Фото Гильдии");
        translationBuilder.add("painting.emd_universe.gon.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.hotline.title", "Узи");
        translationBuilder.add("painting.emd_universe.hotline.author", "[Автор неопознан]");

        translationBuilder.add("painting.emd_universe.how_to_use.title", "Гайд по Сверхвычислителю");
        translationBuilder.add("painting.emd_universe.how_to_use.author", "Лиам Викерс");

        translationBuilder.add("painting.emd_universe.kolokal.title", "Знакомтесь, Колокал!");
        translationBuilder.add("painting.emd_universe.kolokal.author", "Kolokal Studio (feat. Сообщество ERFMD)");

        translationBuilder.add("painting.emd_universe.no_dogs.title", "Бездомных собак не впускать!!!");
        translationBuilder.add("painting.emd_universe.no_dogs.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.old_photo.title", "Старинное фото");
        translationBuilder.add("painting.emd_universe.old_photo.author", "AJ DiSpirito (feat. Glitch Productions)");

        translationBuilder.add("painting.emd_universe.oni_logo.title", "Логотип ОНИ");
        translationBuilder.add("painting.emd_universe.oni_logo.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.poster.title", "Постер к пилоту");
        translationBuilder.add("painting.emd_universe.poster.author", "Glitch Productions");

        translationBuilder.add("painting.emd_universe.pribeyblyat.title", "Разыскиваются!");
        translationBuilder.add("painting.emd_universe.pribeyblyat.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.sd_f.title", "Смущённая Эф");
        translationBuilder.add("painting.emd_universe.sd_f.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.summer_episode.title", "Пляжный эпизод");
        translationBuilder.add("painting.emd_universe.summer_episode.author", "❖BLACK TEARS❖ (feat. Сообщество ERFMD)");

        translationBuilder.add("painting.emd_universe.tau_59.title", "Добро пожаловать в Тау-59");
        translationBuilder.add("painting.emd_universe.tau_59.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.tessa.title", "Знакомтесь, Тесса!");
        translationBuilder.add("painting.emd_universe.tessa.author", "Liam Vickers");

        translationBuilder.add("painting.emd_universe.unknown_logo.title", "???");
        translationBuilder.add("painting.emd_universe.unknown_logo.author", "Сообщество ERFMD");

        translationBuilder.add("painting.emd_universe.wayne.title", "Знакомтесь, Вэйн!");
        translationBuilder.add("painting.emd_universe.wayne.author", "Сообщество ERFMD");

        translationBuilder.add("itemgroup.test", "Тест");
    }
}
