//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudImpresion_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText idSoli;
    EditText carnet;
    EditText codDocente;
    EditText cantExamenes;
    EditText hojasAnex;
    CheckBox realizada;
    CheckBox aprobado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_impresion__consultar);

        idSoli=(EditText) findViewById(R.id.editTextIdSolicitudImpresionConsultar);
        carnet=(EditText) findViewById(R.id.mostrarCarnetSolicitudImpresion);
        codDocente=(EditText) findViewById(R.id.mostrarCodDocenteSolicitudImpresion);
        cantExamenes=(EditText) findViewById(R.id.mostrarCantidadExamenesSolicitudImpresion);
        hojasAnex=(EditText) findViewById(R.id.mostrarHojasAnexasSolicitudImpresion);
        realizada= (CheckBox) findViewById(R.id.checkBoxRealizadoSolicitudImpresion);
        aprobado= (CheckBox) findViewById(R.id.checkBoxAprobadoSolicitudImpresion);

        DBHelper = new DBHelperInicial(this);
    }

    public void consultarImpresion(View v){
        if(idSoli.getText().toString().equals("")){
            Toast.makeText(this, "Debe ingresar un id de solicitud", Toast.LENGTH_SHORT).show();
        }
        else{
            carnet.setText("");
            codDocente.setText("");
            cantExamenes.setText("");
            hojasAnex.setText("");
            realizada.setChecked(false);
            aprobado.setChecked(false);
            DBHelper.abrir();
            Cursor c=DBHelper.consultarSolicitudImpresion(idSoli.getText().toString());
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
            else{
                Toast.makeText(this, "No se encontró solicitud con el id ingresado", Toast.LENGTH_SHORT).show();
            }
            DBHelper.cerrar();
        }
    }

    public void limpiarTexto(View v){
        idSoli.setText("");
        carnet.setText("");
        codDocente.setText("");
        cantExamenes.setText("");
        hojasAnex.setText("");
        realizada.setChecked(false);
        aprobado.setChecked(false);
    }
}