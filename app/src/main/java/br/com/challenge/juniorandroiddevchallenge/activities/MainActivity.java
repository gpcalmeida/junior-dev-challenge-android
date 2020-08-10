package br.com.radixeng.juniorandroiddevchallenge.activities;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.radixeng.juniorandroiddevchallenge.R;
import br.com.radixeng.juniorandroiddevchallenge.network.RetrofitClientInstance;
import br.com.radixeng.juniorandroiddevchallenge.network.models.Films;
import br.com.radixeng.juniorandroiddevchallenge.network.services.GetDataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView episodeTextView;
    private TextView directorTextView;
    private TextView fullNameTextView;
    private ImageView episode1;
    private ImageView episode2;
    private ImageView episode3;
    private ImageView episode4;
    private ImageView episode5;
    private ImageView episode6;
    private Dialog dialogTransparent;
    private Button refreshButton;

    private String currentFilmId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTextView = findViewById(R.id.title_text_view);
        episodeTextView = findViewById(R.id.episode_text_view);
        directorTextView = findViewById(R.id.director_text_view);
        fullNameTextView = findViewById(R.id.full_name_text_view);
        episode1 = findViewById(R.id.episode_one_image_view);
        episode2 = findViewById(R.id.episode_two_image_view);
        episode3 = findViewById(R.id.episode_three_image_view);
        episode4 = findViewById(R.id.episode_four_image_view);
        episode5 = findViewById(R.id.episode_five_image_view);
        episode6 = findViewById(R.id.episode_six_image_view);
        refreshButton = findViewById(R.id.refresh_button);

        dialogTransparent = new Dialog(this, android.R.style.Theme_Black );
        View view = LayoutInflater.from(this ).inflate(R.layout.progress, null );
        dialogTransparent.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialogTransparent.getWindow().setBackgroundDrawableResource( R.color.transparent );
        dialogTransparent.setContentView( view );

        fullNameTextView.setText("Clique em um poster!");

        refreshButton.setOnClickListener((View v) -> {
            clearFields();
            requestData(currentFilmId);
        });

        setupListeners();
    }

    public void setupListeners() {
        episode1.setOnClickListener(view ->{
            clearFields();
            requestData("fMoDABNwV9");
            currentFilmId = "fMoDABNwV9";
        });

        episode2.setOnClickListener(view ->{
            clearFields();
            requestData("NtEIWnlRYH");
            currentFilmId = "NtEIWnlRYH";
        });

        episode3.setOnClickListener(view ->{
            clearFields();
            requestData("RbyX4ouadm");
            currentFilmId = "RbyX4ouadm";
        });

        episode4.setOnClickListener(view ->{
            clearFields();
            requestData("GteveE4ytb");
            currentFilmId = "GteveE4ytb";
        });

        episode5.setOnClickListener(view ->{
            clearFields();
            requestData("mRAWzGNBfG");
            currentFilmId = "mRAWzGNBfG";
        });

        episode6.setOnClickListener(view ->{
            clearFields();
            requestData("SFHc9Y4gXA");
            currentFilmId = "SFHc9Y4gXA";
        });
    }

    public void setTexts(String title, String episode, String director, String fullName) {
        titleTextView.setText(title);
        episodeTextView.setText(episode);
        directorTextView.setText(director);
        fullNameTextView.setText(fullName);
    }

    public void clearFields() {
        titleTextView.setText("");
        episodeTextView.setText("");
        directorTextView.setText("");
        fullNameTextView.setText("");
    }

    public void requestData(String filmId) {
        showDialog();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Films> call = service.getMovie(filmId);

        call.enqueue(new Callback<Films>() {
            @Override
            public void onResponse(@NonNull Call<Films> call, @NonNull Response<Films> response) {
                String title = response.body().getTitle();
                String episode = String.valueOf(response.body().getEpisodeId());
                String director = response.body().getDirector();
                String fullName = response.body().toString();

                setTexts(title, episode, director, fullName);

                Log.d("RESPONSE", response.body().toString());
                hideDialog();
            }

            @Override
            public void onFailure(@NonNull Call<Films> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });
    }

    private void showDialog() {
        dialogTransparent.show();
    }

    private void hideDialog() {
        if( dialogTransparent != null &&  dialogTransparent.isShowing() ){
            dialogTransparent.dismiss();
        }
    }
}