package com.example.lukas.hangman;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView {

    private MvpPresenter mPresenter;

    private TextView mTitleTextView;
    private TextView mWordTextView;
    private EditText mGuessLetterEditText;
    private ImageView mImageView;
    private Button mButton; //guess letter
    private Button mButton2; //play game
    private RelativeLayout mRelativeLayout; //progress bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleTextView = (TextView) findViewById(R.id.textView2);
        mWordTextView = (TextView) findViewById(R.id.textView);
        mGuessLetterEditText = (EditText) findViewById(R.id.editText);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mButton = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.loadingPanel);

        mPresenter = new Presenter(new Model(), this);
    }

//    @Override
//    protected void onPause() {
//
//
//
//    }
//
//    @Override
//    protected void onResume() {
//
//
//    }

    public void guessLetter(View view) {
        mPresenter.onGuessLetter(mGuessLetterEditText.getText().toString());
        mGuessLetterEditText.setText("");
    }

    public void startNewGame(View view) {
        mPresenter.onStartNewGame();
        mButton2.setText("Start new game");
    }

    @Override
    public void printGuessedLetters(String guessedLetters) {
        mWordTextView.setText(guessedLetters);
    }

    @Override
    public void enableInput() {
        mButton.setEnabled(true);
        mGuessLetterEditText.setEnabled(true);
    }

    @Override
    public void enableProgressBar(boolean enabled) {
        if (enabled)
            mRelativeLayout.setVisibility(View.VISIBLE);
        else mRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        mTitleTextView.setText(message);
    }

    @Override
    public void changeImage(int id) {
        mImageView.setImageResource(
                getResources().getIdentifier("hang" + id, "drawable", getPackageName()));
    }

    @Override
    public void disableInput() {
        mButton.setEnabled(false);
        mGuessLetterEditText.setEnabled(false);
    }
}