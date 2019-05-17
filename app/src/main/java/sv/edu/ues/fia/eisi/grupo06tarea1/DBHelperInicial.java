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

import static android.icu.text.MessagePattern.ArgType.SELECT;


public class DBHelperInicial {

    //ARREGLOS PARA CONSULTAR


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
    private static final String DROP_TABLE13 = "DROP TABLE IF EXISTS TipoEvaluacion";
    private static final String DROP_TABLE14 = "DROP TABLE IF EXISTS Evaluaciones";
    private static final String DROP_TABLE15 = "DROP TABLE IF EXISTS EstudianteInscrito";
    private static final String DROP_TABLE16= "DROP TABLE IF EXISTS SolicitudImpresion ";
    private static final String DROP_TABLE17= "DROP TABLE IF EXISTS NotasEstudianteEvaluacion";
    private static final String DROP_TABLE18= "DROP TABLE IF EXISTS SolicitudPrimerRevision ";
    private static final String DROP_TABLE19= "DROP TABLE IF EXISTS SolicitudDiferidoRepetido";
    private static final String DROP_TABLE20= "DROP TABLE IF EXISTS SolicitudDSegundaRevision";
    private static final String DROP_TABLE21= "DROP TABLE IF EXISTS SegundaRevision";
    private static final String DROP_TABLE22= "DROP TABLE IF EXISTS SolicitudEvaluacion";
    private static final String DROP_TABLE23 = "DROP TABLE IF EXISTS DetalleSegundaRevision";
    private static final String DROP_TABLE24 ="DROP TABLE IF EXISTS PrimeraRevision";

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
            //TABLAS PARA ROLES DE USUARIOS AUTOR: ROBERTO ELIEZER VENTURA DOMINGUEZ y MARIA ABIGAIL GIL CORDOVA
            try {

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


                //Autor: Roberto Eliezer Ventura Dominguez
                //Carnet: VD16006
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
                        "    codMateria VARCHAR2(6) NOT NULL PRIMARY KEY,\n" +
                        "    nombreMateria VARCHAR2(60) NOT NULL\n" +
                        ");");
                db.execSQL("CREATE TABLE TipoGrupo (\n" +
                        "    idTipoGrupo INTEGER NOT NULL PRIMARY KEY,\n" +
                        "    nombreGrupo VARCHAR2(25) NOT NULL\n" +
                        ");");
                db.execSQL("CREATE TABLE  MateriaCiclo(\n" +
                        "    numGrupo INTEGER NOT NULL,\n" +
                        "     codMateria VARCHAR2(6) NOT NULL,\n" +
                        "    idCiclo INTEGER NOT NULL,\n" +
                        "    codDocente VARCHAR2(7) NOT NULL,\n" +
                        "    idTipoGrupo INTEGER NOT NULL,\n" +
                        "    PRIMARY KEY(numGrupo, codMateria, idCiclo),\n" +
                        "    CONSTRAINT FKcodMateria FOREIGN KEY(codMateria) REFERENCES Materia(codMateria) ON DELETE RESTRICT,\n" +
                        "    CONSTRAINT FKTipoGrupo FOREIGN KEY(idTipoGrupo) REFERENCES TipoGrupo(idTipoGrupo) ON DELETE RESTRICT\n" +
                        ");");

                db.execSQL("CREATE TABLE EstudianteInscrito(\n" +
                        "    carnet VARCHAR2(7) NOT NULL,\n" +
                        "    numGrupo INTEGER NOT NULL,\n" +
                        "    codMateria VARCHAR2(6) NOT NULL,\n" +
                        "    idCiclo INTEGER NOT NULL,\n" +
                        "    PRIMARY KEY(carnet, numGrupo, codMateria,idCiclo)\n" +
                        ");");

                db.execSQL("CREATE TABLE SolicitudDiferidoRepetido(\n" +
                        "    idSolicitudDiferidoRepetido INTEGER NOT NULL PRIMARY KEY,\n" +
                        "    idEvaluacion INTEGER NOT NULL,\n" +
                        "    carnet VARCHAR2(7) NOT NULL,\n" +
                        "    motivo VARCHAR(20),\n" +
                        "    aprobado BOOLEAN,   \n"+
                        "    idTipoSolicitud INTEGER NOT NULL\n" +
                        ");");

