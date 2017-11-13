package kmitl.proj.jittakan58070012.takemehomedemo;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by 58070012 on 11/13/2017.
 */
@Database(entities = {InAppInfo.class}, version = 1)
public abstract class InAppInfoDB extends RoomDatabase {
    public abstract InAppInfoDAO appInfoDAO();
}
