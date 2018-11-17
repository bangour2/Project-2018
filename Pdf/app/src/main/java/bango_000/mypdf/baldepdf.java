package bango_000.mypdf;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout ;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class baldepdf extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    PDFView baldepdf;
    Button btnShow;
    private CanvasView customCanvas;

    RelativeLayout.LayoutParams params;
    Button addText;
    private RelativeLayout db1_root; //android.support.percent.PercentRelativeLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baldepdf);


        db1_root = (RelativeLayout) findViewById(R.id.db1_root);
        db1_root.setOnDragListener(new MyDragListener());

        baldepdf = (PDFView) findViewById(R.id.pdfView);
        baldepdf.fromAsset("balde.pdf").load();
        baldepdf.setMaxZoom(0);

        customCanvas = findViewById(R.id.signature_canvas);
        db1_root = findViewById(R.id.db1_root);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("initialization");
        categories.add("Ajouter Text");
        categories.add("Ajouter case à cocher");
        categories.add("Reprendre la signature");
        categories.add("Envoyez");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        if (item == "Ajouter Text"){
            CustomEdittext edttxt = new CustomEdittext(this);
            db1_root.addView(edttxt);
            edttxt.setOnLongClickListener(new MyLongClickListner());
            edttxt.setText("changez la");
        }

        if (item == "Ajouter case à cocher"){
            CustomCheckox edttxt = new CustomCheckox(this);
            db1_root.addView(edttxt);
            edttxt.setOnLongClickListener(new MyLongClickListner());
            edttxt.setText("");
        }

        if (item == "Reprendre la signature"){
            customCanvas.clearCanvas();
        }

        if (item == "Envoyez"){

            takeScreenshot();
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

    /*  Method which will take screenshot on Basis of Screenshot Type ENUM  */
    public void takeScreenshot() {
        Bitmap b = null;
            //If Screenshot type is CUSTOM
        b = ScreenshotUtils.getScreenShot(db1_root);

        //If bitmap is not null
        if (b != null) {
            showScreenShotImage(b);//show bitmap over imageview

            File saveFile = ScreenshotUtils.getMainDirectoryName(this);//get the path to save screenshot
            System.out.println(saveFile);
            //path.setText(saveFile+"");
            File file = ScreenshotUtils.store(b, "pdfbalde" + ".jpg", saveFile);//save the screenshot to selected path
            shareScreenshot(file);//finally share screenshot
        } else
            //If bitmap is null show toast message
            Toast.makeText(this, R.string.screenshot_take_failed, Toast.LENGTH_SHORT).show();
    }

    /*  Show screenshot Bitmap */
    private void showScreenShotImage(Bitmap b) {
        //imageView.setImageBitmap(b);
    }

    /*  Share Screenshot  */
    private void shareScreenshot(File file) {
        Uri uri = Uri.fromFile(file);//Convert file path into Uri for sharing
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "recu");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.sharing_text));
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
        startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
    }
}
