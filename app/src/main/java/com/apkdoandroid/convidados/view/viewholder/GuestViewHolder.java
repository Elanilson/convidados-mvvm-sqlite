package com.apkdoandroid.convidados.view.viewholder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.convidados.R;
import com.apkdoandroid.convidados.model.GuestModel;
import com.apkdoandroid.convidados.view.listener.OnlistClick;

public class GuestViewHolder  extends RecyclerView.ViewHolder {

    private TextView textNome;
    private Context context;

    public GuestViewHolder(@NonNull View itemView) {
        super(itemView);
        textNome = itemView.findViewById(R.id.textView4Nome);
        context = itemView.getContext();

    }
    public void bind(GuestModel guestModel, OnlistClick  onlistClick){
        textNome.setText(guestModel.getNome());

        textNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlistClick.onclick(guestModel.getId());
            }
        });

        textNome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Remoção de convidado")
                        .setMessage("Deseja realmente remover ?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onlistClick.onDelete(guestModel.getId());
                            }
                        })
                        .setNeutralButton("Não",null)
                        .show();



                return false;
            }
        });
    }
}
