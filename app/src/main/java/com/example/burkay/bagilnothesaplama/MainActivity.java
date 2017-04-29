package com.example.burkay.bagilnothesaplama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private int midterm_;
    private int final_;
    private float Hsb_average;
    private float Hsb_standart;
    private EditText midterm_txt, final_txt, hsb_average_txt, hsb_standart_txt;
    private TextView aa_txt, ba_txt, bb_txt, cb_txt, cc_txt, dc_txt;
    private TextView result_screen;
    private Button result_Button, clear_Button;
    private static int FINAL_ANGER = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Component definition*/
        midterm_txt = (EditText) findViewById(R.id.midterm_);
        final_txt = (EditText) findViewById(R.id.final_);
        hsb_average_txt = (EditText) findViewById(R.id.average);
        hsb_standart_txt = (EditText) findViewById(R.id.standard);
        result_screen = (TextView) findViewById(R.id.result_screen);
        result_Button = (Button) findViewById(R.id.result_btn);
        clear_Button = (Button) findViewById(R.id.clear_btn);

        aa_txt = (TextView) findViewById(R.id.aa_txt);
        ba_txt = (TextView) findViewById(R.id.ba_txt);
        bb_txt = (TextView) findViewById(R.id.bb_txt);
        cb_txt = (TextView) findViewById(R.id.cb_txt);
        cc_txt = (TextView) findViewById(R.id.cc_txt);
        dc_txt = (TextView) findViewById(R.id.dc_txt);
        /*********end**********/

        result_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isEmpty(midterm_txt) && !isEmpty(final_txt) && !isEmpty(hsb_average_txt) && !isEmpty(hsb_standart_txt)) {

                    midterm_ = Integer.parseInt(midterm_txt.getText().toString());
                    final_ = Integer.parseInt(final_txt.getText().toString());
                    Hsb_average = Float.parseFloat(hsb_average_txt.getText().toString());
                    Hsb_standart = Float.parseFloat(hsb_standart_txt.getText().toString());
                    gettingValue();
                }
                else{
                    Toast.makeText(getApplicationContext(), R.string.check_input, Toast.LENGTH_LONG).show();
                }
            }
        });
        clear_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                midterm_txt.setText("");
                final_txt.setText("");
                hsb_average_txt.setText("");
                hsb_standart_txt.setText("");
            }
        });
    }

    public void gettingValue(){
        DecimalFormat decimalFormat = new DecimalFormat();
        LetterNote note = null;
        float T_note = Transactions.T_Note(midterm_, final_ ,Hsb_average, Hsb_standart);
        decimalFormat.setMaximumFractionDigits(2);
        String T = decimalFormat.format(T_note);
        ClassLevel level =  Transactions.ClassLev(Hsb_average);
        String classLevel = getResources().getString(level.Description());

        if(FINAL_ANGER <= final_)
            note= Transactions.LetterNot_30B(level, T_note,(float) (midterm_+final_)/2);
        else
            note = LetterNote.FF;

        String letterNote = note.Description();
        int recom [] = Transactions.Recommendations(level, midterm_, Hsb_average, Hsb_standart);
        aa_txt.setText(recom[0]<45? "X":String.valueOf(recom[0]));
        ba_txt.setText(recom[1]<45? "X":String.valueOf(recom[1]));
        bb_txt.setText(recom[2]<45? "X":String.valueOf(recom[2]));
        cb_txt.setText(recom[3]<45? "X":String.valueOf(recom[3]));
        cc_txt.setText(recom[4]<45? "X":String.valueOf(recom[4]));
        dc_txt.setText(recom[5]<45? "X":String.valueOf(recom[5]));
        result_screen.setText("T : "+T +"Class level : " + classLevel + "  Letter Note : " + letterNote);
    }

    public boolean isEmpty(EditText editText)
    {
        return editText.getText().toString().trim().length() == 0;
    }
}
