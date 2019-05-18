//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DetalleSegundaRevision_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    Spinner revisiones;
    EditText carnetIn;
   CheckBox asistencia;
   EditText notaIn;
   EditText codDocente;
   ArrayList<String> listaRevisiones = new ArrayList<>();
   String resultRevisiones="";
   Cursor evaluaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_segunda_revision__insertar);
        revisiones = (Spinner) findViewById(R.id.spinnerResultadosRevisionesEvaluaIn);
        carnetIn = (EditText) findViewById(R.id.carnetDetalleSegundaIn);
        asistencia = (CheckBox) findViewById(R.id.asistenciaSegundaRevision);
        notaIn = (EditText) findViewById(R.id.notaDetalleSegundaIn);
        codDocente = (EditText) findViewById(R.id.codigoDocenteDetalleSegundaIn);
    }
    public void  consultarReviSegundaReviDetalleIn(View v)
    {
        String [] parteEvaluacion;
        String idEvaluacion="";
        if(!(codDocente.getText().toString().equals(""))  ) {
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            Cursor resul = DBHelper.consultarMateriasDocente(codDocente.getText().toString());
            if(resul.moveToFirst())
            {
                do {
                    evaluaciones = DBHelper.consultarEvaluaciones(resul.getInt(0), resul.getString(1), resul.getInt(2));
                    if(evaluaciones.moveToFirst())
                    {
                        do{
                            resultRevisiones=(DBHelper.consultarSegundaRevisionExiste(String.valueOf(evaluaciones.getInt(0))));
                            if(resultRevisiones.equals(""))
                            {

                            }
                            else {

                                listaRevisiones.add(resultRevisiones);


                            }}
                        while (evaluaciones.moveToNext());
                    }


                }while(resul.moveToNext());
                if(listaRevisiones.size()==0)
                {
                    Toast.makeText(this,"No hay segundas revisiones para el docente", Toast.LENGTH_SHORT).show();
                }
                else{
                    listaRevisiones.add(0,"Seleccione su revision");
                    ArrayAdapter<CharSequence> adaptadorr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaRevisiones);
                    revisiones.setAdapter(adaptadorr);
                    Toast.makeText(this, "Revisiones encontrados", Toast.LENGTH_SHORT).show();}
            }

            else{
                Toast.makeText(this,"No existe el código de ese docente", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(this,"Ingrese el código del docente", Toast.LENGTH_SHORT).show();
        }
    }
    public void insertarDetalleSegundaRevision(View v)
    {

    }
    public void limpiarTexto(View v) {
        carnetIn.setText("");
        notaIn.setText("");
        codDocente.setText("");
        listaRevisiones.clear();
        revisiones.setAdapter(null);
        notaIn.setText("");



    }

}
