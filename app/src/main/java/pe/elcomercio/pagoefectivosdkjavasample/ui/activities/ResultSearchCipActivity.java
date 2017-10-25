package pe.elcomercio.pagoefectivosdkjavasample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.pagoefectivosdk.cip.usermodel.CipSearch;
import pe.elcomercio.pagoefectivosdkjavasample.R;
import pe.elcomercio.pagoefectivosdkjavasample.adapter.ResultSearchAdapter;

public class ResultSearchCipActivity extends AppCompatActivity {

    private List<CipSearch> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search_cip);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(getString(R.string.result_cip_list))) {
            searchList = (ArrayList<CipSearch>) getIntent().getSerializableExtra(getString(R.string.result_cip_list));
        }

        if (!searchList.isEmpty()) {
            ResultSearchAdapter resultSearchAdapter = new ResultSearchAdapter(searchList);

            //Setup Recycler
            RecyclerView rcvResultSearchCip = (RecyclerView) findViewById(R.id.rcvResultSearchCip);
            rcvResultSearchCip.setAdapter(resultSearchAdapter);
            rcvResultSearchCip.setHasFixedSize(true);
        }
    }
}
