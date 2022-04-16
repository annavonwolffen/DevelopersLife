package com.annevonwolffen.tfs.developerslife.data;

import com.annevonwolffen.tfs.developerslife.domain.GifModel;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * @author Terekhova Anna
 */
public interface DevelopersLifeApi {

    @GET("random?json=true")
    Single<GifModel> getRandomGif();
}
