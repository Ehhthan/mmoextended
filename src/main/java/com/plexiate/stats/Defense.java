package com.plexiate.stats;

import net.Indyuce.mmoitems.api.item.MMOItem;
import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
import net.Indyuce.mmoitems.api.itemgen.NumericStatFormula;
import net.Indyuce.mmoitems.api.itemgen.RandomStatData;
import net.Indyuce.mmoitems.api.util.StatFormat;
import net.Indyuce.mmoitems.stat.data.DoubleData;
import net.Indyuce.mmoitems.stat.data.type.StatData;
import net.Indyuce.mmoitems.stat.type.DoubleStat;
import net.mmogroup.mmolib.api.item.ItemTag;
import net.mmogroup.mmolib.api.item.NBTItem;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;


public class Defense extends DoubleStat {
    public Defense() {
        super("DEFENSE", new ItemStack(Material.SHIELD), "Defense", new String[] { "Defense formula can be set", "in the MMOExtended config." }, new String[] { "all" }, new org.bukkit.Material[0]);
    }
    @Override
    public StatData whenInitialized(Object object) {
        return new DoubleData(object);
    }
    @Override
    public RandomStatData whenInitializedGeneration(Object object) {

        if (object instanceof Number)
            return new NumericStatFormula(Double.valueOf(object.toString()), 0, 0, 0);

        if (object instanceof ConfigurationSection)
            return new NumericStatFormula((ConfigurationSection) object);

        throw new IllegalArgumentException("Must specify a number or a config section");
    }
    @Override
    public void whenApplied(MMOItemBuilder item, StatData data) {
        double value = ((DoubleData)data).generateNewValue();
        item.addItemTag(new ItemTag[] { new ItemTag("MMOITEMS_DEFENSE", Double.valueOf(value)) });
        item.getLore().insert(getPath(), format(value, "#", new StatFormat("##").format(value)));
    }
    public void whenLoaded(MMOItem mmoitem, NBTItem item) {
        if (item.hasTag("MMOITEMS_DEFENSE"))
            mmoitem.setData(this, new DoubleData(item.getDouble("MMOITEMS_DEFENSE")));
    }
}
