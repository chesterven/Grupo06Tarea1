//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SolicitudPrimerRevision_Actualizar extends AppCompatActivity {


    DBHelperInicial DBHelper;

    EditText carnet;
    EditText idEva;
    Spinner idSolicitud;
    CheckBox aprobado;

    ArrayList<String> listaSolicitudes=new ArrayList<>();
    ArrayAdapter<CharSequence> adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_primer_revision__actualizar);

        carnet=(EditText) findViewById(R.id.editTextCarnetSolicitudPrimerRevision);
        idSolicitud=(Spinner)findViewById(R.id.spinnerIdSolicitudPrimerRevisionActualizar);
        aprobado=(CheckBox)findViewById(R.id.checkBoxAprobadoSolicitudPrimerRevision);
        idEva=(EditText) findViewById(R.id.editTextIdEvaluacionSolicitudPrimerRevision);

        listaSolicitudes.add("Seleccione");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado=DBHelper.consultarSolicitudPrimerRevision();
        if(resultado.moveToFirst())
        {
            do{
                listaSolicitudes.add(resultado.getString(0));
            }while(resultado.moveToNext());
        }

        adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaSolicitudes);
        idSolicitud.setAdapter(adaptador);
        idSolicitud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    carnet.setText("");
                    idEva.setText("");
                    Cursor c= DBHelper.consultarSolicitudPrimerRevision(idSolicitud.getSelectedItem().toString());
                    if(c.moveToFirst()){
                        idEva.setText(c.getString(1));
                        carnet.setText(c.getString(2));
                        final boolean b = c.getInt(c.getColumnIndex("aprobado")) != 0;
                        if(b==true){
                            aprobado.setChecked(true);
                        }
                    }
                }
                else{
                    idEva.setText("");
                    carnet.setText("");
                    aprobado.setChecked(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void actualizarSolicitudPrimerRevision(View v){
        if(!carnet.getText().toString().equals("")){
            DBHelper.abrir();
            String msj= DBHelper.actualizarSolicitudPrimerRevision(idSolicitud.getSelectedItem().toString(),aprobado.isChecked());
            DBHelper.cerrar();
            Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
            limpiarTexto();
        }
        else{
            Toast.makeText(this, "Debe seleccionar una solicitud", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarTexto(View v){
        listaSolicitudes.clear();
        listaSolicitudes.add("Seleccione");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado=DBHelper.consultarSolicitudImpresion();
        if(resultado.moveToFirst())
        {
            do{
                listaSolicitudes.add(resultado.getString(0));
            }while(resultado.moveToNext());
        }

        adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaSolicitudes);
        idSolicitud.setAdapter(adaptador);
        carnet.setText("");
        idEva.setText("");
        aprobado.setChecked(false);

    }

    public void limpiarTexto(){
        listaSolicitudes.clear();
        listaSolicitudes.add("Seleccione");
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        Cursor resultado=DBHelper.consultarSolicitudImpresion();
        if(resultado.moveToFirst())
        {
            do{
                listaSolicitudes.add(resultado.getString(0));
            }while(resultado.moveToNext());
        }

        adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaSolicitudes);
        idSolicitud.setAdapter(adaptador);
        carnet.setText("");
        idEva.setText("");
        aprobado.setChecked(false);
    }

}
