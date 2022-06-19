package si.uni_lj.fe.tnuv.tnuv_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database2.CrossRefVajaWorkout;
import si.uni_lj.fe.tnuv.tnuv_app.database2.VajaEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutEntity;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutVaje;

public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.MyViewHolder>{

    Context mContext;
    private ArrayList<Workout> listCustWorkoutov;
    private Vaja clickedVaja;

    //constructor
    public PopupAdapter(Context mContext, Vaja clickedVaja, ArrayList<Workout> listCustWorkoutov) {
        this.mContext = mContext;
        this.clickedVaja = clickedVaja;
        this.listCustWorkoutov = listCustWorkoutov;

    }

    //inflatamo popup_card
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.popup_card, parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // setamo text v cardu
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(listCustWorkoutov.get(position).getIme());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //v novmu threadu se povezemo z bazo in insertamo vajo workoutu
                new Thread(){
                    @Override
                    public void run() {
                        AppDatabase db = Room.databaseBuilder(mContext.getApplicationContext(), AppDatabase.class, "database-name").build();

                        List<WorkoutVaje> vv = db.workoutVajeDAO().getWorkoutVaje();


                        System.out.println("dodajamo wokroutu: " + listCustWorkoutov.get(position).getIme() + "vajo: " + clickedVaja.getImeVaje());
                        CrossRefVajaWorkout cr = new CrossRefVajaWorkout();
                        cr.idWorkouta = listCustWorkoutov.get(position).getIdWorkouta();
                        cr.idVaje = clickedVaja.getIdVaje();
                        db.workoutVajeDAO().insert(cr);

//                        List<WorkoutVaje> vv2 = db.workoutVajeDAO().getWorkoutVaje();
//                        for (int i = 0; i < vv2.size(); i++) {
//                            for (int j = 0; j < vv2.get(i).vajaEntityList.size(); j++) {
//                                System.out.println("par workouta: " + vv2.get(i).workoutEntity.imeWorkouta + "z vajami: " + vv2.get(i).vajaEntityList.get(j).imeVaje);
//
//                            }
//
//                        }


                        //vrne podatke v main thread also i dunno zkaj mors castat v Activity
                        ((Activity) mContext).runOnUiThread( () -> provideFeedbackKerSmoPrijazni( listCustWorkoutov.get(position).getIme(), clickedVaja.getImeVaje()));
                    }
                }.start();
            }
        });
    }
    private void provideFeedbackKerSmoPrijazni(String imeW, String imeV){
        Toast.makeText(mContext,imeV + " has been successfully added to wokrout " + imeW,Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return listCustWorkoutov.size();
    }


    //View Holder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public CardView cv;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.imeWorkouta);
            cv  = (CardView) itemView.findViewById(R.id.cv);
        }
    }
}
