package com.example.tutorialfirebase.Clases;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tutorialfirebase.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmpresaViewHolder extends RecyclerView.ViewHolder {

    public static final String EXTRA_OBJETO_EMPRESA= "com.example.tutorialfirebase.Empresa";
  //  public static final String EXTRA_IMAGEN_EMPRESA = "com.example.tutorialfirebase.EmpresaViewHolder.imagen_empresa";

    public TextView txt_rv_nombre_empresa = null;
    public TextView txt_rv_sector_empresa = null;
    public TextView txt_rv_resumen_empresa = null;
    public TextView txt_rv_direccion_empresa;
    public CircleImageView img_empresa = null;

    final com.example.tutorialfirebase.Clases.ListaEmpresasAdapter lcAdapter;

    public EmpresaViewHolder(@NonNull View itemView, ListaEmpresasAdapter mAdapter) {
        super(itemView);
        txt_rv_nombre_empresa = (TextView)  itemView.findViewById(R.id.txt_rv_nombre_empresa);
        txt_rv_sector_empresa = (TextView)  itemView.findViewById(R.id.txt_rv_sector_empresa);
        txt_rv_resumen_empresa = (TextView)  itemView.findViewById(R.id.txt_rv_resumen_empresa);
        txt_rv_direccion_empresa = (TextView) itemView.findViewById(R.id.txt_rv_direccion_empresa);
        img_empresa = (CircleImageView) itemView.findViewById(R.id.img_empresa);
        this.lcAdapter = mAdapter;
    }

}

