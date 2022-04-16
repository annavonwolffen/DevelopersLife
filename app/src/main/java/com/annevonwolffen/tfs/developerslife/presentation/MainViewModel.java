package com.annevonwolffen.tfs.developerslife.presentation;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.annevonwolffen.tfs.developerslife.data.SharedPrefCache;
import com.annevonwolffen.tfs.developerslife.domain.DevelopersLifeInteractor;
import com.annevonwolffen.tfs.developerslife.domain.GifModel;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Terekhova Anna
 */
public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    private final DevelopersLifeInteractor mInteractor;
    private final SharedPrefCache mCache;

    private MutableLiveData<Boolean> mIsDataLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsShowError = new MutableLiveData<>();

    private MutableLiveData<GifModel> mCurrentGif = new MutableLiveData<>();
    private MutableLiveData<String> mCurrentGifUrl = new MutableLiveData<>();
    private MutableLiveData<Integer> mCurGifIndexMLD = new MutableLiveData<>();

    private final CompositeDisposable mDisposable;

    private int mCurrentGifIndex;

    /**
     * Конструктор
     *
     * @param interactor интерактор для получения гифок и описания к ним
     * @param cache      кэш-менеджер для получения гифок и описания из кэша
     */
    public MainViewModel(@NonNull DevelopersLifeInteractor interactor,
                         @NonNull SharedPrefCache cache) {

        mInteractor = interactor;
        mCache = cache;

        mDisposable = new CompositeDisposable();
        mCurrentGifIndex = mCache.getCurrentGifIndex();
        mCurGifIndexMLD.postValue(mCurrentGifIndex);
    }

    public void onCreated() {
        mIsDataLoading.postValue(false);
        loadGif();
    }

    public LiveData<GifModel> getCurrentGif() {
        return mCurrentGif;
    }

    public LiveData<String> getCurrentGifUrl() {
        return mCurrentGifUrl;
    }

    public LiveData<Integer> getCurrentIndex() {
        return mCurGifIndexMLD;
    }

    public LiveData<Boolean> isDataLoading() {
        return mIsDataLoading;
    }

    public LiveData<Boolean> showError() {
        return mIsShowError;
    }

    public void onClick(@BtnAction String action) {
        mCurrentGifIndex = mCache.getCurrentGifIndex();
        switch (action) {
            case BtnAction.PREVIOUS:
                mCurrentGifIndex--;
                break;
            case BtnAction.NEXT:
                mCurrentGifIndex++;
                break;
            case BtnAction.REPEAT:
                break;
            default:
                Log.d(TAG, "Unknown action");
        }

        loadGif();
    }

    private void loadGif() {
        mDisposable.add(mInteractor.loadGif(String.valueOf(mCurrentGifIndex))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(unused -> {
                    mIsDataLoading.postValue(true);
                    mIsShowError.postValue(false);
                })
                .doAfterTerminate(() -> mIsDataLoading.postValue(false))
                .subscribe(this::onSuccess,
                        this::onError));
    }

    private void onSuccess(GifModel gif) {
        setMLDAndCacheValues(gif);
    }

    private void setMLDAndCacheValues(GifModel gifModel) {
        mCurrentGif.postValue(gifModel);
        mCurrentGifUrl.postValue(gifModel.getGifUrl());
        mCache.putCurrentGifIndex(mCurrentGifIndex);
        mCurGifIndexMLD.postValue(mCurrentGifIndex);
    }

    private void onError(@NonNull Throwable throwable) {
        Log.d(TAG, "Can not get gif info: " + throwable.getMessage());
        mIsShowError.postValue(true);
    }
}
