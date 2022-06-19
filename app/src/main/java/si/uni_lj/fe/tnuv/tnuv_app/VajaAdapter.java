package si.uni_lj.fe.tnuv.tnuv_app;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import android.widget.Toast;
import android.content.Context;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

//ustvarimo class recyclerAdapter za listVaj in znotraj se class MyViewHolder
public class VajaAdapter extends RecyclerView.Adapter<VajaAdapter.MyViewHolder> {
    public ArrayList<Vaja> listVaj;
    public ArrayList<Workout> listWorkoutov;

    public int cardType;
    Context context;
    LayoutInflater ly;

    //constructor
    VajaAdapter(ArrayList<Vaja> listVaj,ArrayList<Workout> listWorkoutov, int cardType){
        this.listVaj = listVaj;
        this.listWorkoutov = listWorkoutov;
        this.cardType = cardType;
    }

    //MyViewHolder defines kako bo izgledal posamezni element v seznamu
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt;
        private TextView muscleTxt;
        private ImageView img;
        private MaterialButton button;
        CardView cv;


        public MyViewHolder(final View view){
            super(view);
            nameTxt =  (TextView)view.findViewById(R.id.textImeVaje);
            muscleTxt =  (TextView)view.findViewById(R.id.textMuscleG);
            img =  (ImageView)view.findViewById(R.id.imgVaje);
            button = view.findViewById(R.id.dodajBtn);
            cv = (CardView)view.findViewById(R.id.cv);

        }
    }

    //potem infatamo nas view kjer napisemo kaj se zgodi onCreate nasega viewHolderja
    @NonNull
    @Override
    public VajaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        switch(cardType) {
            case(0):
                //ce nimamo plusa smo v start workout tabu in checkiramo workout podrobnosti aka vaje ki jih ima
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaje_card_without_plus, parent, false);
                break;
            case(1):
                //ce mamo plus smo v listu vaj kjer jih lahko dodajamo
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaje_card, parent, false);
                break;
            case(2):
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vaje_card2, parent, false);
                break;
            default:
                itemView=null;
        }

        ly = LayoutInflater.from(parent.getContext());
        context = parent.getContext();

        return new MyViewHolder(itemView);
    }

    //tuki setamo ime nase vaje, povezemo text na xmlju z array listom
    //klican tokrat kokr elementov imamo v listu
    @Override
    public void onBindViewHolder(@NonNull VajaAdapter.MyViewHolder holder, int position) {
        String imeV = listVaj.get(position).getImeVaje();
        String muscleV = listVaj.get(position).getMuscleG();
        int imgV = listVaj.get(position).getImgVaje();

        holder.nameTxt.setText(imeV);
        holder.muscleTxt.setText(muscleV);
        holder.img.setImageResource(imgV);

        System.out.println("imgV"+imgV);

        if(cardType == 1) {
            //P O P U P  W I N D O W
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // inflate the layout of the popup window
                    View popupView = ly.inflate(R.layout.popup, null);

                    TextView textNadWorkouti = (TextView) popupView.findViewById(R.id.textNadWorkouti);

                    textNadWorkouti.setText("Add " + listVaj.get(position).getImeVaje() + " to workout: ");

                    // lets taps outside the popup also dismiss it
                    boolean focusable = true;

                    //nastavimo rounded corners backgroud
                    Drawable background = ContextCompat.getDrawable(context, R.drawable.popup_card);

                    // create the popup window
                    final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, focusable);

                    //cancel popup on click of "x"
                    MaterialButton xButton = (MaterialButton) popupView.findViewById(R.id.cancelPopup);
                    xButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                        }
                    });

                    popupWindow.setBackgroundDrawable(background);
                    popupWindow.setElevation(60);

                    // show the popup window, which view you pass in doesn't matter, it is only used for the window tolken
                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                    //klicemo recyclerview za popup
                    RecyclerView recyclerView = (RecyclerView) popupView.findViewById(R.id.recyclerView);

                    //setamo layout manager
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(mLayoutManager);

                    //klicemo adapter in mu podamo listWorkoutov
                    PopupAdapter adapter = new PopupAdapter(context, listVaj.get(position), listWorkoutov);

                    //setamo adapter
                    recyclerView.setAdapter(adapter);

                }


//        holder.cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"The position is:"+position,Toast.LENGTH_SHORT).show();
//            }
            });

        }


    }

//    public void onButtonShowPopupWindowClick(View view) {
//
//        // inflate the layout of the popup window
//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//
//        View popupView = inflater.inflate(R.layout.popup_window, null);
//
//        // create the popup window
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
//
//        // show the popup window
//        // which view you pass in doesn't matter, it is only used for the window tolken
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//
//        // dismiss the popup window when touched
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//    }

    //setamo item count na dolzino arraylista
    @Override
    public int getItemCount() {

        return listVaj.size();
    }
}
