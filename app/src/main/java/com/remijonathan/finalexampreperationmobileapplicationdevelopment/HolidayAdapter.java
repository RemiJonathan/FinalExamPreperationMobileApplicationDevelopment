package com.remijonathan.finalexampreperationmobileapplicationdevelopment;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder> {
    private Context context;
    private Cursor cursor;

    public HolidayAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }

    //The ViewHolder is what takes care of building each individual
    // item based on the item layout created earlier
    public class HolidayViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText;
        public TextView dateText;

        //Treat this like the onCreate of a MainActivity
        //It will make the system know what elements it can
        //touch by adding it to these public variables
        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.text_view_holiday_name);
            dateText = itemView.findViewById(R.id.text_view_holiday_date);
        }
    }

    @NonNull
    @Override
    public HolidayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.holiday_item, parent, false);
        return new HolidayViewHolder(view);
    }

    //Use this to make sure the data is displayed inside our item
    @Override
    public void onBindViewHolder(@NonNull HolidayViewHolder holder, int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }

        String name = cursor.getString(cursor.getColumnIndex(ProjectContract.HolidaysEntry.COLUMN_NAME));
        String date = cursor.getString(cursor.getColumnIndex(ProjectContract.HolidaysEntry.COLUMN_DATE));

        holder.nameText.setText(name);
        holder.dateText.setText(date);
    }

    //return as many items as we have in our database,
    //if you want a specified limited amount you can set this here.
    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor cursor){
        if (cursor !=null) cursor.close();
        this.cursor = cursor;
        if (cursor != null) notifyDataSetChanged();
    }
}
