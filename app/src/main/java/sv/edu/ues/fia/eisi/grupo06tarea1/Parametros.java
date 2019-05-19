//Autor: Christian Ariel Zelaya Tejada
//Carnet:ZT12002
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

public class Parametros {
    private int idParametro;
    private int nombreTipoParametro;
    private int cantidad_Dias;

    public Parametros(){

    }

    public Parametros(int idParametro, int nombreTipoParametro, int cantidad_Dias){
        this.idParametro = idParametro;
        this.nombreTipoParametro = nombreTipoParametro;
        this.cantidad_Dias = cantidad_Dias;
    }

    /* Metodos Set y Get*/
    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public int getnombreTipoParametro() {
        return nombreTipoParametro;
    }

    public void setnombreTipoParametro(int nombreTipoParametro) {
        this.nombreTipoParametro = nombreTipoParametro;
    }

    public int getCantidad_Dias() {
        return cantidad_Dias;
    }

    public void setCantidad_Dias(int cantidad_Dias) {
        this.cantidad_Dias = cantidad_Dias;
    }


}
