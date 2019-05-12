package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoSolicitud_Consultar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText consulta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_solicitud__consultar);
        consulta = (EditText) findViewById(R.id.nombreConsultar);
        DBHelper = new DBHelperInicial(this);
    }

    public void consultarTipoSolicitud(View v){
        boolean encontrado;
        DBHelper.abrir();
        encontrado = DBHelper.consultarTipoSolicitud(consulta.getText().toString());
        if(encontrado==true){
            Toast.makeText(this,"Este tipo de solicitud se encuentra registrado", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"No se encontro en la base de datos", Toast.LENGTH_SHORT).show();
        }
    }
}
