package com.destrostudios.entityworld.synchronizing;

import com.destrostudios.entityworld.bit.BitInputStream;
import com.destrostudios.entityworld.bit.BitOutputStream;

import java.io.IOException;

public interface FieldSerializer<T> {

    void writeField(BitOutputStream bitOutputStream, T value);

    T readField(BitInputStream bitInputStream) throws IOException;
}
