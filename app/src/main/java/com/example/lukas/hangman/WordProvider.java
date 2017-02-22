package com.example.lukas.hangman;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;

public class WordProvider implements MvpWordProvider {
    String result = "";

    @Override
    public void getWordFromApi() {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://randomword.setgetgo.com/get.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String word = new String(responseBody, StandardCharsets.UTF_8);
                result = word;
                System.out.println("word onSuccess " + result);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                error.printStackTrace(System.out);
                result = error.getMessage();
            }
        });
        System.out.println("word provider " + result);
    }

    @Override
    public String getWord() {
        return result;
    }
}