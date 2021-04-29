package com.example.tutorialfirebase.Clases;

import android.content.Context;
import android.icu.text.IDNA;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialfirebase.R;

import java.util.ArrayList;

public class ListaEmpresasAdapter extends RecyclerView.Adapter<EmpresaViewHolder> {

    private Context c;
    private ArrayList<Empresa> listaEmpresas;
    private ArrayList<InfoEmpresa> listaInfoEmpresa;
    private LayoutInflater mInflater;
    private int pagina;

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public ListaEmpresasAdapter(Context c, ArrayList<InfoEmpresa> listaInfoEmpresas) {
        this.c = c;
        this.listaInfoEmpresa = listaInfoEmpresas;
        mInflater = LayoutInflater.from(c);
        this.pagina = 0;
    }

    @NonNull
    @Override
    public EmpresaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_empresa, parent, false);
        return new EmpresaViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpresaViewHolder holder, int position) {
        InfoEmpresa empresaActual = listaInfoEmpresa.get(position);
        /*
        * CircleImageView
        * Nombre
        * Sector
        * Resumen
        * Direccion
        */
        holder.txt_rv_nombre_empresa.setText(String.valueOf("cod_empresa : " + empresaActual.getNombre()));
        holder.txt_rv_sector_empresa.setText(String.valueOf("clave_empr : " + empresaActual.getSector()));
        holder.txt_rv_resumen_empresa.setText(String.valueOf("datos_empr : " + empresaActual.getResumen()));
        holder.txt_rv_direccion_empresa.setText("Dirección: "+ empresaActual.getDireccion());
      /*if(empresaActual.getLogoURL() != null){
            //holder.img_empresa.setImageBitmap(empresaActual.getIdFoto());
        }
        else{
            holder.img_empresa.setImageResource(R.drawable.empresa);
        }*/
    }

    @Override
    public int getItemCount() {
        if (listaEmpresas != null) {
            return listaEmpresas.size();
        } else {
            return 0;
        }
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(ArrayList<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public ArrayList<InfoEmpresa> getListaInfoEmpresas(){return listaInfoEmpresa;}

    public void addEmpresa(InfoEmpresa b){
        listaInfoEmpresa.add(b);
        notifyItemInserted(listaInfoEmpresa.size());
    }

    public InfoEmpresa getEmpresa(int i){
        InfoEmpresa infoEmpresa = listaInfoEmpresa.get(i);
        return infoEmpresa;
    }

}