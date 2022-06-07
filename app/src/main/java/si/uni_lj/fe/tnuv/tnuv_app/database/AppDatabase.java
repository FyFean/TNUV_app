package si.uni_lj.fe.tnuv.tnuv_app.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {VajaEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract VajeDAO vajeDao();
}
