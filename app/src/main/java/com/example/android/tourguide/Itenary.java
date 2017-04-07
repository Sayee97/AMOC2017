package com.example.android.tourguide;


import com.example.android.tourguide.Database.TourDbHelper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.android.tourguide.Database.TourContract.tourEntry;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Itenary extends Activity {


    private String id,dest,add,inD,inT,outD,outT;
    private Button pdf;
    tourAdapter adapter;
    ListView listView;
    private TourDbHelper tdb,tdb1;
    private Cursor cr,cr1;
    info Info;
    private Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenary);
        tdb = new TourDbHelper(this);
        listView = (ListView) findViewById(R.id.listView);
//
//        LayoutInflater inflater = getLayoutInflater();
//        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.pdf, listView, false);
//        listView.addFooterView(footer, null, false);
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

//    public void createPdf(View view){
//        tdb1 = new TourDbHelper(this);
//        cr1 = tdb1.select(tdb1);
//        Document doc = new Document();
//        try {
//            String path= Environment.getExternalStorageDirectory().getAbsolutePath() + "/TourGuide";
//
//
//            File dir = new File(path);
//            if(!dir.exists())
//                dir.mkdirs();
//            System.out.println(dir.getAbsolutePath());
//
//            Log.d("PDFCreator", "PDF Path: " + path);
//
//
//            File file = new File(dir, "Itenary.pdf");
//            FileOutputStream fOut = new FileOutputStream(file);
//            PdfWriter.getInstance(doc,fOut);
//
//            doc.open();
//            Paragraph p,paragraph ;
//            cr1.moveToFirst();
//            do{
//                p = new Paragraph("Name : "+cr1.getString(cr1.getColumnIndex(tourEntry.DESTINATION))
//                        + "\n" + "Address : "+cr1.getString(cr1.getColumnIndex(tourEntry.ADDRESS))
//                        + "\n" + "Check-In Date : "+cr1.getString(cr1.getColumnIndex(tourEntry.CHECKIN_DATE))
//                        + "\n" + "Check-Out Date : "+cr1.getString(cr1.getColumnIndex(tourEntry.CHECKOUT_DATE))
//                        + "\n" + "Check-In Time : "+cr1.getString(cr1.getColumnIndex(tourEntry.CHECKIN_TIME))
//                        + "\n" + "Check-Out Time : "+cr1.getString(cr1.getColumnIndex(tourEntry.CHECKOUT_TIME)));
//                Font paraFont= new Font(Font.FontFamily.COURIER);
//                p.setFont(paraFont);
//                doc.add(p);
//                paragraph = new Paragraph("\n\n");
//                paragraph.setFont(paraFont);
//                doc.add(paragraph);
//            }while (cr1.moveToNext());
//
////            Paragraph p1 = new Paragraph("Hi! I am Sayee!!!! GOOD TO SEE THIS PDF" +
////                    "I used to believe\n" +
////                    "We were burnin' on the edge of somethin' beautiful\n" +
////                    "Somethin' beautiful\n" +
////                    "Selling a dream\n" +
////                    "Smoke and mirrors keep us waitin' on a miracle\n" +
////                    "On a miracle\n" +
////                    "Say, go through the darkest of days\n" +
////                    "Heaven's a heartbreak away\n" +
////                    "Never let you go, never let me down\n" +
////                    "Oh, it's been a hell of a ride\n" +
////                    "Driving the edge of a knife\n" +
////                    "Never let you go, never let me down\n" +
////                    "Don't you give up, nah-nah-nah\n" +
////                    "I won't give up, nah-nah-nah\n" +
////                    "Let me love you\n" +
////                    "Let me love you\n" +
////                    "Don't you give up, nah-nah-nah\n" +
////                    "I won't give up, nah-nah-nah\n" +
////                    "Let me love you\n" +
////                    "Let me love you\n" +
////                    "Oh baby, baby\n" +
////                    "Don't fall asleep\n" +
////                    "At the wheel, we've got a million miles ahead of us\n" +
////                    "Miles ahead of us\n" +
////                    "All that we need\n" +
////                    "Is a rude awakening to know we're good enough\n" +
////                    "Know we're good enough");
//
//        }
//        catch (DocumentException de) {
//            Log.e("PDFCreator", "DocumentException:" + de);
//        } catch (IOException e) {
//            Log.e("PDFCreator", "ioException:" + e);
//        }
//        finally
//        {
//            doc.close();
//        }
//        String[] mailto = {"sayee0612@gmail.com"};
//        Uri uri =Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/TourGuide/","Itenary.pdf"));
//
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Subject");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, "My Body");
//        emailIntent.setType("application/pdf");
//        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
//
//        startActivity(Intent.createChooser(emailIntent, "Send email using:"));
//
//    }

}
