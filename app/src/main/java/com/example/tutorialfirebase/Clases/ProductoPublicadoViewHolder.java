package com.example.tutorialfirebase.Clases;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialfirebase.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductoPublicadoViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public static final String EXTRA_OBJETO_PRODUCTO_PUBLICADO = "com.example.tutorialfirebase.ProductosPublicados";
    //public static final String EXTRA_IMAGEN_PRODUCTO_PUBLICADO = "com.example.tutorialfirebase.ProductoPublicadoViewHolder.imagen_productoPublicado";

    public ListaProductosPublicadosAdapter listaProductosPublicadosAdapter;
    public CircleImageView imgProductoPublicado = null;
    public TextView txtMarcaProductoPublicado = null;
    public TextView txtModeloProductoPublicado = null;
    public TextView txtPrecioProductoPublicado = null;
    public TextView txtStockProductoPublicado = null;
    public TextView txtDescripciónProductoPublicado = null;

    public ProductoPublicadoViewHolder(@NonNull View itemView, ListaProductosPublicadosAdapter listaProductosPublicadosAdapter) {
        super(itemView);
        imgProductoPublicado = (CircleImageView)  itemView.findViewById(R.id.imgProductoPublicado);
        txtMarcaProductoPublicado = (TextView) itemView.findViewById(R.id.txtMarcaProductoPublicado);
        txtModeloProductoPublicado = (TextView) itemView.findViewById(R.id.txtModeloProductoPublicado);
        txtPrecioProductoPublicado = (TextView) itemView.findViewById(R.id.txtPrecioProductoPublicado);
        txtStockProductoPublicado = (TextView) itemView.findViewById(R.id.txtStockProductoPublicado);
        txtDescripciónProductoPublicado = (TextView) itemView.findViewById(R.id.txtDescripcionProductoPublicado);
        this.listaProductosPublicadosAdapter = listaProductosPublicadosAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int posición = getLayoutPosition();
        ProductosPublicados productosPublicados = this.listaProductosPublicadosAdapter.getListaProductosPublicados().get(posición);
        listaProductosPublicadosAdapter.notifyDataSetChanged();

        //Blob imagenProductoPublicado = productosPublicados.getP().getImagen();
        //Producto producto = new Producto(productosPublicados.getP().getCod_producto(), productosPublicados.getP().getCod_QR(), productosPublicados.getP().getMarca(), productosPublicados.getP().getModelo(), productosPublicados.getP().getDescripción(), imagenProductoPublicado);

        ProductosPublicados productoPublicado = new ProductosPublicados(productosPublicados.getIdproductoempresa(), productosPublicados.getCantidad(), productosPublicados.getPrecioventa(), productosPublicados.isHabilitado(), productosPublicados.isArchivado(), productosPublicados.getP(), productosPublicados.getE());

        NavHostFragment navHostFragment = (NavHostFragment) ((FragmentActivity) listaProductosPublicadosAdapter.getC()).getSupportFragmentManager().findFragmentById(R.id.nav_host_contenedor_home);
        NavController navController = navHostFragment.getNavController();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_OBJETO_PRODUCTO_PUBLICADO, productoPublicado);
        navController.navigate(R.id.action_ir_a_detalle_producto_publicado, bundle);
    }
}
