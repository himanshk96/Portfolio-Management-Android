package com.example.portman;

import android.widget.SearchView;

import java.util.List;

public class Section {
private String sectionName;
private List<ItemRow> sectionItems;

    public String getSectionName() {
        return sectionName;
    }

    public List<ItemRow> getSectionItems() {
        return sectionItems;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setSectionItems(List<ItemRow> sectionItems) {
        this.sectionItems = sectionItems;
    }

    public Section(String sectionName, List<ItemRow> sectionItems){
    this.sectionItems=sectionItems;
    this.sectionName=sectionName;
}

    @Override
    public String toString() {
        return "Section{" +
                "sectionName='" + sectionName + '\'' +
                ", sectionItems=" + sectionItems +
                '}';
    }
}

