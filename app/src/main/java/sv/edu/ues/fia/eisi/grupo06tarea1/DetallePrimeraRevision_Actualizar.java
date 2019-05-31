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

public class DetallePrimeraRevision_Actualizar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    Spinner revisiones;
    EditText carnetAct;
    EditText notaAnteriorAct,notaNueva,motivo;
    EditText codDocente;
    String siAlumno;
    Boolean existe;
    String idEvaluacion="";
    ArrayList<String> listaRevisiones = new ArrayList<>();
    String resultRevisiones="";
    Cursor evaluaciones;
    CheckBox asistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_primera_revision__actualizar);

        carnetAct = (EditText) findViewById(R.id.carnetDetallePrimeraAct);
        notaAnteriorAct=(EditText) findViewById(R.id.notaAntDetallePrimeraAct);
        notaNueva=(EditText)findViewById(R.id.notaDetallePrimeraAct);
        revisiones=(Spinner)findViewById(R.id.spinnerResultadosRevisionesEvaPrimeraAct);
        codDocente = (EditText) findViewById(R.id.codigoDocenteDetallePrimeraAct);
        motivo=(EditText)findViewById(R.id.motivoCambioDetallePrimeraAct);
        asistencia = (CheckBox) findViewById(R.id.asistenciaPrimeraRevisionAct);
    }

    public void consultarReviDetallePrimeraReviAct (View v)
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

    public  void actualizarDetallePrimeraRevision(View v)
    {
        if(!(listaRevisiones.size()==0))
        {
            if(revisiones.getSelectedItem().toString().equals("Seleccione su revision")|
                    carnetAct.getText().toString().equals("")|notaAnteriorAct.getText().toString().equals("")
                    |notaNueva.getText().toString().equals("")|motivo.getText().toString().equals(""))
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
                idEvaluacion = DBHelper.consultarPrimeraRevisionConId(revisionParte[0]);
                /*idSoli*/   siAlumno = DBHelper.consultarAlumnoSoliPrimeraRevisionAntesDetalle(Integer.valueOf(idEvaluacion), carnetAct.getText().toString());
                if(siAlumno.equals(""))
                {
                    Toast.makeText(this, "El estudiante no tiene una solicitud de segunda revision", Toast.LENGTH_SHORT).show();
                }
                else {

                    existe = DBHelper.consultarDetallePrimeraAntesDeAcciones(Integer.valueOf(revisionParte[0]), Integer.valueOf(siAlumno));


                    if (existe == true) {

                        DetallePrimeraRevision detalles = new DetallePrimeraRevision();
                        detalles.setIdSolicitudPrimerRevision(Integer.valueOf(siAlumno));
                        detalles.setIdPrimeraRevision(Integer.valueOf(revisionParte[0]));
                        detalles.setNotaOriginal(Float.valueOf(notaAnteriorAct.getText().toString()));
                        detalles.setNotaNueva(Float.valueOf(notaNueva.getText().toString()));
                        detalles.setMotivoCambio(motivo.getText().toString());
                        detalles.setAsitenciaPrimerRevision(asistencia.isChecked());

                        mensaje=DBHelper.actualizarDetallePrimeraRevision(detalles);

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
        notaAnteriorAct.setText("");
        notaNueva.setText("");
        motivo.setText("");
        codDocente.setText("");
        listaRevisiones.clear();
        revisiones.setAdapter(null);
    }
}
