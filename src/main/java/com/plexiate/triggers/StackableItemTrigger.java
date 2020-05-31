package com.plexiate.triggers;

import haveric.stackableItems.util.InventoryUtil;
import net.Indyuce.mmocore.api.quest.trigger.Trigger;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;
import net.mmogroup.mmolib.api.MMOLineConfig;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StackableItemTrigger extends Trigger {
    private final Type type;

    private final String id;

    private final int amount;

    public StackableItemTrigger(MMOLineConfig config) {
        super(config);
        config.validate(new String[] { "type", "id" });
        String format = config.getString("type").toUpperCase().replace("-", "_").replace(" ", "_");
        Validate.isTrue(MMOItems.plugin.getTypes().has(format), "Could not find item type " + format);
        this.type = MMOItems.plugin.getTypes().get(format);
        this.id = config.getString("id").replace("-", "_").toUpperCase();
        this.amount = config.contains("amount") ? Math.max(1, config.getInt("amount")) : 1;
        Validate.isTrue(this.type.getConfigFile().getConfig().contains(this.id), "Could not find item id " + this.id);
    }
    public void apply(PlayerData player) {
        ItemStack item = MMOItems.plugin.getItems().getItem(this.type, this.id);
        if (item == null || item.getType() == Material.AIR)
            return;
        item.setAmount(this.amount);
        if (item != null && item.getType() != Material.AIR)
            InventoryUtil.addItemsToPlayer(player.getPlayer(), item, "");
    }
}
