//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

public class DiasNoHabiles_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText fechaIn;
    EditText cicloIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dias_no_habiles__insertar);
        fechaIn = (EditText) findViewById(R.id.fechaInsertar);
        cicloIn = (EditText) findViewById(R.id.cicloInsertar);
    }
    public void insertarDia(View v){
        if(fechaIn.getText().toString().equals("") | cicloIn.getText().toString().equals("") ){
            Toast.makeText(this,"Ingrese datos en los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            DiasNoHabiles dias = new DiasNoHabiles();
            dias.setFecha(fechaIn.getText().toString());
           int cicloRescatado = DBHelper.consultarCiclo(cicloIn.getText().toString());
            dias.setCiclo(cicloRescatado);
            String mensaje =DBHelper.insertarDia(dias);
            DBHelper.cerrar();
            Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
        }

    }
    public void limpiarTexto(View v) {
        fechaIn.setText("");
        cicloIn.setText("");

    }
}


