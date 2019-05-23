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

public class SolicitudSegundaRevision_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnetSoli,motivo;
    CheckBox aprobado;
    Cursor Evaluacion;
    Spinner evaluaciones;
    SolicitudSegundaRevision solicitud = new SolicitudSegundaRevision();
    ArrayList<String> arrayEva=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_segunda_revision__actualizar);

        carnetSoli=(EditText)findViewById(R.id.carnetSoliSegundaAct);
        aprobado=(CheckBox)findViewById(R.id.checkBoxAprobadoSoliSegunda);
        evaluaciones=(Spinner)findViewById(R.id.spinnerEvaSoliSegundaAct);
        motivo=(EditText)findViewById(R.id.motivoSoliSegundaAct);
    }

    public void consultarEvaluacionSolicitudSegundaAct(View v){
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
                Toast.makeText(this,"No hay revisiones",Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void actualizarSolicitudSegunda(View v){
        String mensaje="";
        if(!(arrayEva.size()==0)){
            if(!(carnetSoli.getText().toString().equals("") | evaluaciones.getSelectedItem().toString().equals("Seleccione evaluacion"))){
                DBHelper = new DBHelperInicial(this);
                DBHelper.abrir();
                solicitud.setCarnet(carnetSoli.getText().toString());
                String eval = evaluaciones.getSelectedItem().toString();
                String[]evalPart = eval.split(" ");
                solicitud.setIdEvaluacion(Integer.valueOf(evalPart[0]));
                solicitud.setMotivo(motivo.getText().toString());
                if(aprobado.isChecked()){
                    solicitud.setAprobado(true);
                }
                else{
                    solicitud.setAprobado(false);
                }
                mensaje = DBHelper.actualizarSolicitudSegundaRevision(solicitud);
                Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Llene los campos necesarios",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Consulte evaluciones",Toast.LENGTH_SHORT).show();
        }

    }

    public void limpiarTexto(View v){
        carnetSoli.setText("");
        motivo.setText("");
        aprobado.setChecked(false);
        arrayEva.clear();
        evaluaciones.setAdapter(null);
    }
}
