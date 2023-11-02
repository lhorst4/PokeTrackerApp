package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.*;


public class MainActivity extends AppCompatActivity {

    LinkedList<Pokemon> pokemonList;

    View.OnClickListener seeEntries = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            ArrayList<String> pokestrarr = new ArrayList<>();

            //if(pokemonList != null){
            //    for (int i = 0; i < pokemonList.size() - 1; i++) {
            //        pokestrarr.add(pokemonList.get(i).toString());
            //    }

                Intent intent = new Intent(getApplicationContext(), ListOfEntries.class);
                //intent.putExtra("pokelist", pokestrarr);
                startActivity(intent);
//            }else{
//                Toast.makeText(getApplicationContext(), "No entries yet.", Toast.LENGTH_LONG).show();
//            }
        }
    };


    View.OnClickListener submitListener = new View.OnClickListener() {

        @Override
        public void onClick(View v){
            resetTextColors();
            try {
                natnum = Integer.valueOf(natnumET.getText().toString());
            }catch(Exception e) {}
            try {
                name = nameET.getText().toString();
            }
            catch(Exception e){}
            try{
                species = speciesET.getText().toString();
            }catch(Exception e) {}
            try {
                genderRB = findViewById(genderRG.getCheckedRadioButtonId());
                gender = genderRB.getText().toString();
            }catch(Exception e) {}
            try {
                level = Integer.valueOf(levelSP.getSelectedItem().toString());
                levelStr = levelSP.getSelectedItem().toString();
            }catch(Exception e) {}
            try {
                heightStr = heightET.getText().toString();
                String txt = heightStr.replaceAll("m", "");
                txt = txt.replaceAll(" ", "");
                height = Float.parseFloat(txt);
            }catch(Exception e) {}
            try {
                weightStr = weightET.getText().toString();
                String txt = weightStr.replaceAll("kg", "");
                txt = txt.replaceAll(" ", "");
                weight = Float.parseFloat(txt);
            }catch(Exception e) {}
            try {
                hp = Integer.valueOf(hpET.getText().toString());
            }catch(Exception e) {}
            try {
                attack = Integer.valueOf(attackET.getText().toString());
            }catch(Exception e) {}
            try {
                defense = Integer.valueOf(defenseET.getText().toString());
            }
            catch(Exception e){}
                // Do nothing because toasts will be shown from checkValues() function
            if(checkValues()){
                submitEntry();
            }
        }
    };

    View.OnClickListener resetAllEntries = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            natnumET.setText(R.string._896);
            nameET.setText(R.string.glastrier);
            speciesET.setText(R.string.wild_horse_pok_mon);
            resetTextColors();

            for(int i = 0; i < 3; i++) {
                View noneView = genderRG.getChildAt(i);
                RadioButton noneButton = (RadioButton) noneView;
                noneButton.setChecked(false);
            }

            heightET.setText(R.string._2_2_m);
            weightET.setText(R.string._800_0_kg);
            levelSP.setSelection(0);
            hpET.setText(R.string._0);
            attackET.setText(R.string._0);
            defenseET.setText(R.string._0);
        }

    };

    EditText natnumET;
    int natnum;
    EditText nameET;
    String name;
    EditText speciesET;
    String species;
    RadioGroup genderRG;
    RadioButton genderRB;

    String gender;
    EditText heightET;
    float height;
    String heightStr;
    EditText weightET;
    float weight;
    String weightStr;
    Spinner levelSP;
    int level;
    String levelStr;
    EditText hpET;
    int hp;
    EditText attackET;
    int attack;
    EditText defenseET;
    int defense;
    Button submitBT;
    Button resetBT;
    Button pokebutton;
    TextView natnumTV;
    TextView nameTV;
    TextView speciesTV;
    TextView genderTV;
    TextView heightTV;
    TextView weightTV;
    TextView levelTV;
    TextView hpTV;
    TextView attackTV;
    TextView defenseTV;
    LinkedList<TextView> TVlist = new LinkedList<TextView>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        natnumET = findViewById(R.id.natnumET);
        nameET = findViewById(R.id.nameET);
        speciesET = findViewById(R.id.speciesET);
        genderRG = findViewById(R.id.genderRG);
        heightET = findViewById(R.id.heightET);
        weightET = findViewById(R.id.weightET);
        levelSP = findViewById(R.id.levelSP);
        hpET = findViewById(R.id.hpET);
        attackET = findViewById(R.id.attackET);
        defenseET = findViewById(R.id.defenseET);

        natnumTV = findViewById(R.id.natnumTV);
        nameTV = findViewById(R.id.nameTV);
        speciesTV = findViewById(R.id.speciesTV);
        genderTV = findViewById(R.id.genderTV);
        heightTV = findViewById(R.id.heightTV);
        weightTV = findViewById(R.id.weightTV);
        levelTV = findViewById(R.id.levelTV);
        hpTV = findViewById(R.id.hpTV);
        attackTV = findViewById(R.id.attackTV);
        defenseTV = findViewById(R.id.defenseTV);

        pokebutton = findViewById(R.id.pokebutton);

        TVlist.add(natnumTV);
        TVlist.add(nameTV);
        TVlist.add(speciesTV);
        TVlist.add(genderTV);
        TVlist.add(heightTV);
        TVlist.add(weightTV);
        TVlist.add(levelTV);
        TVlist.add(hpTV);
        TVlist.add(attackTV);
        TVlist.add(defenseTV);

        submitBT = findViewById(R.id.submitBT);
        resetBT = findViewById(R.id.resetBT);

        resetBT.setOnClickListener(resetAllEntries);
        submitBT.setOnClickListener(submitListener);
        pokebutton.setOnClickListener(seeEntries);

        String[] list = new String[51];
        list[0] = " ";
        for(int i = 1; i < 51; i++){
            list[i] = String.valueOf(i);
        }
        ArrayAdapter<String> levelSPAdapter = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, list);
        levelSP.setAdapter(levelSPAdapter);
        heightET.addTextChangedListener(new TextWatcher() {
            boolean addedSuffixHeight = false;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                String text = heightET.getText().toString();
                heightET.addTextChangedListener(this);
                if(!text.endsWith(" m")){
                    if(text.contains("m")){
                        text = text.replaceAll("m", "");
                    }

                    heightET.setText(text.concat(" m"));
                    heightET.setSelection(text.length());
                    addedSuffixHeight = true;
                    heightET.removeTextChangedListener(this);
                }
            }
            @Override
            public void afterTextChanged(Editable s){
                if(s.length() == 0){
                    addedSuffixHeight = false;
                }
            }
        });
        weightET.addTextChangedListener(new TextWatcher() {
            boolean addedSuffixWeight = false;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                weightET.removeTextChangedListener(this);
                String text = weightET.getText().toString();
                if(!text.endsWith(" kg")){
                    if(text.contains("k")){
                        text = text.replaceAll("k", "");
                    }
                    if(text.contains("g")){
                        text = text.replaceAll("g", "");
                    }

                    weightET.setText(text.concat(" kg"));
                    weightET.setSelection(text.length());
                    addedSuffixWeight = true;
                    weightET.addTextChangedListener(this);
                }
            }
            @Override
            public void afterTextChanged(Editable s){
                if(s.length() == 0){
                    addedSuffixWeight = false;
                }
            }
        });
    }

    private boolean checkValues(){
        boolean success = true;
        LinkedList reasons = new LinkedList<String>(); // Lists what is wrong with the input given by the user
        ArrayList<Character> check = new ArrayList<Character>();
        String allowedChars = "abcdefghijklmnopqrstuvwxyz. ";

        if(natnum > 1010 || natnum < 1){
            success = false;
            reasons.add("National Number not between 1 and 1010.");
            natnumTV.setTextColor(Color.RED);
        }

        for(int i=0;i<allowedChars.length();i++) // makes an array of allowed characters
        {
            check.add(allowedChars.charAt(i));
        }
        name = name.toLowerCase();
        boolean badname = false;
        for(int i = 0; i < name.length(); i++){
            if(!check.contains(name.charAt(i)) && !badname) { // checks if all the characters are allowed
                success = false;
                badname = true;
                reasons.add("Invalid character in Name.");
                nameTV.setTextColor(Color.RED);
            }
        }
        if(name.length() < 3){
            success = false;
            reasons.add("Name too short.");
            nameTV.setTextColor(Color.RED);
        }
        if(name.length() > 12){
            success = false;
            reasons.add("Name too long.");
            nameTV.setTextColor(Color.RED);
        }
        name = name.substring(0,1).toUpperCase() + name.substring(1); // capitalize first letter in name

        species = species.toLowerCase();
        boolean badspecies = false;
        for(int i = 0; i < species.length(); i++){
            if(!check.contains(species.charAt(i)) && !badspecies) { // checks if all the characters are allowed
                success = false;
                badspecies = true;
                reasons.add("Invalid character in Species.");
                speciesTV.setTextColor(Color.RED);
            }
            if(i > 1 && species.charAt(i-1) == ' '){
                species = species.substring(0, i-1) + species.substring(i-1, i).toUpperCase() + species.substring(i);
            }
        }
        species = species.substring(0,1).toUpperCase() + species.substring(1); // capitalize first letter in species

        if(genderRB == null || genderRB.isChecked() == false) {
            success = false;
            reasons.add("No gender selected.");
            genderTV.setTextColor(Color.RED);
        }

        if(height < 0.3){
            success = false;
            reasons.add("Height too short.");
            heightTV.setTextColor(Color.RED);
        }
        if(height > 19.99){
            success = false;
            reasons.add("Height too tall.");
            heightTV.setTextColor(Color.RED);
        }

        if(weight < 0.1){
            success = false;
            reasons.add("Weight too light.");
            weightTV.setTextColor(Color.RED);
        }
        if(weight > 820){
            success = false;
            reasons.add("Weight too heavy.");
            weightTV.setTextColor(Color.RED);
        }

        if(levelSP.getSelectedItem() == " "){
            success = false;
            reasons.add("Level not selected.");
            levelTV.setTextColor(Color.RED);
        }

        if(hp < 1){
            success = false;
            reasons.add("HP too low.");
            hpTV.setTextColor(Color.RED);
        }
        if(hp > 362){
            success = false;
            reasons.add("HP too high.");
            hpTV.setTextColor(Color.RED);
        }

        if(attack < 5){
            success = false;
            reasons.add("Attack too low.");
            attackTV.setTextColor(Color.RED);
        }
        if(attack > 526){
            success = false;
            reasons.add("Attack too high.");
            attackTV.setTextColor(Color.RED);
        }

        if(defense < 5){
            success = false;
            reasons.add("Defense too low.");
            defenseTV.setTextColor(Color.RED);
        }
        if(defense > 614){
            success = false;
            reasons.add("Defense too high.");
            defenseTV.setTextColor(Color.RED);
        }

        if(!success){
            String toastText = "";
            for(int i = 0; i < reasons.size(); i++){
                toastText = toastText + reasons.get(i) + "\n";
            }

            Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();
        }else{
            if(pokemonList == null){
                pokemonList = new LinkedList<>();
            }
            Pokemon p = new Pokemon(natnum, name, species, gender, height, weight, level, hp, attack, defense);

            boolean duplicate = false;

            Cursor mCursor = getContentResolver().query(PokeProvider.contentURI, null, null, null, null, null);

            if(mCursor != null) {
                mCursor.moveToFirst();
                if (mCursor.getCount() > 0) {
                    while (mCursor.isAfterLast() == false) {
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

                        if (a == p.getNatNum() && b.equals(p.getName()) && c.equals(p.getSpecies()) && d.equals(p.getGender())
                                && e == p.getHeight() && f == p.getWeight() && g == p.getLevel() && h == p.getHp() && i == p.getAttack()
                                && j == p.getDefense()) {
                            duplicate = true;
                            break;
                        }
                        mCursor.moveToNext();
                    }
                }
            }


            if(!duplicate) {
                pokemonList.add(p);
                ContentValues mNewValues = new ContentValues();
                mNewValues.put(PokeProvider.COLUMN_1NAME, p.getNatNum());
                mNewValues.put(PokeProvider.COLUMN_2NAME, p.getName());
                mNewValues.put(PokeProvider.COLUMN_3NAME, p.getSpecies());
                mNewValues.put(PokeProvider.COLUMN_4NAME, p.getGender());
                mNewValues.put(PokeProvider.COLUMN_5NAME, p.getHeight());
                mNewValues.put(PokeProvider.COLUMN_6NAME, p.getWeight());
                mNewValues.put(PokeProvider.COLUMN_7NAME, p.getLevel());
                mNewValues.put(PokeProvider.COLUMN_8NAME, p.getHp());
                mNewValues.put(PokeProvider.COLUMN_9NAME, p.getAttack());
                mNewValues.put(PokeProvider.COLUMN_10NAME, p.getDefense());
                getContentResolver().insert(PokeProvider.contentURI, mNewValues);
            }else{
                Toast.makeText(this, "Entry is already in database.", Toast.LENGTH_LONG).show();
                success = false;
            }
        }
        return success;
    }

    private void submitEntry(){
        Toast.makeText(this, "Entry has been added to Poke-Tracker!", Toast.LENGTH_SHORT).show();
    }

    private void errorMessage(){
        Toast.makeText(this, "Error: Make sure all fields are filled out.", Toast.LENGTH_LONG).show();
    }

    private void resetTextColors() {
        for (int i = 0; i < TVlist.size(); i++) {
            TextView tv = TVlist.get(i);
            tv.setTextColor(Color.BLACK);
        }
    }
}