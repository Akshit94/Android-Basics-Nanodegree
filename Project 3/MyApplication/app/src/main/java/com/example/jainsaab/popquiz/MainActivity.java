package com.example.jainsaab.popquiz;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mAnswerText;
    LinearLayout mAlertLinearLayout1, mAlertLinearLayout2, mAlertLinearLayout3, mAlertLinearLayout4;
    ImageView mAlertImage1, mAlertImage2, mAlertImage3, mAlertImage4;
    TextView mAlertTextView1, mAlertTextView2, mAlertTextView3, mAlertTextView4;
    int mCorrectCount;
    RadioGroup mRadioYear, mRadioCompany;
    CheckBox checkBoxLollipop, checkBoxMarshMallow, checkBoxKitkat, checkBoxVanilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCorrectCount = 0;
        mAnswerText = (EditText) findViewById(R.id.cardview_edit_text);

        mAlertLinearLayout1 = (LinearLayout) findViewById(R.id.alert_layout_1);
        mAlertImage1 = (ImageView) findViewById(R.id.alert_image_1);
        mAlertTextView1 = (TextView) findViewById(R.id.alert_text_1);
        mAlertLinearLayout1.setVisibility(View.GONE);

        mAlertLinearLayout2 = (LinearLayout) findViewById(R.id.alert_layout_2);
        mAlertImage2 = (ImageView) findViewById(R.id.alert_image_2);
        mAlertTextView2 = (TextView) findViewById(R.id.alert_text_2);
        mAlertLinearLayout2.setVisibility(View.GONE);

        mAlertLinearLayout3 = (LinearLayout) findViewById(R.id.alert_layout_3);
        mAlertImage3 = (ImageView) findViewById(R.id.alert_image_3);
        mAlertTextView3 = (TextView) findViewById(R.id.alert_text_3);
        mAlertLinearLayout3.setVisibility(View.GONE);

        mAlertLinearLayout4 = (LinearLayout) findViewById(R.id.alert_layout_4);
        mAlertImage4 = (ImageView) findViewById(R.id.alert_image_4);
        mAlertTextView4 = (TextView) findViewById(R.id.alert_text_4);
        mAlertLinearLayout4.setVisibility(View.GONE);

        mRadioYear = (RadioGroup) findViewById(R.id.radio_year);
        mRadioCompany = (RadioGroup) findViewById(R.id.radio_company);

        checkBoxKitkat = (CheckBox) findViewById(R.id.check_kitkat);
        checkBoxLollipop = (CheckBox) findViewById(R.id.check_lollipop);
        checkBoxMarshMallow = (CheckBox) findViewById(R.id.check_marshmallow);
        checkBoxVanilla = (CheckBox) findViewById(R.id.check_vanilla);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void submit(View view) {
        String answer1[] = {"Andy Rubin", "andy rubin", "Andy rubin", "andy Rubin", "ANDY RUBIN"};
        String userAnswer1 = mAnswerText.getText().toString();
        if (userAnswer1.equals("")) {
            setAlert(mAlertTextView1, mAlertImage1, mAlertLinearLayout1, R.string.give_a_shot, R.color.red, R.drawable.ic_sentiment_very_dissatisfied_black_24dp, R.color.red);
        }
        int i;
        for (i = 0; i < answer1.length; ++i) {
            if (userAnswer1.equals(answer1[i])) {
                setAlert(mAlertTextView1, mAlertImage1, mAlertLinearLayout1, R.string.awesome, R.color.androidColor, R.drawable.ic_sentiment_very_satisfied_black_24dp, R.color.androidColor);
                mCorrectCount += 1;
                break;
            }
        }

        if (i == answer1.length && !userAnswer1.equals("")) {
            setAlert(mAlertTextView1, mAlertImage1, mAlertLinearLayout1, R.string.alert, R.color.red, R.drawable.ic_sentiment_very_dissatisfied_black_24dp, R.color.red);
        }

        if(checkBoxKitkat.isChecked() && checkBoxMarshMallow.isChecked() && checkBoxLollipop.isChecked() && !checkBoxVanilla.isChecked()){
            setAlert(mAlertTextView2, mAlertImage2, mAlertLinearLayout2, R.string.awesome, R.color.androidColor, R.drawable.ic_sentiment_very_satisfied_black_24dp, R.color.androidColor);
            mCorrectCount += 1;
        } else if((checkBoxKitkat.isChecked() || checkBoxMarshMallow.isChecked() || checkBoxLollipop.isChecked()) && !checkBoxVanilla.isChecked()) {
            setAlert(mAlertTextView2, mAlertImage2, mAlertLinearLayout2, R.string.close, R.color.orange, R.drawable.ic_sentiment_satisfied_black_24dp, R.color.orange);
        } else if(!checkBoxKitkat.isChecked() && !checkBoxMarshMallow.isChecked() && !checkBoxLollipop.isChecked() && !checkBoxVanilla.isChecked()) {
            setAlert(mAlertTextView2, mAlertImage2, mAlertLinearLayout2, R.string.give_a_shot, R.color.red, R.drawable.ic_sentiment_very_dissatisfied_black_24dp, R.color.red);
        } else {
            setAlert(mAlertTextView2, mAlertImage2, mAlertLinearLayout2, R.string.alert, R.color.red, R.drawable.ic_sentiment_very_dissatisfied_black_24dp, R.color.red);
        }

        int companyId = mRadioCompany.getCheckedRadioButtonId();
        if (companyId == R.id.radio_google) {
            setAlert(mAlertTextView3, mAlertImage3, mAlertLinearLayout3, R.string.awesome, R.color.androidColor, R.drawable.ic_sentiment_very_satisfied_black_24dp, R.color.androidColor);
            mCorrectCount += 1;
        } else if (companyId == R.id.radio_facebook || companyId == R.id.radio_microsoft || companyId == R.id.radio_intel) {
            setAlert(mAlertTextView3, mAlertImage3, mAlertLinearLayout3, R.string.alert, R.color.red, R.drawable.ic_sentiment_very_dissatisfied_black_24dp, R.color.red);
        } else {
            setAlert(mAlertTextView3, mAlertImage3, mAlertLinearLayout3, R.string.give_a_shot, R.color.red, R.drawable.ic_sentiment_very_dissatisfied_black_24dp, R.color.red);
        }


        int yearId = mRadioYear.getCheckedRadioButtonId();
        if (yearId == R.id.radio_2003) {
            setAlert(mAlertTextView4, mAlertImage4, mAlertLinearLayout4, R.string.awesome, R.color.androidColor, R.drawable.ic_sentiment_very_satisfied_black_24dp, R.color.androidColor);
            mCorrectCount += 1;
        } else if (yearId == R.id.radio_2005 || yearId == R.id.radio_2007 || yearId == R.id.radio_2008) {
            setAlert(mAlertTextView4, mAlertImage4, mAlertLinearLayout4, R.string.alert, R.color.red, R.drawable.ic_sentiment_very_dissatisfied_black_24dp, R.color.red);
        } else {
            setAlert(mAlertTextView4, mAlertImage4, mAlertLinearLayout4, R.string.give_a_shot, R.color.red, R.drawable.ic_sentiment_very_dissatisfied_black_24dp, R.color.red);
        }

        Toast.makeText(getApplicationContext(), "You have got " + mCorrectCount + " out of 4 questions correct.\n See the main screen for results.", Toast.LENGTH_LONG).show();
        mCorrectCount = 0;
    }

    public void setAlert(TextView alertTextView, ImageView alertImage, LinearLayout alertLayout, int textId, int textColor, int drawable, int drawableColor) {
        alertTextView.setText(getString(textId));
        alertTextView.setTextColor(getResources().getColor(textColor));
        alertImage.setImageDrawable(getDrawable(drawable));
        alertImage.setColorFilter(ContextCompat.getColor(getApplicationContext(), drawableColor));
        alertLayout.setVisibility(View.VISIBLE);
    }
}