                db.execSQL("CREATE TABLE SolicitudEvaluacion(\n" +
                        "    idEvaluacion INTEGER NOT NULL,\n" +
                        "    idSolicitudDiferidoRepetido INTEGER NOT NULL,\n" +
                        "    notaSoliEvaluacion INTEGER NOT NULL,\n" +
                        "     CONSTRAINT fk_idEvaluacion FOREIGN KEY (idEvaluacion) REFERENCES Evaluaciones(idEvaluacion) ON DELETE RESTRICT,\n" +
                        "     CONSTRAINT fk_idSolicitudDiferidoEvaluacion FOREIGN KEY (idSolicitudDiferidoRepetido) REFERENCES SolicitudDiferidoRepetido(idSolicitudDiferidoRepetido) ON DELETE RESTRICT,\n" +
                        "    PRIMARY KEY (idEvaluacion, idSolicitudDiferidoRepetido)\n" +
                        "    );");

                //Autor: Maria Abigail Gil Cordova
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
                db.execSQL("CREATE TABLE SegundaRevision  (\n" +
                        "   idSegundaRevision INTEGER NOT NULL PRIMARY KEY,\n" +
                        "   idEvaluacion         INTEGER NOT NULL,\n" +
                        "   idLocal             INTEGER NOT NULL,\n" +
                        "   fechaSegundaRevision VARCHAR2(15),\n" +
                        "   horaSegundaRevision VARCHAR2(30),\n" +
                        "   descripcionSegundaRevision         VARCHAR2(50),\n" +
                        "   CONSTRAINT f_k_idEvaluacion FOREIGN KEY (idEvaluacion) REFERENCES Evaluaciones(idEvaluacion) ON DELETE RESTRICT,\n" +
                        "   CONSTRAINT f_k_idlocal FOREIGN KEY (idLocal) REFERENCES Local(idLocal) ON DELETE RESTRICT\n" +
                        "   \n" +
                        ");");
                db.execSQL("CREATE TABLE DetalleSegundaRevision  (\n" +
                        "   idSegundaRevision  INTEGER NOT NULL,\n" +
                        "   idSoliSegundaRevision INTEGER NOT NULL,\n" +
                        "   asistencia BOOLEAN NOT NULL,\n" +
                        "   notaSegundaRevision   FLOAT NOT NULL,\n" +
                        "   CONSTRAINT FKidSoliSegundaRevision FOREIGN KEY (idSoliSegundaRevision) REFERENCES SolicitudSegundaRevision(idSolicitudSegundaRevision) ON DELETE RESTRICT,\n" +
                        "   CONSTRAINT PKDetalleSegundaRevision PRIMARY KEY (idSegundaRevision,idSoliSegundaRevision)\n" +
                        ");");

                db.execSQL("CREATE TABLE PrimeraRevision  (\n" +
                        "   idPrimeraRevision INTEGER NOT NULL PRIMARY KEY,\n" +
                        "   idEvaluacion        INTEGER NOT NULL,\n" +
                        "   idLocal             INTEGER NOT NULL,\n" +
                        "   fechaPrimeraRevision  VARCHAR2(15),\n" +
                        "   descripcionPrimeraRevision VARCHAR2(50),\n" +
                        "   horaPrimeraRevision VARCHAR2(50),\n" +
                        "   CONSTRAINT f_k_idEvaluacion FOREIGN KEY (idEvaluacion) REFERENCES Evaluaciones(idEvaluacion) ON DELETE RESTRICT,\n" +
                        "   CONSTRAINT f_k_idlocal FOREIGN KEY (idLocal) REFERENCES Local(idLocal) ON DELETE RESTRICT\n" +
                        ");");

                //Autor: Christian Ariel Zelaya Tejada
                //Carnet: ZT12002
                db.execSQL("CREATE TABLE TipoEvaluacion (\n" +
                        "   idTipoEvaluacion INTEGER NOT NULL PRIMARY KEY,\n" +
                        "   nombreTipoEvaluacion VARCHAR(25) NOT NULL"  +
                        ");");

                db.execSQL("CREATE TABLE Evaluaciones (\n"  +
                        "   idEvaluacion INTEGER NOT NULL PRIMARY KEY,\n"    +
                        "   idTipoEvaluacion INTEGER NOT NULL,\n"  +
                        "   numGrupo INTEGER NOT NULL   ,\n"  +
                        "   codMateria CHAR(6) NOT NULL,\n"  +
                        "   idCiclo INTEGER NOT NULL,\n"  +
                        "   fechaEvaluacion VARCHAR(15) NOT NULL,\n"   +
                        "   nombreEvaluacion VARCHAR(25) NOT NULL,\n"    +
                        "   descripcion VARCHAR(50) NOT NULL,\n"    +
                        "   CONSTRAINT fk_idTipoEvaluacion FOREIGN KEY (idTipoEvaluacion) REFERENCES TipoEvaluacion(idTipoEvaluacion) ON DELETE RESTRICT,\n" +
                        "   CONSTRAINT fk_numGrupo FOREIGN KEY (numGrupo) REFERENCES MateriaCiclo(numGrupo) ON DELETE RESTRICT,\n"  +
                        "   CONSTRAINT fk_codMateria FOREIGN KEY (codMateria) REFERENCES MateriaCiclo(codMateria) ON DELETE RESTRICT,\n" +
                        "   CONSTRAINT fk_idCiclo FOREIGN KEY (idCiclo) REFERENCES MateriaCiclo(idCiclo) ON DELETE RESTRICT\n" +
                        ");");

