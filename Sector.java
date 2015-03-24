/*
 * Sector.java
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


public class Sector {
	
	private Sector anterior = null;
	private Sector siguiente = null;
	private boolean ocupado;
	private int id = 0;
	
	public Sector() {
		ocupado = false;
	}
	
	public Sector(int x) {
		id = x;
		ocupado = false;
	}
	
	public synchronized Sector getAnterior() {return anterior;}
	public synchronized Sector getSiguiente() {return siguiente;}
	public synchronized boolean getOcupado() {return ocupado;}
	public int getID() {return this.id;}
	public void setAnterior(Sector ant) {anterior = ant;}
	public void setSiguiente(Sector sig) {siguiente = sig;}
	public void setOcupado(boolean x) {ocupado = x;}
	public boolean setNoOcupado(boolean x) {
		if(!this.ocupado) {
			return false;
		} else {
			this.ocupado = false;
			return true;
		}
	}
	public void setOcupado(int x) {id = x;}
}

