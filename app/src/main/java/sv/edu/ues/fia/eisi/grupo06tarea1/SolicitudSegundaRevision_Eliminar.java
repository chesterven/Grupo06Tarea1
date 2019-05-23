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

public class SolicitudSegundaRevision_Eliminar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnetSoli;
    Spinner evaluaciones;
    ArrayList<String> arrayEva= new ArrayList<String>();
    Cursor Evaluacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_segunda_revision__eliminar);
        carnetSoli=(EditText)findViewById(R.id.carnetEliminarSoliSegunda);
        evaluaciones=(Spinner)findViewById(R.id.spinnerEvaEliminarSoliSegunda);
    }

    public void consultarEvaluacionSolicitudSegundaEli(View v){
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        if(carnetSoli.getText().toString().equals("")){
            Toast.makeText(this,"Ingrese el carnet",Toast.LENGTH_SHORT).show();
        }
        else{
            Evaluacion = DBHelper.consultarSolicitudSegundaEstuiante(carnetSoli.getText().toString());
            if(Evaluacion.moveToFirst()){
                do{
                    arrayEva.add(DBHelper.consultarEvaluacionesSolicitud(Evaluacion.getInt(0)));
                }while(Evaluacion.moveToNext());
                if(!(arrayEva.size()==0)){
                    arrayEva.add(0,"Seleccione evaluacion");
                    ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayEva);
                    evaluaciones.setAdapter(adaptador);
                }
            }else{
                Toast.makeText(this,"No tiene solicitud de segunda revisión",Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void eliminarSolicitud(View v){
        String eval;
        if(!(arrayEva.size()==0)){
            if(!(carnetSoli.getText().toString().equals("") | evaluaciones.getSelectedItem().toString().equals("Seleccione evaluacion"))){
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                eval = evaluaciones.getSelectedItem().toString();
                String[] evalPart = eval.split(" ");

                String mensaje = DBHelper.eliminarSolicitudSegundaRevision(Integer.valueOf(evalPart[0]),carnetSoli.getText().toString());
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Seleccione una evaluacion o ingrese un carnet para consultar", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Consulte evaluaciones o no tiene evaluaciones", Toast.LENGTH_SHORT).show();
        }


    }

    public void limpiarTexto(View v){
        carnetSoli.setText("");
        evaluaciones.setAdapter(null);
        arrayEva.clear();
    }
}