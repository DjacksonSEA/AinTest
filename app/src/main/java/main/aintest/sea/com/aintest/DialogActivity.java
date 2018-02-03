package main.aintest.sea.com.aintest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialogActivity extends Activity {

    private Button btnSave;
    private Button btnCancel;
    private EditText etPrice;
    private String oldprice;
    private int position;
    private SQLiteConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        connector = new SQLiteConnector(this);
        final Intent intent = getIntent();
        oldprice = intent.getStringExtra("old_price");
        position = intent.getIntExtra("position", 0);
        position+=1;

        etPrice = findViewById(R.id.etPrice);
        etPrice.setText(oldprice);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newprice = etPrice.getText().toString();
                connector.updatePrice(position, getBaseContext(), newprice);
                finish();
            }
        });

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
