package com.rafil.maru.maru.DI;

import com.rafil.maru.maru.service.FakeReunionApiService;
import com.rafil.maru.maru.service.ReunionApiService;

public class DI {
    public static ReunionApiService myApiService = new FakeReunionApiService();

    public static ReunionApiService getReunionApiService(){return myApiService;}

    public static ReunionApiService getNewInstanceApiService(){
        return new FakeReunionApiService();
    }
}
