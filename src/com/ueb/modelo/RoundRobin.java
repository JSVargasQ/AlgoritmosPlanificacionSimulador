package com.ueb.modelo;

import java.util.ArrayList;

public class RoundRobin extends AlgoritmoPlanificacion
{

	// ---------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		// ---------------------------------------------------------------------------------------------------

		int quantum;
	
		// ---------------------------------------------------------------------------------------------------
		// CONSTRUCTOR
		// ---------------------------------------------------------------------------------------------------	
		
		public RoundRobin( int pQuantum ) 
		{
			super();
			
			this.quantum = pQuantum;
			
		}
		
		
		// ---------------------------------------------------------------------------------------------------
		// METODOS
		// ---------------------------------------------------------------------------------------------------
		

		@SuppressWarnings("unchecked")
		@Override
		public void ejecutar() 
		{
			ArrayList<Proceso> colaSiguiente = new ArrayList<Proceso>();
			
			int procesosTerminados = 0;
			int procesosTotales = this.totalProcesos.size();
			int quantumEjecutado = 0;
			
			this.tiempoActual = 0;

			this.matrizProcesos = new String[this.totalProcesos.size()][this.obtenerDuracionProcesos()+100];
			
			int idActual = Integer.MAX_VALUE;
			
			do
			{
				Proceso ejecutar = null;
				boolean agregar = false;
				
				if(this.procesosEspera.size() > 0)
				{
					if( idActual == Integer.MAX_VALUE )
					{
						for (int i = 0; i < procesosEspera.size(); i++) 
						{
							if( procesosEspera.get(i).getId() < idActual )
							{
								idActual = i;
							}
						}
					}
					
					ejecutar = procesosEspera.get(idActual);
					
					this.matrizProcesos[ejecutar.getId()][this.tiempoActual] = ejecutar.getNombreProceso();
					ejecutar.setDuracionProceso( ejecutar.getDuracionProceso() - 1 );
					
					quantumEjecutado++;
					
					if(ejecutar.getDuracionProceso() == 0)
					{
						this.procesosEspera.remove(0);
						procesosTerminados++;
						quantumEjecutado = 0;
						idActual = Integer.MAX_VALUE;
					}
					else if(quantumEjecutado == this.quantum )
					{
						quantumEjecutado = 0;
						this.procesosEspera.remove(0);
						agregar = true;
						idActual = Integer.MAX_VALUE;
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
					
						procesosEspera.add(actual);
						
					}
				}
				
				
				if( agregar == true)
					colaSiguiente.add(ejecutar);
				
				if( procesosEspera.size() == 0 && colaSiguiente.size() > 0 )
				{
					this.procesosEspera = (ArrayList<Proceso>) colaSiguiente.clone();
					colaSiguiente = new ArrayList<Proceso>();
				}
				
			}
			while( procesosTerminados != procesosTotales );
			
			
			this.limpiarMatriz();
			
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
		

		public int getQuantum()
		{
			return quantum;
		}


		public void setQuantum(int quantum) 
		{
			this.quantum = quantum;
		}
		
		
	
}
