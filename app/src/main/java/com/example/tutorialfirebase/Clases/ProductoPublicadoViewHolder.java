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
    public static final String EXTRA_OBJETO_PRODUCTO_PUBLICADO= "com.example.tutorialfirebase.ProductosPublicados";
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
        //imgProductoPublicado = (CircleImageView)  itemView.findViewById(R.id.img_producto_publicado);
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

        // Bitmap foto_empresa_png = empresa.getIdFoto();
        /* if(foto_empresa_png != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            foto_empresa_png.compress(Bitmap.CompressFormat.PNG, 0, baos);
            //foto_empresa_png.compress(Bitmap.CompressFormat.JPEG, 0, baos);

            byte[] byteArrayfoto = baos.toByteArray();
            intent.putExtra(EXTRA_IMAGEN_EMPRESA, byteArrayfoto);
        }
        */
        ProductosPublicados producto_publicado_sin_imagen = new ProductosPublicados(productosPublicados.getIdproductoempresa(), productosPublicados.getCantidad(), productosPublicados.getPrecioventa(), productosPublicados.isHabilitado(), productosPublicados.isArchivado(), productosPublicados.getP(), productosPublicados.getE());

        NavHostFragment navHostFragment = (NavHostFragment) ((FragmentActivity) listaProductosPublicadosAdapter.getC()).getSupportFragmentManager().findFragmentById(R.id.nav_host_contenedor_home);
        NavController navController = navHostFragment.getNavController();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_OBJETO_PRODUCTO_PUBLICADO, producto_publicado_sin_imagen);
        //navController.navigate(R.id.action_ir_a_productos_publicados, bundle);
    }
}
