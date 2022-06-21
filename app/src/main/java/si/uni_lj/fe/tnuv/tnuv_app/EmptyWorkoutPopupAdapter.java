package si.uni_lj.fe.tnuv.tnuv_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.AppDatabase;
import si.uni_lj.fe.tnuv.tnuv_app.database2.CrossRefVajaWorkout;
import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutVaje;

public class EmptyWorkoutPopupAdapter extends RecyclerView.Adapter<EmptyWorkoutPopupAdapter.MyViewHolder> {

    private Workout novWorkout;
    private ArrayList<Vaja> listVaj;
    Context mContext;
    private PopupWindow popupWindow;

    public EmptyWorkoutPopupAdapter(Context mContext, PopupWindow popupWindow, Workout workout, ArrayList<Vaja> listVaj){
        this.mContext = mContext;
        this.popupWindow = popupWindow;
        this.listVaj = listVaj;
        this.novWorkout = workout;
    }


    @Override
    public EmptyWorkoutPopupAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.popup_card, parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull EmptyWorkoutPopupAdapter.MyViewHolder holder, int position) {
        holder.mTextView.setText(listVaj.get(position).getImeVaje());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //v novmu threadu se povezemo z bazo in insertamo vajo workoutu
                new Thread(){
                    @Override
                    public void run() {
                        AppDatabase db = Room.databaseBuilder(mContext.getApplicationContext(), AppDatabase.class, "database-name").build();


                        List<WorkoutVaje> vv = db.workoutVajeDAO().getWorkoutVaje();

                        System.out.println("dodajamo wokroutu z idjem : " + novWorkout.getIdWorkouta()  + "vajo: " + listVaj.get(position).getImeVaje());
//
                        CrossRefVajaWorkout cr = new CrossRefVajaWorkout();
                        cr.idWorkouta = novWorkout.getIdWorkouta();
                        cr.idVaje = listVaj.get(position).getIdVaje();
                        db.workoutVajeDAO().insert(cr);


                        //vrne podatke v main thread also i dunno zkaj mors castat v Activity
                        ((Activity) mContext).runOnUiThread( () -> provideFeedbackKerSmoPrijazni());
                    }
                }.start();

            }

            public void provideFeedbackKerSmoPrijazni(){
                //Toast.makeText(mContext,clickedVaja.getImeVaje() + " has been successfully added to wokrout " + listCustWorkoutov.get(position).getIme(),Toast.LENGTH_LONG).show();
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return  listVaj.size();
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
