package net.practicalcoding.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.homePageTitle);
        mButton = findViewById(R.id.playBTN);

        mTextView.animate()
                .alpha(0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mTextView.setText("X O");
                        mTextView.animate()
                                .alpha(1f)
                                .setDuration(500)
                                .start();
                    }
                })
                .start();

        mButton.setVisibility(View.INVISIBLE);

        // Delay the start of the animation by 3 seconds using a Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Animate the text from "X O X." to "Tic Tac Toy"
                mTextView.animate()
                        .alpha(0f)
                        .setDuration(500)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                mTextView.setText("Tic Tac Toy");
                                mTextView.animate()
                                        .alpha(1f)
                                        .setDuration(500)
                                        .start();
                            }
                        })
                        .start();

                // Animate the button to slide in from the top of the screen
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -500, 0);
                translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                translateAnimation.setDuration(1000);
                mButton.startAnimation(translateAnimation);

                // Once the animation is complete, make the button visible
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mButton.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }
        }, 3000);

    }


    public void playButtonClick(View view){
        Button button = (Button) view;
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        button.startAnimation(animation);
        Intent intent = new Intent(this, PlayerSetup.class);
        startActivity(intent);
    }



}