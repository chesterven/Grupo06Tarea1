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

public class DetallePrimeraRevision_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    Spinner revisiones;
    EditText carnetIn,motivoCambio;
    CheckBox asistencia;
    EditText notaIn,notaAntIn;
    EditText codDocente;
    String siAlumno;
    String idEvaluacion="";
    ArrayList<String> listaRevisiones = new ArrayList<>();
    String resultRevisiones="";
    Cursor evaluaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_primera_revision__insertar);

        revisiones = (Spinner) findViewById(R.id.spinnerResultadosRevisionesEvaIn);
        carnetIn = (EditText) findViewById(R.id.carnetDetallePrimeraIn);
        asistencia = (CheckBox) findViewById(R.id.asistenciaPrimeraRevisionIn);
        notaIn = (EditText) findViewById(R.id.notaDetallePrimeraIn);
        notaAntIn = (EditText) findViewById(R.id.notaAntDetallePrimeraIn);
        codDocente = (EditText) findViewById(R.id.codigoDocenteDetallePrimeraIn);
        motivoCambio=(EditText)findViewById(R.id.motivoCambioDetallePrimeraIn);
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
                            resultRevisiones=(DBHelper.consultarPrimeraRevisionExiste(String.valueOf(evaluaciones.getInt(0))));
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
                    Toast.makeText(this,"No hay primeras revisiones para el docente", Toast.LENGTH_SHORT).show();
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

    public void insertarDetallePrimeraRevision(View v)
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
                idEvaluacion = DBHelper.consultarPrimeraRevisionConId(revisionParte[0]);
                siAlumno = DBHelper.consultarAlumnoSoliPrimeraRevisionAntesDetalle(Integer.valueOf(idEvaluacion), carnetIn.getText().toString());
                if (siAlumno.equals("")) {
                    Toast.makeText(this, "El estudiante no tiene una solicitud de primera revision", Toast.LENGTH_SHORT).show();
                } else {
                    DetallePrimeraRevision detalles = new DetallePrimeraRevision();
                    detalles.setIdPrimeraRevision(Integer.valueOf(revisionParte[0]));
                    detalles.setIdSolicitudPrimerRevision(Integer.valueOf(siAlumno));
                    detalles.setAsitenciaPrimerRevision(asistencia.isChecked());
                    detalles.setMotivoCambio(motivoCambio.getText().toString());
                    if (notaIn.getText().toString().equals("")||notaAntIn.getText().toString().equals("")||motivoCambio.getText().toString().equals(""))
                    {
                        Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        detalles.setNotaNueva(Float.valueOf(notaIn.getText().toString()));
                        detalles.setNotaOriginal(Float.valueOf(notaAntIn.getText().toString()));
                    }


                    mensaje = DBHelper.insertarDetallePrimeraRevision(detalles);
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
