//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DiasNoHabiles_Consultar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText fechaConsultar;
    EditText fechaMostrar;
    EditText ciclo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles__consultar);
        DBHelper = new DBHelperInicial(this);
        fechaConsultar = (EditText) findViewById(R.id.fechaConsultar);
        fechaMostrar=(EditText) findViewById(R.id.fechaMostrarDias);
        ciclo = (EditText) findViewById(R.id.CicloMostrarDias);
    }
    public void consultarDia(View v)
    {
        if(fechaConsultar.getText().toString().equals("") ){
            Toast.makeText(this,"Ingrese datos en los campos", Toast.LENGTH_SHORT).show();
        }
        else {
            DBHelper.abrir();

            DiasNoHabiles dias= DBHelper.consultarDia(fechaConsultar.getText().toString());
            String resultado= DBHelper.consultarCicloString(dias.getCiclo());
            DBHelper.cerrar();

            String mensaje="Se encontro el registro";
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

            fechaMostrar.setText(dias.getFecha());
            ciclo.setText(resultado);

        }
    }
    public void limpiarTexto(View v) {
        fechaConsultar.setText("");
        fechaMostrar.setText("");
        ciclo.setText("");

    }


}
