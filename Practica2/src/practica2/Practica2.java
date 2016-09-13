/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

/**
 *
 * @author Hector
 */
import java.io.*;
import java.io.BufferedReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.FileReader;
public class Practica2 {
    
    /**
     * @param args the command line arguments
     */
    
    public void leer()
    {
        
        Scanner s = new Scanner(System.in);
        String thisLine,dir,a=".asm",b=".err",i=".inst",ax=null;
        thisLine = null;
        int poslin=0,c=0,pos=0,banbuffer=0,compara=0,banCod=0;
        espacios es;
        String etiqueta = null, codop = null, operando = null, comentario=null,linToken=null,exEt=null,exCod=null,moddir=null;
        Vector<String> cadena;
        cadena = new Vector<>();
        System.out.print("Ruta del archivo? ");
          dir= s.nextLine();
         /**
          * System.out.print(dir);
          */
                try{
             FileInputStream fstr = new FileInputStream(dir+a);
             DataInputStream input = new DataInputStream(fstr);
            BufferedReader lectb = new BufferedReader(new InputStreamReader(input));
            //escribe en el archivo inst
            File ins =new File(dir+i);
            FileWriter fwins=new FileWriter(ins,true);
            BufferedWriter instrucciones=new BufferedWriter(fwins);
            //escribe en el archivo errores
            File f =new File(dir+b);
            FileWriter fw=new FileWriter(f,true);
            BufferedWriter error=new BufferedWriter(fw);
            //escribe en el archivo comentarios
            File f2com =new File("comentarios"+a);
            FileWriter fwcom=new FileWriter(f2com,true);
            BufferedWriter comentarios=new BufferedWriter(fwcom);
           // StringTokenizer Token = new StringTokenizer(dir+a);
            boolean banEnd,espacio,banCom;
            banEnd = false;
            espacio = false;
            banCom= false;
            
             //System.out.println("Linea---ETQ-----CODOP-----OPER---");
            instrucciones.write("Linea---ETQ-----CODOP-----OPER---");
            instrucciones.newLine();
             while((thisLine = lectb.readLine()) != null && banEnd != true){ //empieza a leer las lineas en loop
                        es = new espacios();
                        codop=" ";
	            	operando=" ";
	            	etiqueta=" ";
	                c++;
                       comentario=" ";    
                       
                  // System.out.println("Linea       \n"+thisLine);
                   StringTokenizer Token = new StringTokenizer(thisLine);
                   
                   while(Token.hasMoreTokens())
                   {
                       
                   linToken=Token.nextToken();
                    
                   
                     espacio = es.spacio(thisLine);
                 /* if(thisLine.charAt(0) == ' ' || thisLine.charAt(0) == '\t'){
                             
                            
                            espacio = true; 
                            
                       
                         }
                  else{
                      espacio = false;
                  }*/
                 /**
                  * comentario (empiezan con ; puede ir seguido de cualquier digito  del cero al nueve, cualquier caracter de la 'a' a la 'z' incluyendo mayusculas )
                  */
                    if(linToken.matches(".*[;].*")){

                         poslin=thisLine.indexOf(';');
                         
                      // System.out.println("Posicion linea del comentario   "+poslin+1);
                         comentario=thisLine.substring(poslin+1);
                         
                         StringTokenizer at = new StringTokenizer(comentario);
                         linToken=" ";
                         while(at.hasMoreTokens()){
                             ax=at.nextToken();
                     //      System.out.println("Ax    "+ax);
                            cadena.add(ax);
                           comentarios.write(ax+" ");
                         }
                         comentarios.newLine();
                         linToken=" ";
                         banCom= true;
                     }
                      /**
                       * EntraEtiqueta
                       */
                       /*
                         if(linToken.matches("^[a-zA-Z]{1}[\\w]{0,8}$"))
                         {
                             System.out.println("Etiqueta "+linToken);
                         }*/
                         /**
                         * Entra Codop
                         */
                                if(linToken.matches("^[a-zA-Z]{1,4}(?!\\d )[/.]{0,1}[a-zA-Z]$")&&banCom==false){
                         /**
                          *Inicia practica 2
                          */
                         
                     // System.out.println("Es codop   "+codop);
                         banbuffer=0; 
                         compara=0;
                         
                         
                         String TABOP="TABOP.ASM";
                         
                         try{
                             FileInputStream fsaux = new FileInputStream(TABOP);
                             DataInputStream dsaux = new DataInputStream(fsaux);
                             BufferedReader  braux = new BufferedReader(new InputStreamReader(dsaux));
                             
                             String linaux;
                             System.out.println("Tab lin "+linToken);
                             while((linaux = braux.readLine())!= null){
                                 
                                 StringTokenizer aucod = new StringTokenizer(linaux,"|");
                                   exCod=aucod.nextToken();
                                 //  System.out.println("Tabop: "+exCod);
                                   
                                   if(exCod.compareTo(linToken)==0){
                                       
                                       codop=linToken;
                                       
                                       banCod=1;
                                       moddir=aucod.nextToken();
                                       System.out.println("Codop: "+codop+" Modo de direccionamiento "+moddir);
                                       
                                   }
                                   
                                   
                             }
                             dsaux.close();
                             
                         }catch(Exception r){
                             System.out.println("Hubo un error en el Tabop "+r);
                         }
                         
                         
                         
                         
                         
                         
                         
                        if(codop.matches("^[a-z]{1,4}")&&!"equ".equals(codop)||codop.matches("^[ET][\\w]")){
                            etiqueta=codop;

                            codop="null";
                        }
                         
                         }
                                
                                /**
                                  * Entra Operando
                                  */  
                             else{
                                 
                     /**
                       
                      if(linToken.matches("^[a-z](?!\\d ){0,}[/.]{0,1}.{1,5}")){
                        
                        
                             System.out.println("residuo cod "+linToken);
                             
                                      }
                      */               
                                /**
                                  * Entra Operando
                                  */  
                                 if(codop!=" "&&linToken!=codop&&banCom==false){
                                  
                                     operando=linToken;
                                 //    System.out.println("Operando  "+operando);
                                    
                                     if(codop.equals(" ")){
                                         
                                         codop=linToken;
                                         
                                         
                                       // System.err.println("Error Linea: "+c+"Hubo un error en el operando "+codop);
                                        error.write("Error Linea: "+c+" Hubo un error en el operando "+codop);
                                        error.newLine();
                                         operando=" ";
                                         
                                     }
                                     /**
                                      * Cierra Operando
                                      */
                                 }
                                 else
                                 {
                                     /**
                                      * Espacio
                                      */
                                     
                                   //  thisLine.split("\\s");
                                     
                                     if(espacio==false)
                                     {
                                           // thisLine.split(";");
                                         pos=0;
                                         exEt=" "; 
                                     
                                        // exEt=thisLine.substring(0,pos);
                                        // System.out.println("Pos "+pos);
                                         if(linToken.matches("^[a-zA-Z]{1,8}[^;]{0,1}[\\w]$")&&banCom==false)
                                         {
                                             
                                            //System.out.println("Lin token "+linToken);
                                            if(poslin!=0&&thisLine.charAt(poslin)!=' '&&poslin>2){
                                            //   System.out.println("com pos"+thisLine.charAt(poslin)+"Npos "+poslin);
                                               exEt=thisLine.substring(0,poslin);
                                            //   System.out.println("Etiqueta comen "+exEt);
                                            }
                                            
                                            //System.out.println("Et despues! "+exEt);
                                           //if(linToken.equals(exEt)){
                                               
                                               etiqueta=linToken;
                                             //  System.out.println("etiqueta  "+etiqueta);
                                               
                                               
                                              
                                          //}      
  
                                         
                                     }
                                      
                                     }
                                     
                                     cadena.clear();
                                     
                                     
                                 }
                                     
                                     
                                     
                             }
                         
                             
                     
                        
                        
                        
                   }
                   
                   if(codop==" "){
                       codop="null";
                   }
                   if(etiqueta==" ")
                   {
                       etiqueta="null";
                   }
                   if(operando==" "){
                     operando="null";
                     }
                  if(codop!= "null"){
                      
                  //System.out.println(c+"  ee  "+etiqueta+"  cc  "+codop+"  oo  "+operando);
                  instrucciones.write(c+"      "+etiqueta+"      "+codop+"      "+operando);
                  instrucciones.newLine();
                  }
                  if(operando!="null"&&codop=="null"&&etiqueta!="null")
                  {
                     // System.out.println("Linea: "+c+" Error no se puede tener tener etiqueta u operando sin codop");
                      error.write("Linea: "+c+" Error no se puede tener tener etiqueta y operando sin codop ");
                      error.newLine();
                      
                  }
                  if(operando=="null"&&codop=="null"&&etiqueta!="null"){
                     error.write("Linea: "+c+" Error no se puede tener tener etiqueta sin codop");
                     error.newLine();
                  }
                  if(operando!="null"&&codop=="null"&&etiqueta=="null")
                  {
                      error.write("Linea: "+c+" Error no se puede tener tener operando sin codop");
                      error.newLine();
                  }
                     //  System.out.println(thisLine);//muestra temporal
                     banCom=false;
                     if(codop.equals("END")||codop.equals("End")||codop.equals("end")){//verifica si tiene End
                           banEnd = true;
                       }
                      else if(banEnd != false)
                       {
                         //  System.err.println("Linea: "+c+"Error: no se encontro el final del archivo(End)");
                          error.write("Linea: "+c+"Error: no se encontro el final del archivo(End)");
                           error.newLine();
                       }
                    }
                //System.out.println("Fin del recorrido");   
             //  fw.close();
             comentarios.close();
               error.close();
               instrucciones.close();
            //   comentarios.close();
                }catch(Exception e){
                    System.err.println("Hubo un error en el codigo\n"+e);
                    
    }
        
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Practica2 H = new Practica2();
         H.leer();
        
    }
    
    
}