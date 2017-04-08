package com.example.burkay.bagilnothesaplama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int midterm_;
    private int final_;
    private float Hsb_average;
    private float Hsb_standart;
    private EditText midterm_txt, final_txt, hsb_average_txt, hsb_standart_txt;
    private TextView result_screen;
    private Button result_Button;
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
    }

    public void gettingValue(){
        LetterNote note = null;
        float T_note = Transactions.T_Note(midterm_, final_ ,Hsb_average, Hsb_standart);

        ClassLevel level =  Transactions.ClassLev(Hsb_average);
        String classLevel = level.Description();

        if(FINAL_ANGER <= final_)
            note= Transactions.LetterNot_30B(level, T_note,(float) (midterm_+final_)/2);
        else
            note = LetterNote.FF;

        String letterNote = note.Description();
        int recom [] = Transactions.Recommendations(level, midterm_, Hsb_average, Hsb_standart);
        result_screen.setText("T : "+String.valueOf(T_note) +"Class level : " + classLevel + "  Letter Note : " + letterNote);

        for(int a = 0; a<recom.length; a++)
            Log.d("array : " , String.valueOf(recom[a]));

    }

    public boolean isEmpty(EditText editText)
    {
        return editText.getText().toString().trim().length() == 0;
    }
}
