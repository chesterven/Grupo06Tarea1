package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoSolicitud_Actualizar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText anterior, nuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_solicitud__actualizar);
        DBHelper = new DBHelperInicial(this);
        anterior = (EditText) findViewById(R.id.nombreAnterior);
        nuevo = (EditText) findViewById(R.id.nombreActualizar);
    }

    public void actualizarTipoSolicitud(View v){
        DBHelper.abrir();
        String mensaje =DBHelper.actualizarTipoSolicitud(anterior.getText().toString(),nuevo.getText().toString());
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show();
    }
}
