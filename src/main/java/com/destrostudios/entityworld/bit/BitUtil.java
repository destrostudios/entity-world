package com.destrostudios.entityworld.bit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BitUtil {

    public static int getNeededBitsCount(int value) {
        return (32 - Integer.numberOfLeadingZeros(value));
    }

    public static byte[] writeToBytes(BitSerializable bitSerializable){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BitOutputStream bitOutputStream = new BitOutputStream(byteArrayOutputStream);
        bitSerializable.write(bitOutputStream);
        bitOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static void readFromBytes(BitSerializable bitSerializable, byte[] bytes) throws IOException {
        BitInputStream bitInputStream = new BitInputStream(new ByteArrayInputStream(bytes));
        bitSerializable.read(bitInputStream);
    }
}
