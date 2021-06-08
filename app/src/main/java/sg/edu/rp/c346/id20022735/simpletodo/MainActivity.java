package sg.edu.rp.c346.id20022735.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etEle;
    Button btnAdd;
    Button btnDel;
    Button btnCl;
    ListView lvtask;
    Spinner spAddDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEle = findViewById(R.id.editTextTask);
        btnAdd =findViewById(R.id.btnA);
        btnDel = findViewById(R.id.buttonD);
        btnCl = findViewById(R.id.btnC);
        lvtask =findViewById(R.id.tasks);
        spAddDel = findViewById(R.id.spinner);

        ArrayList<String> alltasks;
        alltasks = new ArrayList<String>();
        ArrayAdapter task = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alltasks);

        lvtask.setAdapter(task);

        spAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnAdd.setEnabled(true);
                btnDel.setEnabled(true);
                switch (position) {


                    case 0:
                        etEle.setHint("Type in a new task here");
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!etEle.getText().toString().isEmpty()){
                                String newtask = etEle.getText().toString();
                                alltasks.add(newtask);
                                task.notifyDataSetChanged(); //force refresh
                                etEle.setText("");
                            }else{
                                    Toast.makeText(MainActivity.this, "Please enter your task", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        btnDel.setEnabled(false);

                        break;
                    case 1:
                        etEle.setHint("Type in the index of task to be removed");
                        etEle.setInputType(InputType.TYPE_CLASS_NUMBER);
                        btnDel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String deltask = etEle.getText().toString();
                                int pos = Integer.parseInt(deltask);
                                if (alltasks.size() == 0) {
                                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                                }
                                for (int i = alltasks.size() - 1; i < alltasks.size(); i++) {
                                    if (i == pos) {
                                        alltasks.remove(i);
                                    } else if (i != pos) {
                                        Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                task.notifyDataSetChanged();
                                etEle.setText("");

                            }});
                            btnAdd.setEnabled(false);
                                break;
                }
            }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });


                btnCl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alltasks.clear();
                        task.notifyDataSetChanged(); //force refresh
                    }
                });
            }
        }