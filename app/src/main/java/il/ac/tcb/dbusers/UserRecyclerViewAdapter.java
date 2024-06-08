package il.ac.tcb.dbusers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import il.ac.tcb.dbusers.databinding.UserViewHolderBinding;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final List<User> dataSet;
    private final UserDao userDao;

    public UserRecyclerViewAdapter(@NonNull List<User> dataSet, UserDao userDao) {
        this.dataSet = dataSet;
        this.userDao = userDao;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UserViewHolderBinding binding = UserViewHolderBinding.inflate(layoutInflater, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = dataSet.get(position);
        Glide.with(holder.itemView.getContext())
                .load(user.picture)
                .apply(new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.error))
                .into(holder.binding.imageViewUserIcon);
        holder.binding.textViewUserFirstName.setText(user.firstName);
        holder.binding.textViewUserLastName.setText(user.lastName);
        holder.binding.textViewUserCountry.setText(user.country);
        holder.binding.textViewUserCity.setText(user.city);

        holder.binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    User userToDelete = dataSet.get(position);
                    deleteUserFromDatabase(userToDelete);
                    dataSet.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, dataSet.size());
                }
            }
        });
    }

    private void deleteUserFromDatabase(User user) {
        // Execute the deletion on a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.deleteUser(user);
            }
        }).start();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
