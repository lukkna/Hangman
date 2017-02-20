package com.example.lukas.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainMvpView {

    private MainMvpPresenter mPresenter;

    private TextView mTitleTextView;
    private TextView mWordTextView;
    private EditText mGuessLetterEditText;
    private ImageView mImageView;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitleTextView = (TextView) findViewById(R.id.textView2);
        mWordTextView = (TextView) findViewById(R.id.textView);
        mGuessLetterEditText = (EditText) findViewById(R.id.editText);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mButton = (Button) findViewById(R.id.button);
        mPresenter = new MainPresenter(new AppDataManager(), this);

        //showCorrectWord();
    }

    public void getLetter(View view) {
        char letter = mPresenter.stringToChar(mGuessLetterEditText.getText().toString());
        mPresenter.guessLetter(letter);
        printGuessedLetters();
        changeImage();
        mPresenter.wordCompleted();
        mPresenter.gameOver();
        mGuessLetterEditText.setText("");
    }

    public void printGuessedLetters() {
        mWordTextView.setText(mPresenter.getGuessedLettersAsString());
        System.out.println(mPresenter.getGuessedLettersAsString());
    }

    public void showCorrectWord() {
        mTitleTextView.setText(mPresenter.getCorrectWord());
    }

    @Override
    public void showMessage(String message) {
        mTitleTextView.setText(message);
        mButton.setEnabled(false);
    }

    @Override
    public void changeImage() {
        mImageView.setImageResource(getResources().getIdentifier(mPresenter.changeImage(), "drawable", getPackageName()));
    }
}