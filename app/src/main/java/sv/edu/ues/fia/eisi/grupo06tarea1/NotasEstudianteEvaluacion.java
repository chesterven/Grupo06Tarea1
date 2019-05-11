//CS16008 Castro Sánchez José Andrés
package sv.edu.ues.fia.eisi.grupo06tarea1;


public class NotasEstudianteEvaluacion {

    private float notaEvaluacion;
    private String carnet;
    private int idEvaluacion;


    public NotasEstudianteEvaluacion(float notaEvaluacion){
        this.notaEvaluacion=notaEvaluacion;
    }


    public float getNotaEvaluacion() {
        return notaEvaluacion;
    }

    public void setNotaEvaluacion(float notaEvaluacion) {
        this.notaEvaluacion = notaEvaluacion;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

}
