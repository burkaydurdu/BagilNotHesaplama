package com.example.burkay.bagilnothesaplama;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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
        image.put("ff",R.drawable.ff);

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
                midterm_txt.setText("");
                final_txt.setText("");
                hsb_average_txt.setText("");
                hsb_standart_txt.setText("");
                arrayInformation.clear();
                imageLogo.setImageResource(R.drawable.ktu_logo);
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

    public void resultFunction(){

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
        float T_note = Transactions.T_Note(midterm_, final_ ,Hsb_average, Hsb_standart);
        decimalFormat.setMaximumFractionDigits(2);
        String T = decimalFormat.format(T_note);
        ClassLevel level =  Transactions.ClassLev(Hsb_average);
        String classLevel = getResources().getString(Transactions.ClassLev(Hsb_average).Description());

        if(FINAL_ANGER <= final_)
            note= Transactions.LetterNot_30B(level, T_note,(float) (midterm_+final_)/2);
        else
            note = LetterNote.FF;

        String letterNote = note.Description();
        int recom [] = Transactions.Recommendations(level, midterm_, Hsb_average, Hsb_standart);
        aa_txt.setText(recom[0]>99 || recom[0] < FINAL_ANGER ? "X":String.valueOf(recom[0]));
        ba_txt.setText(recom[0]>99 || recom[1] < FINAL_ANGER ? "X":String.valueOf(recom[1]));
        bb_txt.setText(recom[0]>99 || recom[2] < FINAL_ANGER ? "X":String.valueOf(recom[2]));
        cb_txt.setText(recom[0]>99 || recom[3] < FINAL_ANGER ? "X":String.valueOf(recom[3]));
        cc_txt.setText(recom[0]>99 || recom[4] < FINAL_ANGER ? "X":String.valueOf(recom[4]));
        dc_txt.setText(recom[5] < FINAL_ANGER ? "X":String.valueOf(recom[5]));
        arrayInformation.add(0,T);
        arrayInformation.add(1,classLevel);
        arrayInformation.add(2,letterNote);

        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),image.get(letterNote));
        imageLogo.setImageBitmap(icon);
    }

    public boolean isEmpty(EditText editText)
    {
        return editText.getText().toString().trim().length() == 0;
    }
}
