package com.msk.decorator.examples;

import java.io.*;

public class InputTest {

    public static void main(String[] args) throws IOException {
        int c;

        try {
            InputStream in = new LowerCaseInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    "src/main/java/com/msk/decorator/examples/text.txt"
                            )
                    )
            );

            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
