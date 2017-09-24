package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here
    Button mTrueButton ,  mfalseButton;
    TextView mQuestionTextView , mScoreTextView;
    int mIndex , mQusetion , mScore;
    ProgressBar mProgressBar;



    // TODO: Declare member variables here:


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };//has constructor calls(new object references) as it's elements
    final  int PROGRESS_BAR_INCREMENT=(int)Math.ceil (100.0/mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            mScore=savedInstanceState.getInt ("scorekey");
            mIndex=savedInstanceState.getInt ("IndexKey");
        }
        else
        {
            mScore=0;
            mIndex=0;

        }
        mTrueButton= (Button) findViewById (R.id.true_button);
        mfalseButton= (Button) findViewById (R.id.false_button);
        mQuestionTextView= (TextView) findViewById (R.id.question_text_view);
        mScoreTextView= (TextView) findViewById (R.id.score);
        mProgressBar= (ProgressBar) findViewById (R.id.progress_bar);

        //retrieve constructor call
       mQusetion=mQuestionBank[mIndex].getQuestionID ();//get "R.id.question_?"

        mQuestionTextView.setText (mQusetion);
        mScoreTextView.setText ("score"+mScore+"/"+mQuestionBank.length);

        mTrueButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                checkAnswer (true);
               updateQuestion ();
            }
        });
        mfalseButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                checkAnswer (false);
               updateQuestion ();
            }
        });


    }

    private void updateQuestion(){
        mIndex=(mIndex+1)%mQuestionBank.length;
        if(mIndex==0){
            AlertDialog.Builder alert=new AlertDialog.Builder (this);
            alert.setTitle ("game over");
            alert.setCancelable (false);
            alert.setMessage ("you scored"+ mScore+ "points");
            alert.setPositiveButton ("close app", new DialogInterface.OnClickListener () {
                @Override
                public void onClick (DialogInterface dialog, int which) {
                    finish ();
                }
            });
            alert.show();
        }
        mQusetion=mQuestionBank[mIndex].getQuestionID ();
        mQuestionTextView.setText (mQusetion);
        mProgressBar.incrementProgressBy (PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText ("Score"+mScore+" / "+mQuestionBank.length);
    }
    private void checkAnswer(boolean userSelection)
    {
        boolean correctAnswer=mQuestionBank[mIndex].isAnswer ();
        if (userSelection ==correctAnswer){
            Toast.makeText (this, R.string.correct_toast, Toast.LENGTH_SHORT).show ();
            mScore+=1;

        }
        else {
            Toast.makeText (this,R.string.incorrect_toast, Toast.LENGTH_SHORT).show ();
        }
    }
    @Override
    public  void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState (outState);
        outState.putInt ("scorekey",mScore);
        outState.putInt ("IndexKey",mIndex);


    }
}
