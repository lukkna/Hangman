package com.example.lukas.hangman.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lukas.hangman.R;

public class MainActivity extends AppCompatActivity implements MainMvpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitleTextView = (TextView) findViewById(R.id.textView2);

        mWordTextView = (TextView) findViewById(R.id.textView);
        mGuessLetterEditText = (EditText) findViewById(R.id.editText);
    }

    MainMvpPresenter mPresenter;

    private TextView mTitleTextView ;//(TextView) findViewById(R.id.textView2);
    private TextView mWordTextView ;//= (TextView) findViewById(R.id.textView);
    private EditText mGuessLetterEditText;// = (EditText) findViewById(R.id.editText);


//    public void guessLetter() {
//
//    }


    public void guessLetter(View view) {
        char letter = mGuessLetterEditText.getText().toString().charAt(0);
        System.out.println(letter);
        mPresenter.guessLetter(letter);
    }
}
