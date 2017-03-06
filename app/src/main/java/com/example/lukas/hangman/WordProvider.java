package com.example.lukas.hangman;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;
import timber.log.Timber;

class WordProvider implements MvpWordProvider {
    @Override
    public void getWordFromApi(WordReceived callback) {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://randomword.setgetgo.com/get.php", new AsyncHttpResponseHandler() {
//        client.get("http://localhost:8080", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String word = new String(responseBody, StandardCharsets.UTF_8);
                Timber.i("Word received: " + word);
                callback.onWordReceived(word);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Timber.e(error, "Failed to received word.");
                callback.onWordReceived("500");
            }
        });
    }
}