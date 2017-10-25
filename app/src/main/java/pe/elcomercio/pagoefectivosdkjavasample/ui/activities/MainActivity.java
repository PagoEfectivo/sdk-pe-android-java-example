package pe.elcomercio.pagoefectivosdkjavasample.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pe.elcomercio.pagoefectivosdkjavasample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generateCipOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, GenerateCipActivity.class);
        startActivity(intent);
    }

    public void searchCipOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, SearchCipActivity.class);
        startActivity(intent);
    }
}