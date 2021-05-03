package com.example.tutorialfirebase;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialfirebase.Clases.ConfiguracionesGenerales;
import com.example.tutorialfirebase.Clases.EmpresaViewHolder;
import com.example.tutorialfirebase.Clases.InfoEmpresa;
import com.example.tutorialfirebase.Clases.ListaProductosPublicadosAdapter;
import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Modelos.ConfiguraciónDB.ConfiguracionesGeneralesDB;
import com.example.tutorialfirebase.Modelos.ProductosPublicadosDB;
import com.example.tutorialfirebase.Utilidades.PaginationListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_producto_publicado #newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_producto_publicado extends Fragment {
    private Button btAtras = null;
    private RecyclerView rvProductosPublicados;
    private ArrayList<ProductosPublicados> productosPublicados;
    private ListaProductosPublicadosAdapter listaProductosPublicadosAdapter = null;
    private int páginaActual;
    private int totalRegistros;
    private int totalPáginas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_producto_publicado, container, false);

        //BOTÓN IR ATRÁS
        btAtras = (Button) vista.findViewById(R.id.btn_Atras);
        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(fragment_producto_publicado.this);
                navController.popBackStack();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        //RECYCLER VIEW CON LOS PRODUCTOS
        totalRegistros = ProductosPublicadosDB.obtenerCantidadProductosPublicados();
        totalPáginas = (totalRegistros / ConfiguracionesGeneralesDB.ELEMENTOS_POR_PAGINA) + 1;

        Log.i("SQL", "Total de registros -> " + String.valueOf(totalRegistros));
        Log.i("SQL", "Total de páginas -> " + String.valueOf(totalPáginas));

        InfoEmpresa infoEmpresa = (InfoEmpresa)getArguments().getSerializable(EmpresaViewHolder.EXTRA_OBJETO_EMPRESA);
        String codEmpresa = infoEmpresa.getCod_empresa();

        páginaActual = 0;
        productosPublicados = ProductosPublicadosDB.obtenerProductosPublicadosPorEmpresa(páginaActual, codEmpresa);
        páginaActual = páginaActual + 1;
        if(productosPublicados != null) {
            Log.i("SQL", "Página actual -> " + String.valueOf(páginaActual));
            Log.i("SQL", "Productos publicados leídos -> " + String.valueOf(this.productosPublicados.size()));
            rvProductosPublicados = vista.findViewById(R.id.rvProductosPublicados);
            listaProductosPublicadosAdapter = new ListaProductosPublicadosAdapter(getActivity(), productosPublicados);
            rvProductosPublicados.setAdapter(listaProductosPublicadosAdapter);
            int orientation = 1;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                rvProductosPublicados.setLayoutManager(new LinearLayoutManager(getActivity()));
            } else {
                rvProductosPublicados.setLayoutManager(new GridLayoutManager(getActivity(), ConfiguracionesGenerales.LANDSCAPE_NUM_COLUMNAS));
            }

            //PAGINACIÓN
            rvProductosPublicados.addOnScrollListener(new PaginationListener((LinearLayoutManager) rvProductosPublicados.getLayoutManager()) {
                private int productosPublicadosLeídos = 0;

                @Override
                protected void loadMoreItems() {
                    int totalRegistrosLeídos = rvProductosPublicados.getLayoutManager().getItemCount();
                    if (totalRegistrosLeídos < totalRegistros) {
                        ArrayList<ProductosPublicados> nuevosProductosPublicados = ProductosPublicadosDB.obtenerProductosPublicadosPorEmpresa(páginaActual, codEmpresa);
                        productosPublicadosLeídos = nuevosProductosPublicados.size();
                        páginaActual++;

                        Boolean resultado = rvProductosPublicados.post(new Runnable() {
                            @Override
                            public void run() {
                                ListaProductosPublicadosAdapter listaProductosPublicadosAdapter1 = (ListaProductosPublicadosAdapter) rvProductosPublicados.getAdapter();
                                ArrayList<ProductosPublicados> productosPublicadosRv = listaProductosPublicadosAdapter1.getListaProductosPublicados();
                                productosPublicadosRv.addAll(nuevosProductosPublicados);
                                rvProductosPublicados.getAdapter().notifyDataSetChanged();
                            }});
                        Log.i("SQL", "Siguiente página -> " + String.valueOf(páginaActual));
                        Log.i("SQL", "Total registros -> " + String.valueOf(totalRegistros));
                        Log.i("SQL", "Total registros leídos -> " + String.valueOf(totalRegistrosLeídos));
                    }
                    else{
                        productosPublicadosLeídos = 0;
                    }
                }
                @Override
                public boolean isLastPage() {
                    if(páginaActual > totalPáginas - 1) {
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            });
        }
        else{
            Log.i("SQL", "Error en el modelo");
        }
        return vista;
    }
}