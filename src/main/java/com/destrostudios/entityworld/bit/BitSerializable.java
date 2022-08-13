package com.destrostudios.entityworld.bit;

import java.io.IOException;

public interface BitSerializable {

    void write(BitOutputStream outputStream);

    void read(BitInputStream inputStream) throws IOException;
}
