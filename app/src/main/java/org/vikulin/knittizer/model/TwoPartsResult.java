package org.vikulin.knittizer.model;

import android.content.Context;
import androidx.annotation.NonNull;

import org.vikulin.knittizer.R;

import java.io.Serializable;

public class TwoPartsResult implements Serializable {

    public TwoPartsResult() {}

    public TwoPartsResult(TwoPartsResult that) {
        this.firstNumber = that.getFirstNumber();
        this.secondNumber = that.getSecondNumber();
        this.firstRowPeriod = that.getFirstRowPeriod();
        this.secondRowPeriod = that.getSecondRowPeriod();
        this.startFromRow = that.getStartFromRow();
        this.isStartStitchLessEndStitch = that.isStartStitchLessEndStitch();
        this.firstStitchesNumber = that.getFirstStitchesNumber();
        this.secondStitchesNumber = that.getSecondStitchesNumber();
    }

    private int startFromRow;
    private int firstNumber;
    private int secondNumber;
    private int firstRowPeriod;
    private int secondRowPeriod;
    private boolean isStartStitchLessEndStitch;
    private int firstStitchesNumber = 1;
    private int secondStitchesNumber = 1;

    public int getStartFromRow() { return startFromRow; }
    public void setStartFromRow(int startFromRow) { this.startFromRow = startFromRow; }

    public int getFirstNumber() { return firstNumber; }
    public void setFirstNumber(int firstNumber) { this.firstNumber = firstNumber; }

    public int getSecondNumber() { return secondNumber; }
    public void setSecondNumber(int secondNumber) { this.secondNumber = secondNumber; }

    public int getFirstRowPeriod() { return firstRowPeriod; }
    public void setFirstRowPeriod(int firstRow) { this.firstRowPeriod = firstRow; }

    public int getSecondRowPeriod() { return secondRowPeriod; }
    public void setSecondRowPeriod(int secondRow) { this.secondRowPeriod = secondRow; }

    public boolean isStartStitchLessEndStitch() { return isStartStitchLessEndStitch; }
    public void setStartStitchLessEndStitch(boolean value) { this.isStartStitchLessEndStitch = value; }

    public int getFirstStitchesNumber() { return firstStitchesNumber; }
    public void setFirstStitchesNumber(int value) { this.firstStitchesNumber = value; }

    public int getSecondStitchesNumber() { return secondStitchesNumber; }
    public void setSecondStitchesNumber(int value) { this.secondStitchesNumber = value; }

    public boolean isPartEquals(){
        return firstNumber == secondNumber &&
                firstRowPeriod == secondRowPeriod &&
                firstStitchesNumber == secondStitchesNumber;
    }

    public void inverse(){
        int tempNumber = firstNumber;
        firstNumber = secondNumber;
        secondNumber = tempNumber;

        int tempRow = firstRowPeriod;
        firstRowPeriod = secondRowPeriod;
        secondRowPeriod = tempRow;
    }

    @NonNull
    public String toString(Context context) {
        if (isStartStitchLessEndStitch) {
            if (isPartEquals()) {
                if (firstStitchesNumber > 1) {
                    return context.getString(R.string.add_n_times_every_m_rows_k_stitches,
                            firstNumber, firstRowPeriod, firstStitchesNumber);
                } else {
                    return context.getString(R.string.add_n_times_every_m_rows,
                            firstNumber, firstRowPeriod);
                }
            } else {
                if (firstStitchesNumber > 1) {
                    return context.getString(R.string.add_complex_n_m_k_p_q_r,
                            firstNumber, firstRowPeriod, firstStitchesNumber,
                            secondNumber, secondRowPeriod, secondStitchesNumber);
                } else {
                    return context.getString(R.string.add_complex_n_m_p_q_r,
                            firstNumber, firstRowPeriod,
                            secondNumber, secondRowPeriod);
                }
            }
        } else {
            if (isPartEquals()) {
                if (firstStitchesNumber > 1) {
                    return context.getString(R.string.sub_n_times_every_m_rows_k_stitches,
                            firstNumber, firstRowPeriod, firstStitchesNumber);
                } else {
                    return context.getString(R.string.sub_n_times_every_m_rows,
                            firstNumber, firstRowPeriod);
                }
            } else {
                if (firstStitchesNumber > 1) {
                    return context.getString(R.string.sub_complex_n_m_k_p_q_r,
                            firstNumber, firstRowPeriod, firstStitchesNumber,
                            secondNumber, secondRowPeriod, secondStitchesNumber);
                } else {
                    return context.getString(R.string.sub_complex_n_m_p_q_r,
                            firstNumber, firstRowPeriod,
                            secondNumber, secondRowPeriod);
                }
            }
        }
    }
}
