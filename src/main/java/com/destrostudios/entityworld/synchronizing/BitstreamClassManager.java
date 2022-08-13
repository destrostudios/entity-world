package com.destrostudios.entityworld.synchronizing;

import java.util.HashMap;

public class BitstreamClassManager {

    public static BitstreamClassManager getInstance() {
        if (instance == null) {
            instance = new BitstreamClassManager();
        }
        return instance;
    }

    private BitstreamClassManager() { }
    private static BitstreamClassManager instance;
    private HashMap<Class<?>, Integer> ids = new HashMap<>();
    private HashMap<Integer, Class<?>> classes = new HashMap<>();
    private int nextID;

    public void register(Class<?>... serializedClasses) {
        for (Class<?> serializedClass : serializedClasses) {
            register(serializedClass);
        }
    }

    public void register(Class<?> serializedClass) {
        ids.put(serializedClass, nextID);
        classes.put(nextID, serializedClass);
        nextID++;
    }

    public int getID(Class<?> serializedClass) {
        Integer id = ids.get(serializedClass);
        if (id == null) {
            throw new IllegalArgumentException("Bitstream class not registered: " + serializedClass.getName());
        }
        return id;
    }

    public Class<?> getClass(int id) {
        return classes.get(id);
    }

    public int getCount() {
        return nextID;
    }
}
