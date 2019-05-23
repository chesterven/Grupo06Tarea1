//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SolicitudImpresion_Insertar extends AppCompatActivity {

    DBHelperInicial DBHelper;

    EditText carnet;
    EditText codDocente;
    EditText cantExamenes;
    EditText hojasAnex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_impresion__insertar);

        carnet=(EditText) findViewById(R.id.editTextCarnetSolicitudImpresionInsertar);
        codDocente=(EditText) findViewById(R.id.editTextCodDocenteSolicitudImpresionInsertar);
        cantExamenes=(EditText) findViewById(R.id.editTextCantidadExamenesSolicitudImpresionInsertar);
        hojasAnex=(EditText) findViewById(R.id.editTextHojsAnexasSolicitudImpresionInsertar);

        carnet.setText("");
        codDocente.setText("");
        cantExamenes.setText("");
        hojasAnex.setText("");

        DBHelper = new DBHelperInicial(this);

    }

    public void insertarSolicitudImp(View v) {
        if (!codDocente.getText().toString().equals("") && !carnet.getText().toString().equals("")) {
            Toast.makeText(this, "No puede ingresar un carnet de alumno y docente al mismo tiempo", Toast.LENGTH_SHORT).show();
        } else {
            if (codDocente.getText().toString().equals("")) {
                if (carnet.getText().toString().equals("")) {
                    Toast.makeText(this, "Debe ingresar un carnet en la solicitud", Toast.LENGTH_SHORT).show();
                } else {
                    if (cantExamenes.getText().toString().equals("")) {
                        Toast.makeText(this, "Debe ingresar un valor para la cantidad de exámenes", Toast.LENGTH_SHORT).show();
                    } else {
                        if (hojasAnex.getText().toString().equals("")) {
                            Toast.makeText(this, "Debe ingresar un valor para la cantidad de hojas anexas", Toast.LENGTH_SHORT).show();
                        } else {
                            DBHelper.abrir();
                            Cursor c= DBHelper.consultarAlumnos(carnet.getText().toString().toUpperCase());
                            if(c.moveToFirst()){
                                final boolean a = c.getInt(c.getColumnIndex("instructor")) != 0;
                                if(a==true){
                                    String msj = DBHelper.insertarSolicitudImpresion(carnet.getText().toString().toUpperCase(), codDocente.getText().toString().toUpperCase(), cantExamenes.getText().toString(), hojasAnex.getText().toString());
                                    Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
                                    limpiarTexto();
                                }else{
                                    Toast.makeText(this, "El carnet ingresado no es instructor", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(this, "No se encontraron alumnos con el carnet ingresado", Toast.LENGTH_LONG).show();
                            }
                            DBHelper.cerrar();

                        }
                    }
                }
            } else {
                if (cantExamenes.getText().toString().equals("")) {
                    Toast.makeText(this, "Debe ingresar un valor para la cantidad de exámenes", Toast.LENGTH_SHORT).show();
                } else {
                    if (hojasAnex.getText().toString().equals("")) {
                        Toast.makeText(this, "Debe ingresar un valor para la cantidad de hojas anexas", Toast.LENGTH_SHORT).show();
                    } else {
                        DBHelper.abrir();
                        String msj = DBHelper.insertarSolicitudImpresion(carnet.getText().toString().toUpperCase(), codDocente.getText().toString().toUpperCase(), cantExamenes.getText().toString(), hojasAnex.getText().toString());
                        DBHelper.cerrar();
                        limpiarTexto();
                        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
    public void limpiarTexto(View v){
        carnet.setText("");
        codDocente.setText("");
        cantExamenes.setText("");
        hojasAnex.setText("");
    }
    public void limpiarTexto(){
        carnet.setText("");
        codDocente.setText("");
        cantExamenes.setText("");
        hojasAnex.setText("");
    }
}