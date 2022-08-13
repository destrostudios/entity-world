package com.destrostudios.entityworld.synchronizing;

import com.destrostudios.entityworld.bit.BitInputStream;
import com.destrostudios.entityworld.bit.BitOutputStream;

import java.io.IOException;

public class RemovedComponentChange extends EntityChange {

    public RemovedComponentChange() {

    }

    public RemovedComponentChange(int entity, Class<?> componentClass) {
        super(entity);
        this.componentClass = componentClass;
    }
    private Class<?> componentClass;

    @Override
    public void write(BitOutputStream outputStream) {
        super.write(outputStream);
        ComponentSerializer.writeClass(outputStream, componentClass);
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException {
        super.read(inputStream);
        componentClass = ComponentSerializer.readClass(inputStream);
    }

    public Class<?> getComponentClass() {
        return componentClass;
    }
}
