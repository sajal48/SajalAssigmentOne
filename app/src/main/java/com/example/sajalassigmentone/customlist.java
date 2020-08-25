package com.example.sajalassigmentone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class customlist extends ArrayAdapter<String> {

    private final Activity context;
    private  final String[] company;
    private final Integer[] img;

    public customlist(Activity context,String[] company,Integer[] img)
    {
        super(context,R.layout.listdesign,company);
        this.context=context;
        this.company=company;
        this.img=img;
    }



    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View row = inflater.inflate(R.layout.listdesign, null, true);
        TextView textView = row.findViewById(R.id.company_name);
        ImageView imageView = row.findViewById(R.id.logo);
        Log.i("Custom","Working");

        textView.setText(company[position]);
        imageView.setImageResource(img[position]);
        return row;
    }
}