                //Autor: José Andrés Castro Sánchez
                //Carnet: CS16008

                db.execSQL("CREATE TABLE SolicitudImpresion (\n" +
                        " idSoliImpresion INTEGER NOT NULL PRIMARY KEY,\n"+
                        " carnet VARCHAR2(7) NOT NULL, \n"+
                        " codDocente VARCHAR2(7) NOT NULL, \n"+
                        " cantidadExamenes INTEGER NOT NULL, \n"+
                        " hojasAnexas INTEGER NOT NULL, \n"+
                        " realizada BOOLEAN NOT NULL, \n"+
                        " aprobado BOOLEAN NOT NULL, \n"+
                        " CONSTRAINT FKcarnet FOREIGN KEY (carnet) REFERENCES Estudiante(carnet) ON DELETE RESTRICT, \n"+
                        " CONSTRAINT FKcodDocente FOREIGN KEY (codDocente) REFERENCES Docente(codDOcente) ON DELETE RESTRICT\n"+
                        ");");

                db.execSQL("CREATE TABLE NotasEstudianteEvaluacion (\n" +
                        " carnet VARCHAR2(7) NOT NULL, \n"+
                        " idEvaluacion INTEGER NOT NULL, \n"+
                        " notaEvaluacion FLOAT NOT NULL, \n"+
                        "PRIMARY KEY (carnet, idEvaluacion),\n"+
                        " CONSTRAINT FKcarnet FOREIGN KEY (carnet) REFERENCES Estudiante(carnet) ON DELETE RESTRICT, \n"+
                        " CONSTRAINT FKidEvaluacion FOREIGN KEY (idEvaluacion) REFERENCES Evaluaciones(idEvaluacion) ON DELETE RESTRICT\n"+
                        ");");

                db.execSQL("CREATE TABLE SolicitudPrimerRevision (\n" +
                        " idSolicitudPrimerRevision INTEGER NOT NULL PRIMARY KEY, \n"+
                        " idEvaluacion INTEGER NOT NULL, \n"+
                        " carnet VARCHAR2(7) NOT NULL, \n"+
                        " aprobado BOOLEAN NOT NULL, \n"+
                        " CONSTRAINT FKcarnet FOREIGN KEY (carnet) REFERENCES Estudiante(carnet) ON DELETE RESTRICT, \n"+
                        " CONSTRAINT FKidEvaluacion FOREIGN KEY (idEvaluacion) REFERENCES Evaluaciones(idEvaluacion) ON DELETE RESTRICT\n"+
                        ");");


                //Autor: Cordero Hernández, Oscar Emmanuel
                //Carnet: CH15013


