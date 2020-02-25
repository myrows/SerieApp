package com.dmtroncoso.moviedb.data.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dmtroncoso.moviedb.common.SharedPreferencesManager;
import com.dmtroncoso.moviedb.model.detail.Detail;

public class DetailViewModel extends AndroidViewModel {
    public DetailRepository detailRepository;
    int idTv = SharedPreferencesManager.getSomeIntValue("idTv");
    public LiveData<Detail> detail;


    public DetailViewModel(@NonNull Application application) {
        super(application);

        detailRepository = new DetailRepository();
        detail = detailRepository.getDetail(idTv);
    }

}
