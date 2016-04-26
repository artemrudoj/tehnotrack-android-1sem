package com.artem.hometask.utils;

import com.artem.hometask.model.Technology;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by artem on 19.04.16.
 */
public class JsonParser {



    private static Technology parseOneTechnologyFromJson(JSONObject jsonTechnologyObject) {
        try {
            String picture = jsonTechnologyObject.getString(Technology.PICTURE);
            String title = jsonTechnologyObject.getString(Technology.TITLE);
            String info = jsonTechnologyObject.getString(Technology.INFO);
            return new Technology(picture, title, info);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Technology> parseListOfTechnologiesFromJson(String json) {
        try {
            JSONObject  rootJsonObject = new JSONObject(json);
            JSONObject technologiesJsonObject = rootJsonObject.getJSONObject(Technology.TECHNOLOGY);
            Iterator<String> keysItr = technologiesJsonObject.keys();
            List<Technology> technologies = new ArrayList<>();
            while (keysItr.hasNext()) {
                String key = keysItr.next();
                JSONObject currentTechnology = (JSONObject) technologiesJsonObject.get(key);
                Technology technology = parseOneTechnologyFromJson(currentTechnology);
                if (technology != null) technologies.add(technology);
            }
            return technologies;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
