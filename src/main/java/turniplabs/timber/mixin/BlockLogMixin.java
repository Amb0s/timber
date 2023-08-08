package turniplabs.timber.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLog;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemToolAxe;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(value = BlockLog.class, remap = false)
final class BlockLogMixin {
    @Inject(method = "onBlockRemoval", at = @At("HEAD"), require = 0)
    private void checkAxe(World world, int x, int y, int z, CallbackInfo ci) {
        boolean isSneaking = Minecraft.getMinecraft(Minecraft.class).thePlayer.isSneaking();
        ItemStack inHand = Minecraft.getMinecraft(Minecraft.class).thePlayer.getCurrentEquippedItem();

        int[] logIDs = {
                Block.logOak.id, Block.logPine.id, Block.logBirch.id,
                Block.logCherry.id, Block.logEucalyptus.id, Block.logOakMossy.id
        };

        if (!isSneaking && inHand != null && inHand.getItem() instanceof ItemToolAxe) {
            byte i = 1;

            for (int j = -i; j <= i; ++j) {
                for (int k = -i; k <= i; ++k) {
                    for (int m = 0; m <= i; ++m) {
                        int n = world.getBlockId(x + j, y + m, z + k);
                        if (Arrays.stream(logIDs).anyMatch(Integer.valueOf(n)::equals)) {
                            Block block = Block.blocksList[world.getBlockId(x + j, y + m, z + k)];
                            int i1 = world.getBlockMetadata(x + j, y + m, z + k);
                            if (block != null && world.setBlockWithNotify(x + j, y + m, z + k, 0)) {
                                block.dropBlockWithCause(world, EnumDropCause.PROPER_TOOL,x + j, y + m, z + k,
                                        i1, new TileEntity());
                            }
                        }
                    }
                }
            }
        }
    }
}
