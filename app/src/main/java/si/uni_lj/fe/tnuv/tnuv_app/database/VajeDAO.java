package si.uni_lj.fe.tnuv.tnuv_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface VajeDAO {

        @Query("SELECT * FROM vaje")
        List<VajaEntity> getAll();

        @Query("SELECT * FROM vaje WHERE id IN (:userIds)")
        List<VajaEntity> loadAllByIds(int[] userIds);

        @Insert
        void insert(VajaEntity users);

        @Delete
        void delete(VajaEntity user);
    }

