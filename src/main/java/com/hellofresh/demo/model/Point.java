package com.hellofresh.demo.model;

public class Point {

    private long time;
    private float x;
    private int y;

    public Point(long time, float x, int y) {
        super();
        this.time = time;
        this.x = x;
        this.y = y;
    }

    public Point(String time, String x, String y) {
        super();
        this.time = Long.parseLong(time);
        this.x = Float.parseFloat(x);
        this.y = Integer.parseInt(y);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return toDate(time) + "; " + x + "; " + y;
    }

    private java.util.Date toDate(long time) {
        return new java.util.Date((long) time * 1000);
    }

}
