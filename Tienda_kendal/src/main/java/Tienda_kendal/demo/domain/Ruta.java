package Tienda_kendal.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "ruta")
public class Ruta implements Serializable {
    // Se recomienda añadir un serialVersionUID
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Integer idRuta;
    private String ruta;
    private boolean requiereRol;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol")
    private Rol rol;

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean isRequiereRol() {
        return requiereRol;
    }

    public void setRequiereRol(boolean requiereRol) {
        this.requiereRol = requiereRol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
}

