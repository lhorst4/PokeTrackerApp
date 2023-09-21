package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.*;


public class MainActivity extends AppCompatActivity {

    View.OnClickListener submitListener = new View.OnClickListener() {

        @Override
        public void onClick(View v){
            try {
                natnum = Integer.valueOf(natnumET.getText().toString());
                name = nameET.getText().toString();
                species = speciesET.getText().toString();
                gender = genderRB.getText().toString();
                heightStr = heightET.getText().toString();
                weightStr = weightET.getText().toString();
                level = Integer.valueOf(levelSP.getSelectedItem().toString());
                hp = Integer.valueOf(hpET.getText().toString());
                attack = Integer.valueOf(attackET.getText().toString());
                defense = Integer.valueOf(defenseET.getText().toString());
                if(checkValues()){
                    submitEntry();
                }
            }
            catch(Exception e){
                errorMessage();
            }
        }
    };

    View.OnClickListener resetAllEntries = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            natnumET.setText(R.string._896);
            nameET.setText(R.string.glastrier);
            speciesET.setText(R.string.wild_horse_pok_mon);

            View noneView = genderRG.getChildAt(2);
            RadioButton noneButton = (RadioButton)noneView;
            noneButton.setChecked(true);

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
    double height;
    String heightStr;
    EditText weightET;
    double weight;
    String weightStr;
    Spinner levelSP;
    int level;
    EditText hpET;
    int hp;
    EditText attackET;
    int attack;
    EditText defenseET;
    int defense;
    Button submitBT;
    Button resetBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        natnumET = findViewById(R.id.natnumET);
        nameET = findViewById(R.id.nameET);
        speciesET = findViewById(R.id.speciesET);
        genderRG = findViewById(R.id.genderRG);
        genderRB = findViewById(genderRG.getCheckedRadioButtonId());
        heightET = findViewById(R.id.heightET);
        weightET = findViewById(R.id.weightET);
        levelSP = findViewById(R.id.levelSP);
        hpET = findViewById(R.id.hpET);
        attackET = findViewById(R.id.attackET);
        defenseET = findViewById(R.id.defenseET);
        submitBT = findViewById(R.id.submitBT);
        resetBT = findViewById(R.id.resetBT);

        resetBT.setOnClickListener(resetAllEntries);
        submitBT.setOnClickListener(submitListener);

        String[] list = new String[100];
        for(int i = 0; i < 100; i++){
            list[i] = String.valueOf(i+1);
        }
        ArrayAdapter<String> levelSPAdapter = new ArrayAdapter<String>(this.getBaseContext(), android.R.layout.simple_spinner_item, list);
        levelSPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSP.setAdapter(levelSPAdapter);
    }

    private boolean checkValues(){
        boolean success = true;
        LinkedList reasons = new LinkedList<String>(); // Lists what is wrong with the input given by the user
        ArrayList<Character> check = new ArrayList<Character>();
        String allowedChars = "abcdefghijklmnopqrstuvwxyz. ";

        if(natnum > 1010 || natnum < 1){
            success = false;
            reasons.add("National Number not between 1 and 1010.");
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
            }
            if(i > 1 && name.charAt(i-1) == ' '){
                name = name.substring(i-1, i).toUpperCase() + name.substring(i);
            }
        }
        name = name.substring(0,1).toUpperCase() + name.substring(1); // capitalize first letter in name

        species = species.toLowerCase();
        boolean badspecies = false;
        for(int i = 0; i < species.length(); i++){
            if(!check.contains(species.charAt(i)) && !badspecies) { // checks if all the characters are allowed
                success = false;
                badspecies = true;
                reasons.add("Invalid character in Species.");
            }
            if(i > 1 && species.charAt(i-1) == ' '){
                species = species.substring(i-1, i).toUpperCase() + species.substring(i);
            }
        }
        species = species.substring(0,1).toUpperCase() + species.substring(1); // capitalize first letter in species

        if(!success){
            String toastText = "";
            for(int i = 0; i < reasons.size(); i++){
                toastText = toastText + reasons.get(i) + "\n";
            }

            Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();
        }

        return success;
    }

    private void submitEntry(){
        Toast.makeText(this, "Entry has been added to Poke-Tracker!", Toast.LENGTH_SHORT).show();
    }

    private void errorMessage(){
        Toast.makeText(this, "Error: Make sure all fields are filled out.", Toast.LENGTH_LONG).show();
    }
}