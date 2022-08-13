package com.destrostudios.entityworld.synchronizing;

import java.util.Collections;
import java.util.HashSet;

public class ComponentClassSet{

    private HashSet<Class<?>> classes = new HashSet<>();

    public void add(Class<?>... componentClasses) {
        Collections.addAll(classes, componentClasses);
    }

    public void remove(Class<?> componentClass) {
        classes.remove(componentClass);
    }

    public boolean contains(Class<?> componentClass) {
        return classes.contains(componentClass);
    }

    public int size() {
        return classes.size();
    }
}
