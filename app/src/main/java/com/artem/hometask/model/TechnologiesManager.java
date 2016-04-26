package com.artem.hometask.model;

import java.util.List;

/**
 * Created by artem on 22.04.16.
 */
public class TechnologiesManager {
    List<Technology> technologies;
    private static TechnologiesManager mInstance = null;


    private TechnologiesManager(){
    }

    public static TechnologiesManager getInstance(){
        if(mInstance == null)
        {
            mInstance = new TechnologiesManager();
        }
        return mInstance;
    }

    public int itemCount() {
        if(technologies == null)
            return 0;
        else
            return technologies.size();
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public Technology getByIndex(int index) {
        if(technologies != null && technologies.size() > index) {
            return technologies.get(index);
        } else {
            return null;
        }
    }
}
