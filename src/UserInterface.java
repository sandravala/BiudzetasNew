import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

public class UserInterface {
	
	public static void main(String[] args) {
	
	// reikes isskaidyti i atskirus metodukus
	
	// pvz paspaudus ivesti pajamas / islaidas, atsidaro naujas langas kur yra laukeliai ivedimui pajamu / islaidu
		
	Biudzetas biudzetas = new Biudzetas();

	JFrame frame = new JFrame("BIUDZETAS");  
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(screenSize.width/2, screenSize.height);

	DefaultListModel<String> model = new DefaultListModel<>();
	JList<String> sarasas = new JList<>(model);

// formatuotas laukelis sumai su valiuta
	NumberFormat sumosFormatas = NumberFormat.getCurrencyInstance();
	JFormattedTextField sumaPI = new JFormattedTextField(sumosFormatas);
	sumaPI.setName("Suma");
	sumaPI.setColumns(10);
	sumaPI.setEditable(true);
	JLabel LBLsumaPI = new JLabel("Suma:");
	LBLsumaPI.setLabelFor(sumaPI);
	sumaPI.setValue((double) 0);

	JLabel labelKategorija = new JLabel("Kategorija:");  
	JTextField kategorijaE = new JTextField(20);
	
	JLabel labelarGautaIBankoSask = new JLabel("gavimo / atsiskaitymo budas:");  
	String[] kurGauta = { "bankas", "grynieji" };
	JComboBox<String> kurGautaComboBox = new JComboBox<>(kurGauta);
	kurGautaComboBox.setSelectedIndex(1);

	JLabel lblPajamosIslaidos = new JLabel("koks irasas :");
	String[] pajamosIslaidos = { "pajamos", "islaidos" };
	JComboBox<String> pajamosIslaidosCB = new JComboBox<>(pajamosIslaidos);
	pajamosIslaidosCB.setSelectedIndex(1);

	JLabel labelpapildomaInfo = new JLabel("Papildoma info:");
	JTextField papildomaInfo = new JTextField(20);
	
	JLabel labelAtsiskaitymoBudas = new JLabel("Atsiskaitymo budas:");  
	JTextField atsiskaitymoBudas = new JTextField(6);
	
	JLabel labelsurastiData = new JLabel("Ivesk data:");  
	JTextField surastiData = new JTextField(12);
	
	JLabel labelsurastiKategorija = new JLabel("Ivesk kategorija:");  
	JTextField surastiKategorija = new JTextField(20);
	
	JButton issaugotiPajamasMygtukas = new JButton(" Issaugoti pajamu irasa ");  
	JButton ivedimoMygtukas = new JButton(" Ivesti pajamas arba islaidas ");
	JButton isvalymoMygtukas = new JButton(" išvalyti ");



	issaugotiPajamasMygtukas.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent a) {  
		
		try {
		double suma = Double.parseDouble(sumaPI.getValue().toString());
		String kategorija = kategorijaE.getText();
		String iBanka = kurGautaComboBox.getSelectedItem().toString();
		String info = papildomaInfo.getText();
		
		
		biudzetas.pridetiPajamas(suma, kategorija, info, iBanka);
		model.addElement(String.format("pridetas pajamu irasas: suma %s Eur, gauta i banka: %s ", suma, iBanka));
		} 
		catch (NumberFormatException e) {
			model.addElement(String.format("Ivedete skaitmenis netinkamu formatu. klaidos klasifikacija: %s", e.getClass()));
		}

		//model.addElement(mixStrings(a, b));
				
		}
	});
	
			
	isvalymoMygtukas.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent a) {  
		model.clear();
	}
	});


	
	JPanel panelVirsus = new JPanel();  
	JPanel panelPajamos = new JPanel();  
	//panelVirsus.setPreferredSize(new Dimension(1000, 100));  
	panelPajamos.setBackground(Color.gray);
	JPanel panelIslaidos = new JPanel();
	panelIslaidos.setBackground(Color.pink);
 
	JPanel panelSkirtukas = new JPanel();
	panelSkirtukas.add(new JLabel("Galimi veiksmai su biudzetu:"));
	JPanel panelMetodai = new JPanel();
	JPanel panelInfo = new JPanel();
	JPanel panelCentras = new JPanel(); 
	panelCentras.setLayout(new GridLayout(3, 0, 1, 1));
	panelCentras.add(panelSkirtukas);
	panelCentras.add(panelMetodai);
	panelMetodai.add(ivedimoMygtukas);
	panelCentras.add(new JScrollPane(panelInfo,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

	
	JPanel panelApacia = new JPanel();  

	panelPajamos.add(sumaPI);
	panelPajamos.add(LBLsumaPI);
	panelPajamos.add(issaugotiPajamasMygtukas);
	panelPajamos.add(kurGautaComboBox); 
	panelPajamos.add(surastiData); 
	panelPajamos.add(labelsurastiData); 
	panelPajamos.add(labelsurastiKategorija); 
	panelPajamos.add(surastiKategorija); 
 
	panelIslaidos.add(atsiskaitymoBudas);
	panelIslaidos.add(labelAtsiskaitymoBudas);

	panelIslaidos.add(labelpapildomaInfo);
	panelIslaidos.add(papildomaInfo);
	
	panelInfo.add(sarasas);


	panelApacia.add(isvalymoMygtukas);
	

	 //panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    // panel.setLayout(new GridLayout(5, 4, 5, 5));
	
	JSplitPane sp1 = new JSplitPane(SwingConstants.HORIZONTAL, panelPajamos, panelIslaidos);
	panelVirsus.add(sp1);
	panelVirsus.setVisible(false);

	//Create the tab container
	JTabbedPane tabs = new JTabbedPane();
		//Set tab container position
	tabs.setBounds(40,20,300,300);
		//Associate each panel with the corresponding tab
	tabs.add("General", panelPajamos);
	tabs.add("Display", panelIslaidos);
	tabs.add("About", panelInfo);
	
    frame.add(panelVirsus.add(tabs));
	
	//frame.add(sp1);
	//frame.add(sp2);
	
	//frame.getContentPane().add(BorderLayout.NORTH, panelVirsus);
	//frame.getContentPane().add(BorderLayout.CENTER, panelCentras);
	//frame.getContentPane().add(BorderLayout.PAGE_END, panelApacia);

	ivedimoMygtukas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				panelVirsus.setVisible(true);
			}
	});

	frame.setVisible(true);
	
	}
	}
