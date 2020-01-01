package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class ViewPDFfiles extends AppCompatActivity {

    PDFView pdfView;
    int postion = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdffiles);

        pdfView = findViewById(R.id.pdfView);

        postion = getIntent().getIntExtra("position" , -1);

        displayPDF();

    }

    private void displayPDF() {

        pdfView.fromFile(MainActivity.fileArrayList.get(postion))
                .enableSwipe(true)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();



    }
}
