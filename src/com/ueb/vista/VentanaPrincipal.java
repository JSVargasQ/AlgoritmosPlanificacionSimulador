package com.ueb.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class VentanaPrincipal extends JFrame implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// ---------------------------------------------------------------------------------------------------
	// CONSTANTES
	// ---------------------------------------------------------------------------------------------------

	public static final String TITULO = "Algoritmos de planificación de procesos";

	public static final String FCFS = "FCFS";
	public static final String ROUND_ROBIN = "Round Robin";
	public static final String SJF = "SJF";
	public static final String SRTF = "SRTF";
	
	
	// ---------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ---------------------------------------------------------------------------------------------------
	
	private JButton btnFcfs;
	private JButton btnRoundRobin;
	private JButton btnSjf;
	private JButton btnSrtf;
	
	private JLabel lbFondo;
	
	
	// ---------------------------------------------------------------------------------------------------
	// RELACIONES
	// ---------------------------------------------------------------------------------------------------
	
	private VentanaFCFS ventanaFcfs;
	private VentanaRoundRobin ventanaRoundRobin;
	private VentanaSJF ventanaSjf;
	private VentanaSRTF ventanaSrtf;
	
	
	// ---------------------------------------------------------------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------------------------------------------------------------

	
	public VentanaPrincipal()
	{
		this.configurarVentanaPrincipal();

		this.inicializarComponentes();
		this.configurarComponentes();
		this.agregarComponentes();
		
	}
	
	
	// ---------------------------------------------------------------------------------------------------
	// METODOS
	// ---------------------------------------------------------------------------------------------------

	private void configurarVentanaPrincipal()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle(TITULO);
		

	}
	
	private void inicializarComponentes()
	{
		this.btnFcfs = new JButton();
		this.btnFcfs.setIcon(new ImageIcon( "./source/botonFcfs.png" ));
		
		this.btnRoundRobin = new JButton();
		this.btnRoundRobin.setIcon(new ImageIcon( "./source/botonRoundRobin.png" ));
		
		this.btnSjf = new JButton();
		this.btnSjf.setIcon(new ImageIcon( "./source/botonSjf.png" ));

		
		this.btnSrtf = new JButton();
		this.btnSrtf.setIcon(new ImageIcon( "./source/botonSrtf.png" ));
		
		this.ventanaFcfs = new VentanaFCFS(this);
		this.ventanaRoundRobin = new VentanaRoundRobin(this);
		this.ventanaSjf = new VentanaSJF(this);
		this.ventanaSrtf = new VentanaSRTF(this);
		
		this.lbFondo = new JLabel();
		this.lbFondo.setIcon(new ImageIcon( "./source/fondoPrincipal.png" ));
	}
	
	private void configurarComponentes()
	{
		this.btnFcfs.setActionCommand(FCFS);
		this.btnRoundRobin.setActionCommand(ROUND_ROBIN);
		this.btnSjf.setActionCommand(SJF);
		this.btnSrtf.setActionCommand(SRTF);
		
		this.btnFcfs.addActionListener(this);
		this.btnRoundRobin.addActionListener(this);
		this.btnSjf.addActionListener(this);
		this.btnSrtf.addActionListener(this);
		
		this.mostrarVentanaPrincipal();
	}
	
	private void agregarComponentes()
	{
		this.setLayout(null);
		
		
		
		this.add( this.btnFcfs );
		this.btnFcfs.setBounds(140, 140, 150, 150);
		

		this.add( this.btnRoundRobin );
		this.btnRoundRobin.setBounds(400, 140, 150, 150);
		
		
		this.add( this.btnSjf );
		this.btnSjf.setBounds(140, 360, 150, 150);
		
		
		this.add( this.btnSrtf );
		this.btnSrtf.setBounds(400, 360, 150, 150);
		
		
		this.add(this.lbFondo);
		this.lbFondo.setBounds(0, 0, 700, 700);
	}
	
	
	private void mostrarFcfs()
	{	
		this.setVisible(false);
		this.ventanaFcfs = new VentanaFCFS(this);
		this.ventanaFcfs.setVisible(true);
	}
	
	private void mostrarRoundRobin()
	{
		this.setVisible(false);
		this.ventanaRoundRobin.setVisible(true);
	}
	
	private void mostrarSjf()
	{
		this.setVisible(false);
		this.ventanaSjf.setVisible(true);
	}
	
	private void mostrarSrtf()
	{
		this.setVisible(false);
		this.ventanaSrtf.setVisible(true);
	}
	
	private void mostrarVentanaPrincipal()
	{
		this.setVisible(true);
		
		this.ventanaFcfs.setVisible(false);
		this.ventanaRoundRobin.setVisible(false);
		this.ventanaSjf.setVisible(false);
		this.ventanaSrtf.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		
		if( comando.equals( VentanaPrincipal.FCFS ))
		{
			this.mostrarFcfs();
		}
		else if( comando.equals( VentanaPrincipal.ROUND_ROBIN ) )
		{
			this.mostrarRoundRobin();
		}
		else if( comando.equals( VentanaPrincipal.SJF ) )
		{
			this.mostrarSjf();
		}
		else if( comando.equals( VentanaPrincipal.SRTF ) )
		{
			this.mostrarSrtf();
		}
		else
		{
			this.mostrarVentanaPrincipal();
		}
	}
	

}
