/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingmain;

import java.util.Scanner;

/**
 *
 * @author gerahn10
 */
public class Player {
    
   public Scanner entrada = new Scanner(System.in);
    
    private String user;
    private String NombreCompleto;
    private String password;
    private int puntos;
    private String ultimas [];
    private int modo; //1.Aleatorio 2.Manual
    
    private int dificultad;//1.Normal  2.Expert 3.Genius
    
    public Player(){}
    
    
    Player(String _user,String _NombreCompleto,String _password,int _puntos){
    
    this.user = _user;
    this.NombreCompleto = _NombreCompleto;
    this.password = _password;
    this.puntos = _puntos;
    ultimas = new String [10];
    for(int j = 0;j<ultimas.length;j++){
    
        ultimas[j]="Vacio";
    
    }
    
    modo=1;
    dificultad=1;
    }
    
    public String getUser(){
    
        return this.user;
    
    }
    
    public String getNomC(){
    
        return this.NombreCompleto;
    }
    
    public String getPass(){
    return this.password;
    }
    
    public int getPuntos(){
    return this.puntos;
    }
    
    public void setUser(String us){
    this.user = us;
    }
    
    public void setNomC(String nomc){
    this.NombreCompleto = nomc;
    
    }
    
    public void setPass(String pass){
    
        this.password = pass;
    }
    
    public void save(String us,boolean gane){
        
    int indice = posVacia();
    
        if(indice == -1){
            
        for(int i = 0;i<ultimas.length;i++){
            
            ultimas[i] = "Vacio";
            
            
       }
        }
        indice= posVacia();
        if( gane == true){
            
            ultimas[indice] = "Gano contra:"+ us;
        
        }
        if(gane == false){
            
        ultimas [indice]="Perdio contra:"+us;
        }
    
    }
    
    public String[] getUltimas(){
    
        return this.ultimas;
    
    }
    public String print(){
    int cont = 1;
    String jugado="\n";
    for (int i=0;i<ultimas.length;i++){
    
         if(ultimas[i] != null)
         {
             jugado += ultimas[i];
             
         
         }
         
    }
    return jugado;
    }
    
   public int posVacia(){
   
   for (int i = 0;i<ultimas.length;i++){
   
   if(ultimas[i] == "Vacio"){
   
   return i;
   }
   
   
   }
   return -1;
   
   }
   
   public void Incrementarpuntos(){
   
   puntos+=3;
       
   }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public int getDifcultad() {
        return dificultad;
    }

    public void setDifcultad(int difcultad) {
        this.dificultad = difcultad;
    }
 

}