package com.example.gestoreprestitifiere;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.example.gestoreprestitifiere.data.AppDatabase;
import com.example.gestoreprestitifiere.data.Fiera;
import com.example.gestoreprestitifiere.data.FieraDao;
import com.example.gestoreprestitifiere.data.Gioco;
import com.example.gestoreprestitifiere.data.GiocoDao;
import com.example.gestoreprestitifiere.data.PrestitoDao;
import com.example.gestoreprestitifiere.data.User;
import com.example.gestoreprestitifiere.data.UserDao;
import com.example.gestoreprestitifiere.excell.ExcellReader;
import com.example.gestoreprestitifiere.ui.main.users.NewUserDialog;
import com.example.gestoreprestitifiere.ui.main.users.UserListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gestoreprestitifiere.ui.main.SectionsPagerAdapter;
import com.example.gestoreprestitifiere.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static AppDatabase db;
    public static Fiera fiera;
    private View mLayout;
    ActivityResultLauncher<Intent> filePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mLayout = binding.getRoot();
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        FloatingActionButton fab = binding.fab;

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "DefaultDB").allowMainThreadQueries().build();

        testDatabase();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NewUserDialog clienti = new NewUserDialog(MainActivity.this, sectionsPagerAdapter);
                clienti.show();

            }
        });
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        filePicker = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent intent1 = result.getData();

                        Uri uri = intent1.getData();
                        try {
                            ExcellReader.readFile(MainActivity.this, uri);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nuovaFiera:
                nuovaFiera();
                return true;
            case R.id.cambiaFiera:
                cambiaFiera();
                return true;
            case R.id.importaGiochi:
                openFilePicker();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuovaFiera() {

    }
    private void cambiaFiera() {

    }
    private  void importaGiochi() {


    }

    public void chooseFile() {
        try {
            Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
            fileIntent.addCategory(Intent.CATEGORY_OPENABLE);

            fileIntent.setType("*/*");
            String [] mimeTypes = {"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
            fileIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            /*
            if (fileType == extensionXLS)
                fileIntent.setType("application/vnd.ms-excel");
            else
                fileIntent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            */
            filePicker.launch(fileIntent);
        } catch (Exception ex) {
            Toast.makeText(this, "ChooseFile error: " + ex.getMessage().toString(), Toast.LENGTH_SHORT);


        }
    }
    public void openFilePicker() {
        try {
            if (checkPermission()) {
                chooseFile();
            }
        } catch (ActivityNotFoundException e) {
        }

    }

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {

            Snackbar.make(mLayout, R.string.storage_access_required,
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", view -> requestStoragePermission()).show();

            return false;
        }
    }
    private void requestStoragePermission() {
        System.out.println("Richiesta");
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Richiesta 1-1");
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

        } else {
            System.out.println("Richiesta 1-2");
            Snackbar.make(mLayout, R.string.storage_unavailable, Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, 0);
        }
    }


    public static GiocoDao getGiocoDao(){
        return db.giocoDao();
    }
    public static UserDao getUserDao() {
        return db.userDao();
    }
    public static PrestitoDao getPrestitoDao() {
        return db.prestitoDao();
    }
    public static FieraDao getFieraDao() {return db.fieraDao();}

    private void testDatabase() {

        FieraDao f = getFieraDao();

        System.out.println(f.countPrestitiFiera("Comicon"));


    }
}