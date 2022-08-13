package com.destrostudios.entityworld.synchronizing;

import com.destrostudios.entityworld.bit.BitInputStream;
import com.destrostudios.entityworld.bit.BitOutputStream;
import com.destrostudios.entityworld.bit.BitSerializable;

import java.io.IOException;

public class EntityChanges implements BitSerializable {

    public EntityChanges() {

    }

    public EntityChanges(EntityChange[] changes) {
        this.changes = changes;
    }
    private EntityChange[] changes;

    @Override
    public void write(BitOutputStream outputStream) {
        outputStream.writeBits(changes.length, 10);
        for (EntityChange change : changes) {
            if (change instanceof NewComponentChange) {
                outputStream.writeBoolean(true);
                outputStream.writeBoolean(true);
            } else if (change instanceof RemovedComponentChange) {
                outputStream.writeBoolean(true);
                outputStream.writeBoolean(false);
            } else if (change instanceof RemovedEntityChange) {
                outputStream.writeBoolean(false);
            }
            change.write(outputStream);
        }
    }

    @Override
    public void read(BitInputStream inputStream) throws IOException {
        int count = inputStream.readBits(10);
        changes = new EntityChange[count];
        for (int i = 0; i < count; i++) {
            EntityChange change;
            boolean componentOrEntity = inputStream.readBoolean();
            if (componentOrEntity) {
                boolean newOrRemoved = inputStream.readBoolean();
                change = (newOrRemoved ? new NewComponentChange() : new RemovedComponentChange());
            } else {
                change = new RemovedEntityChange();
            }
            change.read(inputStream);
            changes[i] = change;
        }
    }

    public EntityChange[] getChanges() {
        return changes;
    }
}
