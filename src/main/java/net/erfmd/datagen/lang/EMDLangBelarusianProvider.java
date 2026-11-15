package net.erfmd.datagen.lang;

import net.erfmd.block.EMDBlocks;
import net.erfmd.item.EMDItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EMDLangBelarusianProvider extends FabricLanguageProvider {
    public EMDLangBelarusianProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "be_by", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(EMDItems.RAW_RUBY, "Сырой рубін");
        translationBuilder.add(EMDItems.RUBY, "Рубін");

        translationBuilder.add(EMDItems.PIECE_OF_CAKE, "Кавалачак торта");
        translationBuilder.add(EMDItems.PIECE_OF_CHOCOLATE_CAKE, "Кавалачак шакаладнага торта");
        translationBuilder.add(EMDItems.PIECE_OF_EXPERIMENT_115, "Кавалачак \"Эксперыменту 115\"");
        translationBuilder.add(EMDItems.PIECE_OF_EXPERIMENT_803S, "Кавалачак \"Эксперыменту 803-S\"");
        translationBuilder.add(EMDItems.CARAMEL, "Карамель");
        translationBuilder.add(EMDItems.CARAMEL_APPLE, "Карамельны яблык");

        translationBuilder.add(EMDItems.CHOCOLATE_CAKE, "Шакаладны торт");
        translationBuilder.add(EMDItems.EXPERIMENT_115, "Эксперымент 115");
        translationBuilder.add(EMDItems.EXPERIMENT_803S, "Эксперымент 803-S");

        translationBuilder.add(EMDItems.ROSE_GOLDEN_APPLE, "Ружовы залаты яблык");

        translationBuilder.add(EMDItems.ROSE_GOLD_INGOT, "Ружовы залаты злітак");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_SWORD, "Ружовы залаты меч");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_AXE, "Ружовая залатая сякера");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_PICKAXE, "Ружовая залатая кірка");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_SHOVEL, "Ружовая залатая рыдлёўка");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_HOE, "Ружовая залатая матыка");

        translationBuilder.add(EMDItems.ROSE_GOLDEN_HELMET, "Ружовы залаты шлем");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_CHESTPLATE, "Ружовы залаты кіраса");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_LEGGINGS, "Ружовыя залатыя понажы");
        translationBuilder.add(EMDItems.ROSE_GOLDEN_BOOTS, "Ружовыя залатыя боты");

        translationBuilder.add(EMDItems.COPPER_COIL, "Мядзяна шпулька");
        translationBuilder.add(EMDItems.COPPER_WIRE, "Мядзяны дрот");
        translationBuilder.add(EMDItems.ACCUMULATOR, "Акумулятар");
        translationBuilder.add(EMDItems.ENERGY_BALL, "Энергетычная сфера");
        translationBuilder.add(EMDItems.SPECIAL_STORAGE, "Чып-плата");
        translationBuilder.add(EMDItems.EMERALD_DUST, "Смарагдавы пыл");
        translationBuilder.add(EMDItems.DIAMOND_DUST, "Дыяментавы пыл");
        translationBuilder.add(EMDItems.METAL_ALLOY, "Металічны сплаў");
        translationBuilder.add(EMDItems.WARPED_NETHERITE_INGOT, "Скажоны нэдарыт");
        translationBuilder.add(EMDItems.HIGH_VOLTAGE_BATTERY, "Высакавольтная батарэя");

        translationBuilder.add(EMDItems.DISASSEMBLY_CHAINSAW, "Бензапіла Дрона-Дэмантажніка");
        translationBuilder.add(EMDItems.LM85_BLADE, "Клінок LM85");

        translationBuilder.add(EMDItems.HAM, "Свіны скарочак");
        translationBuilder.add(EMDItems.HAM_BAT, "Мясная дубінка");
        translationBuilder.add(EMDItems.LOLLICHOP, "Клядзянец");
        translationBuilder.add(EMDItems.CANDY_CANE, "Карамельны кій");
        translationBuilder.add(EMDItems.WORKER_DRONE_ARM, "Рука Дрона-Рабочага");

        translationBuilder.add(EMDItems.CHERRIES, "Вішня");
        translationBuilder.add(EMDItems.BANANA, "Банан");
        translationBuilder.add(EMDItems.HONEYSUCKLE, "Бружмель");
        translationBuilder.add(EMDItems.CHERRY_PIE, "Вішнёвы пірог");
        translationBuilder.add(EMDItems.HONEYSUCKLE_PIE, "Пірог з бружмелі");

        translationBuilder.add(EMDBlocks.POTATO_BLOCK, "Блок бульбы");
        translationBuilder.add(EMDBlocks.POISONOUS_POTATO_BLOCK, "Блок атрутнай бульбы");

        translationBuilder.add(EMDBlocks.RUBY_ORE, "Рубінавая руда");
        translationBuilder.add(EMDBlocks.DEEPSLATE_RUBY_ORE, "Рубінавая руда глыбінь");
        translationBuilder.add(EMDBlocks.NETHER_RUBY_ORE, "Рубінавая руда нэдара");
        translationBuilder.add(EMDBlocks.NETHER_IRON_ORE, "Жалезная руда нэдара");
        translationBuilder.add(EMDBlocks.RAW_RUBY_BLOCK, "Блок сырога рубіна");
        translationBuilder.add(EMDBlocks.RUBY_BLOCK, "Рубінавы блок");
        translationBuilder.add(EMDBlocks.ROSE_GOLD_BLOCK, "Ружовы залаты блок");

        translationBuilder.add(EMDBlocks.STONE_TILES, "Каменная кафля");
        translationBuilder.add(EMDBlocks.STONE_TILE_SLAB, "Пліта з каменнай кафлі");
        translationBuilder.add(EMDBlocks.STONE_TILE_STAIRS, "Прыступкі з каменнай кафлі");
        translationBuilder.add(EMDBlocks.STONE_TILE_WALL, "Сцяна з каменнай кафлі");
        translationBuilder.add(EMDBlocks.CRACKED_STONE_TILES, "Патрэсканая каменная кафля");

        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILES, "Кафля з паліраванага чарніту");
        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILE_SLAB, "Пліта з паліраванай чарнітавай кафлі");
        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILE_STAIRS, "Прыступкі з паліраванай чарнітавай кафлі");
        translationBuilder.add(EMDBlocks.POLISHED_BLACKSTONE_TILE_WALL, "Сцяна з паліраванай чарнітавай кафлі");
        translationBuilder.add(EMDBlocks.CRACKED_POLISHED_BLACKSTONE_TILES, "Патрэсканая кафля з паліраванага чарніту");

        translationBuilder.add(EMDBlocks.SMOOTH_END_STONE, "Гладкі камень Энда");
        translationBuilder.add(EMDBlocks.SMOOTH_END_STONE_SLAB, "Пліта з гладкага камню Энда");
        translationBuilder.add(EMDBlocks.SMOOTH_END_STONE_STAIRS, "Прыступкі з гладкага камню Энда");
        translationBuilder.add(EMDBlocks.END_STONE_TILES, "Кафля з камню Энда");
        translationBuilder.add(EMDBlocks.END_STONE_TILE_SLAB, "Пліта з кафлі Энда");
        translationBuilder.add(EMDBlocks.END_STONE_TILE_STAIRS, "Прыступкі з кафлі Энда");
        translationBuilder.add(EMDBlocks.END_STONE_TILE_WALL, "Сцяна з кафлі Энда");

        translationBuilder.add(EMDBlocks.POLISHED_STONE, "Паліраваны камень");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_SLAB, "Пліта з паліраванай каменя");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_STAIRS, "Прыступкі з паліраванай каменя");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_WALL, "Сцяна з паліраванай каменя");
        translationBuilder.add(EMDBlocks.POLISHED_STONE_PILLAR, "Паліраваная каменная калона");

        translationBuilder.add(EMDBlocks.SANDSTONE_BRICKS, "Пяшчаніковая цэгла");
        translationBuilder.add(EMDBlocks.SANDSTONE_BRICK_SLAB, "Пліта з пяшчанікавай цэглы");
        translationBuilder.add(EMDBlocks.SANDSTONE_BRICK_STAIRS, "Прыступкі з пяшчанікавай цэглы");
        translationBuilder.add(EMDBlocks.SANDSTONE_BRICK_WALL, "Сцяна з пяшчанікавай цэглы");
        translationBuilder.add(EMDBlocks.CHISELED_SANDSTONE_BRICKS, "Разьбяная пяшчаніковая цэгла");

        translationBuilder.add(EMDBlocks.POLISHED_NETHER_BRICKS, "Паліраваная недарская цэгла");
        translationBuilder.add(EMDBlocks.POLISHED_NETHER_BRICK_SLAB, "Пліта з паліраванай недарскай цэглы");
        translationBuilder.add(EMDBlocks.POLISHED_NETHER_BRICK_STAIRS, "Прыступкі з паліраванай недарскай цэглы");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICKS, "Скажоная недарская цэгла");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICK_SLAB, "Пліта з скажонай недарскай цэглы");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICK_STAIRS, "Прыступкі з скажонай недарскай цэглы");
        translationBuilder.add(EMDBlocks.WARP_NETHER_BRICK_WALL, "Сцяна з скажонай недарскай цэглы");

        translationBuilder.add(EMDBlocks.SNOW_BRICKS, "Снежная цэгла");
        translationBuilder.add(EMDBlocks.SNOW_BRICK_SLAB, "Пліта з снежнай цэглы");
        translationBuilder.add(EMDBlocks.SNOW_BRICK_STAIRS, "Прыступкі з снежнай цэглы");
        translationBuilder.add(EMDBlocks.SNOW_BRICK_WALL, "Сцяна з снежнай цэглы");

        translationBuilder.add(EMDBlocks.TERRACOTTA_BRICKS, "Керамікавая цегла");
        translationBuilder.add(EMDBlocks.WHITE_TERRACOTTA_BRICKS, "Белая керамікавая цегла");
        translationBuilder.add(EMDBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, "Светла-шэрая керамікавая цегла");
        translationBuilder.add(EMDBlocks.GRAY_TERRACOTTA_BRICKS, "Шэрая керамікавая цегла");
        translationBuilder.add(EMDBlocks.BLACK_TERRACOTTA_BRICKS, "Чорная керамікавая цегла");
        translationBuilder.add(EMDBlocks.BROWN_TERRACOTTA_BRICKS, "Карычневая керамікавая цегла");
        translationBuilder.add(EMDBlocks.RED_TERRACOTTA_BRICKS, "Чырвоная керамікавая цегла");
        translationBuilder.add(EMDBlocks.ORANGE_TERRACOTTA_BRICKS, "Аранжавая керамікавая цегла");
        translationBuilder.add(EMDBlocks.YELLOW_TERRACOTTA_BRICKS, "Жоўтая керамікавая цегла");
        translationBuilder.add(EMDBlocks.LIME_TERRACOTTA_BRICKS, "Лаймавая керамікавая цегла");
        translationBuilder.add(EMDBlocks.GREEN_TERRACOTTA_BRICKS, "Зялёная керамікавая цегла");
        translationBuilder.add(EMDBlocks.CYAN_TERRACOTTA_BRICKS, "Бірузовая керамікавая цегла");
        translationBuilder.add(EMDBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, "Блакітная керамікавая цегла");
        translationBuilder.add(EMDBlocks.BLUE_TERRACOTTA_BRICKS, "Сіняя керамікавая цегла");
        translationBuilder.add(EMDBlocks.PURPLE_TERRACOTTA_BRICKS, "Фіялетавая керамікавая цегла");
        translationBuilder.add(EMDBlocks.MAGENTA_TERRACOTTA_BRICKS, "Пурпурная керамікавая цегла");
        translationBuilder.add(EMDBlocks.PINK_TERRACOTTA_BRICKS,  "Ружовая керамікавая цегла");

        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICKS, "Абсідыянавая цегла");
        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICK_SLAB, "Пліта з абсідыянавай цеглы");
        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICK_STAIRS, "Прыступкі з абсідыянавай цеглы");
        translationBuilder.add(EMDBlocks.OBSIDIAN_BRICK_WALL, "Сцяна з абсідыянавай цеглы");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN, "Паліраваны абсідыян");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN_SLAB, "Пліта з паліравага абсідыянy");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN_STAIRS, "Прыступкі з паліравага абсідыянy");
        translationBuilder.add(EMDBlocks.POLISHED_OBSIDIAN_WALL, "Сцяна з паліравага абсідыянy");
        translationBuilder.add(EMDBlocks.CHISELED_OBSIDIAN, "Різьблены абсідыян");
        translationBuilder.add(EMDBlocks.OBSIDIAN_PILLAR, "Абсідыянавая калона");

        translationBuilder.add(EMDBlocks.IRON_BAR_DOOR, "Сеткаватыя дзверы");
        translationBuilder.add(EMDBlocks.IRON_BAR_BLOCK, "Кратчаты блок");
        translationBuilder.add(EMDBlocks.IRON_BAR_STAIRS, "Кратчатыя прыступкі");
        translationBuilder.add(EMDBlocks.IRON_BAR_SLAB, "Кратчатая пліта");
        translationBuilder.add(EMDBlocks.IRON_BAR_TRAPDOOR, "Кратчаты люк");
        translationBuilder.add(EMDBlocks.IRON_BAR_WALL, "Кратчатыя сцяна");

        translationBuilder.add(EMDBlocks.REDSTONE_HEART, "Рэдстоўнавае сэрца");

        translationBuilder.add(EMDBlocks.COLORED_REDSTONE_LAMP, "Рознакаляровая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.BLACK_REDSTONE_LAMP, "Чорная рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.BLUE_REDSTONE_LAMP, "Сіняя рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.BROWN_REDSTONE_LAMP, "Карычневая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.CYAN_REDSTONE_LAMP, "Бірузовая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.GRAY_REDSTONE_LAMP, "Шэрая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.GREEN_REDSTONE_LAMP, "Зялёная рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.LIGHT_BLUE_REDSTONE_LAMP, "Блакітная рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.LIGHT_GRAY_REDSTONE_LAMP, "Светла-шэрая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.LIME_REDSTONE_LAMP, "Лаймавая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.MAGENTA_REDSTONE_LAMP, "Пурпурная рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.ORANGE_REDSTONE_LAMP, "Аранжавая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.PINK_REDSTONE_LAMP, "Ружовая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.PURPLE_REDSTONE_LAMP, "Фіялетавая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.RED_REDSTONE_LAMP, "Чырвоная рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.WHITE_REDSTONE_LAMP, "Белая рэдстоўнавая лампа");
        translationBuilder.add(EMDBlocks.YELLOW_REDSTONE_LAMP, "Жоўтая рэдстоўнавая лампа");

        translationBuilder.add(EMDItems.OIL_SUN_ARMOR_TRIM_SMITHING_TEMPLATE, "Кавальскі шаблон");
        translationBuilder.add(EMDItems.LOVE_HAREM_ARMOR_TRIM_SMITHING_TEMPLATE, "Кавальскі шаблон");

        translationBuilder.add("trim_pattern.emd_universe.oil_sun", "Арнамент «Сонца»");
        translationBuilder.add("trim_pattern.emd_universe.love_harem", "Арнамент «Каханне»");

        translationBuilder.add("trim_material.emd_universe.rose", "Матэрыял - Ружовае золата");

        translationBuilder.add("painting.emd_universe.chainsaw_mode.title", "Дрон-Бензапіла");
        translationBuilder.add("painting.emd_universe.chainsaw_mode.author", "Chesnok81");

        translationBuilder.add("painting.emd_universe.cosmic_top_secret.title", "Засакрэчаны праект");
        translationBuilder.add("painting.emd_universe.cosmic_top_secret.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.dushnila.title", "Душніла Эл");
        translationBuilder.add("painting.emd_universe.dushnila.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.extremal_mafia_drones.title", "Экстрэмальныя Дроны Мафіі");
        translationBuilder.add("painting.emd_universe.extremal_mafia_drones.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.family.title", "Сямейнае фота");
        translationBuilder.add("painting.emd_universe.family.author", "Glitch Productions");

        translationBuilder.add("painting.emd_universe.finland_logo.title", "Лагатып Легіёна");
        translationBuilder.add("painting.emd_universe.finland_logo.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.gigachats.title", "Дроны-Гігачаты");
        translationBuilder.add("painting.emd_universe.gigachats.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.gnida.title", "Сутнасць \"Ролтан\"");
        translationBuilder.add("painting.emd_universe.gnida.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.gon.title", "Фота Гільдыі");
        translationBuilder.add("painting.emd_universe.gon.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.hotline.title", "Узи");
        translationBuilder.add("painting.emd_universe.hotline.author", "[Автор неопознан]");

        translationBuilder.add("painting.emd_universe.how_to_use.title", "Гайд па Звышвылічальніку");
        translationBuilder.add("painting.emd_universe.how_to_use.author", "Ліам Вікерс");

        translationBuilder.add("painting.emd_universe.kolokal.title", "Знаёмцеся, Колокал!");
        translationBuilder.add("painting.emd_universe.kolokal.author", "Kolokal Studio (feat. Супольнасць ERFMD)");

        translationBuilder.add("painting.emd_universe.no_dogs.title", "Бяздомных сабак не ўпускаць!!!");
        translationBuilder.add("painting.emd_universe.no_dogs.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.old_photo.title", "Старадаўняе фота");
        translationBuilder.add("painting.emd_universe.old_photo.author", "AJ DiSpirito (feat. Glitch Productions)");

        translationBuilder.add("painting.emd_universe.oni_logo.title", "Лагатып ОНИ");
        translationBuilder.add("painting.emd_universe.oni_logo.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.poster.title", "Постэр да пілота");
        translationBuilder.add("painting.emd_universe.poster.author", "Glitch Productions");

        translationBuilder.add("painting.emd_universe.pribeyblyat.title", "Шукаюць!");
        translationBuilder.add("painting.emd_universe.pribeyblyat.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.sd_f.title", "Збянтэжаная Эф");
        translationBuilder.add("painting.emd_universe.sd_f.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.tau_59.title", "Сардэчна запрашаем у Таў-59");
        translationBuilder.add("painting.emd_universe.tau_59.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.tessa.title", "Знаёмцеся, Тесса!");
        translationBuilder.add("painting.emd_universe.tessa.author", "Ліам Вікерс");

        translationBuilder.add("painting.emd_universe.unknown_logo.title", "???");
        translationBuilder.add("painting.emd_universe.unknown_logo.author", "Супольнасць ERFMD");

        translationBuilder.add("painting.emd_universe.wayne.title", "Знаёмцеся, Вэйн!");
        translationBuilder.add("painting.emd_universe.wayne.author", "Супольнасць ERFMD");

        translationBuilder.add("itemgroup.test", "Тэст");
    }
}
