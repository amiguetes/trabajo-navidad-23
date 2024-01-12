package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Disabled("Hasta que encuentre como hacerlo funcionar en maven")
public class ReverseTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

 
    private void testReverse() {
        final String LINE_SEPARATOR = System.lineSeparator();
        String hello = "hello";
        String world = "world";
        String stop = "stop";
        String input = hello + LINE_SEPARATOR + world + LINE_SEPARATOR + stop + LINE_SEPARATOR;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Reverse.main(new String[]{});

        String[] lines = outContent.toString().split(LINE_SEPARATOR);
        assertEquals((new StringBuilder(hello)).reverse().toString(),lines[0]);
        assertEquals((new StringBuilder(world)).reverse().toString(), lines[1]);
    }
}

