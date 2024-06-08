package il.ac.tcb.dbusers;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import il.ac.tcb.dbusers.databinding.UserActivityBinding;
import il.ac.tcb.dbusers.databinding.UserViewHolderBinding;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public UserViewHolderBinding binding;

    public UserViewHolder(@NonNull UserViewHolderBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }
}