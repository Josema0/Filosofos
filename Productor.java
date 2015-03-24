/*
 * Productor.java
 * 
 * Copyright 2015 José Manuel Jiménez <josema@Josema-PC>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
import java.util.Scanner;
import java.io.*;


public class Productor extends Thread {
	
	private int timeMax = 0;
	private int timeMin = 0;
	private ColaCircular cola;
	private String name;
	
	public Productor (ColaCircular cola, String name, int timeMin, int timeMax) {
		this.cola = cola;
		this.name = name;
		this.timeMin = timeMin;
		this.timeMax = timeMax;
	}
	
	public void run() {
		try {
			Sector temp2 = cola.getCabeza();
			produce(temp2);
		} catch(InterruptedException e) {
			
		}
	}
	
	public synchronized void produce(Sector sector) throws InterruptedException{
		if(sector.getOcupado()) {
			while(sector.getOcupado()) {
				sector = sector.getSiguiente();
				//Recorre todos los sectores hasta allar uno que no esté ocupado
				sleep((int)(Math.random()*(timeMin-timeMin+1)+timeMin)); 
			}
		} else {
			sector.setOcupado(true); //Actualiza el valor en el sector
			sleep((int)(Math.random()*(timeMin-timeMin+1)+timeMin)); 
			//duerme un tiempo antes de volver a hacer algo...
			System.out.println(name + ": He llenado la casilla " +
								sector.getID());
			cola.imprimirDatos();
			sector = sector.getSiguiente();
		}
		produce(sector);
	}
}

