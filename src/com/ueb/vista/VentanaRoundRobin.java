package com.ueb.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.ueb.modelo.RoundRobin;

public class VentanaRoundRobin extends JFrame implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// ---------------------------------------------------------------------------------------------------
		// CONSTANTES
		// ---------------------------------------------------------------------------------------------------

		public static final String TITULO = "Algoritmo Round Robin";

		public static final String BOTON_INGRESAR = "Ingresar";
		public static final String BOTON_ALEATORIO = "Datos aleatorios";
		public static final String BOTON_LIMPIAR = "Limpiar";
		public static final String BOTON_CALCULAR = "Calcular";
		
		public static final int TAMAÑO_CELDAS = 35;

		
		// ---------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		// ---------------------------------------------------------------------------------------------------
		
		private JButton btnIngresar;
		private JButton btnAleatorio;
		private JButton btnLimpiar;
		private JButton btnCalcular;
		
		private JButton btnVolver;
		
		private JLabel lbCantProcesos;
		private JSpinner spinnerProcesos;

		private JLabel lbQuantum;
		private JSpinner spinnerQuantum;
		
		private JTable tablaProcesos;
		private JTable tablaAlgoritmo;
		
		private JScrollPane scrollBarProcesos;
		private JScrollPane scrollBarAlgoritmo;
		
		
		private JLabel lbFondo;		

		
		private RoundRobin algoritmo;
		
		
		private int cantUltIngresada;
		
		//--------------------------------------
		// CONSTRUCTOR
		//--------------------------------------
		
		public VentanaRoundRobin(VentanaPrincipal pVentanaP)
		{
			this.configurarVentanaFCFS();
			this.inicializarComponentes();
			this.configurarBotones(pVentanaP);
			this.agregarComponentes();
		}
		
		
		//--------------------------------------
		// METODOS
		//--------------------------------------
		
				
		private void configurarVentanaFCFS()
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
			this.btnIngresar = new JButton(BOTON_INGRESAR);
			this.btnAleatorio = new JButton(BOTON_ALEATORIO);
			this.btnLimpiar = new JButton(BOTON_LIMPIAR);
			this.btnCalcular = new JButton(BOTON_CALCULAR);
			
			this.btnVolver = new JButton();
			this.btnVolver.setIcon(new ImageIcon( "./source/botonVolver.png" ));
			
			
			this.lbCantProcesos = new JLabel("Cantidad de procesos");
			this.spinnerProcesos = new JSpinner( new SpinnerNumberModel(2, 2, 100, 1) );
			
			this.lbQuantum = new JLabel("Quantum");
			this.spinnerQuantum = new JSpinner( new SpinnerNumberModel(3, 1, 10, 1) );
			
			this.lbFondo = new JLabel();
			this.lbFondo.setIcon(new ImageIcon( "./source/fondoGeneral.png" ));
		}
		
		private void configurarBotones(VentanaPrincipal pVentanaP)
		{
			this.btnIngresar.setActionCommand(BOTON_INGRESAR);
			this.btnAleatorio.setActionCommand(BOTON_ALEATORIO);
			this.btnLimpiar.setActionCommand(BOTON_LIMPIAR);
			this.btnCalcular.setActionCommand(BOTON_CALCULAR);
			
			this.btnIngresar.addActionListener(this);
			this.btnAleatorio.addActionListener(this);
			this.btnLimpiar.addActionListener(this);
			this.btnCalcular.addActionListener(this);
		
			this.btnVolver.addActionListener(pVentanaP);
		}
		
		private void agregarComponentes()
		{
			this.setLayout(null);
			
			this.lbCantProcesos.setFont(new Font("Serif", Font.PLAIN, 15));
			this.add( this.lbCantProcesos );
			this.lbCantProcesos.setBounds(70, 90, 150, 50);
			
			this.add( this.spinnerProcesos );
			this.spinnerProcesos.setBounds(110, 140, 55, 30);

			
			this.lbQuantum.setFont(new Font("Serif", Font.PLAIN, 15));
			this.add( this.lbQuantum );
			this.lbQuantum.setBounds(245, 90, 150, 50);
			
			this.add( this.spinnerQuantum );
			this.spinnerQuantum.setBounds(245, 140, 55, 30);
			
			
			this.add( this.btnIngresar );
			this.btnIngresar.setBounds(60, 200, 130, 40);
			
			this.add( this.btnAleatorio );
			this.btnAleatorio.setBounds(210, 200, 130, 40);

			this.add( this.btnLimpiar );
			this.btnLimpiar.setBounds(60, 260, 130, 40);

			this.add( this.btnCalcular );
			this.btnCalcular.setBounds(210, 260, 130, 40);

			
			this.add( this.btnVolver );
			this.btnVolver.setBounds(20, 20, 100, 30);
			
			//this.btnVolver.setBounds(x, y, width, height);
			
			
			this.tablaProcesos = this.configurarPorDefectoTablaProcesos();
			
			this.scrollBarProcesos = new JScrollPane(this.tablaProcesos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.scrollBarProcesos.setBounds(380, 60, 264, 240);
			this.add(this.scrollBarProcesos);
			
			
			this.tablaAlgoritmo = new JTable();
			
			this.scrollBarAlgoritmo = new JScrollPane(this.tablaAlgoritmo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.scrollBarAlgoritmo.setBounds(60, 350, 584, 270);
			this.add(this.scrollBarAlgoritmo);
			
			this.add(this.lbFondo);
			this.lbFondo.setBounds(0, 0, 700, 700);
		}
		
		
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String comando = e.getActionCommand();
			
			if( comando.equals( BOTON_INGRESAR ) )
				this.ingresarCantidadProcesos();

			else if( comando.equals( BOTON_ALEATORIO ) )
				this.ingresarDatosAleatorios();
			
			else if( comando.equals( BOTON_LIMPIAR ) )
				this.limpiarProcesos();
			
			else if( comando.equals( BOTON_CALCULAR ) )
				this.ejecutarAlgoritmo();
				
			
			
			
		}
		
		
		private void ejecutarAlgoritmo()
		{
			this.algoritmo = new RoundRobin((int) this.spinnerQuantum.getValue());
			
			String[][] matrizInterfaz = this.obtenerMatrizProcesos();
			this.algoritmo.cargarProcesos( matrizInterfaz , this.cantUltIngresada);
			
			this.algoritmo.ejecutar();
			
			String[][] matrizResultado = this.algoritmo.getMatrizProcesos();
			this.mostrarMatrizAlgoritmo(matrizResultado);
			
		}
		
		
		private void ingresarCantidadProcesos()
		{
			this.cantUltIngresada = (int) this.spinnerProcesos.getValue();
		
			this.remove(this.scrollBarProcesos);
			
			this.tablaProcesos = this.configurarTablaProcesos(this.cantUltIngresada);
			
			this.scrollBarProcesos = new JScrollPane(this.tablaProcesos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.scrollBarProcesos.setBounds(380, 60, 264, 240);
			this.add(this.scrollBarProcesos);
		}
		
		
		
		private JTable configurarPorDefectoTablaProcesos( )
		{
			DefaultTableModel tablaModelo = new DefaultTableModel();
			
			String[] columnaActual = new String[0];
			tablaModelo.addColumn("Nombre", columnaActual);
			
			columnaActual = new String[0];
			tablaModelo.addColumn("Duración", columnaActual);
			
			columnaActual = new String[0];
			tablaModelo.addColumn("Tiempo llegada", columnaActual);
			
			
//			for (int i = 1; i < cantProcesos; i++) 
//			{
//				columnaActual = new String[4];
//				
//				for(int j = 1; j < cantProcesos; j++) 
//				{
//					columnaActual[j] = "";
//				}
//				
//				tablaModelo.addColumn(String.valueOf(i), columnaActual);
//			}
			
			JTable table = new JTable(tablaModelo);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false) ;
			
			table.setRowHeight(40);

			DefaultTableCellRenderer defCellRender = new DefaultTableCellRenderer();
			
			table.getColumn( table.getColumnName(0) ).setMinWidth(81);	
			table.getColumn( table.getColumnName(0) ).setMaxWidth(81);
			defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
			table.getColumn( table.getColumnName(0) ).setCellRenderer(defCellRender);
			
			table.getColumn( table.getColumnName(1) ).setMinWidth(70);	
			table.getColumn( table.getColumnName(1) ).setMaxWidth(70);
			defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
			table.getColumn( table.getColumnName(1) ).setCellRenderer(defCellRender);
			
			table.getColumn( table.getColumnName(2) ).setMinWidth(109);	
			table.getColumn( table.getColumnName(2) ).setMaxWidth(109);
			defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
			table.getColumn( table.getColumnName(2) ).setCellRenderer(defCellRender);
			
//			for (int i = 0; i < 3; i++) 
//			{
//				table.getColumn( table.getColumnName(i) ).setMinWidth(TAMAÑO_CELDAS);			
//				table.getColumn( table.getColumnName(i) ).setMaxWidth(TAMAÑO_CELDAS);
//				
//				defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
//				table.getColumn( table.getColumnName(i) ).setCellRenderer(defCellRender);
//			}
			
			
			return table;	
		}
		
		
		
		private JTable configurarTablaProcesos( int pCantProcesos )
		{
			DefaultTableModel tablaModelo = new DefaultTableModel();
			
			String[] columnaActual = null;
			
			for (int i = 0; i < 3; i++) 
			{
				columnaActual = new String[pCantProcesos];
				
				for(int j = 0; j < pCantProcesos; j++) 
				{
					columnaActual[j] = "";
				}
				
				String nombreColumna = "";
				
				switch( i ) 
				{
					case 0:
						nombreColumna = "Nombre";
					break;
					case 1:
						nombreColumna = "Duración";
						break;
					case 2:
						nombreColumna = "Tiempo llegada";
						break;
				}
				
				tablaModelo.addColumn(nombreColumna, columnaActual);
			}
			
			JTable table = new JTable(tablaModelo);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false) ;
			
			table.setRowHeight(40);

			DefaultTableCellRenderer defCellRender = new DefaultTableCellRenderer();
			
			table.getColumn( table.getColumnName(0) ).setMinWidth(81);	
			table.getColumn( table.getColumnName(0) ).setMaxWidth(81);
			defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
			table.getColumn( table.getColumnName(0) ).setCellRenderer(defCellRender);
			
			table.getColumn( table.getColumnName(1) ).setMinWidth(70);	
			table.getColumn( table.getColumnName(1) ).setMaxWidth(70);
			defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
			table.getColumn( table.getColumnName(1) ).setCellRenderer(defCellRender);
			
			table.getColumn( table.getColumnName(2) ).setMinWidth(110);	
			table.getColumn( table.getColumnName(2) ).setMaxWidth(110);
			defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
			table.getColumn( table.getColumnName(2) ).setCellRenderer(defCellRender);
			
			return table;	
		}
		
		private void limpiarProcesos()
		{
			this.remove(this.scrollBarProcesos);
			
			this.tablaProcesos = this.configurarTablaProcesos(this.cantUltIngresada);
			
			this.scrollBarProcesos = new JScrollPane(this.tablaProcesos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.scrollBarProcesos.setBounds(380, 60, 264, 240);
			this.add(this.scrollBarProcesos);
		}
		
		
		private void ingresarDatosAleatorios()
		{

			try
			{
				int rangoMinimo = Integer.parseInt( this.pedirDatoRango("Ingresar el valor mínimo de tiempo de duración de los procesos").trim() );
				int rangoMaximo = Integer.parseInt( this.pedirDatoRango("Ingrese el valor máximo de tiempo de duración de los procesos").trim() );

				if(rangoMinimo > rangoMaximo )
				{
					throw new Exception("El 'rango mayor' es menor que el 'rango menor'.\nVerifique los valores.");
				}
				
				int demoraMaxima = Integer.parseInt( this.pedirDatoRango("Ingrese el valor máximo de tiempo de llegada de los procesos").trim() );

				this.remove(this.scrollBarProcesos);

				DefaultTableModel tablaModelo = new DefaultTableModel();

				String[] columnaActual = null;

				for (int i = 0; i < 3; i++) 
				{
					columnaActual = new String[this.cantUltIngresada];


					if( i == 0 )
					{
						int contador = 1;

						for (int j = 0; j < columnaActual.length; j++, contador++) 
							columnaActual[j] = "Proceso " + contador;

					}
					else if( i == 1 )
					{
						for (int j = 0; j < columnaActual.length; j++) 
						{
							columnaActual[j] = String.valueOf( this.generarNumeroAleatorio(rangoMinimo, rangoMaximo) );
						}
					}
					else if( i == 2 )
					{
						for (int j = 0; j < columnaActual.length; j++) 
						{
							columnaActual[j] = String.valueOf( this.generarNumeroAleatorio(0, demoraMaxima) );
						}
					}


					String nombreColumna = "";

					switch( i ) 
					{
					case 0:
						nombreColumna = "Nombre";
						break;
					case 1:
						nombreColumna = "Duración";
						break;
					case 2:
						nombreColumna = "Tiempo llegada";
						break;
					}

					tablaModelo.addColumn(nombreColumna, columnaActual);
				}

				JTable table = new JTable(tablaModelo);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getTableHeader().setReorderingAllowed(false) ;

				table.setRowHeight(40);

				DefaultTableCellRenderer defCellRender = new DefaultTableCellRenderer();

				table.getColumn( table.getColumnName(0) ).setMinWidth(81);	
				table.getColumn( table.getColumnName(0) ).setMaxWidth(81);
				defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
				table.getColumn( table.getColumnName(0) ).setCellRenderer(defCellRender);

				table.getColumn( table.getColumnName(1) ).setMinWidth(70);	
				table.getColumn( table.getColumnName(1) ).setMaxWidth(70);
				defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
				table.getColumn( table.getColumnName(1) ).setCellRenderer(defCellRender);

				table.getColumn( table.getColumnName(2) ).setMinWidth(110);	
				table.getColumn( table.getColumnName(2) ).setMaxWidth(110);
				defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
				table.getColumn( table.getColumnName(2) ).setCellRenderer(defCellRender);


				this.tablaProcesos = table;
				this.scrollBarProcesos = new JScrollPane(this.tablaProcesos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				this.scrollBarProcesos.setBounds(380, 60, 264, 240);
				this.add(this.scrollBarProcesos);
			}
			catch(NumberFormatException e)
			{
				if( e.getMessage() != "null" )
				{
					this.darMensajeError( "El valor ingresado es invalido." );
				}
			}
			catch(Exception e)
			{
				this.darMensajeError(e.getMessage());
			}
			
		}

		private void darMensajeError(String pMensajeError)
		{
			JOptionPane.showMessageDialog(this, pMensajeError, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		private String pedirDatoRango(String pMensaje)
		{
			return JOptionPane.showInputDialog(this, pMensaje);
		}
		
		
		private int generarNumeroAleatorio(int pNumMinimo, int pNumMaximo)
		{
			return (int)( Math.random() * (pNumMaximo - pNumMinimo + 1) + pNumMinimo  );
		}
		
		
		private String[][] obtenerMatrizProcesos()
		{
			
			String[][] matriz = new String[3][this.cantUltIngresada];
			
			TableModel modelo = this.tablaProcesos.getModel();
			
			for (int i = 0; i < 3; i++) 
			{			
				for (int j = 0; j < this.cantUltIngresada; j++) 
				{
					matriz[i][j] = (String) modelo.getValueAt(j, i);
				}
			}
			
			return matriz;
		}
		
		private void mostrarMatrizAlgoritmo(String[][] pMatriz)
		{
			this.remove(this.scrollBarAlgoritmo);
			
			DefaultTableModel tablaModelo = new DefaultTableModel();
			
			String[] columnaActual = null;
			
			for (int i = 0; i < pMatriz[0].length; i++) 
			{
				columnaActual = new String[this.cantUltIngresada];
				
				
				for (int j = 0; j < this.cantUltIngresada; j++) 
				{
					columnaActual[j] = pMatriz[j][i];
				}

				
				tablaModelo.addColumn(String.valueOf(i), columnaActual);
			}
			
			JTable table = new JTable(tablaModelo);
//			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false) ;
			
			table.setRowHeight(30);

			DefaultTableCellRenderer defCellRender = new DefaultTableCellRenderer();
			defCellRender.setHorizontalAlignment( SwingConstants.CENTER );

			for (int i = 0; i < pMatriz[0].length; i++) 
			{
				table.getColumn( table.getColumnName(i) ).setMinWidth(62);	
				table.getColumn( table.getColumnName(i) ).setMaxWidth(62);
				table.getColumn( table.getColumnName(i) ).setCellRenderer(defCellRender);
			}
			
			
//			table.getColumn( table.getColumnName(1) ).setMinWidth(70);	
//			table.getColumn( table.getColumnName(1) ).setMaxWidth(70);
//			defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
//			table.getColumn( table.getColumnName(1) ).setCellRenderer(defCellRender);
//			
//			table.getColumn( table.getColumnName(2) ).setMinWidth(110);	
//			table.getColumn( table.getColumnName(2) ).setMaxWidth(110);
//			defCellRender.setHorizontalAlignment( SwingConstants.CENTER );
//			table.getColumn( table.getColumnName(2) ).setCellRenderer(defCellRender);
			
			
			table.setEnabled(false);
			this.scrollBarAlgoritmo = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.scrollBarAlgoritmo.setBounds(60, 350, 584, 270);
			this.add(this.scrollBarAlgoritmo);
			
		}
	
}
