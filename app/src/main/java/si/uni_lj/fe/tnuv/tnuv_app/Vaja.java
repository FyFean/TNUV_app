package si.uni_lj.fe.tnuv.tnuv_app;

public class Vaja {
    private String imeVaje;
    private String muscleG;
    private int imgVaje;


    public Vaja(String imeVaje, String muscleG, int imgVaje){
        this.imeVaje = imeVaje;
        this.muscleG = muscleG;
        this.imgVaje = imgVaje;
    }

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

}
