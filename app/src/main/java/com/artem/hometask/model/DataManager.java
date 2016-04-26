package com.artem.hometask.model;

import java.util.List;

/**
 * Created by artem on 22.04.16.
 */
public class DataManager {
    List<Technology> technologies;
    private static DataManager mInstance = null;


    private DataManager(){
    }

    public static DataManager getInstance(){
        if(mInstance == null)
        {
            mInstance = new DataManager();
        }
        return mInstance;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
}
