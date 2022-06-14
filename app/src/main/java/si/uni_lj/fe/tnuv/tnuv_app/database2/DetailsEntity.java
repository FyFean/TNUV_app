package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {

        @ForeignKey(entity = VajaEntity.class,
        parentColumns = "idVaje",
        childColumns = "pripadaVaji",
        onDelete = ForeignKey.CASCADE),

        @ForeignKey(entity = WorkoutEntity.class,
                parentColumns = "idWorkouta",
                childColumns = "pripadaWorkoutu",
                onDelete = ForeignKey.CASCADE),
        })
public class DetailsEntity {
    @PrimaryKey(autoGenerate = true)
    public int idDetails;

    @ColumnInfo(name = "pripadaVaji")
    public int pripadaVaji;

    @ColumnInfo(name = "pripadaWorkoutu")
    public int pripadaWorkoutu;

    @ColumnInfo(name = "setNo")
    public int setNo;

    @ColumnInfo(name = "reps")
    public int reps;

    @ColumnInfo(name = "weight")
    public int weight;

}

