package com.kite.joco.gepgmuwgenp1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = "SORSZAMTESZT";
    TextView tvAktSorszam;
    Button btnGet, btnSet;
    public String sorszam ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAktSorszam = (TextView) findViewById(R.id.tvSorszam);
        btnGet = (Button) findViewById(R.id.btnGet);
        btnSet = (Button) findViewById(R.id.btnSet);


    }

    public void onClick (View v) {
        switch (v.getId()) {
            case (R.id.btnGet) : getSorszam();
                break;
            case (R.id.btnSet) : setSorszam();
                break;
            default:
        }
    }

    void getSorszam() {

        new getSorszamAsync().execute();

        /*if (sorszam != null) {
            tvAktSorszam.setText(sorszam);
        } else {
            Toast.makeText(this,"Hiba a kommunikációban",Toast.LENGTH_SHORT).show();
        }*/
    }

    void showSorszamValue(String s) {
        tvAktSorszam.setText(s);
    }

    void setSorszam() {
        new SetNewSorszamAsync().execute();
        new getSorszamAsync().execute();
    }

class getSorszamAsync extends AsyncTask<String,String,String> {
    String asorszam;

    @Override
    protected String doInBackground(String... strings) {
        asorszam = getAktSorszam();
        return asorszam;
    }

    @Override
    protected void onPostExecute(String s) {
        showSorszamValue(asorszam);
    }

    String getAktSorszam(){
        Call<Gmuwsorszam> aktcall = GepService.getGepRestService().getSorszam();

        String tmpsorszam = "";
        try {
            tmpsorszam = aktcall.execute().body().getSorszam();
            Log.i(LOGTAG,"Kapott sorszám : " +tmpsorszam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmpsorszam;
    }

}

    class SetNewSorszamAsync extends AsyncTask<String,String,String> {
        String asorszam;

        @Override
        protected String doInBackground(String... strings) {
            asorszam = getAktSorszam();
            return asorszam;
        }

        @Override
        protected void onPostExecute(String s) {
            showSorszamValue(asorszam);
        }

        String getAktSorszam(){
            Call<Gmuwsorszam> aktcall = GepService.getGepRestService().getSorszam();

            String tmpsorszam = "";
            try {
                tmpsorszam = aktcall.execute().body().getSorszam();
                Log.i(LOGTAG,"Új generáláshoz kapott sorszám : " +tmpsorszam);
                AlfaNumSorGenerator asGen = new AlfaNumSorGenerator();
                StringBuffer sb = new StringBuffer(tmpsorszam);
                StringBuffer kovSorszam = asGen.getNext(sb);
                Log.i(LOGTAG,"Következő sorszám: " + kovSorszam);
                Gmuwsorszam uj = new Gmuwsorszam(1,kovSorszam.toString());

                Call<String> ujSorszam = GepService.getGepRestService().edit(uj);
                int answercode = ujSorszam.execute().code();
                Log.i(LOGTAG,"Answer code: " + answercode);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return tmpsorszam;
        }

    }
}
