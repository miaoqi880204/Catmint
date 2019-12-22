package org.catmint.io.protocol.mysql;

/**
 * MySQL 字符集<br />
 * <a href="https://dev.mysql.com/doc/internals/en/character-set.html">MySQL 官方文档</a>
 *
 * @author Shuo Xiang
 */
public enum CharSets {
    BIG5(1, "big5"),
    LATIN1(8, "latin1"),
    LATIN2(9, "latin2"),
    CP1251(14, "cp1251"),
    GBK(28, "gbk"),
    GB2312(24, "gb2312"),
    UTF8(33, "utf8"),
    UTF8MB4(45, "utf8mb4");

    CharSets(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private Integer id;
    private String name;

    public static String getName(Integer id) {
        for (CharSets each : values()) {
            if (each.id.equals(id)) {
                return each.name;
            }
        }
        return null;
    }

    public static Integer getId(String name) {
        for (CharSets each : values()) {
            if (each.name.equalsIgnoreCase(name)) {
                return each.id;
            }
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
