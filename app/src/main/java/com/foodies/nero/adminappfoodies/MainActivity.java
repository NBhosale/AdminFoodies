package com.foodies.nero.adminappfoodies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button saveButton;
    private EditText nameRecipe, typeRecipe, imageRecipe, originRecipe, descriptionRecipe;
    public DatabaseReference firebaseDatabase;
    private String nameRecipeHolder, typeRecipeHolder, imageRecipeHolder, originRecipeHolder, descriptionRecipeHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        saveButton = (Button) findViewById(R.id.buttonSave);
        nameRecipe = (EditText) findViewById(R.id.editTextRecipeName);
        typeRecipe = (EditText) findViewById(R.id.editTextRecipeType);
        imageRecipe = (EditText) findViewById(R.id.editTextImageUrl);
        originRecipe = (EditText) findViewById(R.id.editTextRecipeOrigin);
        descriptionRecipe = (EditText) findViewById(R.id.editTextDescription);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameRecipeHolder = nameRecipe.getText().toString();
                typeRecipeHolder = typeRecipe.getText().toString();
                imageRecipeHolder = imageRecipe.getText().toString();
                originRecipeHolder = originRecipe.getText().toString();
                descriptionRecipeHolder = descriptionRecipe.getText().toString();

                if(TextUtils.isEmpty(nameRecipeHolder)){
                    Toast.makeText(MainActivity.this, "Recipe Name Cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(typeRecipeHolder)){
                    Toast.makeText(MainActivity.this, "Recipe Type Cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(imageRecipeHolder)){
                    Toast.makeText(MainActivity.this, "Recipe image Cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                } else if(TextUtils.isEmpty(originRecipeHolder)){
                    Toast.makeText(MainActivity.this, "Recipe origin Cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(descriptionRecipeHolder)){
                    Toast.makeText(MainActivity.this, "Recipe description Cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Recipe recipe = new Recipe(nameRecipeHolder, typeRecipeHolder, imageRecipeHolder, originRecipeHolder, descriptionRecipeHolder);
                firebaseDatabase.child("Recipes").child(nameRecipeHolder).setValue(recipe);
                Toast.makeText(MainActivity.this, "Recipe Added!!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
