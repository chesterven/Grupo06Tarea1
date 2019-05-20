

package sv.edu.ues.fia.eisi.grupo06tarea1;




import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LogPrinter;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText et1,et2;
    DBHelperInicial DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //emperejamos las variable con el xml
        et1= (EditText) findViewById(R.id.usuario);
        et2= (EditText) findViewById(R.id.password);
        DBHelper = new DBHelperInicial(this);
        DBHelper.abrir();
        String mensaje = DBHelper.llenarBD();
        DBHelper.cerrar();
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
    //metodo de ingreso
    public void ingresar(View v){

        String usuario=et1.getText().toString();
        String contrasena=et2.getText().toString();
        if(usuario.equals("") | contrasena.equals("")){
            Toast.makeText(this, "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
        }else{
            DBHelper = new DBHelperInicial(this);
            DBHelper.abrir();
            boolean consulta = DBHelper.consultarUsuario(usuario,contrasena);
            if(consulta==true){


                Intent ven=new Intent(this,MenuCarnet.class);
                String id = DBHelper.consultarUsuarioAcceso(usuario,contrasena);
                ven.putExtra("idusuario",id);
                startActivity(ven);
                et1.setText("");
                et2.setText("");
            }

            else{
                Toast.makeText(this,"Usuario no encontrado", Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
            }
        }


    }

}
