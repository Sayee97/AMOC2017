package com.example.android.tourguide;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.tourguide.Database.TourContract.tourEntry;
import com.example.android.tourguide.Database.TourDbHelper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_create);
        TourDbHelper tourDbHelper = new TourDbHelper(this);
        Cursor cr1 = tourDbHelper.select(tourDbHelper);

        Document doc = new Document();
        try {
            String path= Environment.getExternalStorageDirectory().getAbsolutePath() + "/TourGuide";


            File dir = new File(path);
            if(!dir.exists())
                dir.mkdirs();
            System.out.println(dir.getAbsolutePath());

            Log.d("PDFCreator", "PDF Path: " + path);


            File file = new File(dir, "Itenary.pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter.getInstance(doc,fOut);

            doc.open();
            Paragraph p,paragraph ;
            cr1.moveToFirst();
            do{
                p = new Paragraph("Name : "+cr1.getString(cr1.getColumnIndex(tourEntry.DESTINATION))
                        + "\n" + "Address : "+cr1.getString(cr1.getColumnIndex(tourEntry.ADDRESS))
                        + "\n" + "Check-In Date : "+cr1.getString(cr1.getColumnIndex(tourEntry.CHECKIN_DATE))
                        + "\n" + "Check-Out Date : "+cr1.getString(cr1.getColumnIndex(tourEntry.CHECKOUT_DATE))
                        + "\n" + "Check-In Time : "+cr1.getString(cr1.getColumnIndex(tourEntry.CHECKIN_TIME))
                        + "\n" + "Check-Out Time : "+cr1.getString(cr1.getColumnIndex(tourEntry.CHECKOUT_TIME)));
                Font paraFont= new Font(Font.FontFamily.COURIER);
                p.setFont(paraFont);
                doc.add(p);
                paragraph = new Paragraph("\n\n");
                paragraph.setFont(paraFont);
                doc.add(paragraph);
            }while (cr1.moveToNext());

//            Paragraph p1 = new Paragraph("Hi! I am Sayee!!!! GOOD TO SEE THIS PDF" +
//                    "I used to believe\n" +
//                    "We were burnin' on the edge of somethin' beautiful\n" +
//                    "Somethin' beautiful\n" +
//                    "Selling a dream\n" +
//                    "Smoke and mirrors keep us waitin' on a miracle\n" +
//                    "On a miracle\n" +
//                    "Say, go through the darkest of days\n" +
//                    "Heaven's a heartbreak away\n" +
//                    "Never let you go, never let me down\n" +
//                    "Oh, it's been a hell of a ride\n" +
//                    "Driving the edge of a knife\n" +
//                    "Never let you go, never let me down\n" +
//                    "Don't you give up, nah-nah-nah\n" +
//                    "I won't give up, nah-nah-nah\n" +
//                    "Let me love you\n" +
//                    "Let me love you\n" +
//                    "Don't you give up, nah-nah-nah\n" +
//                    "I won't give up, nah-nah-nah\n" +
//                    "Let me love you\n" +
//                    "Let me love you\n" +
//                    "Oh baby, baby\n" +
//                    "Don't fall asleep\n" +
//                    "At the wheel, we've got a million miles ahead of us\n" +
//                    "Miles ahead of us\n" +
//                    "All that we need\n" +
//                    "Is a rude awakening to know we're good enough\n" +
//                    "Know we're good enough");

        }
        catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
            Log.e("PDFCreator", "ioException:" + de);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally
        {
            doc.close();
        }
        String[] mailto = {"sayee0612@gmail.com"};
        Uri uri =Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/TourGuide/","Itenary.pdf"));

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "My Body");
        emailIntent.setType("application/pdf");
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(emailIntent, "Send email using:"));


    }
}
