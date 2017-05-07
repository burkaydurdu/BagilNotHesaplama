package com.example.burkay.bagilnothesaplama;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{

    private int midterm_;
    private int final_;
    private float Hsb_average;
    private float Hsb_standart;
    private EditText midterm_txt, final_txt, hsb_average_txt, hsb_standart_txt;
    private TextView aa_txt, ba_txt, bb_txt, cb_txt, cc_txt, dc_txt;
    private TextView result_screen_txt;
    private Button result_button, clear_button;
    private ImageView imageLogo;
    private ArrayList<String> arrayInformation = new ArrayList<>();
    private int FINAL_ANGER;
    private HashMap<String,Integer> image;
    private TextView[] noteText;
    private Save saveNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result_button = (Button) findViewById(R.id.result_btn);
        clear_button = (Button) findViewById(R.id.clear_btn);

        midterm_txt = (EditText) findViewById(R.id.midterm_);
        final_txt = (EditText) findViewById(R.id.final_);
        hsb_average_txt = (EditText) findViewById(R.id.average);
        hsb_standart_txt = (EditText) findViewById(R.id.standard);

        aa_txt = (TextView) findViewById(R.id.aa_txt);
        ba_txt = (TextView) findViewById(R.id.ba_txt);
        bb_txt = (TextView) findViewById(R.id.bb_txt);
        cb_txt = (TextView) findViewById(R.id.cb_txt);
        cc_txt = (TextView) findViewById(R.id.cc_txt);
        dc_txt = (TextView) findViewById(R.id.dc_txt);
        result_screen_txt = (TextView) findViewById(R.id.result_screen);

        saveNote = new Save(this);
        FINAL_ANGER = saveNote.getNote();
        imageLogo = (ImageView) findViewById(R.id.image_logo);

        image = new HashMap<>();
        image.put("aa",R.drawable.aa);
        image.put("ba",R.drawable.ba);
        image.put("bb",R.drawable.bb);
        image.put("cb",R.drawable.cb);
        image.put("cc",R.drawable.cc);
        image.put("dc",R.drawable.dc);
        image.put("ff",R.drawable.ff);

        TextView[] note = {aa_txt,ba_txt,bb_txt,cb_txt,cc_txt,dc_txt};
        noteText = note;
        hsb_standart_txt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    resultFunction();
                    return false;
                }
                return true;
            }
        });
        result_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultFunction();
            }
        });
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                midterm_txt.setFocusable(true);
                midterm_txt.setText("");
                final_txt.setText("");
                hsb_average_txt.setText("");
                hsb_standart_txt.setText("");
                arrayInformation.clear();
                logoSet();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.note){

            new MaterialDialog.Builder(this)
                    .title(R.string.note)
                    .inputRange(1,2)
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .input(String.valueOf(saveNote.getNote()), null, new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            FINAL_ANGER = Integer.parseInt(input.toString());
                            saveNote.setNote(FINAL_ANGER);
                            Toast.makeText(getApplicationContext(),R.string.changed,Toast.LENGTH_SHORT).show();
                        }
                    })
                    .show();
        }
        else if(id == R.id.assessment){
            informationAlert();
        }
        return super.onOptionsItemSelected(item);
    }
    private void logoSet(){
        imageLogo.setImageResource(R.drawable.ktu_logo);
    }

    public void resultFunction(){

        if(!isEmpty(midterm_txt)  && !isEmpty(hsb_average_txt)) {
            midterm_ = Integer.parseInt(midterm_txt.getText().toString());
            Hsb_average = Float.parseFloat(hsb_average_txt.getText().toString());
            if(!isEmpty(final_txt)) {
                final_ = Integer.parseInt(final_txt.getText().toString());
                if (final_>100 || final_<0) {
                    Toast.makeText(getApplicationContext(), R.string.control, Toast.LENGTH_LONG).show();
                    logoSet();
                    return;
                }
            }
            if(midterm_>100 || midterm_<0 || Hsb_average>100 || Hsb_average<0){
                Toast.makeText(getApplicationContext(), R.string.control, Toast.LENGTH_LONG).show();
                logoSet();
                return;
            }
            if(Hsb_average < 80) {
                if (isEmpty(hsb_standart_txt)) {
                    Toast.makeText(getApplicationContext(), R.string.check_input, Toast.LENGTH_LONG).show();
                    logoSet();
                }
                else {
                    Hsb_standart = Float.parseFloat(hsb_standart_txt.getText().toString());
                    if(Hsb_standart>100 || Hsb_standart<0) {
                        Toast.makeText(getApplicationContext(), R.string.control, Toast.LENGTH_LONG).show();
                        logoSet();
                        return;
                    }
                    gettingValue();
                }
            }
            else
                gettingValue();
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.check_input, Toast.LENGTH_LONG).show();
            logoSet();
        }
    }

    public void informationAlert(){
        if(!arrayInformation.isEmpty()) {
            String t = "T : " + arrayInformation.get(0);
            String classl = getResources().getString(R.string.classlevel) + " : " + arrayInformation.get(1);
            new MaterialDialog.Builder(this)
                    .title(R.string.assessment)
                    .items(t, classl)
                    .show();
        }
        else{
            Toast.makeText(getApplicationContext(),R.string.check_input,Toast.LENGTH_SHORT).show();
        }
    }

    public void gettingValue(){
        DecimalFormat decimalFormat = new DecimalFormat();
        LetterNote note = null;
        double T_note = Transactions.T_Note(midterm_, final_ ,Hsb_average, Hsb_standart);
        decimalFormat.setMaximumFractionDigits(2);
        String T = decimalFormat.format(T_note);
        ClassLevel level =  Transactions.ClassLev(Hsb_average);
        String classLevel = getResources().getString(Transactions.ClassLev(Hsb_average).Description());

        if(!isEmpty(final_txt)) {
            if (FINAL_ANGER <= final_) {
                if (Hsb_average >= 80)
                    note = Transactions.Absolute_Assessment((midterm_ + final_) / 2);
                else
                    note = Transactions.LetterNote(level, T_note);
            } else
                note = LetterNote.FF;

            String letterNote = note.Description();
            Bitmap icon = BitmapFactory.decodeResource(this.getResources(), image.get(letterNote));
            imageLogo.setImageBitmap(icon);
        }
        else
            logoSet();

        int recom [] = Transactions.Recommendations(level, midterm_, Hsb_average, Hsb_standart);
        for(int count = 0; count < 6; count++)
        {
            if(recom[count] > 100) {
                noteText[count].setText("X");
            }
            else if (recom[count] < FINAL_ANGER) {
                if (count != 0 && recom[count - 1] > FINAL_ANGER)
                    noteText[count].setText(String.valueOf(FINAL_ANGER));
                else
                    noteText[count].setText("X");
            }
            else
                noteText[count].setText(String.valueOf(recom[count]));
        }
        arrayInformation.add(0,T);
        arrayInformation.add(1,classLevel);

    }

    public boolean isEmpty(EditText editText)
    {
        return editText.getText().toString().trim().length() == 0;
    }
}
