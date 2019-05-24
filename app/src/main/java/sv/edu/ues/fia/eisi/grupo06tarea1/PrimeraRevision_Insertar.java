package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PrimeraRevision_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText codDocente;
    Spinner locales;
    Spinner evalua;
    EditText fecha;
    EditText descripcion;
    EditText hora;
    ArrayList<String> localess=new ArrayList<>();
    ArrayList<String> evaluaciones=new ArrayList<>();
    Cursor resultadosEvaluaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_revision__insertar);
        codDocente = (EditText)findViewById(R.id.idCodigoDocenteConsultarPrimera);
        locales = (Spinner) findViewById(R.id.spinnerLocales);
        evalua= (Spinner) findViewById(R.id.spinnerEvaluacionesDocentePrimera);
        fecha = (EditText) findViewById(R.id.fechaPrimeraRevisionIns);
        hora = (EditText) findViewById(R.id.horaPRimeraRevisionIns);
        descripcion = (EditText) findViewById(R.id.descripcionPrimeraRevisionIns);
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
    }

    public  void consultarEvaPrimeraRevi(View v)
    {
        if(!(codDocente.getText().toString().equals(""))) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();

            Cursor materiaCiclo = DBHelper.consultarMateriasDocente(codDocente.getText().toString());

            if (materiaCiclo.moveToFirst()) {
                do {
                    resultadosEvaluaciones=DBHelper.consultarEvaluaciones(materiaCiclo.getInt(0), materiaCiclo.getString(1), materiaCiclo.getInt(2));

                    if(resultadosEvaluaciones.moveToFirst())
                    {
                        do {
                            evaluaciones.add(resultadosEvaluaciones.getInt(0)+ " "+resultadosEvaluaciones.getString(1)+" "+resultadosEvaluaciones.getString(2));

                        }while(resultadosEvaluaciones.moveToNext());
                    }
                    else{



                    }
                } while (materiaCiclo.moveToNext());

                if(evaluaciones.size()==0)
                {
                    Toast.makeText(this, "No hay evaluaciones para ese docente", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    evaluaciones.add(0,"Seleccione su evaluación");
                    ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, evaluaciones);
                    evalua.setAdapter(adaptadorr);
                    Toast.makeText(this, "Evaluaciones encontradas", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "No existe el código del docente", Toast.LENGTH_SHORT).show();
            }

        }


        else
        {
            Toast.makeText(this,"Ingrese el código del docente", Toast.LENGTH_SHORT).show();
        }
    }
}
