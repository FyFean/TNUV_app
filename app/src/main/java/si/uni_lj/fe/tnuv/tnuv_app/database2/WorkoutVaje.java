package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class WorkoutVaje {
    @Embedded public WorkoutEntity workoutEntity;
    @Relation(
            parentColumn = "idWorkouta",
            entityColumn = "idVaje",
            associateBy = @Junction(CrossRefVajaWorkout.class)
    )
    public List<VajaEntity> vajaEntityList;
}