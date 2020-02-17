package com.rafil.maru.maru.service;

import com.rafil.maru.maru.Model.Reunion;

import java.util.List;

public interface ReunionApiService {

    List<Reunion> getReunions();

    void createReunion(Reunion reunion);

    void deleteReunion (Reunion mReunion);





}
