//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SegundaRevision_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText codDocente;
    EditText fecha;
    EditText descripcion;
    Spinner locales;
    Spinner evaluaciones;
    ArrayList<String> listaLocales=new ArrayList<>();
    ArrayList<String> listaEvaluaciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_revision__actualizar);
        codDocente=(EditText)findViewById(R.id.idCodigoDocenteActualizarSegunda);
        fecha=(EditText) findViewById(R.id.fechaSegundaRevisionAct);
        descripcion= (EditText) findViewById(R.id.descripcionSegundaRevisionAct);
        locales=(Spinner) findViewById(R.id.spinnerLocalesAct);
        evaluaciones = (Spinner) findViewById(R.id.spinnerEvaluacionesDocenteSegundaAct);
        listaLocales.add("Seleccione el local");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado=DBHelper.consultarLocales();
        if(resultado.moveToFirst())
        {
            do {
                listaLocales.add(String.valueOf(resultado.getInt(0)) + " " + resultado.getString(1));
            }while(resultado.moveToNext());
        }
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaLocales);
        locales.setAdapter(adaptador);
        listaEvaluaciones.add("Seleccione su evaluación");
        ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEvaluaciones);
        evaluaciones.setAdapter(adaptadorr);

    }
    public void consultarEvaSegundaReviAct(View v)
    {
        if(!(codDocente.getText().toString().equals(""))) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();

            Cursor materiaCiclo = DBHelper.consultarMateriasDocente(codDocente.getText().toString());

            if (materiaCiclo.moveToFirst()) {
                do {
                    listaEvaluaciones.add(DBHelper.consultarEvaluaciones(materiaCiclo.getInt(0), materiaCiclo.getString(1), materiaCiclo.getInt(2)));
                } while (materiaCiclo.moveToNext());

                Toast.makeText(this,"Evaluaciones encontradas", Toast.LENGTH_SHORT).show();
            }
            ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaEvaluaciones);
            evaluaciones.setAdapter(adaptador);
        }
        else
        {
            Toast.makeText(this,"Ingrese datos en el campo", Toast.LENGTH_SHORT).show();
        }
    }
}
