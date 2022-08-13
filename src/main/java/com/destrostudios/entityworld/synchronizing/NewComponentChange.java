package com.destrostudios.entityworld.synchronizing;

import com.destrostudios.entityworld.bit.BitInputStream;
import com.destrostudios.entityworld.bit.BitOutputStream;

import java.io.IOException;

public class NewComponentChange extends EntityChange {

    public NewComponentChange() {

    }

    public NewComponentChange(int entity, Object component) {
        super(entity);
        this.component = component;
    }
    private Object component;

    @Override
    public void write(BitOutputStream outputStream) {
        super.write(outputStream);
        ComponentSerializer.writeClassAndObject(outputStream, component);
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException {
        super.read(inputStream);
        component = ComponentSerializer.readClassAndObject(inputStream);
    }

    public Object getComponent() {
        return component;
    }
}
