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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DetalleSegundaRevision_Actualizar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    Spinner revisiones;
    EditText carnetAct;
    EditText notaAct;
    EditText codDocente;
    String siAlumno;
    Boolean existe;
    String idEvaluacion="";
    ArrayList<String> listaRevisiones = new ArrayList<>();
    String resultRevisiones="";
    Cursor evaluaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_segunda_revision__actualizar);
        carnetAct = (EditText) findViewById(R.id.carnetDetalleSegundaAct);
        notaAct=(EditText) findViewById(R.id.notaDetalleSegundaAct);
        revisiones=(Spinner)findViewById(R.id.spinnerResultadosRevisionesEvaluaAct);
        codDocente = (EditText) findViewById(R.id.codigoDocenteDetalleSegundaAct);

    }
    public void consultarReviSegundaReviDetalleAct (View v)
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
    public  void actualizarDetalleSegundaRevision(View v)
    {
        if(!(listaRevisiones.size()==0))
        {
            if(revisiones.getSelectedItem().toString().equals("Seleccione su revision")|
            carnetAct.getText().toString().equals("")|notaAct.getText().toString().equals(""))
            {
                Toast.makeText(this, "Debe ingresar todos los campos", Toast.LENGTH_SHORT).show();
            }
            else
            {
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                String revision = "";
                String mensaje = "";

                revision = revisiones.getSelectedItem().toString();
                String[] revisionParte = revision.split(" ");
                idEvaluacion = DBHelper.consultarSegundaRevisionConId(revisionParte[0]);
                /*idSoli*/   siAlumno = DBHelper.consultarAlumnoSoliSegundaRevisionAntesDetalle(Integer.valueOf(idEvaluacion), carnetAct.getText().toString());
                if(siAlumno.equals(""))
                {
                    Toast.makeText(this, "El estudiante no tiene una solicitud de segunda revision", Toast.LENGTH_SHORT).show();
                }
                else {

                    existe = DBHelper.consultarDetalleSegundaAntesDeAcciones(Integer.valueOf(revisionParte[0]), Integer.valueOf(siAlumno));


                    if (existe == true) {

                        DetalleSegundaRevision detalles = new DetalleSegundaRevision();
                    detalles.setIdSoliSegundaRevision(Integer.valueOf(siAlumno));
                    detalles.setId_Segunda_Revision(Integer.valueOf(revisionParte[0]));
                    detalles.setNota_SegRevision(Float.valueOf(notaAct.getText().toString()));

                    mensaje=DBHelper.actualizarDetalleSegundaRevision(detalles);

                        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();

                   } else {
                        Toast.makeText(this, "No existe ese detalle de segunda revisión", Toast.LENGTH_SHORT).show();
                    }
                    DBHelper.cerrar();
                }
            }
        }
        else
        {
            Toast.makeText(this, "No ha consultado las revisiones", Toast.LENGTH_SHORT).show();
        }


    }
    public void limpiarTexto(View v) {
        carnetAct.setText("");
        notaAct.setText("");
        codDocente.setText("");
        listaRevisiones.clear();
        revisiones.setAdapter(null);
    }
}
