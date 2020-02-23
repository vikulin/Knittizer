package org.vikulin.knittizer.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TwoPartsResult implements Serializable {

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

    public boolean isPartEquals(){
        return firstNumber==secondNumber && firstRowPeriod==secondRowPeriod;
    }

    public void setStartStitchLessEndStitch(boolean startStitchLessEndStitch) {
        isStartStitchLessEndStitch = startStitchLessEndStitch;
    }

    public int getFirstStitchesNumber() {
        return firstStitchesNumber;
    }

    public void setFirstStitchesNumber(int firstStitchesNumber) {
        this.firstStitchesNumber = firstStitchesNumber;
    }

    public int getSecondStitchesNumber() {
        return secondStitchesNumber;
    }

    public void setSecondStitchesNumber(int secondStitchesNumber) {
        this.secondStitchesNumber = secondStitchesNumber;
    }

    private int firstNumber;

    private int secondNumber;

    private int firstRowPeriod;

    private int secondRowPeriod;

    private boolean isStartStitchLessEndStitch;

    private int firstStitchesNumber = 1;

    private int secondStitchesNumber = 1;

    @NonNull
    @Override
    public String toString() {
        if(isStartStitchLessEndStitch) {
            if(firstNumber==secondNumber && firstRowPeriod==secondRowPeriod) {
                if(firstStitchesNumber>1) {
                    return "Прибавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду по "+firstStitchesNumber+" петли";
                } else {
                    return "Прибавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду";
                }
            } else {
                if(firstStitchesNumber>1) {
                    return "Прибавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду по "+firstStitchesNumber+" петли и " + secondNumber + " раз в каждом " + secondRowPeriod + " ряду по "+secondStitchesNumber+" петле";
                } else {
                    return "Прибавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду и " + secondNumber + " раз в каждом " + secondRowPeriod + " ряду";
                }
            }
        } else {
            if(firstNumber==secondNumber && firstRowPeriod==secondRowPeriod) {
                if(firstStitchesNumber>1) {
                    return "Убавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду по "+firstStitchesNumber+" петли";
                } else {
                    return "Убавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду";
                }
            } else {
                if(firstStitchesNumber>1) {
                    return "Убавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду по "+firstStitchesNumber+" петли и " + secondNumber + " раз в каждом " + secondRowPeriod + " ряду по "+secondStitchesNumber+" петле";
                } else {
                    return "Убавить " + firstNumber + " раз в каждом " + firstRowPeriod + " ряду и " + secondNumber + " раз в каждом " + secondRowPeriod + " ряду";
                }
            }
        }
    }
}
