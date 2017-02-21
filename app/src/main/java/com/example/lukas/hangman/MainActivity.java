package com.example.lukas.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MvpPresenter mPresenter;

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

        mPresenter = new Presenter(new Model(), this);
    }

    public void getLetter(View view) {
        mPresenter.onButtonClick(mGuessLetterEditText.getText().toString());
        mGuessLetterEditText.setText("");
    }

    @Override
    public void printGuessedLetters(String guessedLetters) {
        mWordTextView.setText(guessedLetters);
    }

    @Override
    public void showMessage(String message) {
        mTitleTextView.setText(message);
    }

    @Override
    public void changeImage(int id) {
        mImageView.setImageResource(getResources().getIdentifier("hang" + id, "drawable", getPackageName()));
    }

    @Override
    public void disableInput() {
        mButton.setEnabled(false);
        mGuessLetterEditText.setEnabled(false);
    }
}