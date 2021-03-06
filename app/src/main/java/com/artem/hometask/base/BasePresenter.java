package com.artem.hometask.base;

//get from https://github.com/remind101/android-arch-sample/

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Created by artem on 22.04.16.
 */
public abstract class BasePresenter<M, V> {
    protected M model;
    private WeakReference<V> view;

    public void setModel(M model) {
        resetState();
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    protected void resetState() {
    }

    public void bindView(@NonNull V view) {
        this.view = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    public void unbindView() {
        this.view = null;
    }

    protected V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    protected abstract void updateView();

    protected boolean setupDone() {
        return view() != null && model != null;
    }
}
