package si.uni_lj.fe.tnuv.tnuv_app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Workout implements Parcelable {
    private int idWorkouta;
    private ArrayList<Vaja> vaje;
    private String ime;
    private int trajanje;
    private int totalCals;

    public Workout(int idWorkouta, String ime, int trajanje, int totalCals, ArrayList<Vaja> vaje){
        this.idWorkouta = idWorkouta;
        this.ime = ime;
        this.trajanje = trajanje;
        this.totalCals = totalCals;
        this.vaje = vaje;
    }

    public void setIdWorkouta(int idWorkouta) {
        this.idWorkouta = idWorkouta;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    public void setTotalCals(int totalCals) {
        this.totalCals = totalCals;
    }

    public int getIdWorkouta() {
        return idWorkouta;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public int getTotalCals() {
        return totalCals;
    }

    protected Workout(Parcel in) {
        idWorkouta = in.readInt();
        vaje = in.createTypedArrayList(Vaja.CREATOR);
        ime = in.readString();
        trajanje = in.readInt();
        totalCals = in.readInt();
    }

    public static final Creator<Workout> CREATOR = new Creator<Workout>() {
        @Override
        public Workout createFromParcel(Parcel in) {
            return new Workout(in);
        }

        @Override
        public Workout[] newArray(int size) {
            return new Workout[size];
        }
    };

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public ArrayList<Vaja> getVaje() {
        return vaje;
    }

    public void setVaje(ArrayList<Vaja> vaje) {
        this.vaje = vaje;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idWorkouta);
        parcel.writeTypedList(vaje);
        parcel.writeString(ime);
        parcel.writeInt(trajanje);
        parcel.writeInt(totalCals);
    }
}
