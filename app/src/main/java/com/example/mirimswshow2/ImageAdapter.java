package com.example.mirimswshow2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 수현 on 2017-11-10.
 */

public class ImageAdapter extends ArrayAdapter<String> {
    ImageAdapter(Context context, ArrayList<String> items){
        super(context, R.layout.list_item, items);
    }
    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        LayoutInflater imageInflater = LayoutInflater.from(getContext());
        View view = imageInflater.inflate(R.layout.list_item, parent, false);
        String item = getItem(position);
        String titleItem=item;
        String dateItem=item;
        String textItem=item;
        titleItem=item.substring(0,item.indexOf("\n"));
        dateItem=item.substring(item.indexOf("\n")+1,item.indexOf("\0"));
        textItem=item.substring(item.indexOf("\0")+1);
        TextView titleView = (TextView)view.findViewById(R.id.listTitleView);
        TextView textView = (TextView)view.findViewById(R.id.listTextView);
        TextView dateView = (TextView)view.findViewById(R.id.listDateView);
//        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        titleView.setText(titleItem);
        dateView.setText(dateItem);
        textView.setText(textItem);
//        imageView.setImageResource(R.drawable.round_button);
        return view;
    }
}