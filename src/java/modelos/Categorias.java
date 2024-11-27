/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Rigo
 */
public class Categorias {
    private int pk_categoria;
    private String tipoMascota,
            raza;

    public int getPk_categoria() {
        return pk_categoria;
    }

    public void setPk_categoria(int pk_categoria) {
        this.pk_categoria = pk_categoria;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