                db.execSQL("CREATE TABLE SolicitudSegundaRevision (\n" +
                        " idSolicitudSegundaRevision INTEGER NOT NULL PRIMARY KEY, \n"+
                        " idEvaluacion INTEGER NOT NULL, \n"+
                        " carnet VARCHAR2(7) NOT NULL, \n"+
                        " aprobado BOOLEAN NOT NULL, \n"+
                        "idPrimeraRevision INTEGER NOT NULL,"+
                        "idSolicitudPrimerRevision INTEGER NOT NULL,"+
                        " CONSTRAINT FKcarnet FOREIGN KEY (carnet) REFERENCES Estudiante(carnet) ON DELETE RESTRICT, \n"+
                        " CONSTRAINT FKidEvaluacion FOREIGN KEY (idEvaluacion) REFERENCES Evaluaciones(idEvaluacion) ON DELETE RESTRICT,\n"+
                        " CONSTRAINT FKidPrimeraRevision FOREIGN KEY (idPrimeraRevision) REFERENCES PrimeraRevision(idPrimeraRevision) ON DELETE RESTRICT, \n"+
                        " CONSTRAINT FKidSolicitudPrimerRevision FOREIGN KEY (idSolicitudPrimerRevision) REFERENCES SolicitudPrimerRevision(idSolicitudPrimerRevision) ON DELETE RESTRICT \n"+
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
                db.execSQL(DROP_TABLE13);
                db.execSQL(DROP_TABLE14);
                db.execSQL(DROP_TABLE15);
                db.execSQL(DROP_TABLE16);
                db.execSQL(DROP_TABLE17);
                db.execSQL(DROP_TABLE18);
                db.execSQL(DROP_TABLE19);
                db.execSQL(DROP_TABLE20);
                db.execSQL(DROP_TABLE21);
                db.execSQL(DROP_TABLE22);
                db.execSQL(DROP_TABLE23);
                db.execSQL(DROP_TABLE24);
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
        //AUTOR:ROBERTO ELIEZER VENTURA DOMINGUEZ Y MARIA ABIGAIL GIL CORDOVA
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

        //AUTOR: ROBERTO ELIEZER VENTURA DOMINGUEZ
        db.execSQL("DELETE FROM TipoSolicitud");
        db.execSQL("INSERT INTO TipoSolicitud(nombreTipoSolicitud) VALUES ('Repetido');");
        db.execSQL("INSERT INTO TipoSolicitud(nombreTipoSolicitud) VALUES ('Diferido');");

        db.execSQL("DELETE FROM Estudiante");
        db.execSQL("INSERT INTO Estudiante VALUES('VD16006','Roberto','Ventura',0);");
        db.execSQL("INSERT INTO Estudiante VALUES('GC16001','Abigail','Gil',1);");
        db.execSQL("INSERT INTO Estudiante VALUES('XX16001','Fernando','Xerox',0);");
        db.execSQL("INSERT INTO Estudiante VALUES ('CH15013','Oscar','Hernández',1)");
        db.execSQL("INSERT INTO Estudiante VALUES ('CS16008','José','Castro',0)");
        db.execSQL("INSERT INTO Estudiante VALUES ('ZTE12002','Christian','Zelaya',0)");

        db.execSQL("DELETE FROM Docente");
        db.execSQL("INSERT INTO Docente VALUES('GR00001','Cesar Augusto','Gonzalez Rodriguez',1)");
        db.execSQL("INSERT INTO Docente VALUES('MM00001','Boris Alexander','Montano',0)");
        db.execSQL("INSERT INTO Docente VALUES('CG00001','Carlos','García',0)");
        db.execSQL("INSERT INTO Docente VALUES('JI00001','Jorge','Iraheta',0)");
        db.execSQL("INSERT INTO Docente VALUES('GM00001','Guillermo','Mejia',0)");
        db.execSQL("INSERT INTO Docente VALUES('RC00001','Rudy','Chicas',0)");
        db.execSQL("INSERT INTO Docente VALUES('NH00001','Nelly','Henriquez',0)");


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
        db.execSQL("INSERT INTO Materia VALUES ('IAI115','Introduccion a la informatica');");
        db.execSQL("INSERT INTO Materia VALUES ('HDP115','Herramientas de productividad');");
        db.execSQL("INSERT INTO Materia VALUES ('MEP115','Metodos probabilisticos');");


        db.execSQL("DELETE FROM Tipogrupo");
        db.execSQL("INSERT INTO TipoGrupo(nombreGrupo) VALUES ('Teorico');");
        db.execSQL("INSERT INTO TipoGrupo(nombreGrupo) VALUES ('Laboratorio');");
        db.execSQL("INSERT INTO TipoGrupo(nombreGrupo) VALUES ('Discusion');");

        db.execSQL("DELETE FROM MateriaCiclo");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (1,'MIP115',1,'MM00001',1);");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (1,'PDM115',1,'GR00001',2);");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (1,'SYP115',1,'CG00001',1);");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (2,'MIP115',1,'JI00001',2);");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (2,'HDP115',1,'RC00001',1);");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (1,'MEP115',1,'GM00001',2);");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (1,'HDP115',1,'MM00001',1)");
        db.execSQL("INSERT INTO MateriaCiclo VALUES (1,'IAI115',1,'NH00001',2)");



        db.execSQL("DELETE FROM EstudianteInscrito");
        db.execSQL("INSERT INTO EstudianteInscrito VALUES('VD16006',1,'MIP115',1)");
        db.execSQL("INSERT INTO EstudianteInscrito VALUES('VD16006',1,'PDM115',1)");
        db.execSQL("INSERT INTO EstudianteInscrito VALUES('GC16001',1,'PDM115',1)");
        db.execSQL("INSERT INTO EstudianteInscrito VALUES('GC16001',1,'MIP115',1)");
       db.execSQL("INSERT INTO EstudianteInscrito VALUES('GC16001',1,'SYP115',1)");
       db.execSQL("INSERT INTO EstudianteInscrito VALUES('VD16006',1,'SYP115',1)");
        db.execSQL("INSERT INTO EstudianteInscrito VALUES('CH15013',2,'HDP115',1)");
        db.execSQL("INSERT INTO EstudianteInscrito VALUES ('CS16008',2,'MIP',1)");
        db.execSQL("INSERT INTO EstudianteInscrito VALUES ('CS16008',2,'HDP115',1)");

       db.execSQL("DELETE FROM SolicitudDiferidoRepetido");
       db.execSQL("INSERT INTO SolicitudDiferidoRepetido(idEvaluacion,carnet,motivo,aprobado,idTipoSolicitud) VALUES(3,'VD16006','Enfermedad grave',1,2)");

       db.execSQL("DELETE FROM SolicitudEvaluacion");

        //Autor: Maria Abigail Gil Cordova
        db.execSQL("DELETE FROM Ciclo");
        db.execSQL("DELETE FROM DiasNoHabiles");
        db.execSQL("INSERT INTO Ciclo (ciclo) VALUES ('I2019');");
        db.execSQL("INSERT INTO Ciclo (ciclo) VALUES ('II2019');");
        db.execSQL("INSERT INTO Ciclo (ciclo) VALUES ('I2020');");
        db.execSQL("INSERT INTO Ciclo (ciclo) VALUES ('II2020');");
        db.execSQL("DELETE FROM SegundaRevision");
        //db.execSQL("INSERT INTO SegundaRevision (idEvaluacion,idLocal, fechaSegundaRevision,descripcionSegundaRevision)VALUES(2,1,'12/05/2019','PDM parcial 1')");

        //Autor" Christian Ariel Zelaya Tejada
        db.execSQL("DELETE FROM TipoEvaluacion");
        db.execSQL("INSERT INTO TipoEvaluacion (nombreTipoEvaluacion) VALUES ('Ordinaria');");
        db.execSQL("INSERT INTO TipoEvaluacion (nombreTipoEvaluacion) VALUES ('Repetida');");
        db.execSQL("INSERT INTO TipoEvaluacion (nombreTipoEvaluacion) VALUES ('Diferida');");
        db.execSQL("INSERT INTO TipoEvaluacion (nombreTipoEvaluacion) VALUES ('Suficiencia');");

        db.execSQL("DELETE FROM Evaluaciones");
        db.execSQL("INSERT INTO Evaluaciones (idTipoEvaluacion,numGrupo,codMateria,idCiclo,fechaEvaluacion,nombreEvaluacion,descripcion) VALUES(1,1,'MAT115',1,'2019-03-13','Primer Examen Parcial','Evaluacion de las unidades I y II')");
        db.execSQL("INSERT INTO Evaluaciones (idTipoEvaluacion,numGrupo,codMateria,idCiclo,fechaEvaluacion,nombreEvaluacion,descripcion) VALUES(1,1,'PDM115',1,'2019-03-23','Primer Examen Teorico','Evaluacion de las unidades I, II y III')");
        db.execSQL("INSERT INTO Evaluaciones (idTipoEvaluacion,numGrupo,codMateria,idCiclo,fechaEvaluacion,nombreEvaluacion,descripcion) VALUES(1,1,'MIP115',1,'2019-04-12','Examen escrito 1','Evaluacion de la unidad I')");
        db.execSQL("INSERT INTO Evaluaciones (idTipoEvaluacion,numGrupo,codMateria,idCiclo,fechaEvaluacion,nombreEvaluacion,descripcion) VALUES(1,1,'SYP115',1,'2019-04-15','Parcial I','Folletos 1 y 2')");
        db.execSQL("INSERT INTO Evaluaciones (idTipoEvaluacion,numGrupo,codMateria,idCiclo,fechaEvaluacion,nombreEvaluacion,descripcion) VALUES(2,2,'HDP115',1,'2019-04-15','Parcial I','Unidad 1, 2 y 3')");
       db.execSQL("INSERT INTO Evaluaciones (idTipoEvaluacion,numGrupo,codMateria,idCiclo,fechaEvaluacion,nombreEvaluacion,descripcion) VALUES(1,1,'PDM115',1,'2019-05-20','Segundo Examen Teorico','Evaluacion de las unidades IV y V');");
       db.execSQL("INSERT INTO Evaluaciones (idTipoEvaluacion,numGrupo,codMateria,idCiclo,fechaEvaluacion,nombreEvaluacion,descripcion) VALUES(1,1,'SYP115',1,'2019-05-15','Parcial II','Folletos 3 y 4');");

        db.execSQL("DELETE FROM PrimeraRevision");
        db.execSQL("INSERT INTO PrimeraRevision (idEvaluacion,idLocal,fechaPrimeraRevision,descripcionPrimeraRevision, horaPrimeraRevision) VALUES (2,1,'12/05/2019','PDM parcial I grupo 1','8 am');");

        //Autor: José Andrés Castro Sánchez////
        db.execSQL("DELETE FROM SolicitudImpresion");
        db.execSQL("INSERT INTO SolicitudImpresion (carnet,codDocente,cantidadExamenes,hojasAnexas,realizada,aprobado) VALUES ('VD16006','GR00001',4,8,0,0); ");
        db.execSQL("INSERT INTO SolicitudImpresion (carnet,codDocente,cantidadExamenes,hojasAnexas,realizada,aprobado) VALUES ('GC16001','MM00001',8,16,1,1); ");

        db.execSQL("DELETE FROM NotasEstudianteEvaluacion");
        db.execSQL("INSERT INTO  NotasEstudianteEvaluacion(carnet,idEvaluacion,notaEvaluacion) VALUES ('VD16006',1,8.3); ");
        db.execSQL("INSERT INTO  NotasEstudianteEvaluacion(carnet,idEvaluacion,notaEvaluacion) VALUES ('GC16001',1,8.3); ");

        db.execSQL("DELETE FROM SolicitudPrimerRevision");
        db.execSQL("INSERT INTO SolicitudPrimerRevision (idEvaluacion,carnet,aprobado) VALUES (1,'VD16006',0); ");
        db.execSQL("INSERT INTO SolicitudPrimerRevision (idEvaluacion,carnet,aprobado) VALUES (1,'GC16001',1); ");


        return "BD Llena";
    }

