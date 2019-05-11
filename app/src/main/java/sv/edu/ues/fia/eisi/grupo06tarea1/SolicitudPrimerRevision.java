package sv.edu.ues.fia.eisi.grupo06tarea1;

public class SolicitudPrimerRevision {

    private int idSoliPrimerRevision;
    private int idEvaluacion;
    private String carnet;

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


    public SolicitudPrimerRevision(int idSoliPrimerRevision, int idEvaluacion, String carnet){
        this.idSoliPrimerRevision=idSoliPrimerRevision;
        this.idEvaluacion=idEvaluacion;
        this.carnet=carnet;
    }

}
