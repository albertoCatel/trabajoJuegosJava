package trabajoNavidad;

import daw.com.Pantalla;
import daw.com.Teclado;

public class test {

	static int[] baraja = new int[52];
	static String[] palo = {"♥", "♦", "♣", "♠"};
	static String[] valor = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

	public static void crearBaraja() {
        for (int i = 0; i < 52; i++) {
        	baraja[i] = i;
            }
	}
	
	public static void mezclarBaraja() {
		for (int i = 0; i < 52; i++) {            
			int random = (int) (Math.random() * 52);
            int temp = baraja[i];            
            baraja[i] = baraja[random];
            baraja[random] = temp;
        }
	}
	
	public static int repartirCarta(int jugador, int posCarta) {
		int carta = baraja[posCarta];
		
		String valorDeLaCarta = valor[carta % 13];
		String paloDeLaCarta = palo[carta / 13];
		
		int numCarta = 0;
		if (valorDeLaCarta.equals("J") || valorDeLaCarta.equals("Q") || valorDeLaCarta.equals("K")) {
			numCarta = 10;
		} else if (valorDeLaCarta.equals("A")) {
			numCarta = 11;
		} else {
			numCarta = Integer.parseInt(valorDeLaCarta);
		}
		
		if (jugador == 0) {
			System.out.println("Carta repartida al jugador: [" + paloDeLaCarta + valorDeLaCarta + "]");
		} else if (jugador == 1 ) {
			System.out.println("Se ha repartido una carta a la Banca");
		}
		
		return numCarta;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("===[Nueva Partida]===");
		Pantalla.escribirSaltoLinea();
		
		boolean continuar = true;
		while (continuar) {
			
			crearBaraja();
			mezclarBaraja();
			
			int puntosJugador = 0, puntosBanca = 0;
			int contadorCartas = 0;
			int AsJugador = 0, AsBanca = 0;
			
			int carta = repartirCarta(0, contadorCartas++);
			if (carta == 11) {
				AsJugador++;
				puntosJugador += carta;
				carta = 0;
			} else {
				puntosJugador += carta;
				carta = 0;
			}
			carta = repartirCarta(0, contadorCartas++);
			if (carta == 11) {
				AsJugador++;
				puntosJugador += carta;
				carta = 0;
			} else {
				puntosJugador += carta;
				carta = 0;
			}
			carta = repartirCarta(1, contadorCartas++);
			if (carta == 11) {
				AsBanca++;
				puntosBanca += carta;
				carta = 0;
			} else {
				puntosBanca += carta;
				carta = 0;
			}
			carta = repartirCarta(1, contadorCartas++);
			if (carta == 11) {
				AsBanca++;
				puntosBanca += carta;
				carta = 0;
			} else {
				puntosBanca += carta;
				carta = 0;
			}
			
			//Comprobamos As al principio por si acaso
			while (puntosJugador > 21 && AsJugador > 0) {
				puntosJugador -= 10;
				AsJugador--;
			}
			while (puntosBanca > 21 && AsBanca > 0) {
				puntosBanca -= 10;
				AsBanca--;
			}
			
			Pantalla.escribirSaltoLinea();
			System.out.println("Carta visible de la Banca: [" + palo[baraja[contadorCartas - 1] / 13] + valor[baraja[contadorCartas - 1] % 13] + "]");
			
			
			while (puntosJugador < 21) {
				System.out.println("Total: " + puntosJugador);
				String opcion = Teclado.leerString("¿Deseas otra carta? (S/N): ");
				
				if (opcion.equalsIgnoreCase("S")) {
					Pantalla.escribirSaltoLinea();
	                carta = repartirCarta(0, contadorCartas++);
	    			if (carta == 11) {
	    				AsJugador++;
	    				puntosJugador += carta;
	    				carta = 0;
	    			} else {
	    				puntosJugador += carta;
	    				carta = 0;
	    			}
	    			while (puntosJugador > 21 && AsJugador > 0) {
	    				puntosJugador -= 10;
	    				AsJugador--;
	    			}
	            } else {
	                break;
	            }
			}
			
			
			if (puntosJugador > 21) {
				Pantalla.escribirSaltoLinea();
	        	System.out.println("Total Banca: " + puntosBanca);
	        	System.out.println("Total Jugador: " + puntosJugador);
	            System.out.println("¡Te pasaste de 21! Has perdido.");
			} else {
				
				while (puntosBanca < 17) {
		            carta = repartirCarta(1, contadorCartas++);
					if (carta == 11) {
						AsBanca++;
						puntosBanca += carta;
						carta = 0;
					} else {
						puntosBanca += carta;
						carta = 0;
					}
					while (puntosBanca > 21 && AsBanca > 0) {
						puntosBanca -= 10;
						AsBanca--;
					}
		        }
		        
				Pantalla.escribirSaltoLinea();
		        if (puntosBanca > 21) {
		        	System.out.println("Total Banca: " + puntosBanca);
		        	System.out.println("Total Jugador: " + puntosJugador);
		        	System.out.println("La Banca se pasó de 21, ¡Has ganado!");
		        } else if (puntosBanca == puntosJugador) {
		        	System.out.println("Total Banca: " + puntosBanca);
		        	System.out.println("Total Jugador: " + puntosJugador);
		        	System.out.println("¡Empate!");
		        } else if (puntosBanca >  puntosJugador) {
		        	System.out.println("Total Banca: " + puntosBanca);
		        	System.out.println("Total Jugador: " + puntosJugador);
		        	System.out.println("¡La Banca gana!");
		        } else {
		        	System.out.println("Total Banca: " + puntosBanca);
		        	System.out.println("Total Jugador: " + puntosJugador);
		        	System.out.println("¡Has ganado!");
		        }
			}
			
			Pantalla.escribirSaltoLinea();
			String text = Teclado.leerString("¿Quieres jugar de nuevo? (S/N): ");
	        
	        if (text.equalsIgnoreCase("S")) {
	        	continuar = true;
	        	Pantalla.escribirSaltoLinea();
	        	Pantalla.escribirSaltoLinea();
	        	Pantalla.escribirSaltoLinea();
	        	System.out.println("===[Nueva Partida]===");
	        	Pantalla.escribirSaltoLinea();
	        } else {
	        	continuar = false;
	        }
	        
		}

	}


}
