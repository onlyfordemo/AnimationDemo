package com.example.vikashg.animationtwin;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by VikashG on 4/28/2016.
 */
class ExpandAnimation extends Animation {
    private final View view;
    private final int startHeight;
    private final int finishHeight;

    public ExpandAnimation(View view, int startHeight, int finishHeight) {
        this.view = view;
        this.startHeight = startHeight;
        this.finishHeight = finishHeight;
        setDuration(500);
    }

	
	
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final int newHeight = (int) ((finishHeight - startHeight) * interpolatedTime + startHeight);
        view.getLayoutParams().height = newHeight;
		
		
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
};
