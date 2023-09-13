package com.example.zikercounter;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Notes extends AppCompatActivity {
    private TextView notesTextView;
ImageButton btnSave;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // Disable dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
ListView listView =findViewById(R.id.listview);



// Retrieve the saved notes from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        Set<String> savedNotesSet = preferences.getStringSet("notes", new HashSet<>());
        List<String> notes = new ArrayList<>(savedNotesSet);

        // Create an ArrayAdapter with the notes list and the custom layout file
  adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dialog, R.id.tvcards, notes);

        // Set the adapter to the listView
        listView.setAdapter(adapter);







        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Remove the note from the list and update SharedPreferences
                notes.remove(position);

                SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putStringSet("notes", new HashSet<>(notes));
                editor.apply();

                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();

                Toast.makeText(Notes.this, "Note removed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        btnSave = findViewById(R.id.adBtn);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Notes.this);
                builder.setTitle("Save Notes");

                // Add an EditText to the dialog
                final EditText editText = new EditText(Notes.this);
                editText.setHint("Enter a title for your notes");
                builder.setView(editText);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        View view = LayoutInflater.from(Notes.this).inflate(R.layout.dialog, null);

                        // Find the views in the custom layout file
                        CardView cardView = view.findViewById(R.id.cardsnotes);
                        TextView textView = view.findViewById(R.id.tvcards);


                        if (!editText.getText().toString().equals("")) {
                            // Add the note to the notes list
                            notes.add(editText.getText().toString());

                            SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            // Save the notes to SharedPreferences
                            editor.putStringSet("notes", new HashSet<String>(notes));
                            editor.apply();
                            // Create an ArrayAdapter with the notes list and the custom layout file
                  adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dialog, R.id.tvcards, notes);


                            // Set the adapter to the listView
                            listView.setAdapter(adapter);





                        }




                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Add code here to handle cancel action
                        Toast.makeText(Notes.this, "Notes not saved", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });










        }
}