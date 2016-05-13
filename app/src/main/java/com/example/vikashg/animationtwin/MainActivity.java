package com.example.vikashg.animationtwin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView detailTextView;
    LinearLayout container_textViews;
    RelativeLayout container_main;
    ImageView image_box;
    ImageView image_circle;
    boolean isDetailedView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        container_textViews = (LinearLayout) findViewById(R.id.text_layout);
        detailTextView = (TextView) findViewById(R.id.detail_text);
        image_box = (ImageView) findViewById(R.id.image_box);
        image_circle = (ImageView) findViewById(R.id.image_circle);
        container_main = (RelativeLayout) findViewById(R.id.container_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        container_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate();
                isDetailedView = !isDetailedView;
            }
        });
    }

    private void animate() {
        ObjectAnimator animTextViewHeight = animateTextView();
        animTextViewHeight.setDuration(500);
        animTextViewHeight.setInterpolator(new CustomInterpolator());

        int targetWidthOfCircle, targetWidthOfBox;

        if (!isDetailedView) {
            targetWidthOfBox = 0;
            targetWidthOfCircle = 100;
        } else {
            targetWidthOfBox = 200;
            targetWidthOfCircle = 0;
        }

        image_box.setPivotX(0f);
        image_box.setPivotY(0f);
        /*image_circle.setPivotX(0f);
        image_circle.setPivotY(0f);*/
        resizeWidthWithAnimation(image_box, targetWidthOfBox, 250);
        resizeWidthWithAnimation(image_circle, targetWidthOfCircle, 250);
        //resizeHeightWithAnimation(image_box, targetWidthOfBox, 250);
        /*animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image_circle.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/

        /*resizeWidthWithAnimation(image_circle, targetWidthOfCircle, 250);
        resizeHeightWithAnimation(image_circle, targetWidthOfCircle, 250);*/


        /*ObjectAnimator animContainer = animateWidth();
        animContainer.setDuration(500);
        animContainer.setInterpolator(new CustomInterpolator());*/


        /*ObjectAnimator animVisibility = animateVisibility();
        animVisibility.setDuration(250);
        animVisibility.setInterpolator(new CustomInterpolator());*/

        /*ObjectAnimator animScaleX = animateScaleXY(image_box, "scaleX");
        animScaleX.setDuration(500);
        animScaleX.setInterpolator(new CustomInterpolator());


        ObjectAnimator animScaleY = animateScaleXY(image_box, "scaleY");
        animScaleX.setDuration(500);
        animScaleY.setInterpolator(new CustomInterpolator());*/


        //Set Interpolator

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animTextViewHeight);
        /*animatorSet.play(animTextViewHeight).with(animScaleX);
        animatorSet.play(animScaleX).with(animScaleY);*/
        animatorSet.start();
    }

    private void resizeWidthWithAnimation(View view, int targetWidth, int duration) {
        Animation anim = new ResizeWidthAnimation(view, targetWidth);
        anim.setDuration(duration);
        anim.setInterpolator(new CustomInterpolator());
        view.setAnimation(anim);
        view.animate();
    }

    private Animation resizeHeightWithAnimation(View view, int targetHeight, int duration) {
        Animation anim = new ResizeHeightAnimation(view, targetHeight);
        anim.setDuration(duration);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        view.setAnimation(anim);
        view.animate();
        return anim;
    }

    /*private ObjectAnimator animateWidth() {
        ObjectAnimator animator;
        int start = container_main.getWidth();
        Log.d("Log", "start = " + start + "image = " + image_box.getWidth());
        if (!isDetailedView) {
            animator = ObjectAnimator.ofInt(container_main, "width", start, start - image_box.getWidth());
        } else {
            animator = ObjectAnimator.ofInt(container_main, "width", start, start + image_box.getWidth());
        }
        return animator;
    }*/

    private ObjectAnimator animateScaleXY(View view, String param) {
        ObjectAnimator animator;
       /* view.setPivotX(0f);
        view.setPivotY(0f);*/
        if (!isDetailedView) {
            animator = ObjectAnimator.ofFloat(view, param, 1f, 0f);
        } else {
            animator = ObjectAnimator.ofFloat(view, param, 0f, 1f);
        }
        return animator;
    }

    private ObjectAnimator animateScaleY(View view, String param) {
        ObjectAnimator animator;
        if (!isDetailedView) {
            animator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f);
        } else {
            animator = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f);
        }
        return animator;
    }


    private ObjectAnimator animateVisibility() {
        ObjectAnimator animator;

        if (!isDetailedView) {
            animator = ObjectAnimator.ofFloat(image_box, "visibility", 1, 0);
        } else {
            animator = ObjectAnimator.ofFloat(image_box, "visibility", 0, 1);
        }
        return animator;
    }

    private ObjectAnimator animateTextView() {
        ObjectAnimator animator_maxLines;
        //int currentLineCount = detailTextView.getLineCount();
        //Log.d("Log", "currentLineCount = " + currentLineCount);
        if (detailTextView.getLineCount() <= 2) {
            //detailTextView.invalidate();
            detailTextView.setMaxLines(5);
            //detailTextView.invalidate();
            //int startHeight = currentLineCount * detailTextView.getLineHeight();
            // int endHeight = detailTextView.getLineCount() * detailTextView.getLineHeight();
            //Log.d("Log", "IF - startLineCount = " + currentLineCount + " TargetLineCount = " +detailTextView.getLineCount());

            animator_maxLines = ObjectAnimator.ofInt(detailTextView, "height", 70, 297);
        } else {
            //detailTextView.invalidate();
            //detailTextView.setMaxLines(2);
            // int startHeight = currentLineCount * detailTextView.getLineHeight();
            // int endHeight = detailTextView.getLineCount() * detailTextView.getLineHeight();

            // Log.d("Log", "ELSE - startLineCount = " + currentLineCount + " TargetLineCount = " +
            //        detailTextView.getLineCount());

            animator_maxLines = ObjectAnimator.ofInt(detailTextView, "height", 297, 70);
        }

        return animator_maxLines;
    }
}
