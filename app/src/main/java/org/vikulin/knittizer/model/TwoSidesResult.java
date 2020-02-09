package org.vikulin.knittizer.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TwoSidesResult implements Serializable {

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getFirstRowPeriod() {
        return firstRowPeriod;
    }

    public void setFirstRowPeriod(int firstRow) {
        this.firstRowPeriod = firstRow;
    }

    public int getSecondRowPeriod() {
        return secondRowPeriod;
    }

    public void setSecondRowPeriod(int secondRow) {
        this.secondRowPeriod = secondRow;
    }

    public boolean isStartStitchLessEndStitch() {
        return isStartStitchLessEndStitch;
    }

    public void setStartStitchLessEndStitch(boolean startStitchLessEndStitch) {
        isStartStitchLessEndStitch = startStitchLessEndStitch;
    }

    private int firstNumber;

    private int secondNumber;

    private int firstRowPeriod;

    private int secondRowPeriod;

    private boolean isStartStitchLessEndStitch;

    @NonNull
    @Override
    public String toString() {
        if(isStartStitchLessEndStitch) {
            if(firstNumber==secondNumber && firstRowPeriod==secondRowPeriod) {
                return "Прибавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду";
            } else {
                return "Прибавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду и " + secondNumber + " раз в каждом " + secondRowPeriod + " ряду";
            }
        } else {
            if(firstNumber==secondNumber && firstRowPeriod==secondRowPeriod) {
                return "Убавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду";
            } else {
                return "Убавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду и " + secondNumber + " раз в каждом " + secondRowPeriod + " ряду";
            }
        }
    }
}
