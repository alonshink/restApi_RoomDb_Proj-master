package il.ac.tcb.dbusers;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);
    @Query("DELETE FROM users")
    void deleteAll();

    @Delete
    void deleteUser(User user);


}
