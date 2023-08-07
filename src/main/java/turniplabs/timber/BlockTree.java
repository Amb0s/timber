package turniplabs.timber;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLog;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.world.World;

import java.util.Arrays;

public class BlockTree extends BlockLog {
    public static boolean isAxe;

    public BlockTree(String key, int id) {
        super(key, id);
    }

    public static void setAxe(boolean isAxe) {
        BlockTree.isAxe = isAxe;
    }

    private void check(World world, int x, int y, int z) {
        byte i = 1;

        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                for (int m = 0; m <= i; ++m) {
                    int n = world.getBlockId(x + j, y + m, z + k);
                    if (Arrays.stream(Tree.LOG_TYPES).anyMatch(Integer.valueOf(n)::equals)) {
                        Block block = Block.blocksList[world.getBlockId(x + j, y + m, z + k)];
                        int i1 = world.getBlockMetadata(x + j, y + m, z + k);
                        if (block != null && world.setBlockWithNotify(x + j, y + m, z + k, 0)) {
                            block.dropBlockWithCause(world, EnumDropCause.PROPER_TOOL,x + j, y + m, z + k, i1,
                                    new TileEntity());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBlockRemoval(World world, int x, int y, int z) {
        if (isAxe) {
            this.check(world, x, y, z);
        }

        byte i = 4;
        int j = i + 1;

        if (world.areBlocksLoaded(x - j, y - j, z - j, x + j, y + j, z + j)) {
            for (int k = -i; k <= i; ++k) {
                for (int m = -i; m <= i; ++m) {
                    for (int n = -i; n <= i; ++n) {
                        int i1 = world.getBlockId(x + k, y + m, z + n);
                        if (Arrays.stream(Tree.LEAVES_TYPES).anyMatch(Integer.valueOf(i1)::equals)) {
                            int i2 = world.getBlockMetadata(x + k, y + m, z + n);
                            if ((i2 & 8) == 0) {
                                world.setBlockMetadata(x + k, y + m, z + n, i2 | 8);
                            }
                        }
                    }
                }
            }
        }
    }
}
