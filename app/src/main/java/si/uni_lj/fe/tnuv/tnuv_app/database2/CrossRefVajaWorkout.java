package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.Entity;

@Entity(primaryKeys = {"idVaje", "idWorkouta" })
public class CrossRefVajaWorkout {
    public int idVaje;
    public int idWorkouta;

}