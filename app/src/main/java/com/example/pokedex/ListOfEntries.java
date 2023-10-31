package com.example.pokedex;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListOfEntries extends AppCompatActivity {

    ArrayList<String> pokelist;
    ListView pokelistLV;
    Cursor mCursor;
    public ListOfEntries(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokelist);

        Intent intent = getIntent();
        pokelist = intent.getStringArrayListExtra("pokelist");

        pokelistLV = findViewById(R.id.pokelistview);
        makeList();
    }

    public void makeList(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokelist);
        pokelistLV.setAdapter(adapter);
    }
}