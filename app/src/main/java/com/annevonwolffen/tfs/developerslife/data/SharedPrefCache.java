package com.annevonwolffen.tfs.developerslife.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.annevonwolffen.tfs.developerslife.domain.GifModel;

/**
 * Интерфейс кэш-менеджера для получения объекта гиф-изображения из кэша
 *
 * @author Terekhova Anna
 */
public interface SharedPrefCache {

    int getCurrentGifIndex();

    void putCurrentGifIndex(int curInd);

    @Nullable
    GifModel getCachedGif(@NonNull String key);

    void putGifModelToCache(@NonNull String key, GifModel gifModel);
}
