/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.swing.JTextField;

/**
 *
 * @author Gus
 */
public class AlunosBean {

    int id ;
    String nome;
    String matricula;
    float nota1, nota2, nota3, media;
    byte frequencia;
    
    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }
  
   
    public AlunosBean(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getNota3() {
        return nota3;
    }

    public void setNota3(float nota3) {
        this.nota3 = nota3;
    }

    public byte getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(byte frequencia) {
        this.frequencia = frequencia;
    }    

    
    
}
