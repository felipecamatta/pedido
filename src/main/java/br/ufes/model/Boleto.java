/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import java.util.Random;

/**
 *
 * @author gabriel
 */
public class Boleto {
    
    Random r = new Random();
    private double valor;
    private String codigo;

    public Boleto(double valor) {
        this.valor = valor;
        this.gerarCodigo();
    }
    
    private void gerarCodigo(){
        if(this.valor < 0){
            throw new RuntimeException("Valor inválido!");
        }
        
        this.codigo="";
        for(int i=0; i<48; i++){
            if(i==6 || i ==18 || i== 31){
                this.codigo=this.codigo+".";
            }else if(i==12 || i == 25 || i==38 || i==40){
                this.codigo=this.codigo+" ";
            }else{
                this.codigo=this.codigo+r.nextInt(9);
            }
        }
        System.out.println(this.codigo);
        
    }

    public String getCodigo() {
        return codigo;
    }
    
    
}
