
package net.voxla.hybridvx.item;

import net.voxla.hybridvx.procedures.VileMirrorRightclickedProcedure;
import net.voxla.hybridvx.procedures.VileMirrorItemInInventoryTickProcedure;
import net.voxla.hybridvx.procedures.VileMirrorItemInHandTickProcedure;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

public class VileMirrorItem extends Item {
	public VileMirrorItem() {
		super(new Item.Properties().durability(65).rarity(Rarity.COMMON));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		VileMirrorRightclickedProcedure.execute(world, entity, ar.getObject());
		return ar;
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		if (selected)
			VileMirrorItemInHandTickProcedure.execute(world, entity, itemstack);
		VileMirrorItemInInventoryTickProcedure.execute(world, entity, itemstack);
	}
}
