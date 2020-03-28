package com.ueb.modelo;

import java.util.ArrayList;

public class Srtf extends AlgoritmoPlanificacion
{

	// ---------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		// ---------------------------------------------------------------------------------------------------

		
		// ---------------------------------------------------------------------------------------------------
		// CONSTRUCTOR
		// ---------------------------------------------------------------------------------------------------	
		
		public Srtf() {
			
			super();
			
		}
		
		
		// ---------------------------------------------------------------------------------------------------
		// METODOS
		// ---------------------------------------------------------------------------------------------------
		

		@SuppressWarnings("unchecked")
		@Override
		public void ejecutar() 
		{
			
			int procesosTerminados = 0;
			int procesosTotales = this.totalProcesos.size();
			
			this.tiempoActual = 0;

			this.matrizProcesos = new String[this.totalProcesos.size()][this.obtenerDuracionProcesos()+100];
			
			int idProcesoMenorDuracion = 0;
			
			do
			{
				Proceso ejecutar = null;
				
				if( this.procesosEspera.size() > 0 )
				{
					for (int i = 0; i < procesosEspera.size(); i++) 
					{
						if( procesosEspera.get(i).getDuracionProceso() < procesosEspera.get(idProcesoMenorDuracion).getDuracionProceso() )
						{
							idProcesoMenorDuracion = i;
						}
					}
					
					ejecutar = procesosEspera.get( idProcesoMenorDuracion );
					
					this.matrizProcesos[ejecutar.getId()][this.tiempoActual] = ejecutar.getNombreProceso();
					
					ejecutar.setDuracionProceso( ejecutar.getDuracionProceso() - 1 );
					
					if(ejecutar.getDuracionProceso() == 0)
					{
						this.procesosEspera.remove(idProcesoMenorDuracion);
						procesosTerminados++;
						idProcesoMenorDuracion = 0;
					}
					
					
				}
				
				
				this.tiempoActual++;
				
				
				for (int i = 0; i < this.totalProcesos.size(); i++) 
				{
					Proceso actual = this.totalProcesos.get(i);
					
					if( actual.getTiempoLlegada() < this.tiempoActual )
					{
						actual.setId(this.procesosEjecutados);
						this.procesosEjecutados++;
						this.totalProcesos.remove(i);
						this.matrizProcesos[ actual.getId() ][0] = actual.getNombreProceso();
						
						this.procesosEspera.add(actual);
						
					}
					
				}
				

			}
			while( procesosTerminados != procesosTotales );
			
			this.limpiarMatriz();
		}
		
		public int procesoAEjecutar()
		{

			int posicionProceso = -1;
			int tiempoLlegada = Integer.MAX_VALUE; 
			
			for (int i = 0; i < this.totalProcesos.size(); i++) 
			{
				Proceso actual = this.totalProcesos.get(i);
				
				if( actual.getTiempoLlegada() < tiempoLlegada && actual.getTiempoLlegada() < this.tiempoActual  )
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
