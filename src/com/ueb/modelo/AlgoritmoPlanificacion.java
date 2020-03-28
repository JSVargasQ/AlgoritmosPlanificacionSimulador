package com.ueb.modelo;

import java.util.ArrayList;

public abstract class AlgoritmoPlanificacion 
{

	protected int tiempoTotal;
	protected int tiempoActual;
	
	protected ArrayList<Proceso> totalProcesos;
	
	
	protected ArrayList<Proceso> procesosEspera;
	
	protected String[][] matrizProcesos;
	protected int procesosEjecutados;
	
	
	public AlgoritmoPlanificacion()
	{
		this.tiempoTotal = 0;
		this.tiempoActual = 0;
		
		this.totalProcesos = new ArrayList<Proceso>();

		this.procesosEspera = new ArrayList<Proceso>();
		
		this.totalProcesos = null;
		this.procesosEjecutados = 0;
	}
	
	
	public abstract void ejecutar();

		

	public int getTiempoTotal() 
	{
		return tiempoTotal;
	}


	public void setTiempoTotal(int tiempoTotal) 
	{
		this.tiempoTotal = tiempoTotal;
	}


	public ArrayList<Proceso> getTotalProcesos() 
	{
		return totalProcesos;
	}


	public void setTotalProcesos(ArrayList<Proceso> totalProcesos) 
	{
		this.totalProcesos = totalProcesos;
	}


	public ArrayList<Proceso> getProcesosEspera() 
	{
		return procesosEspera;
	}


	public void setProcesosEspera(ArrayList<Proceso> procesosEspera)
	{
		this.procesosEspera = procesosEspera;
	}

	public int getTiempoActual() 
	{
		return tiempoActual;
	}


	public void setTiempoActual(int tiempoActual)
	{
		this.tiempoActual = tiempoActual;
	}


	public String[][] getMatrizProcesos()
	{
		return matrizProcesos;
	}


	public void setMatrizProcesos(String[][] matrizProcesos)
	{
		this.matrizProcesos = matrizProcesos;
	}


	public int getProcesosEjecutados() 
	{
		return procesosEjecutados;
	}


	public void setProcesosEjecutados(int procesosEjecutados) 
	{
		this.procesosEjecutados = procesosEjecutados;
	}
	
	
	
	
	
}
