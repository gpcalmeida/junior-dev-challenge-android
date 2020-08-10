package br.com.radixeng.juniorandroiddevchallenge.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import br.com.radixeng.juniorandroiddevchallenge.network.RetrofitClientInstance;
import br.com.radixeng.juniorandroiddevchallenge.network.models.Films;
import br.com.radixeng.juniorandroiddevchallenge.network.services.GetDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private Context mContext;
    private Films film;

    public Films requestData(Context context) {
        mContext = context;

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Films> call = service.getMovie("");

        call.enqueue(new Callback<Films>() {
            @Override
            public void onResponse(@NonNull Call<Films> call, @NonNull Response<Films> response) {
                film = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<Films> call, Throwable t) {
                Toast.makeText(mContext, "Erro", Toast.LENGTH_SHORT).show();
            }
        });

        return film;
    }
}
