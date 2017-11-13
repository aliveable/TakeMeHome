package kmitl.proj.jittakan58070012.takemehomedemo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by 58070012 on 11/13/2017.
 */
@Entity(tableName = "IN_APP_INFO")
class InAppInfo {

    @PrimaryKey(autoGenerate = true)
    int id;

    String name;


}
