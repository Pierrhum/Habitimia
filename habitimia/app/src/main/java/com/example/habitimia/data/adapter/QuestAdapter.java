package com.example.habitimia.data.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitimia.R;
import com.example.habitimia.data.model.Quest;
import com.example.habitimia.ui.MainActivity;
import com.example.habitimia.util.MyNotification;
import com.example.habitimia.util.Server;

import java.util.List;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.ViewHolder> {

    private List<Quest> questList;
    private MainActivity activity;

    public QuestAdapter(MainActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_quest_task, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestAdapter.ViewHolder holder, int position) {
        final Quest item = questList.get(position);
        int p = position;
        holder.task.setText(item.getName());
        holder.task.setChecked(false);
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Complete quest");
                    builder.setMessage("You have completed your quest?");
                    builder.setPositiveButton("Claim loot",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteItem(p);
//                                    activity.user = Server.updateUserHP(activity.user,Long.valueOf(item.getDifficulty().ordinal() ));
                                    MyNotification.createNotification(activity, getContext(),
                                            "Congrats! You have claimed "
                                            + item.getDifficulty().ordinal()  + " XP!");
//                                    notifyItemChanged(viewHolder.getAdapterPosition());
                                }
                            });
                    builder.setNegativeButton("No, still not completed", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            holder.task.setChecked(false);
//                            adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
//                    deleteItem(p);
//                    db.updateStatus(item.getId(), 1);
                } else {
                    System.out.println("not checked");
//                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questList.size();
    }

    public Context getContext() {
        return activity;
    }
    public MainActivity getActivity() {
        return activity;
    }

    public void setTasks(List<Quest> questList) {
        this.questList = questList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        Quest item = questList.get(position);
        Server.deleteQuest(item.getId().toString());
        questList.remove(position);
        notifyItemRemoved(position);
    }

//    public void editItem(int position) {
//        Quest item = questList.get(position);
//        Bundle bundle = new Bundle();
//        bundle.putInt("id", Integer.parseInt(item.getId().toString()));
//        bundle.putString("task", item.getName());
////        AddNewTask fragment = new AddNewTask();
////        fragment.setArguments(bundle);
////        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
//        notifyDataSetChanged();
//    }

    public Quest getItem(int position) {
        Quest item = questList.get(position);
        return item;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.questCheckBox);
        }
    }
}
