package com.ueb.modelo;

import java.util.ArrayList;

public class Sjf extends AlgoritmoPlanificacion
{

	// ---------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		// ---------------------------------------------------------------------------------------------------

		
		// ---------------------------------------------------------------------------------------------------
		// CONSTRUCTOR
		// ---------------------------------------------------------------------------------------------------	
		
		public Sjf()
		{
			
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
				else
				{
					this.tiempoActual++;
				}
				
				if( this.totalProcesos.size() == 0 )
					terminado = true;
			}
			
		}
		
		public int procesoAEjecutar()
		{

			int posicionProceso = -1;
			int duracionMin = Integer.MAX_VALUE; 
			
			for (int i = 0; i < this.totalProcesos.size(); i++) 
			{
				Proceso actual = this.totalProcesos.get(i);
				
				if( actual.getDuracionProceso() < duracionMin && actual.getTiempoLlegada() < this.tiempoActual  )
				{
					duracionMin = actual.getTiempoLlegada();
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
		
		
		public void cargarProcesos(String[][] pMatriz, int pCantProcesos)
		{
			this.totalProcesos = new ArrayList<Proceso>();
			Proceso nuevo = null;
			
			for (int i = 0; i < pCantProcesos; i++) 
			{
				String nombreProceso = pMatriz[0][i];
				int duracionProceso = Integer.valueOf( pMatriz[1][i] );
				int llegadaProceso =  Integer.valueOf( pMatriz[2][i] );
				
				nuevo = new Proceso(nombreProceso, duracionProceso, llegadaProceso);
				
				this.totalProcesos.add(nuevo);
			}
			
		}

	
	
	
}
