package wada1028.info3.oepnv_navigator.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import wada1028.info3.oepnv_navigator.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static final String[] STOPS = new String[]{"Europaplatz","Markplatz","Herrenstraße"};

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //Stopfinder Autocomplete

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,STOPS);
        AutoCompleteTextView autoCompleteTextViewStart = root.findViewById(R.id.autoCompleteTextView_Starthaltestelle);
        autoCompleteTextViewStart.setAdapter(arrayAdapter);
        AutoCompleteTextView autoCompleteTextViewZiel = root.findViewById(R.id.autoCompleteTextView_zielhaltestelle);
        autoCompleteTextViewZiel.setAdapter(arrayAdapter);
        return root;
    }
}