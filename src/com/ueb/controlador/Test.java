package com.ueb.controlador;

import java.util.ArrayList;

import com.ueb.modelo.Fcfs;
import com.ueb.modelo.Proceso;

public class Test 
{

	private Fcfs primero;
	
	
	public Test()
	{
		pruebaFCFS();
		
	}
	
	
	public void pruebaFCFS()
	{
		primero = new Fcfs();
		
		ArrayList<Proceso> procesos = new ArrayList<Proceso>();
		
		procesos.add( new Proceso("Proceso 2", 8, 2) );
		procesos.add( new Proceso("Proceso 1", 4, 0) );
		procesos.add( new Proceso("Proceso 3", 1, 5) );
		
		primero.setTotalProcesos( procesos );
		
		primero.ejecutar();
		
		String[][] matrizRta = primero.getMatrizProcesos();
		
		
		for (int i = 0; i < 3; i++) 
		{
			for (int j = 0; j < 50; j++) 
			{
				System.out.print(matrizRta[i][j]);
				
				System.out.print("\t");
			}
			
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) 
	{
		
		String[][] prueba = new String[3][2];
		
		for (int i = 0; i < 3; i++) 
		{
			for (int j = 0; j < 2; j++) 
			{
				System.out.print(prueba[i][j]);
				
				System.out.print("\t");
			}
			
			System.out.println();
		}
		
		
		new Test();
	}
	
	
	
	
}
