package com.ds.myapp.engine;


import com.ds.myapp.bean.NewsInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/11/7.
 */

public interface Engine {

    @GET("refreshlayout/api/moredata{pageNumber}.json")
    Call<List<NewsInfo>> loadMoreData(@Path("pageNumber") int pageNumber);

}
