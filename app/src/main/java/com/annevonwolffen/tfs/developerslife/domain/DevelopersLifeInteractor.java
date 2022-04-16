package com.annevonwolffen.tfs.developerslife.domain;

import androidx.annotation.NonNull;

import io.reactivex.Single;

/**
 * Интерактор для получения объекта гиф-изображения
 *
 * @author Terekhova Anna
 */
public interface DevelopersLifeInteractor {
    Single<GifModel> loadGif(@NonNull String key);
}
