/*
 * ColaCircular.java
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
import java.util.*;

public class ColaCircular extends Thread{
	
	private Sector cabeza = null;
	private static int tam = 0;
	private List<Productor> productores = new ArrayList<Productor>();
	private List<Consumidor> consumidores = new ArrayList<Consumidor>();
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Por favor elige el tamaño de la cola: ");
		int tam = sc.nextInt();
		System.out.print("Defina el numero de productores: ");
		int pro = sc.nextInt();
		System.out.print("Defina el numero de consumidores: ");
		int con = sc.nextInt();
		sc.nextLine();
		ColaCircular cola = new ColaCircular(tam);
		for(int i = 0; i<pro;i++) {
			System.out.print("Nombre de productor: ");
			String name = sc.nextLine();
			System.out.print("Rango de aleatoriedad mínimo ( " + name + "): ");
			int min = sc.nextInt();
			System.out.print("Rango de aleatoriedad máximo ( " + name + "): ");
			int max = sc.nextInt();
			sc.nextLine();
			Productor temp = new Productor(cola, name, min, max);
			productores.add(temp);
		}
		for(int i = 0; i<con; i++) {
			System.out.print("Nombre de consumidor: ");
			String name = sc.nextLine();
			System.out.print("Rango de aleatoriedad mínimo ( " + name + "): ");
			int min = sc.nextInt();
			System.out.print("Rango de aleatoriedad máximo ( " + name + "): ");
			int max = sc.nextInt();
			sc.nextLine();
			Consumidor temp = new Consumidor(cola, name, min, max);
			consumidores.add(temp);
		}
		for(int i = 0; i<consumidores.size(); i++) {
			Consumidor temp = consumidores.get(i);
			temp.start();
		}
		for(int i = 0; i<productores.size(); i++) {
			Productor temp = productores.get(i);
			temp.start();
		}
	}
	
	public static void main (String args[]) {
		(new ColaCircular()).start();
	}
	
	public ColaCircular(){}
	
	public ColaCircular(int tam) {
		Sector temp = new Sector(); //Sector temporal
		for(int i = 0; i<tam; i++) {
			if(cabeza!=null) {
				Sector temporal = new Sector(i+1);
				temporal.setAnterior(temp);
				temp.setSiguiente(temporal);
				temp = temporal;
				
			} else { //Cola vacía
				Sector cab = new Sector(i+1);
				cabeza = cab;
				temp = cabeza;
			}
		}
		cabeza.setAnterior(temp);
		temp.setSiguiente(cabeza);
	}
	
	public void imprimirDatos() {
		int x = 0;
		Sector temp = cabeza;
		String estado = "";
		do{
			if(temp.getOcupado()) {
				estado = "1";
			} else {
				estado = "0";
			}
			System.out.print(temp.getID() + ". " + estado + "   ");
			temp = temp.getSiguiente();
			x++;
		}while(temp!=cabeza);
		System.out.println("\n " + "-------------------------------------------");
	}
	
	public Sector getCabeza() {
		return this.cabeza;
	}
}

