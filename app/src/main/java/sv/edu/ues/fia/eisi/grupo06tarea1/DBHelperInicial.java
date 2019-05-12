package sv.edu.ues.fia.eisi.grupo06tarea1;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;


public class DBHelperInicial {

    //ARREGLOS PARA CONSULTAR
    private static final String [] camposDia = new String [] {"fecha","ciclo"};
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    private static final String DROP_TABLE1 = "DROP TABLE IF EXISTS USUARIO; ";
    private static final String DROP_TABLE2 = "DROP TABLE IF EXISTS OPCIONCRUD; ";
    private static final String DROP_TABLE3 = "DROP TABLE IF EXISTS ACCESOUSUARIO; ";
    private static final String DROP_TABLE4 = "DROP TABLE IF EXISTS TipoSolicitud";
    private static final String DROP_TABLE5 = "DROP TABLE IF EXISTS DiasNoHabiles";
    private static final String DROP_TABLE6 = "DROP TABLE IF EXISTS Ciclo";
    private static final String DROP_TABLE7 = "DROP TABLE IF EXISTS Estudiante";
    private static final String DROP_TABLE8 = "DROP TABLE IF EXISTS Docente";
    private static final String DROP_TABLE9 = "DROP TABLE IF EXISTS Local";
    private static final String DROP_TABLE10 = "DROP TABLE IF EXISTS Materia";
    private static final String DROP_TABLE11 = "DROP TABLE IF EXISTS TipoGrupo";
    private static final String DROP_TABLE12 = "DROP TABLE IF EXISTS MateriaCiclo";

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
            //TABLAS PARA ROLES DE USUARIOS AUTOR: ROBERTO ELIEZER VENTURA DOMINGUEZ
            try {
                //Autor: Roberto Eliezer Ventura Dominguez
                //Carnet: VD16006
                db.execSQL("CREATE TABLE USUARIO  (\n" +
                        "   IDUSUARIO            CHAR(2)                         not null,\n" +
                        "   NOMUSUARIO           VARCHAR2(30)                    not null,\n" +
                        "   CLAVE                CHAR(5)                         not null);");
                db.execSQL("CREATE TABLE OPCIONCRUD  (\n" +
                        "   IDOPCION             CHAR(3)                         not null,\n" +
                        "   DESOPCION            VARCHAR2(30)                    not null,\n" +
                        "   NUMCRUD              INTEGER                         not null);");
                db.execSQL("CREATE TABLE ACCESOUSUARIO  (\n" +
                        "   IDUSUARIO            CHAR(2)                         not null,\n" +
                        "   IDOPCION             CHAR(3)                         not null);");
                db.execSQL("CREATE TABLE TipoSolicitud (\n" +
                        " idTipoSolicitud INTEGER NOT NULL PRIMARY KEY,\n" +
                        " nombreTipoSolicitud VARCHAR2(30) not null)");
                db.execSQL("CREATE TABLE Estudiante (\n" +
                        "    carnet VARCHAR2(7) NOT NULL PRIMARY KEY,\n" +
                        "    nombreEstudiante VARCHAR2(30) NOT NULL,\n" +
                        "    apellidoEstudiante VARCHAR2(30) NOT NULL,\n" +
                        "    instructor BOOLEAN\n" +
                        ");");
                db.execSQL("CREATE TABLE Docente (\n" +
                        "    codDocente VARCHAR2(7) NOT NULL PRIMARY KEY,\n" +
                        "    nombreDocente VARCHAR2(30) NOT NULL,\n" +
                        "    apellidoDocente VARCHAR2(30)NOT NULL,\n" +
                        "    director BOOLEAN\n" +
                        ");");
                db.execSQL("CREATE TABLE Local(\n" +
                        "    idLocal INTEGER NOT NULL PRIMARY KEY,\n" +
                        "    nombreLocal VARCHAR2(20) NOT NULL\n" +
                        ");");
                db.execSQL("CREATE TABLE Materia (\n" +
                        "    codMateria VARCHAR2(5) NOT NULL PRIMARY KEY,\n" +
                        "    nombreMateria VARCHAR2(20) NOT NULL\n" +
                        ");");
                db.execSQL("CREATE TABLE TipoGrupo (\n" +
                        "    idTipoGrupo INTEGER NOT NULL PRIMARY KEY,\n" +
                        "    nombreGrupo VARCHAR2(25) NOT NULL\n" +
                        ");");
                db.execSQL("CREATE TABLE  MateriaCiclo(\n" +
                        "    numGrupo INTEGER NOT NULL,\n" +
                        "     codMateria VARCHAR2(5) NOT NULL,\n" +
                        "    idCiclo INTEGER NOT NULL,\n" +
                        "    codDocente VARCHAR2(7) NOT NULL,\n" +
                        "    idTipoGrupo INTEGER NOT NULL,\n" +
                        "    PRIMARY KEY(numGrupo, codMateria, idCiclo),\n" +
                        "    CONSTRAINT FKcodMateria FOREIGN KEY(codMateria) REFERENCES Materia(codMateria) ON DELETE RESTRICT,\n" +
                        "    CONSTRAINT FKTipoGrupo FOREIGN KEY(idTipoGrupo) REFERENCES TipoGrupo(idTipoGrupo) ON DELETE RESTRICT\n" +
                        ");");


                //Autor: Maria Abigail Gil Cordov
                //Carnet: GC16001
                db.execSQL("CREATE TABLE Ciclo (\n" +
                        "   idCiclo            INTEGER NOT NULL PRIMARY KEY,\n" +
                        "   ciclo             VARCHAR(6) NOT NULL\n" +
                        ");");
                db.execSQL("CREATE TABLE DiasNoHabiles  (\n" +
                        "   idDia              INTEGER NOT NULL PRIMARY KEY,\n" +
                        "   idCiclo             INTEGER NOT NULL ,\n" +
                        "   fecha               VARCHAR2(15)NOT NULL,\n" +
                        "   CONSTRAINT f_k_ciclo FOREIGN KEY (idCiclo) REFERENCES Ciclo(idCiclo) ON DELETE RESTRICT\n" +
                        ");");

                //Autor: Christian Ariel Zelaya Tejada
                //Carnet: ZT12002

                db.execSQL("CREATE TABLE TipoEvaluacion (\n" +
                        "   idTipoEvaluacion NUMBER(6) NOT NULL PRIMARY KEY,\n" +
                        "   nombreTipoEvaluacion VARCHAR(25) NOT NULL"  +
                        ");");



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
                db.execSQL(DROP_TABLE4);
                db.execSQL(DROP_TABLE5);
                db.execSQL(DROP_TABLE6);
                db.execSQL(DROP_TABLE7);
                db.execSQL(DROP_TABLE8);
                db.execSQL(DROP_TABLE9);
                db.execSQL(DROP_TABLE10);
                db.execSQL(DROP_TABLE11);
                db.execSQL(DROP_TABLE12);

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

    public boolean consultarUsuario(String user, String clave) { //AUTOR:ROBERTO ELIEZER VENTURA

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

    public String consultarUsuarioAcceso(String user, String clave){ //AUTOR:ROBERTO ELIEZER VENTURA
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

    public String llenarBD(){
        abrir();
        //AUTOR:ROBERTO ELIEZER VENTURA DOMINGUEZ
        db.execSQL("DELETE FROM USUARIO");
        db.execSQL("DELETE FROM OPCIONCRUD");
        db.execSQL("DELETE FROM ACCESOUSUARIO");

        db.execSQL("INSERT INTO USUARIO(IDUSUARIO,NOMUSUARIO,CLAVE) VALUES ('01','ADMIN','01234');");
        db.execSQL("INSERT INTO USUARIO(IDUSUARIO,NOMUSUARIO,CLAVE) VALUES ('02','PROFESOR','56789');");
        db.execSQL("INSERT INTO USUARIO(IDUSUARIO,NOMUSUARIO,CLAVE) VALUES ('03','ESTUDIANTE','ABCDE');");
        db.execSQL("INSERT INTO USUARIO(IDUSUARIO,NOMUSUARIO,CLAVE) VALUES ('04','ESTUDIANTE_INSTRUCTOR','00000');");

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

        db.execSQL("DELETE FROM TipoSolicitud");
        db.execSQL("INSERT INTO TipoSolicitud(nombreTipoSolicitud) VALUES ('Repetido');");

        db.execSQL("DELETE FROM Estudiante");
        db.execSQL("INSERT INTO Estudiante VALUES('VD16006','Roberto','Ventura',0);");
        db.execSQL("INSERT INTO Estudiante VALUES('GC16001','Abigail','Gil',1);");

        db.execSQL("DELETE FROM Docente");
        db.execSQL("INSERT INTO Docente VALUES('GR00001','Cesar Augusto','Gonzalez Rodriguez',1)");
        db.execSQL("INSERT INTO Docente VALUES('MM00001','Boris Alexander','Montano',0)");


        db.execSQL("DELETE FROM Local");
        db.execSQL("INSERT INTO Local(nombreLocal) VALUES ('D11');");
        db.execSQL("INSERT INTO Local(nombreLocal) VALUES ('C11');");
        db.execSQL("INSERT INTO Local(nombreLocal) VALUES ('B21');");
        db.execSQL("INSERT INTO Local(nombreLocal) VALUES ('LCOMP1');");

        db.execSQL("DELETE FROM Materia");
        db.execSQL("INSERT INTO Materia VALUES ('MAT115','Matematica 1');");
        db.execSQL("INSERT INTO Materia VALUES ('PDM115','Programacion Dispositivos Moviles');");
        db.execSQL("INSERT INTO Materia VALUES ('SYP115','Sistemas y Procedimientos');");
        db.execSQL("INSERT INTO Materia VALUES ('MIP115','Microprogramacion');");

        db.execSQL("DELETE FROM Tipogrupo");
        db.execSQL("INSERT INTO TipoGrupo(nombreGrupo) VALUES ('Teorico');");
        db.execSQL("INSERT INTO TipoGrupo(nombreGrupo) VALUES ('Laboratorio');");
        db.execSQL("INSERT INTO TipoGrupo(nombreGrupo) VALUES ('Discusion');");

        db.execSQL("DELETE FROM MateriaCiclo");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (1,'MAT115',1,'MM00001',1);");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (1,'PDM115',1,'GR00001',2);");






        //Autor: Maria Abigail Gil Cordova
        db.execSQL("DELETE FROM Ciclo");
        db.execSQL("DELETE FROM DiasNoHabiles");
        db.execSQL("INSERT INTO Ciclo (ciclo) VALUES ('I2019');");
        db.execSQL("INSERT INTO Ciclo (ciclo) VALUES ('II2019');");
        db.execSQL("INSERT INTO Ciclo (ciclo) VALUES ('I2020');");
        db.execSQL("INSERT INTO Ciclo (ciclo) VALUES ('II2020');");

        //Autor" Christian Ariel Zelaya Tejada
        db.execSQL("INSERT INTO TipoEvaluacion (idTipoEvaluacion,nombreTipoEvaluacion) VALUES ('01','Ordinaria');");
        db.execSQL("INSERT INTO TipoEvaluacion (idTipoEvaluacion,nombreTipoEvaluacion) VALUES ('02','Repetida');");
        db.execSQL("INSERT INTO TipoEvaluacion (idTipoEvaluacion,nombreTipoEvaluacion) VALUES ('03','Diferida');");
        db.execSQL("INSERT INTO TipoEvaluacion (idTipoEvaluacion,nombreTipoEvaluacion) VALUES ('04','Suficiencia');");

        return "Usuarios Guardados";
    }

//********************Autor: Maria Abigail Gil Cordova********************
//*******************Carnet: GC16001********************

    //Metodo para consulta la integridad de la tabla de dia no habil
public boolean consultarDiaNoHabilIntegridad(String fecha){
    String[] parametro = {fecha};
    String[] columna = {"fecha"};
    Cursor c = db.query("DiasNoHabiles",columna,"fecha=?",parametro,null,null,null);
    if(c.moveToFirst()){
        return true;
    }
    else {
        return false;
    }

}
//Metodo que recibe el ciclo en string ejemplo I2019 y regresa su respectivo id al consultar en su tabla Ciclo
    public int  consultarCiclo(String ciclo){
        String[] parametro = {ciclo};
        String[] columna = {"idCiclo"};
        Cursor c = db.query("Ciclo",columna,"ciclo=?",parametro,null,null,null);

        if(c.moveToFirst()){
            do{
                int idCiclo=c.getInt(0);
                return idCiclo;
            }while(c.moveToNext());

        }
        else {
            return 0;
        }

    }
    public String  consultarCicloString(int cicloCon){
        String cicloConvert= Integer.toString(cicloCon);
        String [] parametro = {cicloConvert};
        String[] columna = {"ciclo"};
        Cursor c = db.query("Ciclo",columna,"idCiclo=?",parametro,null,null,null);

        if(c.moveToFirst()){
            do{
                String cicloResul=c.getString(0);
                return cicloResul;
            }while(c.moveToNext());

        }
        else {
            String mensaje="No existe ese ciclo";
            return mensaje;
        }

    }
//Metodo para insertar un dia no habil
    public String insertarDia(DiasNoHabiles dia){
        String mensaje="La fecha ya existe, inserte otra";
        boolean existe=consultarDiaNoHabilIntegridad(dia.getFecha());
       if(existe==false){
            String regInsertados="Registro Insertado Nº= ";
            long contador=0;
            ContentValues dias = new ContentValues();
            dias.put("idCiclo", dia.getCiclo());
            dias.put("fecha", dia.getFecha());
            contador=db.insert("DiasNoHabiles", null, dias);
            if(contador==-1 || contador==0)
            {
                regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            }
            else {
                regInsertados=regInsertados+contador;
            }
            return regInsertados;
        }
       else
           return mensaje;
        }
//Metodo para eliminar el dia no habil

public String eliminarDia(String fecha){
    String regAfectados="filas afectadas=";
    int contador = 0;
    String[] parametro = {fecha};
    boolean existe = consultarDiaNoHabilIntegridad(fecha);
    if(existe == true){
        contador+=db.delete("DiasNoHabiles","fecha=?",parametro);
        regAfectados+=contador;
        return regAfectados;
    }
    else{
        return "No existe ese día no hábil";
    }
}
//Metodo para consultar dia no habil
public DiasNoHabiles consultarDia(String fechaCon){

    String[] parametro = {fechaCon};
    String[] columna = {"idCiclo","fecha"};
    Cursor cursor = db.query("DiasNoHabiles", columna, "fecha = ?", parametro, null, null, null);
    if(cursor.moveToFirst()){
        DiasNoHabiles dias = new DiasNoHabiles();
        dias.setCiclo(cursor.getInt(0));
        dias.setFecha(cursor.getString(1));
        return dias;
    }else{ return null;
    }
}
//Metodo para actualizar el dia no habil
public String actualizarDia(String fechaAnterior, String fechaNueva, int cicloNuevo)
{
Boolean existe = consultarDiaNoHabilIntegridad(fechaAnterior);
    if(existe == true)
    {

        String[] id = {fechaAnterior};
        ContentValues cv = new ContentValues();
        cv.put("fecha",fechaNueva);
        cv.put("idCiclo",cicloNuevo);
        db.update("DiasNoHabiles", cv, "fecha = ?", id);
        return "Registro Actualizado Correctamente";
    }
    else
   {
        return "El registro que ingreso no existe";
    }
}


    //********************Autor: Roberto Eliezer Ventura Dominguez********************
//*******************Carnet: VD16006********************
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

    //Metodo Ingresar Tipo Solicitud
    public String insertarTipoSolicitud(String nombre){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues tipoSoli = new ContentValues();
        tipoSoli.put("nombreTipoSolicitud", nombre);
        contador=db.insert("TipoSolicitud", null, tipoSoli);
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;

    }

    //Metodo de consultar tipo Solicitud
    public boolean consultarTipoSolicitud(String nombre){
        String[] parametro = {nombre};
        String[] columna = {"nombreTipoSolicitud"};
        Cursor c = db.query("TipoSolicitud",columna,"nombreTipoSolicitud=?",parametro,null,null,null);
        if(c.moveToFirst()){
            return true;
        }
        else {
            return false;
        }

    }

    public String actualizarTipoSolicitud(String anterior, String nuevo){
        boolean existe = consultarTipoSolicitud(anterior); //Verificar Integrida
        if(existe==true){
            String[] nombreActual = {anterior};
            ContentValues cv = new ContentValues();
            cv.put("nombreTipoSolicitud",nuevo);
            db.update("TipoSolicitud",cv,"nombreTipoSolicitud=?",nombreActual);
            return "Registro actualizado";
        }
        else{
            return "No existe ese Tipo Solicitud";
        }
    }

    public String eliminarTipoSolicitud(String nombre){
        String regAfectados="filas afectadas=";
        int contador = 0;
        String[] parametro = {nombre};
        boolean existe = consultarTipoSolicitud(nombre);
        if(existe == true){
            contador+=db.delete("TipoSolicitud","nombreTipoSolicitud=?",parametro);
            regAfectados+=contador;
            return regAfectados;
        }
        else{
            return "No existe ese tipo de solicitud";
        }
    }
    //********************Autor: ********************
//*******************Carnet: ********************





}
