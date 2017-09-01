package nossafirma.com.br.meuapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import nossafirma.com.br.meuapp.R;
import nossafirma.com.br.meuapp.infra.StoreViewHolder;
import nossafirma.com.br.meuapp.model.Store;

/**
 * Created by Rodrigo on 30/08/2017.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private List<Store> _stores;
    private Context _context;

    public class StoreViewHolder extends RecyclerView.ViewHolder {

        public TextView storeName;
        public Spinner regions;
        public Spinner beers;
        public TextView value;

        public StoreViewHolder(View view) {
            super(view);
            storeName = (TextView) view.findViewById(R.id.etStoreName);
            regions = (Spinner) view.findViewById(R.id.spRegions);
            beers = (Spinner) view.findViewById(R.id.spBeers);
            value = (TextView) view.findViewById(R.id.etValue);
        }
    }

    public StoreAdapter(List<Store> stores, Context context) {
        this._stores = stores;
        this._context = context;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(_context).inflate(R.layout.fragment_list_stores, parent, false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder viewHolder, int position) {
        Store store = this._stores.get(position);

// Erro aconteceu aqui.
        viewHolder.storeName.setText(store.getName());
        viewHolder.value.setText(store.getValue().toString());

    }

    @Override
    public int getItemCount() {
        return _stores.size();
    }
}
