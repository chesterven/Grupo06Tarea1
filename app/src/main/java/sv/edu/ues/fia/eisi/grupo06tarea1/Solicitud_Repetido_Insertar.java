package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Solicitud_Repetido_Insertar extends AppCompatActivity {

    String accesos = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud__repetido__insertar);
        Intent recibir = getIntent();
        accesos = recibir.getStringExtra("idusuario");

        EditText codMateria;
        codMateria = (EditText) findViewById(R.id.codMateria);
        codMateria.setText(accesos);
    }

}
