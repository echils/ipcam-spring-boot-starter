package com.github.ipcam.entity.onvif.digest.fromhttpclient;

/**
 * ParserCursor
 *
 * @author echils
 * @see "https://mvnrepository.com/artifact/com.burgstaller/okhttp-digest"
 */
public class ParserCursor {

    private final int lowerBound;
    private final int upperBound;
    private int pos;

    public ParserCursor(int lowerBound, int upperBound) {
        if (lowerBound < 0) {
            throw new IndexOutOfBoundsException("Lower bound cannot be negative");
        } else if (lowerBound > upperBound) {
            throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
        } else {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.pos = lowerBound;
        }
    }

    public int getLowerBound() {
        return this.lowerBound;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    public int getPos() {
        return this.pos;
    }

    public void updatePos(int pos) {
        if (pos < this.lowerBound) {
            throw new IndexOutOfBoundsException();
        } else if (pos > this.upperBound) {
            throw new IndexOutOfBoundsException();
        } else {
            this.pos = pos;
        }
    }

    public boolean atEnd() {
        return this.pos >= this.upperBound;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(16);
        buffer.append('[');
        buffer.append(this.lowerBound);
        buffer.append('>');
        buffer.append(this.pos);
        buffer.append('>');
        buffer.append(this.upperBound);
        buffer.append(']');
        return buffer.toString();
    }
}
