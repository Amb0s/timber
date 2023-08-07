package turniplabs.timber;

import net.minecraft.client.sound.block.BlockSoundDispatcher;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import turniplabs.halplibe.mixin.accessors.BlockAccessor;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.mixin.accessors.DispatcherAccessor;

public class Timber implements ModInitializer {
    public static final String MOD_ID = "timber";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Block treeBlock;

        // Registers modified tree blocks
        for (Tree tree: Tree.values()) {
            if (!tree.equals(Tree.OAK_RETRO) && !tree.equals(Tree.CHERRY_FLOWERING) && !tree.equals(Tree.SHRUB)) {
                Block.blocksList[tree.logID] = null;

                treeBlock = new BlockTree(tree.logName,tree.logID)
                        .withTexCoords(tree.textureCoordinates[0], tree.textureCoordinates[1],
                                tree.textureCoordinates[2], tree.textureCoordinates[3])
                        .withTags(BlockTags.FENCES_CONNECT, BlockTags.MINEABLE_BY_AXE);


                ((BlockAccessor) treeBlock).callSetHardness(Tree.BARE_HANDS_HARDNESS);
                ((DispatcherAccessor) BlockSoundDispatcher.getInstance()).callAddDispatch(treeBlock,Tree.SOUND);
            }
        }

        LOGGER.info("Timber initialized");
    }
}
