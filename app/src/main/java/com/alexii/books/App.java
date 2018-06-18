package com.alexii.books;

import android.app.Activity;
import android.app.Application;
import android.databinding.DataBindingUtil;


import com.alexii.books.common.databinding.DefaultDataBindingComponent;
import com.alexii.books.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    DefaultDataBindingComponent dataBindingComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);

        DataBindingUtil.setDefaultComponent(dataBindingComponent);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
