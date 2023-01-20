package ambos.timber;

import turniplabs.halplibe.mixin.accessors.BlockAccessor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.src.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Timber implements ModInitializer {
    public static final String MOD_ID = "timber";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static String name(String name) {
        return Timber.MOD_ID + "." + name;
    }

    @Override
    public void onInitialize() {
        Block treeBlock;

        // Registers modified tree blocks
        for (Tree tree: Tree.values()) {
            if (!tree.equals(Tree.OAK_RETRO) && !tree.equals(Tree.CHERRY_FLOWERING) && !tree.equals(Tree.SHRUB)) {
                Block.blocksList[tree.logID] = null;

                treeBlock = new BlockTree(tree.logID)
                        .setBlockName(tree.logName)
                        .setTexCoords(tree.textureCoordinates[0], tree.textureCoordinates[1],
                                tree.textureCoordinates[2], tree.textureCoordinates[3])
                        .setFenceCanConnectTo();

                if (tree.equals(Tree.OAK)) {
                    treeBlock.setMossGrowable();
                }

                ((BlockAccessor) treeBlock).callSetHardness(Tree.BARE_HANDS_HARDNESS);
                ((BlockAccessor) treeBlock).callSetStepSound(Tree.SOUND);
            }
        }

        LOGGER.info("Timber initialized");
    }
}
