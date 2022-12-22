package com.ayata.question.dashboard;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Typewriter extends androidx.appcompat.widget.AppCompatTextView {

    private CharSequence myText="";
    private int myindex;
    private long myDelay = 150;

    public Typewriter(Context context) {
        super(context);
    }

    public Typewriter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler myHandler = new Handler();

    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(myText.subSequence(0,myindex++));
            if (myindex<=myText.length()){
                myHandler.postDelayed(characterAdder,myDelay);
            }
        }
    };

    public void animateText(CharSequence mytext)
    {
        myText = mytext;
        myindex=0;
        setText("");

        myHandler.removeCallbacks(characterAdder);
        myHandler.postDelayed(characterAdder, myDelay);
    }

    public void setCharacterDelay(long m) {
        myDelay = m;
    }

    /*


     */

}

