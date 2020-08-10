package br.com.radixeng.juniorandroiddevchallenge.network.services;

import br.com.radixeng.juniorandroiddevchallenge.network.models.Films;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("/classes/SWAPI_Film/{id}")
    Call<Films> getMovie(@Path("id") String id);

}
