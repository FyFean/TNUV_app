package si.uni_lj.fe.tnuv.tnuv_app.database2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import si.uni_lj.fe.tnuv.tnuv_app.database2.VajaEntity;

@Dao
public interface VajeDAO {

        @Query("SELECT * FROM VajaEntity")
        List<VajaEntity> getAll();

        @Insert
        void insert(VajaEntity vaje);

        @Delete
        void delete(VajaEntity vaje);

    }

