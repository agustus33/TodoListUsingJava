package com.android.todolist;

import androidx.appcompat.app.AppCompatActivity;

import com.android.todolist.R;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addButton;
    ListView listView;
    EditText task;
    List<String> todo ;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add_item);
        task = findViewById(R.id.editTextTask);
        listView  = findViewById(R.id.list_item);
        todo = new ArrayList<>();
        addButton.setOnClickListener(v -> {
            todo.add(task.getText().toString());
            try {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                System.out.println(e);
            }
            arrayAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.list_view,todo);
            listView.setAdapter(arrayAdapter);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
                TextView textView = (TextView) view;
                textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                todo.remove(position);
                Context context = getApplicationContext();
                CharSequence text = "Deleting Task";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                arrayAdapter.notifyDataSetChanged();

            });





    }
}