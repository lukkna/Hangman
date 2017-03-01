package com.example.lukas.hangman;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;

public class WordProvider implements MvpWordProvider {
    @Override
    public void getWordFromApi(WordReceived callback) {
        final AsyncHttpClient client = new AsyncHttpClient();
        //client.get("http://randomword.setgetgo.com/get.php", new AsyncHttpResponseHandler() {
        client.get("http://10.0.0.243:8080", new AsyncHttpResponseHandler() {
//        client.get("http://127.0.0.1:8080", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String word = new String(responseBody, StandardCharsets.UTF_8);
                callback.onWordReceived(word);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                error.printStackTrace(System.out);
                String word = new String(responseBody, StandardCharsets.UTF_8);
                System.out.println(word);
            }
        });
    }
}