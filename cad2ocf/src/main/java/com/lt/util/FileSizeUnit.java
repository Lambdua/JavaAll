package com.lt.util;

/**
 * 文件大小单位枚举类
 *
 * @author lihaitao
 * @since 2019-11-19
 */
public enum FileSizeUnit {

    BYTE(0),
    KB(1),
    MB(2),
    GB(3);

    private int code;

    FileSizeUnit(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    /**
     * 将文件大小的单位转为字节
     *
     * @param fileSize 文件大小
     * @author lihaitao
     */
    public long toByte(long fileSize) {
        switch (this) {
            case BYTE:
                return fileSize;
            case KB:
                return fileSize * 1024;
            case MB:
                return fileSize * 1024 * 1024;
            case GB:
                return fileSize * 1024 * 1024 * 1024;
            default:
                return 0L;
        }
    }

    /**
     * 将文件大小的单位转为KB
     *
     * @param fileSize 文件大小
     * @author lihaitao
     */
    public long toKB(long fileSize) {
        switch (this) {
            case BYTE:
                return fileSize / 1024;
            case KB:
                return fileSize;
            case MB:
                return fileSize * 1024;
            case GB:
                return fileSize * 1024 * 1024;
            default:
                return 0;
        }
    }

    /**
     * 将文件大小的单位转为MB
     *
     * @param fileSize 文件大小
     * @author lihaitao
     */
    public long toMB(long fileSize) {
        switch (this) {
            case BYTE:
                return fileSize / 1024 / 1024;
            case KB:
                return fileSize / 1024;
            case MB:
                return fileSize;
            case GB:
                return fileSize * 1024;
            default:
                return 0;
        }
    }

    /**
     * 将文件大小的单位转为GB
     *
     * @param fileSize 文件大小
     * @author lihaitao
     */
    public double toGB(double fileSize) {
        switch (this) {
            case BYTE:
                return fileSize / 1024 / 1024 / 1024;
            case KB:
                return fileSize / 1024 / 1024;
            case MB:
                return fileSize / 1024;
            case GB:
                return fileSize;
            default:
                return 0;
        }
    }

}
