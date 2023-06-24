package cep;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblBuscarCEP = new JLabel("Buscar CEP - ver 1.0");
		lblBuscarCEP.setBounds(46, 38, 217, 14);
		getContentPane().add(lblBuscarCEP);
		
		JLabel lblAutor = new JLabel("@Author Valdir com minicurso do Prof José de Assis");
		lblAutor.setBounds(46, 92, 341, 14);
		getContentPane().add(lblAutor);
		
		JLabel lblWEBService = new JLabel("WEB Service:");
		lblWEBService.setBounds(46, 144, 79, 14);
		getContentPane().add(lblWEBService);
		
		JLabel lblWebService = new JLabel("https://viacep.com.br/");
		lblWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://viacep.com.br/");
			}
		});
		lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWebService.setForeground(SystemColor.textHighlight);
		lblWebService.setBounds(135, 144, 165, 14);
		getContentPane().add(lblWebService);
		
		JButton btnFacebook = new JButton("");
		btnFacebook.setBackground(SystemColor.control);
		btnFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://www.facebook.com/valdir.matos.3150");
			}
		});
		btnFacebook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFacebook.setBorder(null);
		btnFacebook.setForeground(SystemColor.control);
		btnFacebook.setIcon(new ImageIcon(Sobre.class.getResource("/img/facebook.png")));
		btnFacebook.setBounds(46, 186, 48, 48);
		getContentPane().add(btnFacebook);
		
		JButton btnYoutube = new JButton("");
		btnYoutube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://www.youtube.com/channel/UCTvCwDZsOyrgReyfoXg_MMg");
			}
		});
		btnYoutube.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnYoutube.setBorder(null);
		btnYoutube.setBackground(SystemColor.control);
		btnYoutube.setIcon(new ImageIcon(Sobre.class.getResource("/img/youtube.png")));
		btnYoutube.setBounds(146, 186, 48, 48);
		getContentPane().add(btnYoutube);

	}
	private void link(String site) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
