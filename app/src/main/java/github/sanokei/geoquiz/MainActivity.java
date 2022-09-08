package github.sanokei.geoquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //data members
    private Button mTrueButton;
    private Button mFalseButton;
    private Boolean mUndo = true;
    private CoordinatorLayout mLayout;

    Snackbar snackbar = Snackbar.make(mLayout, "You answered the question!",Snackbar.LENGTH_LONG )
            .setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //leave the button enabled
                    mUndo = true;
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //associate two buttons with the widget on the view of the xml file
        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mLayout = (CoordinatorLayout)findViewById(R.id.layout);

        mTrueButton.setOnClickListener(v ->
            {
                Snackbar.make(mLayout, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                snackbar.show();
            }
        );
        mFalseButton.setOnClickListener(v ->
            {
                Snackbar.make(mLayout, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
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