package com.example.isabelsolano.country;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CountryList extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ArrayList<String> country_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        String[] array_country_list = getResources().getStringArray(R.array.countries);
        country_list = new ArrayList<>(Arrays.asList(array_country_list));

        // Obtengo referencia del country list
        ListView list = (ListView) findViewById(R.id.country_list);

        //Adapter.
        // Todos los listView tienen un adaptador
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,country_list); //Contexto - layout de cada pastillita - datos
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CountryList.this,String.format(country_list.get(position)),Toast.LENGTH_SHORT).show();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CountryList.this);
                builder.setTitle(R.string.confirmación);
                String msg = getResources().getString(R.string.confirm_message);
                builder.setMessage(msg+country_list.get(position)+"?");
                builder.setPositiveButton(R.string.erase, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        country_list.remove(position);
                        adapter.notifyDataSetChanged();
                    }

                });
                builder.setNegativeButton(android.R.string.cancel,null);
                
                builder.create().show();
                return true;
            }
        });

    }
}
