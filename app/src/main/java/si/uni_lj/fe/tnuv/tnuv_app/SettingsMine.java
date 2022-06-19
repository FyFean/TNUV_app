package si.uni_lj.fe.tnuv.tnuv_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import si.uni_lj.fe.tnuv.tnuv_app.database2.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database2.PersonEntity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class SettingsMine extends AppCompatActivity implements View.OnClickListener {
    Workout clickedWorkout;
    TextView myAwesomeTextView;
    private Button saveBtn;
    Context context;
    EditText imePriimek;
    EditText teza;
    EditText visina;
    EditText zeljeneKalorije;
    EditText zeljenCas;
    Spinner spol;


    // onClick za shrani button,
    // TODO: naredi shrani button in ga poveži s tem
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:
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

                saveBtn.setOnClickListener(
                        new View.OnClickListener()
                        {
                            public void onClick(View view)
                            {
                                //Log.v("EditText", mEdit.getText().toString());
                                String ip = imePriimek.getText().toString();

                                //če je uporabnik vnesel novo ime in priimek - edittext ni prazen, kliči funkcijo, ki updejta ta field v bazi
                                if (ip.matches("") == false) {
                                    System.out.println("nova oseba shranjena");
                                    db.personDAO().updateImePriimek(ip, idOsebe);
                                    System.out.println("Nova oseba"+db.personDAO().getAll().get(0).imePriimek);
                                }

                                String t = teza.getText().toString();


                                if (t.matches("") == false) {
                                    int tInt =Integer.parseInt(t);
                                    db.personDAO().updateTeza(tInt, idOsebe);
                                }

                                String v = visina.getText().toString();


                                if (v.matches("") == false) {
                                    int vInt =Integer.parseInt(v);
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

//                                String sp = spol.getSelectedItem().toString();
//
//                                if (sp.matches("") == false) {
//                                    db.personDAO().updateSpol(sp, idOsebe);
//                                }
                            }
                        });

                break;
            default:
                break;
        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);
        //clickedWorkout = getIntent().getParcelableExtra("mylist");
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
        context = this;

    }

}
