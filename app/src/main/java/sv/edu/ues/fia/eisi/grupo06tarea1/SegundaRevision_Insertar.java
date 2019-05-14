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
import android.widget.Toast;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLDisplay;

public class SegundaRevision_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText codDocente;
    Spinner locales;
    Spinner evalua;
    EditText fecha;
    EditText descripcion;
    ArrayList<String> localess=new ArrayList<>();
    ArrayList<String> evaluaciones=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision__insertar);
        codDocente = (EditText)findViewById(R.id.idCodigoDocenteConsultarSegunda);
        locales = (Spinner) findViewById(R.id.spinnerLocales);
        evalua= (Spinner) findViewById(R.id.spinnerEvaluacionesDocenteSegunda);
        fecha = (EditText) findViewById(R.id.fechaSegundaRevisionIn);
        descripcion = (EditText) findViewById(R.id.descripcionSegundaRevisionIn);
        localess.add("Seleccione el local");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado=DBHelper.consultarLocales();
        if(resultado.moveToFirst())
        {
            do {
                localess.add(String.valueOf(resultado.getInt(0)) + " " + resultado.getString(1));
            }while(resultado.moveToNext());
        }


       ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,localess);
       locales.setAdapter(adaptador);
        evaluaciones.add("Seleccione su evaluación");
        ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, evaluaciones);
        evalua.setAdapter(adaptadorr);

    }

    public  void consultarEvaSegundaRevi(View v)
    {
        if(!(codDocente.getText().toString().equals(""))) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();

            Cursor materiaCiclo = DBHelper.consultarMateriasDocente(codDocente.getText().toString());

            if (materiaCiclo.moveToFirst()) {
                do {
                    evaluaciones.add(DBHelper.consultarEvaluaciones(materiaCiclo.getInt(0), materiaCiclo.getString(1), materiaCiclo.getInt(2)));
                } while (materiaCiclo.moveToNext());

                Toast.makeText(this,"Evaluaciones encontradas", Toast.LENGTH_SHORT).show();
            }
            ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, evaluaciones);
            evalua.setAdapter(adaptador);
        }
        else
        {
            Toast.makeText(this,"Ingrese datos en el campo", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertarSegundaRevision(View v) {

        if ((evalua.getSelectedItem().toString().equals("Seleccione su evaluación"))
                | (locales.getSelectedItem().toString().equals("Seleccione el local"))
                | (fecha.getText().toString().equals("") | descripcion.getText().toString().equals("")))
        {
            Toast.makeText(this,"Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        } else {

            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            String mensaje = "";
            String evaluacion = "";
            SegundaRevision segunda = new SegundaRevision();
            evaluacion = evalua.getSelectedItem().toString();
            String[] evaluacionParte = evaluacion.split(" ");
            String local = "";

            local = locales.getSelectedItem().toString();
            String[] localParte = local.split(" ");

            segunda.setIdEvaluacion(Integer.valueOf(evaluacionParte[0]));
            segunda.setIdLocal(Integer.valueOf(localParte[0]));
            segunda.setFechaSegundaRevision(fecha.getText().toString());
            segunda.setDescripcion(descripcion.getText().toString());

            mensaje = DBHelper.insertarSegundaRevision(segunda);
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }
    }
    public void activityTestigos()
    {

    }

    public void limpiarTexto(View v)
    {

         codDocente.setText("");
         fecha.setText("");
        descripcion.setText("");

    }
}
