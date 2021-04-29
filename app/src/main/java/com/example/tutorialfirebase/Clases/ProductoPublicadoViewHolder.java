package com.example.tutorialfirebase.Clases;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialfirebase.R;

public class ProductoPublicadoViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public static final String EXTRA_OBJETO_PRODUCTO_PUBLICADO= "com.example.tutorialfirebase.ProductosPublicados";
    //  public static final String EXTRA_IMAGEN_PRODUCTO_PUBLICADO = "com.example.tutorialfirebase.ProductoPublicadoViewHolder.imagen_productoPublicado";

    public ImageView img_producto_publicado = null;
    public TextView txt_rv_marca_producto_publicado = null;
    public TextView txt_rv_modelo_producto_publicado = null;
    public TextView txt_rv_precio_producto_publicado = null;
    public TextView txt_rv_stock_producto_publicado = null;
    public TextView txt_rv_descripcion_producto_publicado = null;

    final com.example.tutorialfirebase.Clases.ListaProductosPublicadosAdapter productopAdapter;

    public ProductoPublicadoViewHolder(@NonNull View itemView, ListaProductosPublicadosAdapter mAdapter) {
        super(itemView);
        // img_producto_publicado = (ImageView)  itemView.findViewById(R.id.img_producto_publicado);
        txt_rv_marca_producto_publicado = (TextView)  itemView.findViewById(R.id.txt_rv_marca_producto_publicado);
        txt_rv_modelo_producto_publicado = (TextView)  itemView.findViewById(R.id.txt_rv_modelo_producto_publicado);
        txt_rv_precio_producto_publicado = (TextView)  itemView.findViewById(R.id.txt_rv_precio_producto_publicado);
        txt_rv_stock_producto_publicado = (TextView)  itemView.findViewById(R.id.txt_rv_stock_producto_publicado);
        txt_rv_descripcion_producto_publicado = (TextView)  itemView.findViewById(R.id.txt_rv_descripcion_producto_publicado);

        this.productopAdapter = mAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        ProductosPublicados productosPublicados = this.productopAdapter.getListaProductosPublicados().get(mPosition);
        productopAdapter.notifyDataSetChanged();

        ProductosPublicados producto_publicado_sin_image = new ProductosPublicados(productosPublicados.getIdproductoempresa(), productosPublicados.getCantidad(), productosPublicados.getPrecioventa(), productosPublicados.isHabilitado(), productosPublicados.isArchivado(), productosPublicados.getP(), productosPublicados.getE()); //,productosPublicados.getP().getId_foto());

        NavHostFragment navHostFragment = (NavHostFragment) ((FragmentActivity) productopAdapter.getC()).getSupportFragmentManager().findFragmentById(R.id.nav_host_contenedor_home);
        NavController navController = navHostFragment.getNavController();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_OBJETO_PRODUCTO_PUBLICADO, producto_publicado_sin_image);
        //navController.navigate(R.id.action_ir_a_productos_publicados, bundle);



        // Bitmap foto_empresa_png = empresa.getIdFoto();
       /* if(foto_empresa_png != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            foto_empresa_png.compress(Bitmap.CompressFormat.PNG, 0, baos);
            //  foto_empresa_png.compress(Bitmap.CompressFormat.JPEG, 0, baos);

            byte[] byteArrayfoto = baos.toByteArray();
            intent.putExtra(EXTRA_IMAGEN_EMPRESA, byteArrayfoto);
        }
        */

    }
}
