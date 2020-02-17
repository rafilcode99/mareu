package com.rafil.maru.maru.service;

import com.rafil.maru.maru.Controler.ReunionsListRecyclerViewAdapter;
import com.rafil.maru.maru.Model.Reunion;

import java.util.List;

public class FakeReunionApiService implements ReunionApiService {

    private List<Reunion> mReunionList = FakeReunionsGenerator.generateFakeReunions();

    @Override
    public List<Reunion> getReunions() {
        return mReunionList;
    }

    @Override
    public void createReunion(Reunion reunion) {
        mReunionList.add(reunion);

    }

    @Override
    public void deleteReunion(Reunion mReunion) {
        mReunionList.remove(mReunion);
    }


}
