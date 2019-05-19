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

public class SolicitudSegundaRevision_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnet;
    Cursor datos;
    ArrayList<String> evaluaciones = new ArrayList<String>();
    Spinner spinnerEva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_segunda_revision__insertar);
        carnet=(EditText)findViewById(R.id.carnetSoliSegunda);
        spinnerEva=(Spinner)findViewById(R.id.spinnerEva);

    }

    public void consultarEvaluacionesRevision(View v){
        Cursor eval;
        if(carnet.getText().toString().equals(""))
        {
            Toast.makeText(this,"Ingrese su carnet",Toast.LENGTH_SHORT).show();
        }
        else {

            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            datos = DBHelper.consultarEstudianteInscrito(carnet.getText().toString());
            if(datos.moveToFirst()) {
                do {
                    eval = DBHelper.consultarEvaluaciones(datos.getInt(0), datos.getString(1), datos.getInt(2));
                    if((eval.moveToFirst())){
                        do{
                            evaluaciones.add(eval.getInt(0)+" "+eval.getString(1)+" "+eval.getString(2));
                        }while(eval.moveToNext());

                    }

                } while (datos.moveToNext());
                if(evaluaciones.size()==0){
                    Toast.makeText(this,"No hay evaluaciones disponibles",Toast.LENGTH_LONG).show();
                }
                else{
                    evaluaciones.add(0,"Seleccione evaluacion");
                    ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,evaluaciones);
                    spinnerEva.setAdapter(adaptador);
                    Toast.makeText(this, "Puede seleccionar una evaluacion", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this,"No esta inscrito en ninguna materia o no existe ese carnet",Toast.LENGTH_LONG).show();
            }


        }
    }

}
