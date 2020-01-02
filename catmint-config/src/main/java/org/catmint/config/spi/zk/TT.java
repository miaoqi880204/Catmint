package org.catmint.config.spi.zk;

import org.catmint.common.utilities.PropertiesUtils;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author QIQI
 * @date
 */
public class TT {
    public static void main(String[] args) {
        System.out.println( PropertiesUtils.readValue( "config.properties","org.catmint.zk.address" ) );
    }
}
