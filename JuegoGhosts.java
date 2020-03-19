package swingmain;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class JuegoGhosts extends JFrame implements ActionListener{
	private JPanel pPrincipal;
      
	Celda celda[][];
	
	String codigo[][] ;
	int turno = 0;

	Celda seleccionada;
	JLabel etiPlayer2;
	JLabel etiBuenos2;
	JLabel etiMalos2;
	JLabel etiPlayer1;
	JLabel etiBuenos1;
	JLabel etiMalos1;
        JButton btnJugar;
        Player [] user;
	
	JLabel etiEstado;
	
        int user1log;
        int user2log;
        
	int estado = 0;
	
	int buenas = 4;
	int malas = 4;
	
	boolean finalizo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Player [] usuarios,int user1,int user2) {
		new JuegoGhosts(usuarios,user1,user2);
	}

        
        
	public JuegoGhosts(Player [] users,int user1,int user2) {
            user=users;
            user1log=user1;
            user2log=user2;
  
            codigo = new String[6][6];
            
                SeleccionDificultad();
            
           if(user[user1log].getModo()==1){
           
           estado=3;
           }
           
            
	 	getContentPane().setLayout(null);
		
                
                
		setSize(1000,620);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pPrincipal.setBounds(getWidth()/2 - 520/2, getHeight()/2 - 520/2 - 10, 520, 520);
		getContentPane().add(pPrincipal);
		pPrincipal.setLayout(new GridLayout(6, 6, 0, 0));
                
                btnJugar = new JButton("Retirar");
		btnJugar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnJugar.addActionListener(this);
		btnJugar.setBounds(10, 200, 220, 28);
		getContentPane().add(btnJugar);
                
		
		etiPlayer1 = new JLabel(user[user1log].getNomC()+" (F2)");
		etiPlayer1.setFont(new Font("Tahoma", Font.BOLD, 12));
		etiPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		etiPlayer1.setBounds(10, 20, 220, 28);
		getContentPane().add(etiPlayer1);
		
		etiBuenos1 = new JLabel();
		etiBuenos1.setHorizontalAlignment(SwingConstants.CENTER);
		etiBuenos1.setFont(new Font("Tahoma", Font.BOLD, 12));
		etiBuenos1.setBounds(10, 59, 220, 28);
		getContentPane().add(etiBuenos1);
		
		etiMalos1 = new JLabel();
		etiMalos1.setHorizontalAlignment(SwingConstants.CENTER);
		etiMalos1.setFont(new Font("Tahoma", Font.BOLD, 12));
		etiMalos1.setBounds(10, 98, 220, 28);
		getContentPane().add(etiMalos1);
		
		etiBuenos2 = new JLabel();
		etiBuenos2.setHorizontalAlignment(SwingConstants.CENTER);
		etiBuenos2.setFont(new Font("Tahoma", Font.BOLD, 12));
		etiBuenos2.setBounds(770, 59, 204, 28);
		getContentPane().add(etiBuenos2);
		
		etiMalos2 = new JLabel();
		etiMalos2.setHorizontalAlignment(SwingConstants.CENTER);
		etiMalos2.setFont(new Font("Tahoma", Font.BOLD, 12));
		etiMalos2.setBounds(770, 98, 204, 28);
		getContentPane().add(etiMalos2);
		
		etiPlayer2 = new JLabel(user[user2log].getNomC()+" (F1)");
		etiPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		etiPlayer2.setFont(new Font("Tahoma", Font.BOLD, 12));
		etiPlayer2.setBounds(770, 20, 204, 28);
		getContentPane().add(etiPlayer2);
		
		etiEstado = new JLabel(user[user1log].getNomC()+", seleccione los \""+buenas+" fantasmas BUENOS.\"");
		etiEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		etiEstado.setHorizontalAlignment(SwingConstants.CENTER);
		etiEstado.setBounds(340, 11, 278, 28);
		getContentPane().add(etiEstado);
		
		celda = new Celda[6][6];
		for (int x = 0; x < celda.length; x++) {
			for (int i = 0; i < celda[x].length; i++) {
				celda[x][i] = new Celda(codigo[x][i], x, i);
				celda[x][i].setBackground(Color.BLUE);
				celda[x][i].setFont(new Font("Tahoma", 1, 18));
				celda[x][i].addActionListener(this);
				pPrincipal.add(celda[x][i]);
			}
		}
                 if(user[user1log].getModo()==1){
              do{
              int j =0;
              Random ac= new Random();
              
              do{
                j = ac.nextInt(5);
              }while(j<0 ||j>5);
                
                    for(int p=0;p<6;p++){
                    for( j=0;j<6;j++){
                    
                        if(!celda[p][j].getLista() && !celda[p][j].getTipo().isEmpty()){
                                    if(estado == 3){
                                    if(celda[p][j].getTipo().contentEquals("F2")){
                                    if(buenas > 0){
                                    celda[p][j].setBueno(true);
                                    buenas--;
                                    etiEstado.setText(user[user1log].getNomC()+", seleccione los \""+buenas+" fantasmas BUENOS.\"");
                                    if(buenas == 0){
                                    etiEstado.setText(user[user1log].getNomC()+", seleccione los \""+malas+" fantasmas MALOS.\"");
                                    }
                                    }
                                    else{

                                    celda[p][j].setBueno(false);
                                    malas--;
                                    etiEstado.setText(user[user1log].getNomC()+", seleccione los \""+malas+" fantasmas MALOS.\"");
                                    if(malas == 0){

                                    estado = 4;
                                    buenas = 4;
                                    malas = 4;
                                    if(user[user1log].getDifcultad()==2){
                                    buenas = 2;
                                        malas = 2;
                                    }

                                    if(user[user1log].getDifcultad()==3){
                                    buenas = 1;
                                    malas = 1;
                                    }
                                    etiEstado.setText(user[user2log].getNomC()+", seleccione los \""+buenas+" fantasmas BUENOS.\"");
                                    }
                }
                celda[p][j].setLista(true);
        }

}

else if(estado == 4){
        if(celda[p][j].getTipo().contentEquals("F1")){

                if(buenas > 0){
                        celda[p][j].setBueno(true);
                        buenas--;
                        etiEstado.setText(user[user2log].getNomC()+", seleccione los \""+buenas+" fantasmas BUENOS.\"");
                        if(buenas == 0){
                                etiEstado.setText(user[user2log].getNomC()+", seleccione los \""+malas+" fantasmas MALOS.\"");
                        }
                }
                else{
                        celda[p][j].setBueno(false);
                        malas--;
                        etiEstado.setText(user[user2log].getNomC()+", seleccione los \""+malas+" fantasmas MALOS.\"");
                        if(malas == 0){
                                estado = 2;
                                verificarEstado();
                        }
                }
                celda[p][j].setLista(true);
                                                                                
									}
								}
							}
						}

                    
                    }
                    
                    
                    
                    
              }while(estado!=2);
                }
		
//		verificarEstado();
		
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void verificarEstado(){
		
		int malos1 = 0;
		int buenos1 = 0;
		int malos2 = 0;
		int buenos2 = 0;
		for (int x = 0; x < celda.length; x++) {
			for (int i = 0; i < celda[x].length; i++) {
				if(celda[x][i].getTipo().equalsIgnoreCase("F2")){
					if(celda[x][i].getBueno()){
						buenos2++;
					}
					else{
						malos2++;
					}
				}
				else if(celda[x][i].getTipo().equalsIgnoreCase("F1")){
					if(celda[x][i].getBueno()){
						buenos1++;
					}
					else{
						malos1++;
					}
				}
			}
		}
		
		etiBuenos1.setText("Buenas: "+buenos2);
		etiBuenos2.setText("Buenas: "+buenos1);
		
		etiMalos1.setText("Malas: "+malos2);
		etiMalos2.setText("Malas: "+malos1);
		
		
		if(turno == 0){
			if(buenos2 == 0 || malos2 == 0){
				JOptionPane.showMessageDialog(null, "Ha ganado \""+user[user2log].getNomC()+"\"");
                                user[user1log].save(user[user2log].getNomC(),false);
                                user[user2log].Incrementarpuntos();
                                user[user2log].save(user[user1log].getNomC(),true);
				finalizo = true;
                                MenuJuego ac=new MenuJuego(user,user1log);
                                   ac.setVisible(true);
					dispose();
                                
			}
			else{
				if(!finalizo){
					etiEstado.setText("Turno de \""+user[user1log].getNomC()+"\"");
				}
			}
		}
		else if(turno == 1){
			if(buenos1 == 0 || malos1 == 0){
				JOptionPane.showMessageDialog(null, "Ha ganado \""+user[user1log].getNomC()+"\"");
                                
                                user[user1log].save(user[user2log].getNomC(),true);
                                user[user1log].Incrementarpuntos();
                                user[user2log].save(user[user1log].getNomC(),false);
				finalizo = true;
                                MenuJuego ac=new MenuJuego(user,user1log);
                                                ac.setVisible(true);
					dispose();
			}
			else{
				if(!finalizo){
					etiEstado.setText("Turno de \""+user[user2log].getNomC()+"\"");
				}
			}
		}
	}
	
	
	public boolean validarPosicion(int x, int direccion, int i){
            
		if((x == seleccionada.getFila()+direccion && i == seleccionada.getColumna()) 
                        || (x == seleccionada.getFila() && (i == seleccionada.getColumna()-1 
                        || i == seleccionada.getColumna()+1))){
			return true;
		}
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
            
		try {       
                    if(e.getSource() == btnJugar){
				
				if (turno==0){
                                   
                                    JOptionPane.showMessageDialog(null,"Gano jugador:"+user[user2log].getNomC());
                                
                                    user[user1log].save(user[user2log].getNomC(),false);
                                     user[user2log].Incrementarpuntos();
                                       user[user2log].save(user[user1log].getNomC(),true);
                                       finalizo = true;
                                     MenuJuego ac=new MenuJuego(user,user1log);
                                     ac.setVisible(true);
                                     dispose();
                                   
                                    
                                }
                                if (turno==1){
                                   
                                    JOptionPane.showMessageDialog(null,"Gano jugador:"+user[user1log].getNomC());
                                
                                    user[user2log].save(user[user1log].getNomC(),false);
                                     user[user1log].Incrementarpuntos();
                                       user[user1log].save(user[user2log].getNomC(),true);
                                       finalizo = true;
                                     MenuJuego ac=new MenuJuego(user,user1log);
                                     ac.setVisible(true);
                                     dispose();
                                   
                                    
                                }
                                
			}
                
                    
               for (int x = 0; x < celda.length; x++) {
for (int i = 0; i < celda[x].length; i++) {
if(e.getSource() == celda[x][i]){
if(estado == 2){
if(seleccionada == null  ){
seleccionar(x, i);
}
else{
if(turno == 0 && validarPosicion(x, 1, i)  ||  turno == 1 && validarPosicion(x, -1, i)){
if(!seleccionar(x, i) && turno==0 && celda[x][i] != celda[0][0] && celda[x][i] != celda[0][5]){
                                                                            
celda[x][i].setTipo(seleccionada.getTipo());
celda[x][i].setBueno(seleccionada.getBueno());
seleccionada.setTipo("");
seleccionada = null;
                                                                                
										
                                                                                    
	if(x == 5 && (i==0||i==5)){
     if(!celda[x][i].getBueno() || celda[x][i].getBueno()){
                                            
	JOptionPane.showMessageDialog(null, "Ha ganado \""+user[user1log].getNomC()+"\"");
	user[user1log].save(user[user2log].getNomC(),true);
         user[user1log].Incrementarpuntos();
         user[user2log].save(user[user1log].getNomC(),false);
                                       
         MenuJuego ac=new MenuJuego(user,user1log);
     ac.setVisible(true);
         dispose();
                                        
	}
		}
	else{
	turno = 1;
         verificarEstado();
	}
          }

	if(!seleccionar(x, i) && turno==1 && celda[x][i] != celda[5][0] && celda[x][i] != celda[5][5]){	
            celda[x][i].setTipo(seleccionada.getTipo());
            celda[x][i].setBueno(seleccionada.getBueno());
             seleccionada.setTipo("");
             seleccionada = null;
                      
	if(x == 0&&(i==0||i==5) ){
		if(!celda[x][i].getBueno() || celda[x][i].getBueno()){
		JOptionPane.showMessageDialog(null, "Ha ganado \""+user[user2log].getNomC()+"\"");
													
             user[user1log].save(user[user2log].getNomC(),false);
              user[user2log].Incrementarpuntos();
                user[user2log].save(user[user1log].getNomC(),true);
		finalizo = true;
                 MenuJuego ac=new MenuJuego(user,user1log);
                  ac.setVisible(true);
		dispose();
              finalizo = true;
	}
		}
		turno = 0;
		verificarEstado();
		}
}
                
seleccionada=null;
			}
		}


	if(estado==0 || estado==1){
                                                    
                                                    
	if(!celda[x][i].getLista() && !celda[x][i].getTipo().isEmpty()){
			if(estado == 0){
			if(celda[x][i].getTipo().contentEquals("F2")){
			if(buenas > 0){
			celda[x][i].setBueno(true);
			buenas--;
			etiEstado.setText(user[user1log].getNomC()+", seleccione los \""+buenas+" fantasmas BUENOS.\"");
			if(buenas == 0){
		 etiEstado.setText(user[user1log].getNomC()+", seleccione los \""+malas+" fantasmas MALOS.\"");
				}
					}
			else{
                           turno=1;
			celda[x][i].setBueno(false);
			malas--;
			etiEstado.setText(user[user1log].getNomC()+", seleccione los \""+malas+" fantasmas MALOS.\"");
			if(malas == 0){
                                                                                            
				estado = 1;
				buenas = 4;
			 malas = 4;
                         if(user[user1log].getDifcultad()==2){
                      buenas = 2;
			malas = 2;
                                      }
                         if(user[user1log].getDifcultad()==3){
                                  buenas = 1;
					malas = 1;
                             }
				etiEstado.setText(user[user2log].getNomC()+", seleccione los \""+buenas+" fantasmas BUENOS.\"");
				}
				}
				celda[x][i].setLista(true);
				}
			}
                          else if(estado == 1){
			if(celda[x][i].getTipo().contentEquals("F1")){
                          if(buenas > 0){
			celda[x][i].setBueno(true);
			buenas--;
			etiEstado.setText(user[user2log].getNomC()+", seleccione los \""+buenas+" fantasmas BUENOS.\"");
			if(buenas == 0){
			etiEstado.setText(user[user2log].getNomC()+", seleccione los \""+malas+" fantasmas MALOS.\"");
			}
			}
			else{
			celda[x][i].setBueno(false);
			malas--;
			etiEstado.setText(user[user2log].getNomC()+", seleccione los \""+malas+" fantasmas MALOS.\"");
			if(malas == 0){
			estado = 2;
			verificarEstado();
			}
			}
			celda[x][i].setLista(true);
					}
					}
							}
						}
                                                
                                                
}
                                        

					

				}
			}
                        
                                              
                        
		} catch (Exception e2) {

		}
	}
	
	public boolean seleccionar(int fila, int columna){
		if(turno == 0){
			if(celda[fila][columna].getTipo().equalsIgnoreCase("F2")){
				seleccionada = celda[fila][columna];
				return true;
			}
		}
		else if(turno == 1){
			if(celda[fila][columna].getTipo().equalsIgnoreCase("F1")){
				seleccionada = celda[fila][columna];
				return true;
			}
		}
		return false;
	}
	
	public String pedirString(String mensaje){
		String cadena = "";
		do {
			cadena = JOptionPane.showInputDialog(mensaje);
			if(cadena.trim().length() <= 1){
				JOptionPane.showMessageDialog(null, "La cadena debe contener al menoos 2 caracteres");
			}
		} while (cadena.trim().length() <= 1);
		return cadena;
	}
        
        public void SeleccionDificultad(){

           if(user[user1log].getDifcultad()==1){
 codigo[0][0]="|";codigo[0][1]="F2";codigo[0][2]="F2";codigo[0][3]="F2";codigo[0][4]="F2";codigo[0][5]="|";
 codigo[1][0]="";codigo[1][1]="F2";codigo[1][2]="F2";codigo[1][3]="F2";codigo[1][4]="F2";codigo[1][5]="";              
 codigo[2][0]="";codigo[2][1]="";codigo[2][2]="";codigo[2][3]="";codigo[2][4]="";codigo[2][5]="";             
 codigo[3][0]="";codigo[3][1]="";codigo[3][2]="";codigo[3][3]="";codigo[3][4]="";codigo[3][5]="";          
 codigo[4][0]="";codigo[4][1]="F1";codigo[4][2]="F1";codigo[4][3]="F1";codigo[4][4]="F1";codigo[4][5]="";
 codigo[5][0]="|";codigo[5][1]="F1";codigo[5][2]="F1";codigo[5][3]="F1";codigo[5][4]="F1";codigo[5][5]="|";
 
         buenas = 4;
	malas = 4;
           
           }
           
            if(user[user1log].getDifcultad()==2){
 codigo[0][0]="|";codigo[0][1]="";codigo[0][2]="F2";codigo[0][3]="F2";codigo[0][4]="";codigo[0][5]="|";
 codigo[1][0]="";codigo[1][1]="F2";codigo[1][2]="";codigo[1][3]="F2";codigo[1][4]="";codigo[1][5]="";              
 codigo[2][0]="";codigo[2][1]="";codigo[2][2]="";codigo[2][3]="";codigo[2][4]="";codigo[2][5]="";             
 codigo[3][0]="";codigo[3][1]="";codigo[3][2]="";codigo[3][3]="";codigo[3][4]="";codigo[3][5]="";          
 codigo[4][0]="";codigo[4][1]="";codigo[4][2]="F1";codigo[4][3]="F1";codigo[4][4]="";codigo[4][5]="";
 codigo[5][0]="|";codigo[5][1]="F1";codigo[5][2]="";codigo[5][3]="F1";codigo[5][4]="";codigo[5][5]="|";
 
       buenas = 2;
	 malas = 2;
           
           }
            
            if(user[user1log].getDifcultad()==3){
 codigo[0][0]="|";codigo[0][1]="";codigo[0][2]="F2";codigo[0][3]="F2";codigo[0][4]="";codigo[0][5]="|";
 codigo[1][0]="";codigo[1][1]="";codigo[1][2]="";codigo[1][3]="";codigo[1][4]="";codigo[1][5]="";              
 codigo[2][0]="";codigo[2][1]="";codigo[2][2]="";codigo[2][3]="";codigo[2][4]="";codigo[2][5]="";             
 codigo[3][0]="";codigo[3][1]="";codigo[3][2]="";codigo[3][3]="";codigo[3][4]="";codigo[3][5]="";          
 codigo[4][0]="";codigo[4][1]="";codigo[4][2]="F1";codigo[4][3]="F1";codigo[4][4]="";codigo[4][5]="";
 codigo[5][0]="|";codigo[5][1]="";codigo[5][2]="";codigo[5][3]="";codigo[5][4]="";codigo[5][5]="|";
 
       buenas = 1;
	 malas = 1;
           
           }

}
}




