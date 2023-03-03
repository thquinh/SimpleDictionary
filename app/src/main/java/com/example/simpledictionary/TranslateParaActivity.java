package com.example.simpledictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class TranslateParaActivity extends AppCompatActivity {
    Button translate;
    Button speak;
    EditText enText;
    EditText viText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_para);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://google-translate1.p.rapidapi.com/language/translate/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiTranslateInterface ati = retrofit.create(ApiTranslateInterface.class);

        TextToSpeech mTTS = new TextToSpeech(this, i -> {
            if (i != TextToSpeech.SUCCESS) {
                speak.setEnabled(false);
                Toast.makeText(this, "Speaker isn't supported", Toast.LENGTH_SHORT);
                return;
            }
        });

        translate = findViewById(R.id.trans);
        speak = findViewById(R.id.speaker);
        enText = findViewById(R.id.eng_text);
        viText = findViewById(R.id.vi_text);

        translate.setOnClickListener(view -> {
            enText.clearFocus();
            String temp = enText.getText().toString().trim();
            if (temp == "") return;
            else {
                Call<String> call = ati.getTranslation(temp, "vi", "en");
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (!response.isSuccessful()) {
                            viText.setText("Code: " + response.code());
                            return;
                        }
                       viText.setText(formatResponse(response.body()));
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        viText.setText(t.getMessage());
                    }
                });

            }
        });

        speak.setOnClickListener(view -> {
            enText.clearFocus();
            String temp = enText.getText().toString().trim();
            if (temp == "") return;
            else {
                int result = mTTS.setLanguage(Locale.ENGLISH);

                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) speak.setEnabled(false);
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT);
                mTTS.speak(temp, TextToSpeech.QUEUE_FLUSH, null, "speaker");
            }
        });
    }

    public interface ApiTranslateInterface {
        @Headers({
                "content-type: application/x-www-form-urlencoded",
	            "Accept-Encoding: application/gzip",
	            "X-RapidAPI-Key: 3815ba5800msh5d913b7d7bf0e5fp1fa2eajsndbf498773c42",
	            "X-RapidAPI-Host: google-translate1.p.rapidapi.com",
            })
        @FormUrlEncoded
        @POST("v2")
        Call<String> getTranslation(@Field("q") String text, @Field("target") String target, @Field("source") String source);
    }

    public String formatResponse(String text) {
        String temp = text.substring(44, text.length() - 5);
        return temp;
    }
}