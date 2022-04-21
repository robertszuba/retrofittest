package com.onegini.mobile.retrofittest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {

  @GET("zen")
  Call<String> helloWorld();
}
