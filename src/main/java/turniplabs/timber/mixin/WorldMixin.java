package turniplabs.timber.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemToolAxe;
import net.minecraft.core.world.World;
import turniplabs.timber.BlockTree;
import turniplabs.timber.Tree;
import turniplabs.halplibe.mixin.accessors.BlockAccessor;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = World.class, remap = false)
public class WorldMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    private void checkAxe(CallbackInfo ci) {
        boolean isSneaking = Minecraft.getMinecraft(Minecraft.class).thePlayer.isSneaking();
        ItemStack inHand = Minecraft.getMinecraft(Minecraft.class).thePlayer.getCurrentEquippedItem();

        if (!isSneaking && inHand != null && inHand.getItem() instanceof ItemToolAxe) {
            for (int logType: Tree.LOG_TYPES) {
                ((BlockAccessor) Block.blocksList[logType]).callSetHardness(Tree.AXE_HARDNESS);
            }

            BlockTree.setAxe(true);
        } else {
            for (int logType: Tree.LOG_TYPES) {
                ((BlockAccessor) Block.blocksList[logType]).callSetHardness(Tree.BARE_HANDS_HARDNESS);
            }

            BlockTree.setAxe(false);
        }
    }
}
