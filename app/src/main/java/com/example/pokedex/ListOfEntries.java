package com.example.pokedex;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListOfEntries extends AppCompatActivity {

    LinkedList<Pokemon> pokemon;
    ListView pokelistLV;
    Cursor mCursor;



    public ListOfEntries(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokelist);

        Intent intent = getIntent();
        //pokelist = intent.getStringArrayListExtra("pokelist");

        pokelistLV = findViewById(R.id.pokelistview);
        makeList();
        setCursorAdapter();
        registerForContextMenu(pokelistLV);
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

        //ArrayAdapter<Pokemon> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokemon);
        //pokelistLV.setAdapter(adapter);

        // ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pokelist);
        // pokelistLV.setAdapter(adapter);
    }
    public void setCursorAdapter(){
        mCursor = getContentResolver().query(PokeProvider.contentURI, null, null, null, null);
        if (mCursor != null) {
            if(mCursor.getCount() == 0){
                return;
            }
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2,
                    mCursor,
                    new String[] { PokeProvider.COLUMN_2NAME, PokeProvider.COLUMN_7NAME },
                    new int[] { android.R.id.text1, android.R.id.text2 },0);
            pokelistLV.setAdapter(adapter);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        if (item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Deleting item", Toast.LENGTH_LONG).show();
            Pokemon p = pokemon.get(position);
            pokemon.remove(position);
            String mSelectedClause = PokeProvider.COLUMN_1NAME + " = ? " + " AND " +
                    PokeProvider.COLUMN_2NAME + " = ? " + " AND " +
                    PokeProvider.COLUMN_3NAME + " = ? " + " AND " +
                    PokeProvider.COLUMN_4NAME + " = ? " + " AND " +
//                    PokeProvider.COLUMN_5NAME + " = ? " + " AND " +
//                    PokeProvider.COLUMN_6NAME + " = ? " + " AND " +
                    PokeProvider.COLUMN_7NAME + " = ? " + " AND " +
                    PokeProvider.COLUMN_8NAME + " = ? " + " AND " +
                    PokeProvider.COLUMN_9NAME + " = ? " + " AND " +
                    PokeProvider.COLUMN_10NAME + " = ? ";
            String[] mSelectionArgs = { String.valueOf(p.getNatNum()) , p.getName(), p.getSpecies(), p.getGender(),
                    /*String.valueOf(p.getHeight()), String.valueOf(p.getWeight()),*/ String.valueOf(p.getLevel()),
                    String.valueOf(p.getHp()), String.valueOf(p.getAttack()), String.valueOf(p.getDefense()) };

            int mRowsDeleted = getContentResolver().delete(PokeProvider.contentURI, mSelectedClause, mSelectionArgs);

        }
        makeList();
        setCursorAdapter();
        return true;
    }
}