//********************Autor: Maria Abigail Gil Cordova********************
//*******************Carnet: GC16001********************

    //consulta las materias que le corresponden a un docente
public Cursor consultarMateriasDocente(String codDocente){
        String [] parametro = {codDocente};
        String []columna = {"numGrupo","CodMateria","idCiclo"};
        Cursor c = db.query("MateriaCiclo",columna,"codDocente=?",parametro,null,null,null);

        return c;
}
//Metodo para insertar una segunda revision
    public String insertarSegundaRevision(SegundaRevision revision)
    {
        String mensaje = "";
        Boolean primera=consultarPrimeraRevisionAntesSegundaRevision(revision.getIdEvaluacion());
        if(primera ==true) {
            mensaje = consultarSegundaRevisionExiste(String.valueOf(revision.getIdEvaluacion()));
            if (mensaje.equals("")) {
                String regInsertados = "Registro Insertado Nº= ";
                long contador = 0;
                ContentValues rev = new ContentValues();
                rev.put("idEvaluacion", revision.getIdEvaluacion());
                rev.put("idLocal", revision.getIdLocal());
                rev.put("fechaSegundaRevision", revision.getFechaSegundaRevision());
                rev.put("horaSegundaRevision", revision.getHora());
                rev.put("descripcionSegundaRevision", revision.getDescripcion());
                contador = db.insert("SegundaRevision", null, rev);
                if (contador == -1 || contador == 0) {
                    regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
                } else {
                    regInsertados = regInsertados + contador;
                }
                return regInsertados;
            } else {

                return "Ya existe una revisión para esa evaluación";
            }
        }
        else{
            return "No existe una primera revisión de la evaluación";
        }

    }
