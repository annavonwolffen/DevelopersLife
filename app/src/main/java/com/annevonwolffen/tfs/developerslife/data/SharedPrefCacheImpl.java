package com.annevonwolffen.tfs.developerslife.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.annevonwolffen.tfs.developerslife.domain.GifModel;
import com.google.gson.GsonBuilder;

/**
 * Реализация кэша для получения гифок на {@link SharedPreferences}
 *
 * @author Terekhova Anna
 */
public class SharedPrefCacheImpl implements SharedPrefCache {

    private static final String TAG = "SharedPrefCacheImpl";

    private static final String DEV_LIFE_PREF = "DEV_LIFE_PREF";
    private static final String CUR_INDEX_KEY = "CUR_INDEX_KEY";

    private final SharedPreferences mPreferences;

    public SharedPrefCacheImpl(Context context) {
        mPreferences = context.getSharedPreferences(DEV_LIFE_PREF, Context.MODE_PRIVATE);
    }

    @Override
    public int getCurrentGifIndex() {
        return mPreferences.getInt(CUR_INDEX_KEY, 0);
    }

    @Override
    public void putCurrentGifIndex(int curInd) {
        mPreferences.edit().putInt(CUR_INDEX_KEY, curInd).apply();
    }

    @Nullable
    @Override
    public GifModel getCachedGif(@NonNull String key) {
        final String jsonGifModel = mPreferences.getString(key, null);
        if (jsonGifModel == null) {
            return null;
        }
        return new GsonBuilder().create().fromJson(jsonGifModel, GifModel.class);
    }

    @Override
    public void putGifModelToCache(@NonNull String key, GifModel gifModel) {
        final String jsonModel = new GsonBuilder().create().toJson(gifModel);
        mPreferences.edit().putString(key, jsonModel).apply();
    }
}
