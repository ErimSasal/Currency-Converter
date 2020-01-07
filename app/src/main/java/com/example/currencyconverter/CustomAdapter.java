package com.example.currencyconverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String> {
    Context context;
    String[] names;
    int[] images;

    public CustomAdapter(Context context, String[] names, int[] images){
        super(context,R.layout.currency_spinner_row);
        this.context=context;
        this.names = names;
        this.images = images;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.currency_spinner_row,null);
        TextView t1 = row.findViewById(R.id.currency_name);
        ImageView i1 = row.findViewById(R.id.currency_flag);

        t1.setText(names[position]);
        i1.setImageResource(images[position]);
        return row;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.currency_spinner_row,null);
        TextView t1 = row.findViewById(R.id.currency_name);
        ImageView i1 = row.findViewById(R.id.currency_flag);

        t1.setText(names[position]);
        i1.setImageResource(images[position]);
        return row;
    }
}
