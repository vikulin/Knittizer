package org.vikulin.knittizer.model;

import java.util.ArrayList;
import java.util.List;

public class PartialKnittingResult {

    //partial knitting list
    List<String> partialKnittingStitchesList = new ArrayList<>();

    List<String> deckerKnittingStitchesList = new ArrayList<>();

    List<String> partialKnittingRowsList = new ArrayList<>();

    List<String> deckerRowsList = new ArrayList<>();

    public List<String> getPartialKnittingStitchesList() {
        return partialKnittingStitchesList;
    }

    public void addPartialKnittingStitch(String partialKnittingStitch) {
        this.partialKnittingStitchesList.add(partialKnittingStitch);
    }

    public List<String> getDeckerKnittingStitchesList() {
        return deckerKnittingStitchesList;
    }

    public void addDeckerKnittingStitch(String deckerKnittingStitch) {
        this.deckerKnittingStitchesList.add(deckerKnittingStitch);
    }

    public List<String> getPartialKnittingRowsList() {
        return partialKnittingRowsList;
    }

    public void addPartialKnittingRow(String partialKnittingRow) {
        this.partialKnittingRowsList.add(partialKnittingRow);
    }

    public List<String> getDeckerRowsList() {
        return deckerRowsList;
    }

    public void addDeckerRow(String deckerRow) {
        this.deckerRowsList.add(deckerRow);
    }

}
