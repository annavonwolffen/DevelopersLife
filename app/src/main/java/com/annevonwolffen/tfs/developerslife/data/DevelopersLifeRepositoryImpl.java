package com.annevonwolffen.tfs.developerslife.data;

import com.annevonwolffen.tfs.developerslife.domain.DevelopersLifeRepository;
import com.annevonwolffen.tfs.developerslife.domain.GifModel;

import io.reactivex.Single;

/**
 * Реализация репозитория {@link DevelopersLifeRepository}
 *
 * @author Terekhova Anna
 */
public class DevelopersLifeRepositoryImpl implements DevelopersLifeRepository {

    @Override
    public Single<GifModel> getRandomGif() {
        return getRandomGifRemote();
    }

    private Single<GifModel> getRandomGifRemote() {
        return ApiHelper.getInstance()
                .getApi()
                .getRandomGif();
    }
}
