
package net.voxla.hybridvx.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class VileBoneItem extends Item {
	public VileBoneItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
