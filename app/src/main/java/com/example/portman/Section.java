package com.example.portman;

import android.widget.SearchView;

import java.util.List;

public class Section {
private String sectionName;
private List<String> sectionItems;

    public String getSectionName() {
        return sectionName;
    }

    public List<String> getSectionItems() {
        return sectionItems;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setSectionItems(List<String> sectionItems) {
        this.sectionItems = sectionItems;
    }

    public Section(String sectionName, List<String> sectionItems){
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

