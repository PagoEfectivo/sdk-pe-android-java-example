package pe.elcomercio.pagoefectivosdkjavasample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.PagoEfectivoSdk;
import pe.elcomercio.pagoefectivosdk.cip.SearchListener;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipError;
import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipSearch;
import pe.elcomercio.pagoefectivosdkjavasample.R;
import pe.elcomercio.pagoefectivosdkjavasample.adapter.SearchAdapter;
import pe.elcomercio.pagoefectivosdkjavasample.ui.activities.ResultSearchCipActivity;

public class SearchCipActivity extends AppCompatActivity implements SearchListener {

    private PagoEfectivoSdk instance;

    private RecyclerView rcvSearch;
    private SearchAdapter searchAdapter;
    private List<Integer> cipList = new ArrayList<>();

    private Toast toastDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cip);
        init();
    }

    private void init() {
        //Init UI
        cipList.add((int) System.currentTimeMillis());

        //Setup Recycler
        searchAdapter = new SearchAdapter(cipList);
        rcvSearch = (RecyclerView) findViewById(R.id.rcvSearch);
        rcvSearch.setAdapter(searchAdapter);
        rcvSearch.setHasFixedSize(true);

        //Get Instance from PagoEfectivo SDK
        instance = PagoEfectivoSdk.getInstance();
    }

    public void searchCipSdkOnClick(View view) {
        showMessage(getString(R.string.searching_cip));

        List<Integer> cipListToSearch = new ArrayList<>();

        for (int i = 0; i < cipList.size(); i++) {
            EditText nameEditText = rcvSearch.getChildAt(i).findViewById(R.id.txtSearchCip);
            if (!nameEditText.getText().toString().isEmpty()) {
                cipListToSearch.add(Integer.parseInt(nameEditText.getText().toString()));
            }
        }

        instance.searchCip(cipListToSearch, this);
    }

    public void addCipRow(View view) {
        cipList.add((int) System.currentTimeMillis());
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnSearchSuccessful(List<CipSearch> list) {

        Intent intent = new Intent(this, ResultSearchCipActivity.class);
        intent.putExtra(getString(R.string.result_cip_list), (ArrayList<CipSearch>) list);
        startActivity(intent);

        if (toastDialog != null) {
            toastDialog.cancel();
        }
    }

    @Override
    public void OnCipError(List<CipError> list) {
        StringBuilder errorMessage = new StringBuilder();
        for (CipError error : list) {
            errorMessage.append("- (").append(error.getCode()).append(")").append(" | ").append(error.getField()).append(" --> ").append(error.getMessage()).append("\n");
        }

        showMessage(errorMessage.toString());
    }

    @Override
    public void OnCipFailure(String s) {
        showMessage(s);
    }

    private void showMessage(String message) {
        if (toastDialog != null) {
            toastDialog.cancel();
        }
        toastDialog = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toastDialog.show();
    }
}
