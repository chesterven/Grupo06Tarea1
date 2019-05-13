//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoSolicitud_Eliminar extends AppCompatActivity {
    DBHelperInicial DBHelper;
    EditText nombreEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_solicitud__eliminar);
        DBHelper = new DBHelperInicial(this);
        nombreEliminar = (EditText) findViewById(R.id.tipoEliminar);
    }

    public void eliminarTipo(View v){
        DBHelper.abrir();
        String mensaje = DBHelper.eliminarTipoSolicitud(nombreEliminar.getText().toString());
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        nombreEliminar.setText("");
    }
}
