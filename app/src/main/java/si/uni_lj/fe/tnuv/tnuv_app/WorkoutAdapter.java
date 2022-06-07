package si.uni_lj.fe.tnuv.tnuv_app;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.MyViewHolder> {
    public ArrayList<Workout> workoutList;
    Context context;

    public WorkoutAdapter(ArrayList<Workout> workoutList){
        this.workoutList = workoutList;
    }

    //MyViewHolder defines kako bo izgledal posamezni element v seznamu
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTxt;
        CardView cv;

        public MyViewHolder(final View view){
            super(view);
            nameTxt =  (TextView)view.findViewById(R.id.imeWorkouta);
            cv = (CardView)view.findViewById(R.id.cv);
        }
    }

    //potem infatamo nas view kjer napisemo kaj se zgodi onCreate nasega viewHolderja
    @NonNull
    @Override
    public WorkoutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_card, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    //tuki setamo ime nase vaje, povezemo text na xmlju z array listom
    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.MyViewHolder holder, int position) {
        System.out.println(workoutList.get(position).getIme());
        String imeW = workoutList.get(position).getIme();
        holder.nameTxt.setText(imeW);

        // onClick klicemo novo aktivnost in ji passamo paramentre
        holder.cv.setOnClickListener(view -> {
            Intent intent = new Intent(context, WorkoutDetails.class);
            intent.putExtra("mylist", workoutList.get(position));
            context.startActivity(intent);
        });

    }

    //setamo item count na dolzino arraylista
    @Override
    public int getItemCount() {
        System.out.println(workoutList.size());
        return workoutList.size();
    }
}

