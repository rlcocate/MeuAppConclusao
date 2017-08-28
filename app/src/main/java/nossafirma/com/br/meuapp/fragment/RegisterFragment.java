package nossafirma.com.br.meuapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import nossafirma.com.br.meuapp.R;
import nossafirma.com.br.meuapp.model.Beer;
import nossafirma.com.br.meuapp.model.Region;
import nossafirma.com.br.meuapp.sqlite.BeerDAO;
import nossafirma.com.br.meuapp.sqlite.RegionDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    Context context;
    private Spinner spRegions;
    private Spinner spBeers;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = container.getContext();

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        //TODO: como obter o findViewById do Spinner que está na Activity???

        spRegions = (Spinner) view.findViewById(R.id.spRegions);
        spBeers = (Spinner) view.findViewById(R.id.spBeers);

        List<Region> regions = new RegionDAO(context).getAll();

        ArrayAdapter<Region> regionAdapter = new ArrayAdapter<Region>(context, android.R.layout.simple_spinner_item, regions);
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRegions.setAdapter(regionAdapter);

        List<Beer> beers = new BeerDAO(context).getAll();

        ArrayAdapter<Beer> beerAdapter = new ArrayAdapter<Beer>(context, android.R.layout.simple_spinner_item, beers);
        beerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBeers.setAdapter(beerAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    public void save() {

    }
}
