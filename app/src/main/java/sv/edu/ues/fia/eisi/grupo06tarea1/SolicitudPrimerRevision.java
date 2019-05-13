//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;

public class SolicitudPrimerRevision {

    private int idSoliPrimerRevision;
    private int idEvaluacion;
    private String carnet;
    private boolean aprobado;

    public int getIdSoliPrimerRevision() {
        return idSoliPrimerRevision;
    }

    public void setIdSoliPrimerRevision(int idSoliPrimerRevision) {
        this.idSoliPrimerRevision = idSoliPrimerRevision;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public SolicitudPrimerRevision(int idSoliPrimerRevision, int idEvaluacion, String carnet, boolean aprobado){
        this.idSoliPrimerRevision=idSoliPrimerRevision;
        this.idEvaluacion=idEvaluacion;
        this.carnet=carnet;
        this.aprobado=aprobado;
    }

    public SolicitudPrimerRevision(){

    }

}
