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
   String siAlumno;
   String idEvaluacion="";
   ArrayList<String> listaRevisiones = new ArrayList<>();
   String resultRevisiones="";
   Cursor evaluaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_segunda_revision__insertar);
        revisiones = (Spinner) findViewById(R.id.spinnerResultadosRevisionesEvaluaIn);
        carnetIn = (EditText) findViewById(R.id.carnetDetalleSegundaIn);
        asistencia = (CheckBox) findViewById(R.id.asistenciaSegundaRevisionIn);
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
        if(!(listaRevisiones.size()==0))
        {
            if(revisiones.getSelectedItem().toString().equals("Seleccione su revision")|
           carnetIn.getText().toString().equals("") )
            {
                Toast.makeText(this, "Debe seleccionar una revision e ingresar su carnet", Toast.LENGTH_SHORT).show();
            }

       else {
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String revision = "";
                String mensaje = "";
                revision = revisiones.getSelectedItem().toString();
                String[] revisionParte = revision.split(" ");
                idEvaluacion = DBHelper.consultarSegundaRevisionConId(revisionParte[0]);
                siAlumno = DBHelper.consultarAlumnoSoliSegundaRevisionAntesDetalle(Integer.valueOf(idEvaluacion), carnetIn.getText().toString());
                if (siAlumno.equals("")) {
                    Toast.makeText(this, "El estudiante no tiene una solicitud de segunda revision", Toast.LENGTH_SHORT).show();
                } else {
                    DetalleSegundaRevision detalles = new DetalleSegundaRevision();
                    detalles.setId_Segunda_Revision(Integer.valueOf(revisionParte[0]));
                    detalles.setIdSoliSegundaRevision(Integer.valueOf(siAlumno));
                    detalles.setAsitencia_SegRevision(asistencia.isChecked());
                    if (notaIn.getText().toString().equals(""))
                    {

                    }
                    else
                    {
                        detalles.setNota_SegRevision(Float.valueOf(notaIn.getText().toString()));
                    }


                    mensaje = DBHelper.insertarDetalleSegundaRevision(detalles);
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            Toast.makeText(this, "No ha consultado las revisiones", Toast.LENGTH_SHORT).show();
        }



    }
    public void Limpiar(View v) {
        carnetIn.setText("");
        notaIn.setText("");
        codDocente.setText("");
        listaRevisiones.clear();
        revisiones.setAdapter(null);

    }

}
