package com.londonappbrewery.quizzler;

/**
 * Created by mahe on 23-Sep-17.
 */

public class TrueFalse {
    private  int mQuestionID;
    private boolean mAnswer;
    public TrueFalse(int questionResurceID,boolean trueOrFalse){
        mQuestionID=questionResurceID;
        mAnswer=trueOrFalse;


    }

    public int getQuestionID () {
        return mQuestionID;
    }

    public void setQuestionID (int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer () {
        return mAnswer;
    }

    public void setAnswer (boolean answer) {
        mAnswer = answer;
    }
}
