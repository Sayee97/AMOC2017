package com.example.android.tourguide;


import com.example.android.tourguide.Database.TourDbHelper;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.tourguide.Database.TourContract.tourEntry;


public class Itenary extends Activity {


    private String id,dest,add,inD,inT,outD,outT;
    tourAdapter adapter;
    ListView listView;
    TourDbHelper tdb;
    Cursor cr;
    info Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenary);
        tdb = new TourDbHelper(this);
        listView = (ListView) findViewById(R.id.listView);
        cr = tdb.select(tdb);
        adapter = new tourAdapter(this,R.layout.list_item);
        cr.moveToFirst();
        do {
            id=cr.getString(cr.getColumnIndex(tourEntry._ID));
            dest = cr.getString(cr.getColumnIndex(tourEntry.DESTINATION));
            add = cr.getString(cr.getColumnIndex(tourEntry.ADDRESS));
            inD = cr.getString(cr.getColumnIndex(tourEntry.CHECKIN_DATE));
            inT = cr.getString(cr.getColumnIndex(tourEntry.CHECKIN_TIME));
            outD = cr.getString(cr.getColumnIndex(tourEntry.CHECKOUT_DATE));
            outT = cr.getString(cr.getColumnIndex(tourEntry.CHECKOUT_TIME));
            Info = new info(id,dest,add,inD,outD,inT,outT);
            adapter.add(Info);
        }while (cr.moveToNext());
        cr.close();
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.itenary_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_item:
//                adapter.remove(adapterContextMenuInfo.position);
//                adapter.notifyDataSetChanged();
//                listView.setAdapter(adapter);
//                  listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                      @Override
//                      public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                          info Info = (info) parent.getItemAtPosition(position);
//                          tdb.delete(tdb,Info.getId());
//                          adapter.remove(adapterContextMenuInfo.position);
//                          adapter.notifyDataSetChanged();
//                          listView.setAdapter(adapter);
//                          return true;
//                      }
//                  });


                tourAdapter adapter = (tourAdapter) listView.getAdapter();
                if (adapter.getCount()>0){
                    info Item = (info) adapter.getItem(adapterContextMenuInfo.position);
                    tdb.delete(tdb,Item.getId());
                    adapter.deleteRowItem(Item);
                }


                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
//
//    public void reload(){
//        cr = tdb.select(tdb);
//        adapter = new tourAdapter(this,R.layout.list_item);
////        destination.setText("");
////        address.setText("");
////        inTime.setText("");
////        outTime.setText("");
////        inDate.setText("");
////        outDate.setText("");
//        cr.moveToFirst();
//        do {
////            destination.append(cr.getString(0));
////            address.append(cr.getString(1));
////            inDate.append(cr.getString(2));
////            outDate.append(cr.getString(3));
////            inTime.append(cr.getString(4));
////            outTime.append(cr.getString(5));
//            id=cr.getString(cr.getColumnIndex(tourEntry._ID));
//            dest = cr.getString(cr.getColumnIndex(tourEntry.DESTINATION));
//            add = cr.getString(cr.getColumnIndex(tourEntry.ADDRESS));
//            inD = cr.getString(cr.getColumnIndex(tourEntry.CHECKIN_DATE));
//            inT = cr.getString(cr.getColumnIndex(tourEntry.CHECKIN_TIME));
//            outD = cr.getString(cr.getColumnIndex(tourEntry.CHECKOUT_DATE));
//            outT = cr.getString(cr.getColumnIndex(tourEntry.CHECKOUT_TIME));
//            Info = new info(id,dest,add,inD,outD,inT,outT);
//            adapter.add(Info);
//        }while (cr.moveToNext());
//        cr.close();
//
//        listView.setAdapter(adapter);
//
//
//    }

}
