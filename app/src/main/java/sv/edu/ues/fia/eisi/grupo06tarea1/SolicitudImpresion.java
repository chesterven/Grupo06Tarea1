//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;


public class SolicitudImpresion {
    private int idSoliImpresion;
    private String carnet;
    private String codDocente;
    private int cantidadExamenes;
    private int hojasAnexas;
    private boolean realizada;
    private boolean aprobado;



    public SolicitudImpresion(int idSoliImpresion,String carnet,String codDocente,int cantidadExamenes,int hojasAnexas, boolean realizada,boolean aprobado){
        this.idSoliImpresion=idSoliImpresion;
        this.carnet=carnet;
        this.codDocente=codDocente;
        this.cantidadExamenes=cantidadExamenes;
        this.hojasAnexas=hojasAnexas;
        this.realizada=realizada;
        this.aprobado=aprobado;
    }

    public int getIdSoliImpresion() {
        return idSoliImpresion;
    }

    public void setIdSoliImpresion(int idSoliImpresion) {
        this.idSoliImpresion = idSoliImpresion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCodDocente() {
        return codDocente;
    }

    public void setCodDocente(String codDocente) {
        this.codDocente = codDocente;
    }

    public int getCantidadExamenes() {
        return cantidadExamenes;
    }

    public void setCantidadExamenes(int cantidadExamenes) {
        this.cantidadExamenes = cantidadExamenes;
    }

    public int getHojasAnexas() {
        return hojasAnexas;
    }

    public void setHojasAnexas(int hojasAnexas) {
        this.hojasAnexas = hojasAnexas;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }


}
