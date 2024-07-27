package com.zenkodyazilim.langfella.inventory;

public interface InventoryService {
    boolean isAvailable(String item, int quantity);
}
