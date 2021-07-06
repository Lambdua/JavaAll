package com.lt.Z_common.demo.point;

/**
 * @author 梁先生
 * @Date 2020/10/12
 * 线程安全的point
 **/
public class SafePoint {
    private int x, y;

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized int getY() {
        return y;
    }

    public SafePoint(SafePoint safePoint) {
        this(safePoint.getX(),safePoint.getY());
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized void setY(int y) {
        this.y = y;
    }
}
