package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

@SuppressWarnings("serial")
public class Cep extends JFrame {

	private JPanel contentPane;
	private final JLabel lblCEP = new JLabel("CEP");
	private JTextField tfCep;
	private JTextField tfEndereco;
	private JTextField tfBairro;
	private JTextField tfCidade;
	private JComboBox cbUF;
	private JLabel lblCheck;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cep() {
		setTitle("Buscar CEP");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblCEP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCEP.setBounds(41, 24, 28, 31);
		contentPane.add(lblCEP);

		tfCep = new JTextField();
		tfCep.setBounds(79, 29, 90, 20);
		contentPane.add(tfCep);
		tfCep.setColumns(10);

		JLabel lblEndereco = new JLabel("Endereço");
		lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereco.setBounds(0, 82, 68, 14);
		contentPane.add(lblEndereco);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setBounds(22, 128, 46, 14);
		contentPane.add(lblBairro);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setBounds(22, 172, 46, 14);
		contentPane.add(lblCidade);

		JLabel lblUF = new JLabel("UF");
		lblUF.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUF.setBounds(306, 172, 19, 14);
		contentPane.add(lblUF);

		tfEndereco = new JTextField();
		tfEndereco.setBounds(78, 79, 318, 20);
		contentPane.add(tfEndereco);
		tfEndereco.setColumns(10);

		tfBairro = new JTextField();
		tfBairro.setBounds(78, 125, 318, 20);
		contentPane.add(tfBairro);
		tfBairro.setColumns(10);

		tfCidade = new JTextField();
		tfCidade.setBounds(78, 170, 225, 20);
		contentPane.add(tfCidade);
		tfCidade.setColumns(10);

		cbUF = new JComboBox();
		cbUF.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cbUF.setBounds(335, 168, 61, 22);
		contentPane.add(cbUF);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(22, 216, 89, 23);
		contentPane.add(btnLimpar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					tfCep.requestFocus();
				} else {
					buscarCep();
				}
			}
		});
		btnBuscar.setBounds(236, 28, 89, 23);
		contentPane.add(btnBuscar);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/about.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBorder(null);
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setBounds(349, 11, 57, 48);
		contentPane.add(btnSobre);

		lblCheck = new JLabel("");
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck.setBounds(179, 29, 20, 20);
		contentPane.add(lblCheck);

		// Biblioteca ATXY2K
		RestrictedTextField validar = new RestrictedTextField(tfCep);
		validar.setOnlyNums(true);
		validar.setLimit(8);

	}

	public void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String erro = null;
		String cep = tfCep.getText();
		try {
			URL url = new URL("https://viacep.com.br/ws/" + cep + "/xml/");

			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("logradouro")) {
					tfEndereco.setText(element.getText());
				}
				if (element.getQualifiedName().equals("localidade")) {
					tfCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					tfBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cbUF.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("erro")) {
					erro = element.getText();
					if (erro.equals("true")) {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
						tfCep.setText(null);
						tfCep.requestFocus();
					}
				} else {
					lblCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void limpar() {
		tfCep.setText(null);
		tfEndereco.setText(null);
		tfCidade.setText(null);
		tfBairro.setText(null);
		cbUF.setSelectedItem(null);
		tfCep.requestFocus();
		lblCheck.setIcon(null);
	}
}
