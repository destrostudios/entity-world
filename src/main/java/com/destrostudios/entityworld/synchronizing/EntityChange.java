package com.destrostudios.entityworld.synchronizing;

import com.destrostudios.entityworld.bit.BitInputStream;
import com.destrostudios.entityworld.bit.BitOutputStream;
import com.destrostudios.entityworld.bit.BitSerializable;

import java.io.IOException;

public class EntityChange implements BitSerializable {

    public EntityChange() {

    }

    protected EntityChange(int entity) {
        this.entity = entity;
    }
    private int entity;

    @Override
    public void write(BitOutputStream outputStream) {
        outputStream.writeInteger(entity);
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException {
        entity = inputStream.readInteger();
    }

    public int getEntity() {
        return entity;
    }
}
