package main.aintest.sea.com.aintest;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "AinTest";

    private Button btnParse;
    private Button btnProduct;

    private static ProductsApi ApiProducts;
    private SQLiteConnector connector;
    private SQLiteDatabase db;
    private List<Products> mList;
    private ArrayList<Products> mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiProducts = RetrofitController.getApi();

        connector = new SQLiteConnector(this);
        db = connector.getWritableDatabase();

        btnProduct = (Button) findViewById(R.id.btnProduct);
        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
                finish();
            }
        });


        btnParse = (Button) findViewById(R.id.btnParse);
        btnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<ProductsXml> call = ApiProducts.getProducts();
                call.enqueue(new Callback<ProductsXml>() {
                    @Override
                    public void onResponse(Call<ProductsXml> call, Response<ProductsXml> response) {
                        if (response.isSuccessful()) {
                            mList = response.body().products;
                            mProducts = new ArrayList<>(mList);
                            Log.d(LOG_TAG, "Successfuly!");

                            ContentValues values = new ContentValues();
                            try {
                                for (int i = 0; i < mProducts.get(0).product.size(); i++) {
                                    values.put("_id", mProducts.get(0).product.get(i).getId());
                                    values.put("name", mProducts.get(0).product.get(i).getName());
                                    values.put("price", mProducts.get(0).product.get(i).getPrice());
                                    long newRowId = db.insert("Products", null, values);
                                }
                                Log.d(LOG_TAG, "Successfuly!");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_SHORT).show();


                        } else {
                            Log.d(LOG_TAG, "Code" + response.code());
                        }
                    }
                    @Override
                    public void onFailure(Call<ProductsXml> call, Throwable t) {
                        Log.d(LOG_TAG, "Fail" + t);
                    }
                });
            }
        });

    }
}