/*Metodo para verificar que al ingresar una segunda revision esta evaluacion cuente con
   una primera revision llevada a cabo previamente si devuelve true dejara insertar
   la segunda revision y si regresa false es que no ha habido primera revision para esa evaluacion*/

public  Boolean consultarPrimeraRevisionAntesSegundaRevision(int idEvaluacion) {
    String[] id = {String.valueOf(idEvaluacion)};
    String[] columna = {"idEvaluacion"};
    Cursor c = db.query("PrimeraRevision", columna, "idEvaluacion=?", id, null, null, null, null);
    if (c.moveToFirst()) {
        return true;
    } else {

        return false;
    }
}
//Metodo para consultar el nombre del local

    public String obtenerLocal(int idLocal)
    {
        String [] parametro ={String.valueOf(idLocal)};
        String [] columna ={"nombreLocal"};
        String nombre="";
        Cursor c=db.query("Local",columna,"idLocal=?",parametro,null,null,null,null);
        if(c.moveToFirst())
        {
            do{
                nombre=c.getString(0);
                return nombre;
            }while(c.moveToNext());
        }
        return "No se encontro";

    }
    //Metodo para consultar la segunda revision
    public SegundaRevision consultarSegundaRevision(int idRevision)
    {
        String [] id ={String.valueOf(idRevision)};
        String []columnas = {"idLocal","fechaSegundaRevision","horaSegundaRevision","descripcionSegundaRevision"};
        Cursor c = db.query("SegundaRevision",columnas,"idSegundaRevision=?",id,null,null,null,null);
        if(c.moveToFirst())
        {
            SegundaRevision segunda = new SegundaRevision();
            segunda.setIdLocal(c.getInt(0));
            segunda.setFechaSegundaRevision(c.getString(1));
            segunda.setHora(c.getString(2));
            segunda.setDescripcion(c.getString(3));
            return segunda;

        }
        return null;
    }

    //Metodo que sirve para consulta si a una evaluacion le pertenece una segunda revision recibiendo asi el id de la evaluacion
    //retorna un string con los datos d ela evaluacion

