package com.artem.hometask.splashscreen;

import android.os.AsyncTask;
import android.support.annotation.NonNull;


import com.artem.hometask.base.BasePresenter;
import com.artem.hometask.model.DataManager;
import com.artem.hometask.model.Technology;
import com.artem.hometask.utils.JsonParser;
import com.artem.hometask.utils.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * Created by artem on 22.04.16.
 */
public class SplashScreenPresenter extends BasePresenter<List<Technology>, SplashScreenView> {
    private boolean isLoadingData = false;
    private final String DOWNLOADING_URL = "http://mobevo.ext.terrhq.ru/shr/j/ru/technology.js";

    @Override
    protected void updateView() {}

    @Override
    public void bindView(@NonNull SplashScreenView view) {
        super.bindView(view);
        if (model == null && !isLoadingData) {
            loadData();
        }
    }

    private void loadData() {
        isLoadingData = true;
        new LoadDataTask().execute(DOWNLOADING_URL);
    }

    private class LoadDataTask extends AsyncTask<String, Void, List<Technology>> {
        @Override
        protected List<Technology> doInBackground(String... params) {
            if (params != null && params.length > 0) {
                try {
                    URL url = new URL(params[0]);
                    HttpURLConnection connection = null;
                    try {
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        InputStream is = new BufferedInputStream(connection.getInputStream());
                        String json = StringUtils.readInputStream(is);
                        List<Technology> techs = JsonParser.parseListOfTechnologiesFromJson(json);
                        is.close();
                        return techs;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        return null;
                    }
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Technology> techs) {
            if(!isCancelled()) {
                if (techs != null)
                    setModel(techs);
                    DataManager.getInstance().setTechnologies(techs);
                if(view() != null)
                    view().goToListActivity();
            }
            isLoadingData = false;
        }
    }

}
