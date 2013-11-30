package com.worldofzaar.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Kseon
 * Date: 26.11.13
 * Time: 12:40
 * To change this template use File | Settings | File Templates.
 */
public class ClassificationConstructor {
    public ClassificationConstructor(){

    }

    public List<ClassificationAdapter> CreateClassAdapterList(List<Object[]> input){
        List<ClassificationAdapter> classificationAdapter = new ArrayList<ClassificationAdapter>();
        for (Object[] tmp : input) {
            classificationAdapter.add(new ClassificationAdapter(tmp));
        }
        return classificationAdapter;
    }
}
