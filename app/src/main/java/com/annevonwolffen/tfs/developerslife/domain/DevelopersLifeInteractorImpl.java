package com.annevonwolffen.tfs.developerslife.domain;

import androidx.annotation.NonNull;

import com.annevonwolffen.tfs.developerslife.data.SharedPrefCache;

import io.reactivex.Single;

/**
 * Реализация интерактора {@link DevelopersLifeInteractor} для получения гифок из кэша и с сервера
 *
 * @author Terekhova Anna
 */
public class DevelopersLifeInteractorImpl implements DevelopersLifeInteractor {

    private final DevelopersLifeRepository mRepository;
    private final SharedPrefCache mSharedPrefCache;

    /**
     *
     * @param repository  репозиторий для загрузки модели гиф-изображения с сервера
     * @param cache       кэш для получения модели гиф-изображения без вызова АПИ
     */
    public DevelopersLifeInteractorImpl(@NonNull DevelopersLifeRepository repository,
                                        @NonNull SharedPrefCache cache) {
        mRepository = repository;
        mSharedPrefCache = cache;
    }

    @Override
    public Single<GifModel> loadGif(@NonNull String key) {
        return getGifModelFromCache(key)
                .onErrorResumeNext(getRandomGifModelRemote(key));
    }

    private Single<GifModel> getRandomGifModelRemote(@NonNull String key) {
        return mRepository.getRandomGif()
                .doOnSuccess(gifModel -> saveGifModelToCache(key, gifModel));
    }

    private Single<GifModel> getGifModelFromCache(@NonNull String key) {
        return Single.fromCallable(() -> mSharedPrefCache
                .getCachedGif(key));
    }

    private void saveGifModelToCache(@NonNull String key, GifModel gifModel) {
        mSharedPrefCache.putGifModelToCache(key, gifModel);
    }
}
