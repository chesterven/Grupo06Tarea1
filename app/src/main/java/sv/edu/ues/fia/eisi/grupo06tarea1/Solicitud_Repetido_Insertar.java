//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Solicitud_Repetido_Insertar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText carnet;
    Spinner spinnerResultado;
    ArrayList<String> evaluaciones = new ArrayList<String>();
    Cursor datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__repetido__insertar);
        spinnerResultado = (Spinner) findViewById(R.id.spinnerResultadosRepetido);
        carnet = (EditText) findViewById(R.id.carnetRepetidoIn);
    }

    public void consultarEvaluacuonesRepetido(View v){
        if(carnet.getText().toString().equals(""))
        {
            Toast.makeText(this,"Ingrese su carnet",Toast.LENGTH_SHORT).show();
        }
        else {
            evaluaciones.add("Seleccione evaluacion");
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            datos = DBHelper.consultarEstudianteInscrito(carnet.getText().toString());
            if (datos.moveToFirst()) {
                do {
                    evaluaciones.add(DBHelper.consultarEvaluaciones(datos.getInt(0), datos.getString(1), datos.getInt(2)));

                } while (datos.moveToNext());
                Toast.makeText(this, "Puede seleccionar una evaluacion", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No tiene Evaluaciones", Toast.LENGTH_SHORT).show();
            }
            ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, evaluaciones);
            spinnerResultado.setAdapter(adaptador);
        }
        }

        public void insertarSolicitudRepetido(View v){
            if(spinnerResultado.getSelectedItem().toString().equals("Seleccione evaluacion")){
                Toast.makeText(this,"Seleccione una evaluacion",Toast.LENGTH_SHORT).show();
            }else{
                String mensaje = "";
                String evaluacion;
                Solicitud_RepetidoDiferido solicitud = new Solicitud_RepetidoDiferido();
                evaluacion = spinnerResultado.getSelectedItem().toString();
                String [] evaluacionPartes = evaluacion.split(" ");
                solicitud.setCarnet(carnet.getText().toString());
                solicitud.setIdEvaluacion(Integer.valueOf(evaluacionPartes[0]));
                solicitud.setAprobado(false);
                solicitud.setIdTipoSolicitud(1);
                DBHelper.abrir();
                mensaje = DBHelper.insertarSolicitudDiferidoRepetido(solicitud);

                Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
            }
        }

    public void limpiarTexto(View v){
        carnet.setText("");

    }
}
