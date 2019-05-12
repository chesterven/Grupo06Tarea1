//Autor: Maria Abigail Gil Cordova
//Carnet:GC16001
//Grupo de trabajo: 06

package sv.edu.ues.fia.eisi.grupo06tarea1;


import java.sql.Date;


public class DiasNoHabiles
{
    private int idDia;
    private int ciclo;
    private Date fecha;


    public DiasNoHabiles(){

    }

    public DiasNoHabiles(int idDia, int ciclo, Date fecha) {
        this.idDia = idDia;
        this.ciclo = ciclo;
        this.fecha = fecha;
    }


    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
