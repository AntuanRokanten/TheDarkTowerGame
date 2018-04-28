package com.anton4j.darktower.util;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ant
 */
public class ResourceUtilsTest {

    @Test
    public void getResourceLines() {
        List<String> lines = ResourceUtils.getResourceLines("banner");

        assertEquals(5, lines.size());

        assertEquals("line 1", lines.get(0));
        assertEquals("line 2", lines.get(1));
        assertEquals("line 3", lines.get(2));
        assertEquals("line 4", lines.get(3));
        assertEquals("line 5", lines.get(4));
    }

}