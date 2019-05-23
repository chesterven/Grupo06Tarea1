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

public class SolicitudImpresion_Actualizar extends AppCompatActivity {

    DBHelperInicial DBHelper;

    EditText carnet;
    EditText codDocente;
    EditText cantExamenes;
    EditText hojasAnex;
    Spinner idImpresion;
    CheckBox aprobado;
    CheckBox realizada;

    ArrayList<String> listaSolicitudes=new ArrayList<>();
    ArrayAdapter<CharSequence> adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_impresion__actualizar);

        carnet=(EditText) findViewById(R.id.editTextCarnetSolicitudImpresion);
        codDocente=(EditText) findViewById(R.id.editTextCodDocenteSolicitudImpresion);
        cantExamenes=(EditText) findViewById(R.id.editTextCantidadExamenesSolicitudImpresion);
        hojasAnex=(EditText) findViewById(R.id.editTextHojsAnexasSolicitudImpresion);
        aprobado=(CheckBox)findViewById(R.id.checkBoxAprobadoSolicitudImpresion);
        realizada=(CheckBox)findViewById(R.id.checkBoxRealizadoSolicitudImpresion);
        idImpresion=(Spinner)findViewById(R.id.spinnerIdSolicitudImpresionActualizar);


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
        idImpresion.setAdapter(adaptador);
        idImpresion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    carnet.setText("");
                    codDocente.setText("");
                    cantExamenes.setText("");
                    hojasAnex.setText("");
                    realizada.setChecked(false);
                    aprobado.setChecked(true);
                    Cursor c= DBHelper.consultarSolicitudImpresion(idImpresion.getSelectedItem().toString());
                    if(c.moveToFirst()){
                        carnet.setText(c.getString(1));
                        codDocente.setText(c.getString(2));
                        cantExamenes.setText(String.valueOf(c.getInt(3)));
                        hojasAnex.setText(String.valueOf(c.getInt(4)));
                        final boolean a = c.getInt(c.getColumnIndex("realizada")) != 0;
                        if(a==true){
                            realizada.setChecked(true);
                        }
                        final boolean b = c.getInt(c.getColumnIndex("aprobado")) != 0;
                        if(b==true){
                            aprobado.setChecked(true);
                        }
                    }
                }
                else{
                    codDocente.setText("");
                    carnet.setText("");
                    cantExamenes.setText("");
                    hojasAnex.setText("");
                    realizada.setChecked(false);
                    aprobado.setChecked(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void actualizarSolicitudImpresion(View v){
        if(cantExamenes.getText().toString().equals("")){
            Toast.makeText(this, "Debe seleccionar una solicitud", Toast.LENGTH_SHORT).show();
        }
        else{
            if(realizada.isChecked() && !aprobado.isChecked()){
                Toast.makeText(this, "No puede haber realizado una impresión sin ser aprobada", Toast.LENGTH_SHORT).show();
            }
            else{
                DBHelper.abrir();
                String msj= DBHelper.actualizarSolicitudImpresion(idImpresion.getSelectedItem().toString(),realizada.isChecked(),aprobado.isChecked());
                DBHelper.cerrar();
                Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
                limpiarTexto();
            }
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
        idImpresion.setAdapter(adaptador);

        carnet.setText("");
        codDocente.setText("");
        cantExamenes.setText("");
        hojasAnex.setText("");
        aprobado.setChecked(false);
        realizada.setChecked(false);
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
        idImpresion.setAdapter(adaptador);

        carnet.setText("");
        codDocente.setText("");
        cantExamenes.setText("");
        hojasAnex.setText("");
        aprobado.setChecked(false);
        realizada.setChecked(false);
    }
}