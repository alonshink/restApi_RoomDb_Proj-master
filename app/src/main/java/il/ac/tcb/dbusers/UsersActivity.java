package il.ac.tcb.dbusers;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import il.ac.tcb.dbusers.databinding.UserActivityBinding;

public class UsersActivity extends AppCompatActivity {
    UserActivityBinding binding;
    UserDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = UserActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        );
        db = UserDB.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<User> users=db.usersDao().getAll();
           UserRecyclerViewAdapter recyclerViewAdapter = new UserRecyclerViewAdapter(users,db.usersDao());
           binding.recyclerView.setAdapter(recyclerViewAdapter);

        }
    }



