package com.example.lukas.hangman;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import cz.msebera.android.httpclient.Header;

class WordProvider implements MvpWordProvider {
    private static final Logger LOGGER = Logger.getLogger(Model.class.getName());

    @Override
    public void getWordFromApi(WordReceived callback) {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://randomword.setgetgo.com/get.php", new AsyncHttpResponseHandler() {
//        client.get("http://localhost:8080", new AsyncHttpResponseHandler() {
//        client.get("http://127.0.0.1:8080", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String word = new String(responseBody, StandardCharsets.UTF_8);
                LOGGER.log(Level.INFO, "Word received: " + word);
                callback.onWordReceived(word);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LOGGER.log(Level.SEVERE, "Failed to receive word!", error);
                callback.onWordReceived("500");
            }
        });
    }
}