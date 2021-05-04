package com.example.tutorialfirebase.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialfirebase.Modelos.ConfiguraciónDB.ConfiguracionesGeneralesDB;
import com.example.tutorialfirebase.R;

import java.util.ArrayList;

import static com.example.tutorialfirebase.Utilidades.ImagenesBlobBitmap.blob_to_bitmap;

public class ListaProductosPublicadosAdapter extends RecyclerView.Adapter<ProductoPublicadoViewHolder> {
    private Context c;
    private ArrayList<ProductosPublicados> listaProductosPublicados;
    private LayoutInflater mInflater;
    private int página;

    public ListaProductosPublicadosAdapter(Context c, ArrayList<ProductosPublicados> listaProductosPublicados) {
        this.c = c;
        this.listaProductosPublicados = listaProductosPublicados;
        mInflater = LayoutInflater.from(c);
        this.página = 0;
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

    @NonNull
    @Override
    public ProductoPublicadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_producto_publicado, parent, false);
        return new ProductoPublicadoViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoPublicadoViewHolder holder, int position) {
        ProductosPublicados productoPublicadoActual = listaProductosPublicados.get(position);
        holder.txtMarcaProductoPublicado.setText(String.valueOf("Marca : " + productoPublicadoActual.getP().getMarca()));
        holder.txtModeloProductoPublicado.setText(String.valueOf("Modelo : " + productoPublicadoActual.getP().getModelo()));
        holder.txtPrecioProductoPublicado.setText(String.valueOf("Precio : " + productoPublicadoActual.getPrecioventa() + " €"));
        holder.txtStockProductoPublicado.setText(String.valueOf("Cantidad : " + productoPublicadoActual.getCantidad()));
        holder.txtDescripciónProductoPublicado.setText(String.valueOf("Descripción : " + productoPublicadoActual.getP().getDescripción()));
        if(productoPublicadoActual.getP().getImagen() == null){
            holder.imgProductoPublicado.setImageResource(R.drawable.producto);
        } else{
            holder.imgProductoPublicado.setImageBitmap(blob_to_bitmap(productoPublicadoActual.getP().getImagen(), ConfiguracionesGeneralesDB.ANCHO_FOTO, ConfiguracionesGeneralesDB.ALTO_FOTO));

        }
    }

    @Override
    public int getItemCount() {
        if (listaProductosPublicados != null) {
            return listaProductosPublicados.size();
        } else {
            return 0;
        }
    }
}