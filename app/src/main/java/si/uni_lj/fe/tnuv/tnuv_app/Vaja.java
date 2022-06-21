package si.uni_lj.fe.tnuv.tnuv_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Vaja implements Parcelable {
    private int idVaje;
    private String imeVaje;
    private String muscleG;
    private int imgVaje;
    private String desc;
    private int cals;
    private int recommendedSets;
    private int recommendedReps;


    public Vaja(int idVaje, String imeVaje, String muscleG, int imgVaje, String desc, int cals, int recommendedReps, int recommendedSets){
        this.idVaje = idVaje;
        this.imeVaje = imeVaje;
        this.muscleG = muscleG;
        this.imgVaje = imgVaje;
        this.desc = desc;
        this.cals = cals;
        this.recommendedSets = recommendedSets;
        this.recommendedReps = recommendedReps;
    }

    protected Vaja(Parcel in) {
        imeVaje = in.readString();
        muscleG = in.readString();
        imgVaje = in.readInt();
        desc = in.readString();
        cals = in.readInt();
        recommendedSets = in.readInt();
        recommendedReps = in.readInt();
    }

    public void setIdVaje(int idVaje) {
        this.idVaje = idVaje;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCals(int cals) {
        this.cals = cals;
    }

    public int getIdVaje() {
        return idVaje;
    }

    public int getRecommendedReps() {
        return recommendedReps;
    }

    public int getRecommendedSets() {
        return recommendedSets;
    }

    public void setRecommendedSets(int recommendedSets) {
        this.recommendedSets = recommendedSets;
    }

    public String getDesc() {
        return desc;
    }

    public int getCals() {
        return cals;
    }

    public static final Creator<Vaja> CREATOR = new Creator<Vaja>() {
        @Override
        public Vaja createFromParcel(Parcel in) {
            return new Vaja(in);
        }

        @Override
        public Vaja[] newArray(int size) {
            return new Vaja[size];
        }
    };

    public String getImeVaje(){
        return imeVaje;
    }
    public String getMuscleG(){
        return muscleG;
    }
    public int getImgVaje(){
        return imgVaje;
    }

    public void setImeVaje(String imeVaje){
        this.imeVaje = imeVaje;
    }
    public void setMuscleG(String muscleG){
        this.muscleG = muscleG;
    }

    public void setImgVaje(int imgVaje){
        this.imgVaje = imgVaje;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imeVaje);
        parcel.writeString(muscleG);
        parcel.writeInt(imgVaje);
    }
}
