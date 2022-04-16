package com.annevonwolffen.tfs.developerslife.presentation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.annevonwolffen.tfs.developerslife.data.DevelopersLifeRepositoryImpl;
import com.annevonwolffen.tfs.developerslife.data.SharedPrefCache;
import com.annevonwolffen.tfs.developerslife.data.SharedPrefCacheImpl;
import com.annevonwolffen.tfs.developerslife.domain.DevelopersLifeInteractor;
import com.annevonwolffen.tfs.developerslife.domain.DevelopersLifeInteractorImpl;
import com.annevonwolffen.tfs.developerslife.domain.DevelopersLifeRepository;


/**
 * @author Terekhova Anna
 */
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context mApplicationContext;

    public ViewModelProviderFactory(@NonNull Context applicationContext) {
        mApplicationContext = applicationContext.getApplicationContext();
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (MainViewModel.class.equals(modelClass)) {
            SharedPrefCache sharedPrefCache = new SharedPrefCacheImpl(mApplicationContext);
            DevelopersLifeRepository developersLifeRepository = new DevelopersLifeRepositoryImpl();
            DevelopersLifeInteractor developersLifeInteractor = new DevelopersLifeInteractorImpl(developersLifeRepository, sharedPrefCache);
            return (T) new MainViewModel(developersLifeInteractor, sharedPrefCache);
        } else {
            return super.create(modelClass);
        }
    }
}
