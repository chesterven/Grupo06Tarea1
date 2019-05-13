//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLDisplay;

public class SegundaRevision_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText idEvaluacion;
    Spinner locales;
    EditText fecha;
    EditText descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision__insertar);
        idEvaluacion = (EditText) findViewById(R.id.idEvaluacionSegundaRevisionIn);
        locales = (Spinner) findViewById(R.id.spinnerLocales);
        fecha = (EditText) findViewById(R.id.fechaSegundaRevisionIn);
        descripcion = (EditText) findViewById(R.id.descripcionSegundaRevisionIn);
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();


       ArrayList<String>resultados= DBHelper.consultarLocales();
       ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,resultados);
       locales.setAdapter(adaptador);
    }

    public void insertarSegundaRevision(View v)
    {



    }
    public void activityTestigos()
    {

    }

    public void limpiarTexto(View v)
    {

         idEvaluacion.setText("");
         fecha.setText("");
        descripcion.setText("");


    }
}
