package com.example.tutorialfirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tutorialfirebase.Clases.ProductoPublicadoViewHolder;
import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Modelos.ConfiguraciónDB.ConfiguracionesGeneralesDB;

import java.sql.Blob;

import static com.example.tutorialfirebase.Utilidades.ImagenesBlobBitmap.blob_to_bitmap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_detalle_producto_publicado #newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_detalle_producto_publicado extends Fragment {
    private Button btAtras;

    private ImageView imgDetalleProductoPublicado;
    private TextView txtCantidadDetalleProductoPublicado;
    private TextView txtPrecioDetalleProductoPublicado;
    private TextView txtMarcaDetalleProductoPublicado;
    private TextView txtModeloDetalleProductoPublicado;
    private TextView txtDescripciónDetalleProductoPublicado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_detalle_producto_publicado, container, false);

        //BOTÓN IR ATRÁS
        btAtras = (Button) vista.findViewById(R.id.btn_Atras);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(fragment_detalle_producto_publicado.this);
                navController.popBackStack();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        //DETALLES PRODUCTO PUBLICADO
        imgDetalleProductoPublicado = (ImageView) vista.findViewById(R.id.imgDetalleProductoPublicado);
        txtCantidadDetalleProductoPublicado = (TextView) vista.findViewById(R.id.txtCantidadDetalleProductoPublicado);
        txtPrecioDetalleProductoPublicado = (TextView) vista.findViewById(R.id.txtPrecioDetalleProductoPublicado);
        txtMarcaDetalleProductoPublicado = (TextView) vista.findViewById(R.id.txtMarcaDetalleProductoPublicado);
        txtModeloDetalleProductoPublicado = (TextView) vista.findViewById(R.id.txtModeloDetalleProductoPublicado);
        txtDescripciónDetalleProductoPublicado = (TextView) vista.findViewById(R.id.txtDescripciónDetalleProductoPublicado);

        ProductosPublicados productoPublicado = (ProductosPublicados) getArguments().getSerializable(ProductoPublicadoViewHolder.EXTRA_OBJETO_PRODUCTO_PUBLICADO);
            Blob imagen= productoPublicado.getP().getImagen();
        if (productoPublicado != null){
            if(imagen == null){
                imgDetalleProductoPublicado.setImageResource(R.drawable.producto);
            } else{
                imgDetalleProductoPublicado.setImageBitmap(blob_to_bitmap(imagen, ConfiguracionesGeneralesDB.ANCHO_FOTO, ConfiguracionesGeneralesDB.ALTO_FOTO));

            }
            //imgDetalleProductoPublicado.setImageBitmap(blob_to_bitmap(productoPublicado.getP().getImagen(), ConfiguracionesGeneralesDB.ANCHO_FOTO, ConfiguracionesGeneralesDB.ALTO_FOTO));
            txtCantidadDetalleProductoPublicado.setText(String.valueOf(productoPublicado.getCantidad()) + " unidades");
            txtPrecioDetalleProductoPublicado.setText(String.valueOf(productoPublicado.getPrecioventa() + "€"));
            txtMarcaDetalleProductoPublicado.setText(String.valueOf(productoPublicado.getP().getMarca()));
            txtModeloDetalleProductoPublicado.setText(String.valueOf(productoPublicado.getP().getModelo()));
            String descripción = String.valueOf(productoPublicado.getP().getDescripción());
            if (descripción.length() > 11){
                txtDescripciónDetalleProductoPublicado.setText(descripción.substring(0, 10) + "...");
            }else {
                txtDescripciónDetalleProductoPublicado.setText(descripción);
            }
        }else {
            Log.i("ERROR", "El producto publicado no se ha recibido del fragment anterior correctamente");
        }

        return vista;
    }
}