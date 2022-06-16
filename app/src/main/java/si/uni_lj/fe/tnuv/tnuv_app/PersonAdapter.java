//to je recycler view --> definirasš akko bo zgledal en element v seznamu in pol ta recycler view napolne podatke notr

package si.uni_lj.fe.tnuv.tnuv_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    public ArrayList<Workout> workoutList;
    Context context;

    public PersonAdapter(ArrayList<Workout> workoutList){
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
    public PersonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_card, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    //tuki setamo ime nase vaje, povezemo text na xmlju z array listom
    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder holder, int position) {
        System.out.println(workoutList.get(position).getIme());
        String imeW = workoutList.get(position).getIme();
        holder.nameTxt.setText(imeW);

        // onClick klicemo novo aktivnost in ji passamo paramentre
        //tle mas bdefiniran kako zgleda ena kartica od workouta in nanjo lahko da on click listener
        //in potem on click definiras, da se ti zacne nova aktivnost --> aktivnost je pa workout podrobnosti
        //s put estra passa parametre iz ene aktivnosti v drugo
        //ker ne mroes objectov passat kot celih, mores uporabit parcelable in to potem samo zravn razreda workouts das implements Parecalbel in alt enter ind deleee yaaa
        holder.cv.setOnClickListener(view -> {
            Intent intent = new Intent(context, WorkoutPodrobnosti.class);
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

