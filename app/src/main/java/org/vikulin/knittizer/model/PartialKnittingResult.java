package org.vikulin.knittizer.model;

import java.io.Serializable;

public class PartialKnittingResult implements Serializable {

    private int phases;

    private int fractionalPhases;

    private int base;

    public int getPhases() {
        return phases;
    }

    public void setPhases(int phases) {
        this.phases = phases;
    }

    public int getFractionalPhases() {
        return fractionalPhases;
    }

    public void setFractionalPhases(int fractionalPhases) {
        this.fractionalPhases = fractionalPhases;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

}
