package com.example.tutorialfirebase.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialfirebase.R;

import java.util.ArrayList;

public class ListaProductosPublicadosAdapter extends RecyclerView.Adapter<ProductoPublicadoViewHolder> {

private Context c;
private ArrayList<ProductosPublicados> listaProductosPublicados;
private LayoutInflater mInflater;
private int pagina;

public int getPagina() {
        return pagina;
        }

public void setPagina(int pagina) {
        this.pagina = pagina;
        }

public ListaProductosPublicadosAdapter(Context c, ArrayList<ProductosPublicados> listaProductosPublicados) {
        this.c = c;
        this.listaProductosPublicados = listaProductosPublicados;
        mInflater = LayoutInflater.from(c);
        this.pagina = 0;
        }

@NonNull
@Override
public ProductoPublicadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_producto_publicado, parent, false);
        return new ProductoPublicadoViewHolder(mItemView, this);
        }

@Override
public void onBindViewHolder(@NonNull ProductoPublicadoViewHolder holder, int position) {
        ProductosPublicados productoPublicadoActual = listaProductosPublicados.get(position);
        holder.txt_rv_marca_producto_publicado.setText(String.valueOf("marca : " + productoPublicadoActual.getP().getMarca()));
        holder.txt_rv_modelo_producto_publicado.setText(String.valueOf("modelo : " + productoPublicadoActual.getP().getModelo()));
        holder.txt_rv_precio_producto_publicado.setText(String.valueOf("precio : " + productoPublicadoActual.getPrecioventa() + " €"));
        holder.txt_rv_stock_producto_publicado.setText(String.valueOf("Cantidad : " + productoPublicadoActual.getCantidad()));
        holder.txt_rv_descripcion_producto_publicado.setText(String.valueOf("descripcion : " + productoPublicadoActual.getP().getDescripción()));
     /* if(productoPublicadoActual.getIdFoto() != null){
            holder.img_empresa.setImageBitmap(productoPublicadoActual.getIdFoto());
        }
        else{
            holder.img_empresa.setImageResource(R.drawable.empresa);
        }*/
        }

@Override
public int getItemCount() {
        if (listaProductosPublicados != null) {
        return listaProductosPublicados.size();
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

public ArrayList<ProductosPublicados> getListaProductosPublicados() {
        return listaProductosPublicados;
        }

public void setListaProductosPublicados(ArrayList<ProductosPublicados> listaProductosPublicados) {
        this.listaProductosPublicados = listaProductosPublicados;
        }

        }