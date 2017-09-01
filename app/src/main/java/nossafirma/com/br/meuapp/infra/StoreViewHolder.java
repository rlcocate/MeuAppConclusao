package nossafirma.com.br.meuapp.infra;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import nossafirma.com.br.meuapp.R;

/**
 * Created by Rodrigo on 30/08/2017.
 */

public class StoreViewHolder extends RecyclerView.ViewHolder {

    final EditText storeName;
    final Spinner regions;
    final Spinner beers;
    final EditText value;

    public StoreViewHolder(View view) {
        super(view);

        storeName = (EditText) view.findViewById(R.id.etStoreName);
        regions = (Spinner) view.findViewById(R.id.spRegions);
        beers = (Spinner) view.findViewById(R.id.spBeers);
        value = (EditText) view.findViewById(R.id.etValue);
    }
}
