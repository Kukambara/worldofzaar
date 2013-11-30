package com.worldofzaar.adapter;

import com.worldofzaar.entity.Blazon;
import com.worldofzaar.entity.RuClassText;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.11.13
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationAdapter {
    private Integer classId;
    private Integer raceId;
    private Integer blazonId;
    private String className;
    private String classDescription;
    private String classPictureUrl;
    private String classBlazonUrl;
    private String classClothUrl;

    public ClassificationAdapter(Object[] input) {
        this.classId = ((RuClassText)input[1]).getClassification().getClassificationId();
        this.raceId = ((RuClassText)input[1]).getClassification().getRace().getRaceId();
        this.blazonId = ((Blazon)input[0]).getBlazonId();
        this.className = ((RuClassText)input[1]).getClassName();
        this.classDescription = ((RuClassText)input[1]).getClassDescription();
        this.classPictureUrl = ((RuClassText)input[1]).getClassPictureNamePath();
        this.classBlazonUrl = ((Blazon)input[0]).getBlazonPath();
        this.classClothUrl = ((Blazon)input[0]).getCloth().getClothPath();
    }


    public Integer getBlazonId() {
        return blazonId;
    }

    public void setBlazonId(Integer blazonId) {
        this.blazonId = blazonId;
    }

    public String getClassClothUrl() {
        return classClothUrl;
    }

    public void setClassClothUrl(String classClothUrl) {
        this.classClothUrl = classClothUrl;
    }

    public Integer getRaceId() {
        return raceId;
    }

    public void setRaceId(Integer raceId) {
        this.raceId = raceId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public String getClassPictureUrl() {
        return classPictureUrl;
    }

    public void setClassPictureUrl(String classPictureUrl) {
        this.classPictureUrl = classPictureUrl;
    }

    public String getClassBlazonUrl() {
        return classBlazonUrl;
    }

    public void setClassBlazonUrl(String classBlazonUrl) {
        this.classBlazonUrl = classBlazonUrl;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
