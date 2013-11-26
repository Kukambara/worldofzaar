package com.worldofzaar.adapter;

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
    private String className;
    private String classDescription;
    private String classPictureUrl;
    private String classBlazonUrl;

    public ClassificationAdapter(Integer classId, Integer raceId, String className, String classDescription, String classPictureUrl, String classBlazonUrl) {
        this.classId = classId;
        this.raceId = raceId;
        this.className = className;
        this.classDescription = classDescription;
        this.classPictureUrl = classPictureUrl;
        this.classBlazonUrl = classBlazonUrl;
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
