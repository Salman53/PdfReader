package com.example.pdfreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.util.ArrayList;

public class PdfAdapter extends ArrayAdapter<File> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<File> al_pdf;

    public PdfAdapter(@NonNull Context context, ArrayList<File> al_pdf) {
        super(context, R.layout.pdf_adapter , al_pdf);
        this.context = context;
        this.viewHolder = viewHolder;
        this.al_pdf = al_pdf;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if (al_pdf.size()>0)
        {
            return al_pdf.size();
        }
        return 1;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pdf_adapter , parent , false);
            viewHolder = new ViewHolder();

            viewHolder.tv_filename = convertView.findViewById(R.id.id_adapter_tv);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder =(ViewHolder) convertView.getTag();
        }

        viewHolder.tv_filename.setText(al_pdf.get(position).getName());
        return convertView;

    }

    public class ViewHolder{
        TextView tv_filename;
    }


}
