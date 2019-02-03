package com.example.sherif.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button bt_generate , bt_scan;
    private EditText text;
    private ImageView imageView;
    public static TextView tx_qr_result;
    String code_text = "Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_generate = (Button) findViewById(R.id.bt_generate);
        bt_scan = (Button) findViewById(R.id.btn_scan);
        text = (EditText) findViewById(R.id.text);
        imageView = (ImageView) findViewById(R.id.img_qrcode);
        tx_qr_result = (TextView) findViewById(R.id.tx_qrresult);

        bt_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                code_text = text.getText().toString().trim();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                if (!code_text.equalsIgnoreCase("")) {
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(code_text, BarcodeFormat.QR_CODE, 200, 200);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"please write anything",Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Scan.class);
                startActivity(intent);
            }
        });
       /* bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("scanning");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();


            }
        });*/

    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (intentResult!=null && intentResult.getContents()!=null){
            tx_qr_result.setText(intentResult.getContents());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/
}
