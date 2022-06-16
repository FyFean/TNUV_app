package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import si.uni_lj.fe.tnuv.tnuv_app.Person;

@Entity(foreignKeys = {

        @ForeignKey(entity = PersonEntity.class,
                parentColumns = "idPerson",
                childColumns = "pripadaOsebi",
                onDelete = ForeignKey.CASCADE),
})
public class WorkoutEntity {
    @PrimaryKey(autoGenerate = true)
    public int idWorkouta;

    @ColumnInfo(name = "ime_workouta")
    public String imeWorkouta;

    @ColumnInfo(name = "isCustom")
    public int isCustom;

    @ColumnInfo(name = "trajanje")
    public int trajanje;

    @ColumnInfo(name = "totalCals")
    public int totalCals;

    @ColumnInfo(name = "pripadaOsebi")
    public int pripadaOsebi;

}