package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidad.Persona;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Formularo {

	private JFrame frame;
	private JTextField textNombre;
	private JTextField textEdad;
	private JTextField textSexo;
	private JTextField textPeso;
	private JTextField textAltura;
	Persona p= new Persona();

	/**
	 * Launch the application.
	 */
	private boolean camposVacios() {
		if(textNombre.getText()==null||textNombre.getText().isEmpty()||textEdad.getText()==null||
				textEdad.getText().isEmpty()||textAltura.getText()==null||textAltura.getText().isEmpty()||
				textPeso.getText()==null||textPeso.getText().isEmpty()||
				textSexo.getText()==null||textSexo.getText().isEmpty())
			return true;
	     	return false;
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formularo window = new Formularo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Formularo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(104, 34, 70, 15);
		frame.getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(192, 32, 114, 19);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblEdad = new JLabel("EDAD");
		lblEdad.setBounds(104, 61, 70, 15);
		frame.getContentPane().add(lblEdad);
		
		textEdad = new JTextField();
		
		textEdad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String texto=textEdad.getText().trim();
				if((e.getKeyCode()<48||e.getKeyCode()>57)&&(e.getKeyCode()<96||e.getKeyCode()>105))
					textEdad.setText(texto.substring(0,texto.length()-1));
			}
			
		});
		
		textEdad.setBounds(192, 63, 114, 19);
		frame.getContentPane().add(textEdad);
		textEdad.setColumns(10);
		
		JLabel lblSexo = new JLabel("SEXO");
		lblSexo.setBounds(104, 88, 70, 15);
		frame.getContentPane().add(lblSexo);
		
		JLabel lblPeso = new JLabel("PESO");
		lblPeso.setBounds(104, 131, 70, 15);
		frame.getContentPane().add(lblPeso);
		
		JLabel lblAltura = new JLabel("ALTURA");
		lblAltura.setBounds(104, 177, 70, 15);
		frame.getContentPane().add(lblAltura);
		
		textSexo = new JTextField();
		textSexo.setBounds(192, 94, 114, 19);
		frame.getContentPane().add(textSexo);
		textSexo.setColumns(10);
		
		textPeso = new JTextField();
		textPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String texto=textPeso.getText().trim();
				if(e.getKeyCode()!=46&&e.getKeyCode()!=110&&(e.getKeyCode()<48||e.getKeyCode()>57)&&(e.getKeyCode()<96||e.getKeyCode()>105))
					textPeso.setText(texto.substring(0,texto.length()-1));
			}
			
		});
			

		textPeso.setBounds(192, 129, 114, 19);
		frame.getContentPane().add(textPeso);
		textPeso.setColumns(10);
		
		textAltura = new JTextField();
		textAltura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String texto=textAltura.getText().trim();
				if(e.getKeyCode()!=46&&e.getKeyCode()!=110&&(e.getKeyCode()<48||e.getKeyCode()>57)&&(e.getKeyCode()<96||e.getKeyCode()>105))
					textAltura.setText(texto.substring(0,texto.length()-1));
			}
			
			
		});
		textAltura.setBounds(192, 175, 114, 19);
		frame.getContentPane().add(textAltura);
		textAltura.setColumns(10);
		
		JButton btnImc = new JButton("IMC");
		btnImc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(camposVacios())
					JOptionPane.showMessageDialog(null, "NO PUEDES DEJAR CAMPOS EN BLANCO");
				else {
					p.setNombre(textNombre.getText());
					p.setAltura(Double.parseDouble(textAltura.getText()));
					p.setPeso(Double.parseDouble(textPeso.getText()));
					
					int imc=p.calcularIMC();
					if(imc==0)
					JOptionPane.showMessageDialog(null,"TU PESO ES EL IDEAL" );
					else if(imc==-1)
						JOptionPane.showMessageDialog(null,"ESTÁS POR DEBAJO DE TU PESO IDEAL" );
					else
						JOptionPane.showMessageDialog(null,"ESTÁS POR ENCIMA DE TU PESO IDEAL" );
				}
			
			}
		});
		btnImc.setBounds(43, 217, 117, 25);
		frame.getContentPane().add(btnImc);
		
		JButton btnNewButton = new JButton("adulto?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(camposVacios())
					JOptionPane.showMessageDialog(null, "NO PUEDES DEJAR CAMPOS EN BLANCO");
				else {
					p.setEdad(Integer.parseInt(textEdad.getText()));
					if(p.esMayorDeEdad())
						JOptionPane.showMessageDialog(null,"YA ERES MAYOR DE EDAD" );
					else
						JOptionPane.showMessageDialog(null,"AÚN NO ERES MAYOR DE EDAD" );
				}
				
			}
		});
		btnNewButton.setBounds(189, 217, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnInformacion = new JButton("Info");
		btnInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(camposVacios())
					JOptionPane.showMessageDialog(null, "NO PUEDES DEJAR CAMPOS EN BLANCO");
				else {
					p=new Persona(textNombre.getText(),
							Integer.parseInt(textEdad.getText()),
							textSexo.getText().trim().charAt(0),
							Double.parseDouble(textPeso.getText()),
							Double.parseDouble(textAltura.getText()));
					JOptionPane.showMessageDialog(null, p);
				}
					
				
			}
		});
		btnInformacion.setBounds(318, 217, 117, 25);
		frame.getContentPane().add(btnInformacion);
	}
}
