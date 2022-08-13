package com.destrostudios.entityworld.synchronizing.fieldserializers;

import com.destrostudios.entityworld.bit.BitInputStream;
import com.destrostudios.entityworld.bit.BitOutputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestFloatingPointNumbers {

    @Test
    public void testFloatingPointNumbers() throws IOException {
        double number = 567.82472;
        int exponentBits = 8;
        double[] expected = new double[] {
            512.0, // 1
            512.0, // 2
            512.0, // 3
            544.0, // 4
            560.0, // 5
            560.0, // 6
            564.0, // 7
            566.0, // 8
            567.0, // 9
            567.5, // 10
            567.75, // 11
            567.75, // 12
            567.8125, // 13
            567.8125, // 14
            567.8125, // 15
            567.8203125, // 16
            567.82421875, // 17
            567.82421875, // 18
            567.82421875, // 19
            567.82470703125, // 20
            567.82470703125, // 21
            567.82470703125, // 22
            567.82470703125, // 23
        };
        for (int mantisseBits = 1; mantisseBits <= 23; mantisseBits++) {
            FieldSerializer_DoubleAsFloat serializer = new FieldSerializer_DoubleAsFloat(mantisseBits, exponentBits);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BitOutputStream bitOutputStream = new BitOutputStream(byteArrayOutputStream);
            serializer.writeField(bitOutputStream, number);
            bitOutputStream.close();
            byte[] data = byteArrayOutputStream.toByteArray();
            BitInputStream bitInputStream = new BitInputStream(new ByteArrayInputStream(data));
            double value = serializer.readField(bitInputStream);
            assertEquals(value, expected[mantisseBits - 1], 0);
        }
    }
}
