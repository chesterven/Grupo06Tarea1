//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

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
    }

    public void insertarSegundaRevision(View v)
    {
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();

        DBHelper.cerrar();


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
