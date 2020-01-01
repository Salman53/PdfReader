package com.example.pdfreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.security.Permission;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv_pdf;
    public static ArrayList<File> fileArrayList = new ArrayList<>();
    PdfAdapter obj_adapter;
    public static int REQUEST_PERMISSION = 1;
    boolean booleanPermission ;
    File dir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_pdf = findViewById(R.id.id_listview);

        dir = new File(Environment.getExternalStorageDirectory().toString());

        Permission_fn();

        lv_pdf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext() , ViewPDFfiles.class);
                intent.putExtra("position" , position);
                startActivity(intent);

            }
        });



    }

    private void Permission_fn() {

        if((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){

            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);
            }
        }
    else{

        booleanPermission = true;
        getfile(dir);
        obj_adapter = new PdfAdapter(getApplicationContext() , fileArrayList);
        lv_pdf.setAdapter(obj_adapter);


            }


        }



    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                booleanPermission = true;
                getfile(dir);
                obj_adapter = new PdfAdapter(getApplicationContext() , fileArrayList);
                lv_pdf.setAdapter(obj_adapter);

            }

            else {

                Toast.makeText(getApplicationContext() , "Please Allow the Permission" , Toast.LENGTH_SHORT).show();
            }
        }
    }

    public ArrayList<File> getfile(File dir){

        File listFile[] = dir.listFiles();

        if (listFile != null && listFile.length>0){

            for (int i=0 ; i<listFile.length;i++) {

                if (listFile[i].isDirectory()) {

                    getfile(listFile[i]);
                } else {

                    boolean booleanpdf = false;

                    if (listFile[i].getName().endsWith(".pdf")) {

                        for (int j = 0; j < fileArrayList.size(); j++) {
                            if (fileArrayList.get(j).equals(listFile[i].getName())) {

                                booleanpdf = true;
                            } else {


                            }
                        }

                        if (booleanpdf) {

                            booleanpdf = false;
                        } else {

                            fileArrayList.add(listFile[i]);
                        }

                    }
                }
            }

    }
        return fileArrayList;
}
}
