package com.destrostudios.entityworld;

public class EntityUtil {

    public static void transferComponents(EntityWorld entityWorld, int sourceEntity, int targetEntity, Class<?>[] componentClasses) {
        for (Class<?> componentClass : componentClasses) {
            Object component = entityWorld.getComponent(sourceEntity, componentClass);
            if (component != null) {
                entityWorld.setComponent(targetEntity, component);
            }
        }
    }
}
