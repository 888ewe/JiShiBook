package com.chenmo.jizhangbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvMain;

    private List<CostBean> list;
    private CostAdapter adapter;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lvMain = (ListView) findViewById(R.id.lv_main);
        databaseHelper = new DatabaseHelper(this);
        initData();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("新增事件");
                LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
                View view1=inflater.inflate(R.layout.alert_view,null);
                final EditText et_cost_title= (EditText) view1.findViewById(R.id.et_cost_title);
                final EditText et_cost_money= (EditText) view1.findViewById(R.id.et_cost_money);
                final DatePicker datepicker= (DatePicker) view1.findViewById(R.id.datepicker);
                builder.setView(view1);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CostBean costBean=new CostBean();
                        if("".equals(et_cost_money.getText().toString())||"".equals(et_cost_title.getText().toString())) {
                            Toast.makeText(MainActivity.this, "请填写数据", Toast.LENGTH_SHORT).show();
                        }else {
                            costBean.costTitle=et_cost_title.getText().toString();
                            costBean.costMoney=et_cost_money.getText().toString();
                            costBean.costDate=datepicker.getYear()+"-"+(datepicker.getMonth()+1)+"-"+datepicker.getDayOfMonth();
                            databaseHelper.insertCost(costBean);
                            list.add(costBean);
                            adapter.notifyDataSetChanged();
                        }

                    }
                });
                builder.setNegativeButton("cancel",null);
                builder.create().show();
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();

//        databaseHelper.deletrAllData();

        Cursor allCostData = databaseHelper.getAllCostData();
        if (allCostData != null) {
            while (allCostData.moveToNext()) {
                CostBean costBean = new CostBean();
                costBean.costTitle = allCostData.getString(allCostData.getColumnIndex("cost_title"));
                costBean.costDate = allCostData.getString(allCostData.getColumnIndex("cost_date"));
                costBean.costMoney = allCostData.getString(allCostData.getColumnIndex("cost_money"));
                list.add(costBean);
            }
            allCostData.close();
        }
        adapter = new CostAdapter(list, MainActivity.this);
        lvMain.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_chart) {
            Intent intent = new Intent(MainActivity.this, ChartActivity.class);
            intent.putExtra("cost_list", (Serializable) list);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
