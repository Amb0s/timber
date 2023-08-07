package turniplabs.timber;

import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.client.sound.block.BlockSounds;
import net.minecraft.core.block.Block;

public enum Tree {
    OAK (Block.logOak.id, Block.leavesOak.id, "log.oak", new int[]{1, 20, 0, 20}),
    PINE (Block.logPine.id, Block.leavesPine.id, "log.pine", new int[]{1, 23, 0, 23}),
    BIRCH (Block.logBirch.id, Block.leavesBirch.id, "log.birch", new int[]{1, 24, 0, 24}),
    CHERRY (Block.logCherry.id, Block.leavesCherry.id, "log.cherry", new int[]{1, 25, 0, 25}),
    EUCALYPTUS (Block.logEucalyptus.id, Block.leavesEucalyptus.id, "log.eucalyptus",
            new int[]{1, 26, 0, 26}),
    OAK_MOSSY (Block.logOakMossy.id, Block.leavesOak.id, "log.oak.mossy", new int[]{1, 21, 0, 21}),
    OAK_RETRO (Block.logOak.id, Block.leavesOakRetro.id, "log.oak", new int[]{1, 20, 0, 20}),
    CHERRY_FLOWERING (Block.logCherry.id, Block.leavesCherryFlowering.id, "log.cherry",
            new int[]{1, 25, 0, 25}),
    SHRUB (Block.logOak.id, Block.leavesShrub.id, "log.oak", new int[]{1, 20, 0, 20});

    public final int logID;
    public final int leavesID;
    public final String logName;
    public final int[] textureCoordinates;

    public static final float BARE_HANDS_HARDNESS = 2.0F;

    public static final float AXE_HARDNESS = 6 * BARE_HANDS_HARDNESS;

    public static final BlockSound SOUND = BlockSounds.WOOD;

    public static final int[] LOG_TYPES = {
            OAK.logID, PINE.logID, BIRCH.logID, CHERRY.logID, EUCALYPTUS.logID, OAK_MOSSY.logID
    };

    public static final int[] LEAVES_TYPES = {
            OAK.leavesID, PINE.leavesID, BIRCH.leavesID, CHERRY.leavesID, EUCALYPTUS.leavesID, OAK_RETRO.leavesID,
            CHERRY_FLOWERING.leavesID, SHRUB.leavesID
    };

    Tree(int logID, int leavesID, String logName, int[] textureCoordinates) {
        this.logID = logID;
        this.leavesID = leavesID;
        this.logName = logName;
        this.textureCoordinates = textureCoordinates;
    }
}
