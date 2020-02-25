package com.dmtroncoso.moviedb.retrofit;

import com.dmtroncoso.moviedb.data.PopularResponse;
import com.dmtroncoso.moviedb.model.detail.Detail;
import com.dmtroncoso.moviedb.model.popular.Serie;
import com.dmtroncoso.moviedb.model.recommendation.Recommendation;
import com.dmtroncoso.moviedb.model.season.Season;
import com.dmtroncoso.moviedb.model.topRated.PageRated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

    @GET("/3/tv/popular")
    Call<Serie> getSeries();

    @GET("/3/tv/popular")
    Call<PopularResponse> getSeriesPopularResponse();

    @GET("/3/tv/{tv_id}")
    Call<Detail> getDetailById(@Path("tv_id") int tv_id);

    @GET("/3/tv/{tv_id}/recommendations")
    Call<Recommendation> getRecommendationsById(@Path("tv_id") int tv_id);

    @GET("/3/tv/top_rated")
    Call<PageRated> getAllTopRated();

    @GET("/3/tv/{tv_id}/season/{season_number}")
    Call<Season> getSeasonBySerieId(@Path("tv_id") int tv_id, @Path("season_number") int season_number);
}
