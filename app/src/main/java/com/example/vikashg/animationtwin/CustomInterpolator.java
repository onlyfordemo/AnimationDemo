package com.example.vikashg.animationtwin;

import android.animation.TimeInterpolator;

public class CustomInterpolator implements TimeInterpolator {

    CustomInterpolator() {

    }

    @Override
    public float getInterpolation(float input) {
        if((input /= 0.5) < 1) {
            return (float) (-1 * 0.5 * (Math.sqrt(1 - input * input) - 1));
        }

        return (float) (0.5 * (Math.sqrt(1 - (input -= 2) * input) + 1));
    }
}
