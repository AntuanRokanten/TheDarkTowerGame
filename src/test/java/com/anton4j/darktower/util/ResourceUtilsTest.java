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

        assertEquals(9, lines.size());

        assertEquals(" _______    ______   _______   __    __        ________   ______   __       __  ________  _______ .", lines.get(0));
        assertEquals("|       \\  /      \\ |       \\ |  \\  /  \\      |        \\ /      \\ |  \\  _  |  \\|        \\|       \\.", lines.get(1));
        assertEquals("| #######\\|  ######\\| #######\\| ## /  ##       \\########|  ######\\| ## / \\ | ##| ########| #######\\", lines.get(2));
        assertEquals("| ##  | ##| ##__| ##| ##__| ##| ##/  ##          | ##   | ##  | ##| ##/  #\\| ##| ##__    | ##__| ##", lines.get(3));
        assertEquals("| ##  | ##| ##    ##| ##    ##| ##  ##           | ##   | ##  | ##| ##  ###\\ ##| ##  \\   | ##    ##", lines.get(4));
        assertEquals("| ##  | ##| ########| #######\\| #####\\           | ##   | ##  | ##| ## ##\\##\\##| #####   | #######\\", lines.get(5));
        assertEquals("| ##__/ ##| ##  | ##| ##  | ##| ## \\##\\          | ##   | ##__/ ##| ####  \\####| ##_____ | ##  | ##", lines.get(6));
        assertEquals("| ##    ##| ##  | ##| ##  | ##| ##  \\##\\         | ##    \\##    ##| ###    \\###| ##     \\| ##  | ##", lines.get(7));
        assertEquals(" \\#######  \\##   \\## \\##   \\## \\##   \\##          \\##     \\######  \\##      \\## \\######## \\##   \\##", lines.get(8));
    }

}