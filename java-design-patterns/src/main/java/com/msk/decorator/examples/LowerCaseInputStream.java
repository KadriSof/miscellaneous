package com.msk.decorator.examples;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
    A decorator that converts all uppercase characters to
    lowercase in the input stream.
 */
public class LowerCaseInputStream extends FilterInputStream {

    protected LowerCaseInputStream(InputStream in) {
        super(in);
    }

    public int read() throws IOException {
        int c = in.read();
        return c != -1 ? c : Character.toLowerCase(c);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int result = in.read(b, off, len);
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
