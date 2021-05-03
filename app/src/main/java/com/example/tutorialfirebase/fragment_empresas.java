package com.example.tutorialfirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registration.projectClass.RecyclerItemClickListener;
import com.example.tutorialfirebase.Clases.InfoEmpresa;
import com.example.tutorialfirebase.Clases.ListaEmpresasAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.example.tutorialfirebase.Clases.EmpresaViewHolder.EXTRA_OBJETO_EMPRESA;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_empresas #newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_empresas extends Fragment {
    private static final int PETICION1 = 1;
    private RecyclerView mRecyclerView;
    private ListaEmpresasAdapter mAdapter;
    private ArrayList<InfoEmpresa> infoEmpresas;
    private int pagina_actual;
    private int total_registros;
    private int total_paginas;
    private int num_columnas_landscape;

    private FirebaseFirestore db;
    private InfoEmpresa infoEmpresa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.fragment_empresas, container, false);

        /*---COGER ELEMENTOS DE LA BASE DE DATOS DE FIREBASE---*/
        db = FirebaseFirestore.getInstance();
        infoEmpresas = new ArrayList<InfoEmpresa>();
        mRecyclerView = (RecyclerView) vista.findViewById(R.id.rv_empresas);
        mAdapter = new ListaEmpresasAdapter(getActivity(), infoEmpresas);
        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        db.collection("businessdata").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.i("illo","Coge los datos de businessdata");
                if (task.isSuccessful()){
                    Log.i("illo","Tiene exito coger datos");
                    for (QueryDocumentSnapshot document : task.getResult()){
                        DocumentReference documentReference = db.collection("businessdata").document(document.getId()).collection("editinfoempresa").document("infoEmpresa");
                        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()){
                                    Log.i("illo","Tiene exito coger el documento infoEmpresa");
                                    DocumentSnapshot info = task.getResult();
                                    if (info.exists()){
                                        infoEmpresa = info.toObject(InfoEmpresa.class);
                                        mAdapter.addEmpresa(infoEmpresa);
                                        Log.i("illo","Añade empresas al ArrayList");
                                        //mAdapter.addEmpresa(infoEmpresa);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                infoEmpresa = mAdapter.getEmpresa(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(EXTRA_OBJETO_EMPRESA, infoEmpresa);
                Navigation.findNavController(vista).navigate(R.id.action_ir_a_productos_publicados,bundle);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        /*---FIN COGER ELEMENTOS FIREBASE---*/


//            int orientation = 1;*/
//            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            } else {
//                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), ConfiguracionesGenerales.LANDSCAPE_NUM_COLUMNAS));
//            }
//
//            // paginacion
//            mRecyclerView.addOnScrollListener(new PaginationListener((LinearLayoutManager) mRecyclerView.getLayoutManager()) {
//                private int empresas_leidas = 0;
//                @Override
//                protected void loadMoreItems() {
//
//                    int total_registros_leidos = mRecyclerView.getLayoutManager().getItemCount();
//                    if (total_registros_leidos < total_registros) {
//                        ArrayList<Empresa> nuevasEmpresas = EmpresaController.obtenerEmpresas(pagina_actual);
//                        empresas_leidas = nuevasEmpresas.size();
//                        pagina_actual++;
//
//                        Boolean resultado = mRecyclerView.post(new Runnable()
//                        {
//                            @Override
//                            public void run() {
//                                ListaEmpresasAdapter mAdapter1 =(ListaEmpresasAdapter) mRecyclerView.getAdapter();
//                                ArrayList<Empresa> empresas_rv = mAdapter1.getListaEmpresas();
//                                empresas_rv.addAll(nuevasEmpresas);
//                                mRecyclerView.getAdapter().notifyDataSetChanged();
//                            }});
//                        Log.i("sql", "siguiente pagina -> " + String.valueOf(pagina_actual));
//                        Log.i("sql", "total registros -> " + String.valueOf(total_registros));
//                        Log.i("sql", "total registros leidos -> " + String.valueOf(total_registros_leidos));
//                        Log.i("sql", "empresas leidas -> " + String.valueOf(this.empresas_leidas));
//
//                    }
//                    else{
//                        empresas_leidas = 0;
//                    }
//                }
//                @Override
//                public boolean isLastPage() {
//                    if(pagina_actual > total_paginas -1 ) {
//                        return true;
//                    }
//                    else{
//                        return false;
//                    }
//                }
//            });
//        }
//        else{
//            mostrarToast("no se pudo establecer la conexion con la base de datos");
//            Log.i("sql", "error en el sql");
//        }
        return vista;

}
    private void mostrarToast(String texto) {
        Toast.makeText(getActivity(),texto, Toast.LENGTH_SHORT).show();
    }
}