package com.destrostudios.entityworld.synchronizing.fieldserializers;

import com.destrostudios.entityworld.bit.BitInputStream;
import com.destrostudios.entityworld.bit.BitOutputStream;
import com.destrostudios.entityworld.synchronizing.FieldSerializer;

import java.io.IOException;

public class FieldSerializer_DoubleAsFloat implements FieldSerializer<Double> {

    public FieldSerializer_DoubleAsFloat(int mantissaBits, int exponentBits) {
        floatSerializer = new FieldSerializer_Float(mantissaBits, exponentBits);
    }
    private FieldSerializer_Float floatSerializer;

    @Override
    public void writeField(BitOutputStream bitOutputStream, Double value) {
        floatSerializer.writeField(bitOutputStream, value.floatValue());
    }

    @Override
    public Double readField(BitInputStream bitInputStream) throws IOException {
        return floatSerializer.readField(bitInputStream).doubleValue();
    }
}
