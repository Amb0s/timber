package ambos.timber.mixin;

import ambos.timber.BlockTree;
import ambos.timber.Tree;
import turniplabs.halplibe.mixin.accessors.BlockAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemToolAxe;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = World.class, remap = false)
public class WorldMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    private void checkAxe(CallbackInfo ci) {
        boolean isSneaking = Minecraft.getMinecraft().thePlayer.isSneaking();
        ItemStack inHand = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();

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
