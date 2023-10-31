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
    LinkedList<Pokemon> pokemon;
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

        mCursor = getContentResolver().query(
                PokeProvider.contentURI, null, null,
                null, null);
        pokemon = new LinkedList<>();
        if(mCursor != null){
            mCursor.moveToFirst();
            if(mCursor.getCount() > 0){
                while(mCursor.isAfterLast() == false){
                    int a = mCursor.getInt(1);
                    String b = mCursor.getString(2);
                    String c = mCursor.getString(3);
                    String d = mCursor.getString(4);
                    float e = mCursor.getFloat(5);
                    float f = mCursor.getFloat(6);
                    int g = mCursor.getInt(7);
                    int h = mCursor.getInt(8);
                    int i = mCursor.getInt(9);
                    int j = mCursor.getInt(10);

                    pokemon.add(new Pokemon(a, b, c, d, e , f, g , h , i , j));
                    mCursor.moveToNext();
                }
            }
        }

        ArrayAdapter<Pokemon> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokemon);
        pokelistLV.setAdapter(adapter);

        // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokelist);
        // pokelistLV.setAdapter(adapter);
    }
}