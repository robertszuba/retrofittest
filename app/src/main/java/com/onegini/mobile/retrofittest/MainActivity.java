package com.onegini.mobile.retrofittest;

import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

  private TextView textView;
  private GitHubService service;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textView = findViewById(R.id.textView);
    findViewById(R.id.button).setOnClickListener(view -> makeRequest());

    prepareClient();
  }

  private void prepareClient() {
    final Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build();

    service = retrofit.create(GitHubService.class);
  }

  public void makeRequest() {
    textView.setText("...");
    Call<String> repos = service.helloWorld();
    repos.enqueue(new Callback<String>() {
      @Override
      public void onResponse(Call<String> call, Response<String> response) {
        Log.d("TEST", response.body());
        textView.setText(response.body());
      }

      @Override
      public void onFailure(Call<String> call, Throwable t) {
        t.printStackTrace();
        textView.setText(t.getMessage());
      }
    });
  }
}
