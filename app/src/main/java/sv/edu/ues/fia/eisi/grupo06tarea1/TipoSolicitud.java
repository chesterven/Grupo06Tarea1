//Autor: Roberto Eliezer Ventura Dominguez
//Carnet:VD16006
//Grupo de trabajo: 06
package sv.edu.ues.fia.eisi.grupo06tarea1;

public class TipoSolicitud {

  private int idTipoSolicitud;
  private String nombreTipoSolicitud;

    public TipoSolicitud(int idTipoSolicitud, String nombreTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
        this.nombreTipoSolicitud = nombreTipoSolicitud;
    }

    public int getIdTipoSolicitud() {
        return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(int idTipoSolicitud) {
        this.idTipoSolicitud = idTipoSolicitud;
    }

    public String getNombreTipoSolicitud() {
        return nombreTipoSolicitud;
    }

    public void setNombreTipoSolicitud(String nombreTipoSolicitud) {
        this.nombreTipoSolicitud = nombreTipoSolicitud;
    }
}
