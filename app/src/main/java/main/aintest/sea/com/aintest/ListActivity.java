package main.aintest.sea.com.aintest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {

    private RecyclerView rv;
    private AdapterRecyclerView adapterRV;
    private SQLiteConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecyclerView();
    }

    public void loadRecyclerView(){
        connector = new SQLiteConnector(this);
        rv = (RecyclerView) findViewById(R.id.recycle_view_products);
        adapterRV = new AdapterRecyclerView(connector.getProducts());
        rv.setAdapter(adapterRV);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
