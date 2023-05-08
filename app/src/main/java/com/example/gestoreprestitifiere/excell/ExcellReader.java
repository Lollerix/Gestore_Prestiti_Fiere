package com.example.gestoreprestitifiere.excell;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.gestoreprestitifiere.MainActivity;
import com.example.gestoreprestitifiere.data.Gioco;
import com.example.gestoreprestitifiere.data.GiocoDao;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcellReader {


    public static void readFile(Context context, Uri uri) throws IOException {
        try {
            System.out.println("Iniziamo per aprire " + uri);
            InputStream inStream;
            Workbook wb = null;
            ContentResolver cr = context.getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String fileType = mime.getExtensionFromMimeType((cr.getType(uri)));
            System.out.println("Apertura Stream");
            inStream = cr.openInputStream(uri);

            if (fileType.equals("XLS"))
                wb = new HSSFWorkbook(inStream);
            else
                wb = new XSSFWorkbook(inStream);


            inStream.close();
            System.out.println("Chiusura Stream " + wb.getNumberOfSheets() + " ");
            Sheet sheet = wb.getSheetAt(0);
            System.out.println("Sheet presa");
            ArrayList<Gioco> listaGiochi = new ArrayList<>();
            GiocoDao dao = MainActivity.getGiocoDao();
            System.out.println("Inizio for");
            for(Row row : sheet) {
                if(row.getRowNum() > 0) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    String name = null;
                    ArrayList<Double> players = new ArrayList<>();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        switch (cell.getCellType()) {
                            case NUMERIC:
                                players.add(cell.getNumericCellValue());
                                break;
                            case STRING:
                                name = cell.getStringCellValue();
                                break;
                        }
                    }
                    if(name != null) {
                        dao.insert(new Gioco(name));
                    }

                }
            }

        } catch (Exception ex) {
            System.out.println("Errore " + ex.getMessage());
            Toast.makeText(context, "ChooseFile error: " + ex.getMessage().toString(), Toast.LENGTH_SHORT);
        }


    }
}
