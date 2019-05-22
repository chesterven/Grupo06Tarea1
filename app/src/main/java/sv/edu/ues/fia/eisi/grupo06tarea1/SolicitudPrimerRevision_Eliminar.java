//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudPrimerRevision_Eliminar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText idSoli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_primer_revision__eliminar);

        idSoli=(EditText)findViewById(R.id.idSolicitudPrimerRevisionEliminar);

        DBHelper=new DBHelperInicial(this);
    }

    public void eliminarSolicitudPrimerRevision(View v){
        if(idSoli.getText().toString().equals("")){
            Toast.makeText(this, "Debe ingresar un id para poder eliminar", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper.abrir();
            String msj=DBHelper.eliminarSolicitudPrimerRevision(idSoli.getText().toString());
            DBHelper.cerrar();
            limpiarTexto();
            Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v){
        idSoli.setText("");
    }

    public void limpiarTexto(){
        idSoli.setText("");
    }
}
