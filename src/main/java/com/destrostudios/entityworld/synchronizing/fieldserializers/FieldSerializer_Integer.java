package com.destrostudios.entityworld.synchronizing.fieldserializers;

import com.destrostudios.entityworld.bit.BitInputStream;
import com.destrostudios.entityworld.bit.BitOutputStream;
import com.destrostudios.entityworld.synchronizing.FieldSerializer;

import java.io.IOException;

public class FieldSerializer_Integer implements FieldSerializer<Integer> {

    public FieldSerializer_Integer(int bits) {
        this.bits = bits;
    }
    private int bits;

    @Override
    public void writeField(BitOutputStream bitOutputStream, Integer value) {
        bitOutputStream.writeBits(value, bits);
    }

    @Override
    public Integer readField(BitInputStream bitInputStream) throws IOException {
        return bitInputStream.readBits(bits);
    }
}
