package com.example.tutorialfirebase.Utilidades;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorialfirebase.Clases.ConfiguracionesGenerales;

public class PaginationListener extends RecyclerView.OnScrollListener{

    public static final int PAGE_START=0;

    @NonNull
    private LinearLayoutManager layoutManager;

    private static final int PAGE_SIZE=ConfiguracionesGenerales.ELEMENTOS_POR_PAGINA;

    public PaginationListener(@NonNull LinearLayoutManager layoutManager){
        this.layoutManager=layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView,int dx,int dy){
        super.onScrolled(recyclerView,dx,dy);
        int visibleItemCount=layoutManager.getChildCount();
        int totalItemCount=layoutManager.getItemCount();
        int firstVisibleItemPosition=layoutManager.findFirstVisibleItemPosition();
        if(!isLastPage()){
            if((visibleItemCount+firstVisibleItemPosition)>=totalItemCount &&firstVisibleItemPosition>=0 &&totalItemCount>=PAGE_SIZE){
                loadMoreItems();
            }
        }
    }

    protected void loadMoreItems() {

    }

    public boolean isLastPage() {
        return false;
    }
}
