package si.uni_lj.fe.tnuv.tnuv_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import android.widget.Toast;
import android.content.Context;

//ustvarimo class recyclerAdapter za listVaj in znotraj se class MyViewHolder
public class VajaAdapter extends RecyclerView.Adapter<VajaAdapter.MyViewHolder> {
    public ArrayList<Vaja> listVaj;
    Context context;
    public boolean hasPlus;

    //constructor
    VajaAdapter(ArrayList<Vaja> listVaj, boolean hasPlus){
        this.listVaj = listVaj;
        this.hasPlus = hasPlus;
    }

    //MyViewHolder defines kako bo izgledal posamezni element v seznamu
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt;
        private TextView muscleTxt;
        private ImageView img;
        CardView cv;


        public MyViewHolder(final View view){
            super(view);
            nameTxt =  (TextView)view.findViewById(R.id.textImeVaje);
            muscleTxt =  (TextView)view.findViewById(R.id.textMuscleG);
            img =  (ImageView)view.findViewById(R.id.imgVaje);
            cv = (CardView)view.findViewById(R.id.cv);
        }
    }

    //potem infatamo nas view kjer napisemo kaj se zgodi onCreate nasega viewHolderja
    @NonNull
    @Override
    public VajaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if(!hasPlus){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaje_card_without_plus, parent, false);
        }else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaje_card, parent, false);
        }
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    //tuki setamo ime nase vaje, povezemo text na xmlju z array listom
    @Override
    public void onBindViewHolder(@NonNull VajaAdapter.MyViewHolder holder, int position) {
        String imeV = listVaj.get(position).getImeVaje();
        String muscleV = listVaj.get(position).getMuscleG();
        int imgV = listVaj.get(position).getImgVaje();

        holder.nameTxt.setText(imeV);
        holder.muscleTxt.setText(muscleV);
        holder.img.setImageResource(imgV);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"The position is:"+position,Toast.LENGTH_SHORT).show();
            }
        });


    }

    //setamo item count na dolzino arraylista
    @Override
    public int getItemCount() {

        return listVaj.size();
    }
}
