package github.sanokei.geoquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

import github.sanokei.geoquiz.Question;


public class MainActivity extends AppCompatActivity {

    //data members
    private Button mTrueButton;
    private Button mFalseButton;
    private Boolean mUndo = true;
    private CoordinatorLayout mLayout;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank;
    private int mCurrentIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getTextResID();
        mQuestionTextView.setText(question);

        //associate two buttons with the widget on the view of the xml file
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mLayout = (CoordinatorLayout)findViewById(R.id.layout);

        Snackbar snackbar = Snackbar.make(mLayout, "You answered the question!",Snackbar.LENGTH_LONG )
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //leave the button enabled
                        mUndo = true;
                    }
                });

        mTrueButton.setOnClickListener(v ->
            {
//                mTrueButton.setClickable(false);
                snackbar.show();
            }
        );
        mFalseButton.setOnClickListener(v ->
            {
//                mFalseButton.setClickable(false);
                snackbar.show();

            }
        );

        //if the user does not click UNDO, the snackbar dismissess after a couple of second and the answer YES is selected disable the buttons
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                //see Snackbar.Callback docs for event details
                if (!mUndo)
                {
                    Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                    mTrueButton.setEnabled(false);
                    mFalseButton.setEnabled(false);
                }
            }

            @Override
            public void onShown(Snackbar snackbar) {
            }
        });


    }


}