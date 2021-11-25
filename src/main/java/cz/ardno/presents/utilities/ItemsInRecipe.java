package cz.ardno.presents.utilities;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ItemsInRecipe<T> extends ArrayList<T> {

    @Override
    public boolean equals(Object o) {
        boolean done = false;
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i) == null && ((ArrayList<ItemStack>) o).get(i) == null)) {
                continue;
            }
            if ((this.get(i) == null && ((ArrayList<ItemStack>) o).get(i) != null) || (this.get(i) != null && ((ArrayList<ItemStack>) o).get(i) == null)) {
                break;
            }
            if (!((ItemStack) this.get(i)).isSimilar(((ArrayList<ItemStack>) o).get(i))) {
                break;
            }
            if (i + 1 == this.size()) {
                done = true;
                break;
            }
        }
        return done;
    }
}
