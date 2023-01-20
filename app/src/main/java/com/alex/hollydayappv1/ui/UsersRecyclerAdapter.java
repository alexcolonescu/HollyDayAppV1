package com.alex.hollydayappv1.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alex.hollydayappv1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<UsersItem> usersItemArrayList;
    DatabaseReference databaseReference;

    public UsersRecyclerAdapter(Context context, ArrayList<UsersItem> usersItemArrayList) {
        this.context = context;
        this.usersItemArrayList = usersItemArrayList;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UsersItem holidays = usersItemArrayList.get(position);

        holder.textHoliday.setText("Holiday: " + holidays.getHolidayName());
        holder.textDestination.setText("Destination: " + holidays.getHolidayDestination());
        holder.textCountry.setText("Country: " + holidays.getHolidayCountry());

        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        ViewDialogUpdate viewDialogUpdate = new ViewDialogUpdate();
                        viewDialogUpdate.showDialog(context, holidays.getHolidayID(), holidays.getHolidayName(), holidays.getHolidayDestination(), holidays.getHolidayCountry());


            }
        });
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialogConfirmDelete viewDialogConfirmDelete = new ViewDialogConfirmDelete();
                viewDialogConfirmDelete.showDialog(context, holidays.getHolidayID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textHoliday;
        TextView textDestination;
        TextView textCountry;

        Button buttonDelete;
        Button buttonUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textHoliday = itemView.findViewById(R.id.textHoliday);
            textDestination = itemView.findViewById(R.id.textDestination);
            textCountry = itemView.findViewById(R.id.textCountry);


            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonUpdate = itemView.findViewById(R.id.buttonUpdate);
        }
    }

    public class ViewDialogUpdate {
        public void showDialog(Context context, String id, String holiday, String destination, String country) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert_dialog_add_new_user);

            EditText textHoliday = dialog.findViewById(R.id.textHoliday);
            EditText textDestination = dialog.findViewById(R.id.textDestination);
            EditText textCountry = dialog.findViewById(R.id.textCountry);

            textHoliday.setText(holiday);
            textDestination.setText(destination);
            textCountry.setText(country);


            Button buttonUpdate = dialog.findViewById(R.id.buttonUpdate);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);

            buttonUpdate.setText("UPDATE");

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();
                }
            });

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String newHoliday = textHoliday.getText().toString();
                    String newDestination = textDestination.getText().toString();
                    String newCountry = textCountry.getText().toString();

                    if (holiday.isEmpty() || destination.isEmpty() || country.isEmpty()) {
                        Toast.makeText(context, "Please Enter All data...", Toast.LENGTH_SHORT).show();
                    } else {
                        if (newHoliday.equals(holiday) && newDestination.equals(destination) && newCountry.equals(country)) {
                            Toast.makeText(context, "This are no change", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("Holiday").child(id).setValue(new UsersItem(id, holiday, destination, country));
                            Toast.makeText(context, "Holiday Update successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }
    }

    public class ViewDialogConfirmDelete {
        public void showDialog(Context context, String id) {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.view_dialog_confirm_delete);


            Button buttonDelete = dialog.findViewById(R.id.buttonDelete);
            Button buttonCancel = dialog.findViewById(R.id.buttonCancel);


            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference.child("Holiday").child(id).removeValue();
                    Toast.makeText(context, "User Deleted successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }
}
