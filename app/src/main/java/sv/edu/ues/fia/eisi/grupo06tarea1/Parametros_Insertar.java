//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class Parametros_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;
    EditText nombre;
    EditText dias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros__insertar);
        nombre = (EditText) findViewById(R.id.nombInsertar);
        dias = (EditText) findViewById(R.id.diasInsertar);
    }
    public void insertarParametro(View v){
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();

        if(nombre.getText().toString().equals("") | dias.getText().toString().equals("") ){
            Toast.makeText(this,"Ingrese datos en los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper.insertarTipoParametro(nombre.getText().toString());
            Parametros param = new Parametros();
            param.setCantidad_Dias(Integer.parseInt(dias.getText().toString()));
            int nombreTipoParametroRescatado = DBHelper.consultarParametro(nombre.getText().toString());
            param.setnombreTipoParametro(nombreTipoParametroRescatado);
            String mensaje = DBHelper.insertarParametro(param);
            DBHelper.cerrar();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

        }

    }
    public void limpiarTexto(View v) {
        nombre.setText("");
        dias.setText("");

    }
}
