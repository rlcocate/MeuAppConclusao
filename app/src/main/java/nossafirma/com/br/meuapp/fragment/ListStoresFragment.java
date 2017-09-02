package nossafirma.com.br.meuapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nossafirma.com.br.meuapp.R;
import nossafirma.com.br.meuapp.adapter.StoreAdapter;
import nossafirma.com.br.meuapp.model.Beer;
import nossafirma.com.br.meuapp.model.Region;
import nossafirma.com.br.meuapp.model.Store;
import nossafirma.com.br.meuapp.sqlite.StoreDAO;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListStoresFragment extends Fragment {

    private Context _context;

    public ListStoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.fragment_list_stores, container, false);

        // Obtem contexto do container (Activity).
        _context = container.getContext();

        // Bind do RecyclerView.
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvStores);

        List<Store> stores;

        try {

            // Recupera Lista das Stores.
            stores = new StoreDAO(_context).getAll();

//        List<Store> stores = new ArrayList<Store>();
//
//        Store store = new Store();
//        store.setName("Extra");
//        store.setBeer(new Beer(1, "Brahma"));
//        store.setRegion(new Region(1, "ZS", "Zona Sul"));
//        store.setValue(1.5);

//        stores.add(store);

            // Define adapter para carregar informações.
            recyclerView.setAdapter(new StoreAdapter(stores, _context));

            RecyclerView.LayoutManager layout = new LinearLayoutManager(_context, LinearLayoutManager.VERTICAL, false);

            recyclerView.setLayoutManager(layout);

            return view;
        } catch (Exception e) {
            Log.d("RV", e.getMessage());
        } finally {
            return view;
        }
    }

}
