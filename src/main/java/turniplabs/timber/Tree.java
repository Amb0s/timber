package turniplabs.timber;

import net.minecraft.client.sound.block.BlockSound;
import net.minecraft.client.sound.block.BlockSounds;
import net.minecraft.core.block.Block;

public enum Tree {
    OAK (Block.logOak.id, Block.leavesOak.id, "log.oak"),
    PINE (Block.logPine.id, Block.leavesPine.id, "log.pine"),
    BIRCH (Block.logBirch.id, Block.leavesBirch.id, "log.birch"),
    CHERRY (Block.logCherry.id, Block.leavesCherry.id, "log.cherry"),
    EUCALYPTUS (Block.logEucalyptus.id, Block.leavesEucalyptus.id, "log.eucalyptus"),
    OAK_MOSSY (Block.logOakMossy.id, Block.leavesOak.id, "log.oak.mossy"),
    OAK_RETRO (Block.logOak.id, Block.leavesOakRetro.id, "log.oak"),
    CHERRY_FLOWERING (Block.logCherry.id, Block.leavesCherryFlowering.id, "log.cherry"),
    SHRUB (Block.logOak.id, Block.leavesShrub.id, "log.oak");

    public final int logID;
    public final int leavesID;
    public final String logName;

    Tree(int logID, int leavesID, String logName) {
        this.logID = logID;
        this.leavesID = leavesID;
        this.logName = logName;
    }

    public static final int[] LOG_TYPES = {
            OAK.logID, PINE.logID, BIRCH.logID, CHERRY.logID, EUCALYPTUS.logID, OAK_MOSSY.logID
    };
}
