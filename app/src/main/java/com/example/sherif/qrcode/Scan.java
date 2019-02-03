package com.example.sherif.qrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scan extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);

    }

    @Override
    public void handleResult(Result result) {

        MainActivity.tx_qr_result.setText(result.getText());
        Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_SHORT);
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
}
