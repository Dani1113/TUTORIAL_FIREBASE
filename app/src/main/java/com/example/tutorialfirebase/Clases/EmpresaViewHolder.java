package com.example.tutorialfirebase.Clases;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tutorialfirebase.Utilidades.ImagenesBlobBitmap;


import com.example.tutorialfirebase.MostrarDetalleEmpresaActivity;
import com.example.tutorialfirebase.R;

import java.io.ByteArrayOutputStream;

public class EmpresaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_OBJETO_EMPRESA= "com.example.tutorialfirebase.Empresa";
  //  public static final String EXTRA_IMAGEN_EMPRESA = "com.example.tutorialfirebase.EmpresaViewHolder.imagen_empresa";

    public TextView txt_rv_cod_empresa = null;
    public TextView txt_rv_clave_empresa = null;
    public TextView txt_rv_datos_empresa = null;
    public ImageView img_empresa = null;

    final com.example.tutorialfirebase.Clases.ListaEmpresasAdapter lcAdapter;

    public EmpresaViewHolder(@NonNull View itemView, ListaEmpresasAdapter mAdapter) {
        super(itemView);
        txt_rv_cod_empresa = (TextView)  itemView.findViewById(R.id.txt_rv_cod_empresa);
        txt_rv_clave_empresa = (TextView)  itemView.findViewById(R.id.txt_rv_clave_empresa);
        txt_rv_datos_empresa = (TextView)  itemView.findViewById(R.id.txt_rv_datos_empresa);
       // img_empresa = (ImageView)  itemView.findViewById(R.id.img_empresa);
        this.lcAdapter = mAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Empresa empresa;
        empresa = this.lcAdapter.getListaEmpresas().get(mPosition);
        lcAdapter.notifyDataSetChanged();
        Intent intent = new Intent(lcAdapter.getC(), MostrarDetalleEmpresaActivity.class);
        Empresa empresa_sin_imagen = new Empresa(empresa.getCod_empresa(), empresa.getClave_empr(), empresa.getDatos_empr());//, empresa.getIdFoto());
        NavHostFragment navHostFragment = (NavHostFragment)((FragmentActivity)lcAdapter.getC()).getSupportFragmentManager().findFragmentById(R.id.navegador_menu);
        NavController navController1 = navHostFragment.getNavController();
        navController1.navigate(R.id.action_ir_a_productos_empresa);
        //intent.putExtra(EXTRA_OBJETO_EMPRESA, (Parcelable) empresa_sin_imagen);
       // Bitmap foto_empresa_png = empresa.getIdFoto();
       /* if(foto_empresa_png != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            foto_empresa_png.compress(Bitmap.CompressFormat.PNG, 0, baos);
            //  foto_empresa_png.compress(Bitmap.CompressFormat.JPEG, 0, baos);

            byte[] byteArrayfoto = baos.toByteArray();
            intent.putExtra(EXTRA_IMAGEN_EMPRESA, byteArrayfoto);
        }
        */
        lcAdapter.getC().startActivity(intent);
    }
}

