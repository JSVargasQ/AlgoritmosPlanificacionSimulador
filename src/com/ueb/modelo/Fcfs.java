package com.ueb.modelo;


public class Fcfs extends AlgoritmoPlanificacion
{

	// ---------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ---------------------------------------------------------------------------------------------------

	
	// ---------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------------	
	
	public Fcfs() {
		
		super();
		
	}
	
	
	// ---------------------------------------------------------------------------------------------------
	// METODOS
	// ---------------------------------------------------------------------------------------------------
	

	@Override
	public void ejecutar() 
	{
		this.tiempoActual = 1;
		boolean terminado = false;
		this.matrizProcesos = new String[this.totalProcesos.size()][this.obtenerDuracionProcesos()+100];
		
		while( terminado == false )
		{
			int i = procesoAEjecutar();
			System.out.println("Proceso a ejecutar: " + i);
			
			if( i != -1 ) 
			{
				Proceso actual = this.totalProcesos.get(i);
				this.matrizProcesos[this.procesosEjecutados][0] = actual.getNombreProceso();
				
				for (int j = 0; j < actual.getDuracionProceso(); j++) 
				{
					this.matrizProcesos[this.procesosEjecutados][this.tiempoActual] = actual.getNombreProceso();

					this.tiempoActual++;
					
				}
				
				this.totalProcesos.remove(i);
				this.procesosEjecutados++;
			}
			
			if( this.totalProcesos.size() == 0 )
				terminado = true;
			
			this.tiempoActual++;
		}
		
	}
	
	public int procesoAEjecutar()
	{
		int posicionProceso = -1;
		int tiempoLlegada = Integer.MAX_VALUE; 
		
		for (int i = 0; i < this.totalProcesos.size(); i++) 
		{
			Proceso actual = this.totalProcesos.get(i);
			
			if( actual.getTiempoLlegada() < tiempoLlegada )
			{
				tiempoLlegada = actual.getTiempoLlegada();
				posicionProceso = i;
			}
		}
		
		return posicionProceso;
	}
	
	
	public int obtenerDuracionProcesos()
	{
		int contador = 0;
		
		for(int i = 0; i < this.totalProcesos.size(); i++)
			contador = contador + this.totalProcesos.get(i).getDuracionProceso();
		
		return contador;
	}
	
	
}
