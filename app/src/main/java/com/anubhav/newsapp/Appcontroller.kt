package com.anubhav.newsapp

import android.app.Application
import com.anubhav.newsapp.di.*

class Appcontroller : Application()
{

    lateinit var mApiComponent:ApiComponent

    override fun onCreate() {
        super.onCreate()
        app = this

        mApiComponent = DaggerApiComponent.builder()
            .appModule(AppModule(this))
            .apiHelper(ApiHelper())
            .dBModule(DBModule(this))
            .build()

    }

    companion object {
        lateinit var app: Appcontroller
            private set
    }

}