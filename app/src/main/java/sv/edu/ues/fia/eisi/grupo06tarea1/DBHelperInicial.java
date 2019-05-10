package sv.edu.ues.fia.eisi.grupo06tarea1;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelperInicial {
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    private static final String DROP_TABLE1 = "DROP TABLE IF EXISTS USUARIO; ";
    private static final String DROP_TABLE2 = "DROP TABLE IF EXISTS OPCIONCRUD; ";
    private static final String DROP_TABLE3 = "DROP TABLE IF EXISTS ACCESOUSUARIO; ";


    public DBHelperInicial(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "Grupo06.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("create table USUARIO  (\n" +
                        "   IDUSUARIO            CHAR(2)                         not null,\n" +
                        "   NOMUSUARIO           VARCHAR2(30)                    not null,\n" +
                        "   CLAVE                CHAR(5)                         not null);");
                db.execSQL("create table OPCIONCRUD  (\n" +
                        "   IDOPCION             CHAR(3)                         not null,\n" +
                        "   DESOPCION            VARCHAR2(30)                    not null,\n" +
                        "   NUMCRUD              INTEGER                         not null);");
                db.execSQL("create table ACCESOUSUARIO  (\n" +
                        "   IDUSUARIO            CHAR(2)                         not null,\n" +
                        "   IDOPCION             CHAR(3)                         not null);");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                //Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE1);
                db.execSQL(DROP_TABLE2);
                db.execSQL(DROP_TABLE3);
                onCreate(db);
            } catch (Exception e) {
                //Message.message(context,""+e);
            }
        }
    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar() {
        DBHelper.close();
    }

    public boolean consultarUsuario(String user, String clave) {

        String usua="", pass="", idUsuario="";
        Cursor fila;
        String[] columna = {"IDUSUARIO", "NOMUSUARIO", "CLAVE"};
        String[] parametro = {user, clave};
        fila = db.query("Usuario", columna, "NOMUSUARIO=? AND CLAVE=?", parametro, null, null, null, null);
        if (fila.moveToFirst() == true) {
            idUsuario = fila.getString(0);
            usua = fila.getString(1);
            pass = fila.getString(2);
        }
        if (user.equals(usua) && clave.equals(pass)) {
            return true;
        }
        else
            return false;
    }

    public String consultarUsuarioAcceso(String user, String clave){
        String usua="", pass="", idUsuario="";
        Cursor fila;
        String[] columna = {"IDUSUARIO", "NOMUSUARIO", "CLAVE"};
        String[] parametro = {user, clave};
        fila = db.query("Usuario", columna, "NOMUSUARIO=? AND CLAVE=?", parametro, null, null, null, null);
        if (fila.moveToFirst() == true) {
            idUsuario = fila.getString(0);
            usua = fila.getString(1);
            pass = fila.getString(2);
        }
        return idUsuario;
    }

    public String llenarUsuarios(){
        abrir();
        db.execSQL("DELETE FROM USUARIO");
        db.execSQL("DELETE FROM OPCIONCRUD");
        db.execSQL("DELETE FROM ACCESOUSUARIO");

        db.execSQL("INSERT INTO USUARIO(IDUSUARIO,NOMUSUARIO,CLAVE) VALUES ('01','ADMIN','01234');");
        db.execSQL("INSERT INTO USUARIO(IDUSUARIO,NOMUSUARIO,CLAVE) VALUES ('02','PROFESOR','56789');");
        db.execSQL("INSERT INTO USUARIO(IDUSUARIO,NOMUSUARIO,CLAVE) VALUES ('03','ALUMNO','ABCDE');");
        db.execSQL("INSERT INTO USUARIO(IDUSUARIO,NOMUSUARIO,CLAVE) VALUES ('04','ALUMNO_INSTRUCTOR','00000');");

        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('001','TABLA PARAMETROS','1');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('002','TABLA EVALUACIONES','2');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('003','TABLA NOTA_ESTUDIANTE_EVALUACION','3');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('004','TABLA SOLICITUD PRIMERA REVISION','4');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('005','TABLA SOLICITUD SEGUNDA REVISION ','5');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('006','TABLA DE SOLICITUDES','6');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('007','TABLA PRIMERA REVISION','7');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('008','TABLA SEGUNDA REVISION','8');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('009','TABLA SOLICITUD IMPRESION','9');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('010','TABLA DIAS NO HABILES','10');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('011','TABLA DETALLE PRIMERA','11');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('012','TABLA DETALLE SEGUNDA','12');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('013','TABLA MATERIA_CICLO','13');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('014','TABLA SOLICITUD_EVALUACION','14');");
        db.execSQL("INSERT INTO OPCIONCRUD (IDOPCION,DESOPCION,NUMCRUD) VALUES ('015','TABLA TIPO SOLICITUD','15');");


        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','001');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','002');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','003');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','004');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','005');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','006');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','007');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','008');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','009');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','011');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','012');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','013');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('02','014');");


        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('03','004');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('03','005');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('03','006');");

        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('04','009');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('04','004');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('04','005');");
        db.execSQL("INSERT INTO ACCESOUSUARIO(IDUSUARIO,IDOPCION) VALUES ('04','006');");

        return "Usuarios Guardados";
    }

    public ArrayList<String> consultarAccesos(String user){
        ArrayList<String> accesos = new ArrayList<String>();
        Cursor fila;
        String[] columna = {"IDOPCION"};
        String[] parametro = {user};
        fila = db.query("ACCESOUSUARIO",columna,"IDUSUARIO=?",parametro,null,null,null,null);
        if (fila.moveToFirst()){
            do{
                accesos.add(fila.getString(0));
            }while(fila.moveToNext());
        }
        return accesos;
    }
}






