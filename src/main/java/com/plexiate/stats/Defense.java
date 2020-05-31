package com.plexiate.stats;

import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
import net.Indyuce.mmoitems.api.util.StatFormat;
import net.Indyuce.mmoitems.stat.data.DoubleData;
import net.Indyuce.mmoitems.stat.data.type.StatData;
import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.mmogroup.mmolib.api.item.ItemTag;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Defense extends DoubleStat {
    public Defense() {
        super("Defense", new ItemStack(Material.SHIELD), "Defense", new String[] { "The defense amount is how", "much damage a player takes." }, new String[] { "all" }, new org.bukkit.Material[0]);
    }
    @Override
    public void whenApplied(MMOItemBuilder item, StatData data) {
        double value = ((DoubleData) data).generateNewValue();
        item.addItemTag(new ItemTag(getNBTPath(), value));
        item.getLore().insert(getPath(), format(value, "#", new StatFormat("##").format(value)));
    }
}
