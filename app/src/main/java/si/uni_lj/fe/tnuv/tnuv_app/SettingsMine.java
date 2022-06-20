package si.uni_lj.fe.tnuv.tnuv_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import si.uni_lj.fe.tnuv.tnuv_app.database2.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database2.PersonEntity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class SettingsMine extends AppCompatActivity implements View.OnClickListener {
    Workout clickedWorkout;
    TextView myAwesomeTextView;
    private Button saveBtn;
    private Button backBtn;
    Context context;
    EditText imePriimek;
    EditText teza;
    EditText visina;
    EditText zeljeneKalorije;
    EditText zeljenCas;
    Spinner spol;
    Spinner s;


    // onClick za shrani button,
    // TODO: naredi shrani button in ga poveži s tem
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:

                new Thread(){

                    @Override
                    public void run() {

                        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").allowMainThreadQueries().build();

                        //super.onBackPressed();
                        //shrani vse spremenjene inputiče v bazo
                        saveBtn = (Button)findViewById(R.id.saveBtn);
                        imePriimek = (EditText)findViewById(R.id.nameSurname);
                        teza = (EditText)findViewById(R.id.teza);
                        visina = (EditText)findViewById(R.id.visina);
                        zeljeneKalorije = (EditText)findViewById(R.id.zeljeneCal);
                        zeljenCas = (EditText)findViewById(R.id.casTel);
                        spol = (Spinner)findViewById(R.id.gender);
                        //dobi ID od osebe
                        List<PersonEntity> p = db.personDAO().getAll();
                        int idOsebe = p.get(0).idPerson;

                        //Log.v("EditText", mEdit.getText().toString());
                        String ip = imePriimek.getText().toString();

                        System.out.println("ip.matches() : " + ip.matches(""));
                        System.out.println("teza.matches() : " +  teza.getText().toString().matches(""));

                        //če je uporabnik vnesel novo ime in priimek - edittext ni prazen, kliči funkcijo, ki updejta ta field v bazi
                        if (ip.matches("") == false) {
                            //System.out.println("nova oseba shranjena");
                            db.personDAO().updateImePriimek(ip, idOsebe);

                            //System.out.println("Nova oseba"+db.personDAO().getAll().get(0).imePriimek);
                        }

                        String t = teza.getText().toString();

                        if (t.matches("") == false) {
                            int tInt =Integer.parseInt(t);
                            db.personDAO().updateTeza(tInt, idOsebe);

                            System.out.println("smo v  teza");
                            //System.out.println("Nova oseba"+db.personDAO().getAll().get(0).bodyWeight);
                        }

                        String vis = visina.getText().toString();

                        if (vis.matches("") == false) {
                            int vInt =Integer.parseInt(vis);
                            db.personDAO().updateVisina(vInt, idOsebe);

                        }

                        String zelCal = zeljeneKalorije.getText().toString();

                        if (zelCal.matches("") == false) {
                            int zelCalInt =Integer.parseInt(zelCal);
                            db.personDAO().updateZelCal(zelCalInt, idOsebe);

                        }

                        String zelCas = zeljenCas.getText().toString();

                        if (zelCas.matches("") == false) {
                            int zelCasInt =Integer.parseInt(zelCas);
                            db.personDAO().updateZelCas(zelCasInt, idOsebe);

                        }

                        String sp = spol.getSelectedItem().toString();

                        if (sp.matches("") == false) {
                            db.personDAO().updateSpol(sp, idOsebe);

                            System.out.println("a smo slucajn v spolu");
//                            System.out.println("spol"+db.personDAO().getAll().get(0).spol);
                        }
                        runOnUiThread( () -> feetBack());
                    }
                }.start();

                super.onBackPressed();
                break;


//                Intent intent = new Intent(this, HomeFragment.class);
//                startActivityForResult(intent);
//
//
//                                        Intent intent123 = new Intent(this, HomeFragment.class);
//                                        System.out.println("going homeeee");
//                                        //intent.putExtra("clickedWorkout", clickedWorkout);
//                                        context.startActivity(intent123);
            case R.id.backBtn:
                super.onBackPressed();
                break;
        }

    }

    private void feetBack(){
        Toast.makeText(context,"Uspešno posodobljen profil",Toast.LENGTH_LONG).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        //clickedWorkout = getIntent().getParcelableExtra("mylist");
        saveBtn = findViewById(R.id.saveBtn);
        backBtn = findViewById(R.id.backBtn);
        saveBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        context = this;


        //dropdown menu
        String[] arraySpinner = new String[] {
                "Moški", "Ženska", "Drugo"
        };
        s = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

//        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String tutorialsName = parent.getItemAtPosition(position).toString();
//                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView <?> parent) {
//            }
//        });

    }
}
