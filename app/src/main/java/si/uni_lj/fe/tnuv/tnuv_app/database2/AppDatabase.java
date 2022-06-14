package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import si.uni_lj.fe.tnuv.tnuv_app.database2.WorkoutVajeDAO;

@Database(entities = {VajaEntity.class, WorkoutEntity.class, CrossRefVajaWorkout.class, DetailsEntity.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VajeDAO vajeDao();
    public abstract WorkoutDAO workoutDAO();
    public abstract WorkoutVajeDAO workoutVajeDAO();
    public abstract DetailsDAO detailsDAO();
}
