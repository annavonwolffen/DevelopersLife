package com.annevonwolffen.tfs.developerslife.domain;

import io.reactivex.Single;

/**
 * Репозиторий для загрузки гиф-изображения и описания к нему с сервера
 *
 * @author Terekhova Anna
 */
public interface DevelopersLifeRepository {
    Single<GifModel> getRandomGif();
}
