package com.moviles.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private TextView mQuestionTextView;
	
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
			new TrueFalse(R.string.question_ocean,true),
			new TrueFalse(R.string.question_mideast,false),
			new TrueFalse(R.string.question_africa,false),
			new TrueFalse(R.string.question_americas,true),
			new TrueFalse(R.string.question_asia,true)			
	};
	
	private int mCurrentIndex=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		int question = mQuestionBank[mCurrentIndex].getmQuestion();
		mQuestionTextView.setText(question);	
		
		mNextButton =  (Button)findViewById(R.id.next_button);
		mNextButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				int question = mQuestionBank[mCurrentIndex].getmQuestion();
				mQuestionTextView.setText(question);
			}
		});
		
		mTrueButton=(Button)findViewById(R.id.true_button);
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();				
				checkAnswer(true);
			}
		});
		
		mFalseButton=(Button)findViewById(R.id.false_button);	
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
				checkAnswer(false);
			}
		});
	}
	
	private void checkAnswer ( boolean userPresTrue){
		
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();
		
		int messageResId=0;
		
		if (userPresTrue == answerIsTrue){
			messageResId=R.string.correct_toast;
		}else {
			messageResId=R.string.incorrect_toast;
		}
		Toast.makeText(this,messageResId, Toast.LENGTH_SHORT).show();
	}

}
