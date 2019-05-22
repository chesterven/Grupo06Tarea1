//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudPrimerRevision_Consultar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText idSoli;
    EditText idEva;
    EditText carnet;
    CheckBox aprobado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_primer_revision__consultar);

        idSoli= (EditText) findViewById(R.id.idSolicitudPrimerRevisionConsultar);
        idEva= (EditText) findViewById(R.id.mostrarEvaluacionId);
        carnet= (EditText) findViewById(R.id.mostrarCarnetConsultar);
        aprobado= (CheckBox) findViewById(R.id.checkBoxAprobadoSolicitudPrimerRevision);

        DBHelper=new DBHelperInicial(this);
    }

    public void consultarPrimerRevision(View v){
        if(idSoli.getText().toString().equals("")){
            Toast.makeText(this, "Debe ingresar un id de solicitud", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper.abrir();
            Cursor c=DBHelper.consultarSolicitudPrimerRevision(idSoli.getText().toString());
            if(c.moveToFirst()){
                limpiarTexto();
                carnet.setText(c.getString(2));
                idEva.setText(c.getString(1));
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
        carnet.setText("");
        idEva.setText("");
        idSoli.setText("");
        aprobado.setChecked(false);
    }

    public void limpiarTexto(){
        carnet.setText("");
        idEva.setText("");
        aprobado.setChecked(false);
    }
}