public String consultarSegundaRevisionExiste (String idEvaluacion)
{
    String mensaje="";
    String [] parametro = {idEvaluacion};
    String []columna = {"IdSegundaRevision","descripcionSegundaRevision"};
    Cursor c = db.query("SegundaRevision",columna,"idEvaluacion=?",parametro,null,null,null);
        if(c.moveToFirst())
        {
            mensaje=String.valueOf(c.getInt(0))+" "+c.getString(1);
            return mensaje;
        }
        else{
            return "";
        }

}

//Metodo para actualizar segunda revision
public String actualizarSegundaRevision(SegundaRevision segunda)
{
        String[] id ={String.valueOf(segunda.getId_Segunda_Revision())};
        ContentValues cv = new ContentValues();
        cv.put("idLocal",segunda.getIdLocal());
        cv.put("fechaSegundaRevision",segunda.getFechaSegundaRevision());
        cv.put("horaSegundaRevision", segunda.getHora());
        cv.put("descripcionSegundaRevision",segunda.getDescripcion());
        db.update("SegundaRevision",cv,"idSegundaRevision=?",id);
        return "Registro actualizado correctamente";
}
//Metodo para eliminar la segunda revision
    public String eliminarSegundaRevision(int idSegunda)
    {
        String regAfectados="filas afectadas=";
        int contador = 0;
        String[] parametro = {String.valueOf(idSegunda)};

            contador+=db.delete("SegundaRevision","idSegundaRevision=?",parametro);


            return "Registro borrado con éxito";

    }

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
//Metodo para consultar los locales, regresa el nombre de todos los locales que se encuentran en la BD
//en un ArrayList
    public Cursor consultarLocales()
    {
        String[] columna={"idLocal","nombreLocal"};
        Cursor c=db.query("Local",columna,null,null,null,null,null);
        return c;
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

    //METODO PARA CONSULTAR EN LA TABLA ESTUDIANTEINSCRITO PARA SABER QUE MATERIA Y EN QUE GRUPO Y CICLO EL ESTUDIANTE ESTA
    public Cursor consultarEstudianteInscrito(String carnet){
        ArrayList<String> inscrito = new ArrayList<String>();
        String[] carnetEstudiante = {carnet};
        String[] columnas = {"numGrupo","codMateria","idCiclo"};
        Cursor c = db.query("EstudianteInscrito",columnas,"carnet=?",carnetEstudiante,null,null,null);
        return c;
        }

        //METODO PARA CONSULTAR LAS EVALUACIONES LOS PARAMETRO VIENEN DEL CURSOR DE LA CONSULTA ESTUDIANTE INSCRITO
        public Cursor consultarEvaluaciones(int nGrupo, String mat, int ciclo){
        String resultado = "";
        String[] parametro = {String.valueOf(nGrupo),mat,String.valueOf(ciclo)};
        String[] columnas = {"idEvaluacion","codMateria","nombreEvaluacion"};
        Cursor c = db.query("Evaluaciones",columnas,"numGrupo=? AND codMateria=? AND idCiclo=?",parametro,null,null,null);
        if(c.moveToFirst()){
            resultado =c.getInt(0) +" "+ c.getString(1) +" "+c.getString(2);
            return c;
        }
        else{
            return c;
        }
        }

    public String consultarEvaluacionDifRepEvaluacion(int nGrupo, String mat, int ciclo, String tipo){
        String resultado = "";
        String[] parametro = {String.valueOf(nGrupo),mat,String.valueOf(ciclo), tipo};
        String[] columnas = {"idEvaluacion","codMateria","nombreEvaluacion"};
        Cursor c = db.query("Evaluaciones",columnas,"numGrupo=? AND codMateria=? AND idCiclo=? AND idTipoEvaluacion=?",parametro,null,null,null);
        if(c.moveToFirst()){
            resultado =c.getInt(0) +" "+ c.getString(1) +" "+c.getString(2);
            return resultado;
        }
        else{
            return resultado="";
        }
    }

        //METODO INSERTAR SIRVE PARA REPETIDO Y DIFERIDO
        public String insertarSolicitudDiferidoRepetido(Solicitud_RepetidoDiferido solicitud){
        Cursor solic;
            String regInsertados="Registro Insertado Nº= ";
            long contador=0;
            solic = consultarSolicitudesDifRep(solicitud.getIdEvaluacion(),solicitud.getCarnet());
            if(solic.moveToFirst()){
                return "Esa evaluacion ya tiene solicitud";
            }else{
                ContentValues soli = new ContentValues();
                soli.put("idEvaluacion",solicitud.getIdEvaluacion());
                soli.put("carnet", solicitud.getCarnet());
                soli.put("motivo", solicitud.getMotivoSolicitud());
                soli.put("aprobado", solicitud.isAprobado());
                soli.put("idTipoSolicitud", solicitud.getIdTipoSolicitud());
                contador= db.insert("SolicitudDiferidoRepetido",null,soli);
                if(contador==-1 || contador==0)
                {
                    regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
                }
                else {
                    regInsertados=regInsertados+contador;
                }
                return regInsertados;
            }


        }

        //METODO PARA CONSULTAR EN LA TABLA DE SOLICITUDES LAS EVALUACIONES QUE UN ESTUDIANTE A SOLICITADO
        public Cursor consultarSolicitudesDiferidoRepetido(String carnet){
            String[] parametros = {carnet};
            String[] columnas = {"idEvaluacion","motivo","aprobado","idTipoSolicitud"};
            Cursor c = db.query("SolicitudDiferidoRepetido",columnas,"carnet=?",parametros,null,null,null);
            return c;
        }

    public Cursor consultarSolicitudesDifRep(int idEvaluacion, String carnet){
        String[] parametros = {String.valueOf(idEvaluacion),carnet};
        String[] columnas = {"motivo","aprobado","idTipoSolicitud"};
        Cursor c = db.query("SolicitudDiferidoRepetido",columnas,"idEvaluacion=? AND carnet=?",parametros,null,null,null);
        return c;
    }

    public Cursor consultarSolicitudesSoliEva(int idEvaluacion){
        String[] parametros = {String.valueOf(idEvaluacion),String.valueOf(1)};
        String[] columnas = {"idSolicitudDiferidoRepetido","carnet","aprobado","idTipoSolicitud"};
        Cursor c = db.query("SolicitudDiferidoRepetido",columnas,"idEvaluacion=? AND aprobado=?",parametros,null,null,null);
        return c;
    }

        //METODO PARA CONSULTAR EVALUACIONES POR SU ID
        public String consultarEvaluacionesSolicitud(int idSolicitud){
            String resultado="";
            String[] parametros = {String.valueOf(idSolicitud)};
            String[] columnas = {"idEvaluacion","codMateria", "nombreEvaluacion"};
            Cursor c = db.query("Evaluaciones",columnas,"idEvaluacion=?",parametros,null,null,null);
            if(c.moveToFirst()){
                resultado =c.getInt(0) +" "+ c.getString(1) +" "+c.getString(2);
                return resultado;
            }
            else{
                return resultado;
            }
        }

        public String eliminarSolicitudDiferidoRepetido(int idEvalacuion, String carnet){
            String regAfectados="filas afectadas=";
            int contador = 0;
            String[] parametro = {String.valueOf(idEvalacuion),carnet};

                contador+=db.delete("SolicitudDiferidoRepetido","idEvaluacion=? AND carnet=?",parametro);
                regAfectados+=contador;
                return regAfectados;
        }

    public String actualizarSolicitudDiferidoRepetido(Solicitud_RepetidoDiferido solicitud){

            String[] parametro = {String.valueOf(solicitud.getIdEvaluacion()),solicitud.getCarnet()};
            ContentValues cv = new ContentValues();
            cv.put("motivo",solicitud.getMotivoSolicitud());
            cv.put("aprobado",solicitud.isAprobado());
            db.update("SolicitudDiferidoRepetido",cv,"idEvaluacion=? AND carnet=?",parametro);
            return "Registro actualizado";

    }

    public String insertarSolicitudEvaluacion(SolicitudEvaluacion solicitud){
        Cursor solic;
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        ContentValues soli = new ContentValues();
            soli.put("idEvaluacion",solicitud.getIdEvaluacion());
            soli.put("idSolicitudDiferidoRepetido", solicitud.getIdSolicitud());
            soli.put("nota",solicitud.getNotaSoliEvaluacion());
            contador= db.insert("SolicitudEvaluacion",null,soli);
            if(contador==-1 || contador==0)
            {
                return regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            }
            else {
                return regInsertados=regInsertados+contador;
            }

    }







    //********************Autor: ********************
//*******************Carnet: ********************





}
