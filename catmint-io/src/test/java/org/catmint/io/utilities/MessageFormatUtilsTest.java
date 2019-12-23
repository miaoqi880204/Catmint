package org.catmint.io.utilities;

import org.catmint.common.utilities.MessageFormatUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see MessageFormatUtils
 * @author Shuo Xiang
 */
public class MessageFormatUtilsTest {

    @Test
    public void format() {
        Assert.assertEquals("11223", MessageFormatUtils.format("{}12{}3", 1, 2));
        Assert.assertEquals("123{", MessageFormatUtils.format("123{", 1, 2));
        Assert.assertEquals("1", MessageFormatUtils.format("{}", 1, 2));
        Assert.assertEquals("1{}23", MessageFormatUtils.format("1{}23"));
        Assert.assertEquals("1231,2", MessageFormatUtils.format("123{},{}", 1, 2));
    }
}