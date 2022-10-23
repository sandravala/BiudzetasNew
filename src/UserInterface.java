import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;

public class UserInterface {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        // reikes isskaidyti i atskirus metodukus

        // pvz paspaudus ivesti pajamas / islaidas, atsidaro naujas langas kur yra laukeliai ivedimui pajamu / islaidu

        Biudzetas biudzetas = new Biudzetas();

        JFrame frame = new JFrame("BIUDZETAS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.5);
        int height = (int) (screenSize.height * 0.5);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> duomenysSpausdinimui = new JList<>(model);

        JLabel lblVeiksmai = new JLabel("galimi Veiksmai :");
        String[] veiksmai = {"Atidaryti biudžeto failą", "Rodyti visus įvestus įrašus", "Įvesti pajamas", "Įvesti išlaidas", "Ištrinti įrašą", "Redaguoti įrašą", "Saugoti įvestus duomenis į failą", "Ištrinti viską"};
        JComboBox<String> veiksmaiCB = new JComboBox<>(veiksmai);
        veiksmaiCB.setSelectedIndex(0);

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
        String[] kurGauta = {"bankas", "grynieji"};
        JComboBox<String> kurGautaComboBox = new JComboBox<>(kurGauta);
        kurGautaComboBox.setSelectedIndex(1);

//        JLabel lblPajamosIslaidos = new JLabel("koks irasas :");
//        String[] pajamosIslaidos = {"pajamos", "islaidos"};
//        JComboBox<String> pajamosIslaidosCB = new JComboBox<>(pajamosIslaidos);
//        pajamosIslaidosCB.setSelectedIndex(1);

        JLabel labelpapildomaInfo = new JLabel("Papildoma info:");
        JTextField papildomaInfo = new JTextField(20);

        JLabel labelAtsiskaitymoBudas = new JLabel("Atsiskaitymo budas:");
        JTextField atsiskaitymoBudas = new JTextField(6);

        JLabel labelsurastiData = new JLabel("Ivesk data:");
        JTextField surastiData = new JTextField(12);

        JLabel labelsurastiKategorija = new JLabel("Ivesk kategorija:");
        JTextField surastiKategorija = new JTextField(20);

        JButton issaugotiPajamasMygtukas = new JButton(" Išsaugoti pajamų įrašą ");
        JButton issaugotiIslaidasMygtukas = new JButton(" Išsaugoti išlaidų irasa ");
        JButton ivedimoMygtukas = new JButton(" Ivesti pajamas arba islaidas ");
        JButton isvalymoMygtukas = new JButton(" išvalyti ");
        JButton veiksmuMygtukas = new JButton(" go! ");


        issaugotiPajamasMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {

                try {
                    double suma = Double.parseDouble(sumaPI.getValue().toString());
                    String kategorija = kategorijaE.getText();
                    String iBanka = kurGautaComboBox.getSelectedItem().toString();
                    String info = papildomaInfo.getText();


                    biudzetas.pridetiPajamas(suma, kategorija, info, iBanka);
                    model.addElement(String.format("pridetas pajamu irasas: suma %s Eur, gauta i banka: %s ", suma, iBanka));
                } catch (NumberFormatException e) {
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

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        JPanel panelVeiksmai = new JPanel();
        panelVeiksmai.add(lblVeiksmai);
        panelVeiksmai.add(veiksmaiCB);
        panelVeiksmai.add(veiksmuMygtukas);
        c.gridx = 0;
        c.gridy = 0;
        frame.add(panelVeiksmai, c);

        JPanel panelFirst = new JPanel();
        c.weightx = c.weighty = 1.0;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(panelFirst, c);

        JPanel panelSecond = new JPanel();
        c.weightx = c.weighty = 1.0;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 2;
        frame.add(panelSecond, c);

        JPanel panelSaugoti = new JPanel();
        c.gridx = 0;
        c.gridy = 3;
        frame.add(panelSaugoti, c);


        JPanel panelAtidaryti = new JPanel();
        // cia idesiu text field failo pavadinimo irasymui ir mygtuka atidaryti

        JPanel panelIvestiPajamuIrasa = new JPanel();
        panelIvestiPajamuIrasa.add(LBLsumaPI);
        panelIvestiPajamuIrasa.add(LBLsumaPI);
        panelIvestiPajamuIrasa.add(labelKategorija);
        panelIvestiPajamuIrasa.add(kategorijaE);
        panelIvestiPajamuIrasa.add(labelarGautaIBankoSask);
        panelIvestiPajamuIrasa.add(kurGautaComboBox);
        panelIvestiPajamuIrasa.add(labelpapildomaInfo);
        panelIvestiPajamuIrasa.add(papildomaInfo);
        panelIvestiPajamuIrasa.add(issaugotiPajamasMygtukas);


        JPanel panelIvestiIslaiduIrasa = new JPanel();
        panelIvestiIslaiduIrasa.add(LBLsumaPI);
        panelIvestiIslaiduIrasa.add(LBLsumaPI);
        panelIvestiIslaiduIrasa.add(labelKategorija);
        panelIvestiIslaiduIrasa.add(kategorijaE);
        panelIvestiIslaiduIrasa.add(labelarGautaIBankoSask);
        panelIvestiIslaiduIrasa.add(kurGautaComboBox);
        panelIvestiIslaiduIrasa.add(labelpapildomaInfo);
        panelIvestiIslaiduIrasa.add(papildomaInfo);
        panelIvestiIslaiduIrasa.add(issaugotiIslaidasMygtukas);

        JPanel panelIstrintiIrasa = new JPanel();
        JPanel panelRedaguotiIrasa = new JPanel();
        JPanel panelSaugotiIFaila = new JPanel();
        JPanel panelIstrintiViska = new JPanel();

        JPanel panelIvestis = new JPanel();

        JPanel panelIsvestis = new JPanel();
        //panelIsvestis.setLayout(new GridLayout());
        JScrollPane scrollPane = new JScrollPane(duomenysSpausdinimui);
        panelIsvestis.add(scrollPane);


//        frame.setLayout(new GridLayout(4, 1));
//        frame.add(panelVeiksmai);
//        frame.add(panelFirst);
//        frame.add(panelSecond);
//        frame.add(panelSaugoti);


//  , , ,

        veiksmuMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                String veiksmas = veiksmaiCB.getSelectedItem().toString();
                switch (veiksmas) {
                    case "Atidaryti biudžeto failą":
                        panelFirst.add(panelAtidaryti);
                        panelSecond.add(panelIsvestis);
                        panelAtidaryti.setVisible(true);
                        break;
                    case "Rodyti visus įvestus įrašus":

                        ArrayList<String> pajamuIrasai = new ArrayList<>(biudzetas.spausdintiUI("pajamas"));
                        for (String irasas : pajamuIrasai) {
                            model.addElement(irasas);
                        }
                        ArrayList<String> islaiduIrasai = new ArrayList<>(biudzetas.spausdintiUI("islaidas"));
                        for (String irasas : islaiduIrasai) {
                            model.addElement(irasas);
                        }

                        panelFirst.add(panelIsvestis);
                        scrollPane.setVisible(true);
                        panelIsvestis.setVisible(true);
                        panelFirst.setVisible(true);

                        panelFirst.validate();
                        break;
                    case "Įvesti pajamas":
                        panelFirst.add(panelIvestiPajamuIrasa);
                        panelSecond.add(panelIsvestis);
                        panelIvestiPajamuIrasa.setVisible(true);
                        panelFirst.setVisible(true);
                        panelSecond.setVisible(true);

                        break;
                    case "Įvesti išlaidas":
                        panelFirst.add(panelIvestiIslaiduIrasa);
                        panelSecond.add(panelIsvestis);
                        panelIvestiIslaiduIrasa.setVisible(true);
                        break;
                    case "Ištrinti įrašą":
                        panelFirst.add(panelIstrintiIrasa);
                        panelSecond.add(panelIsvestis);

                        panelIstrintiIrasa.setVisible(true);
                        break;
                    case "Redaguoti įrašą":
                        panelFirst.add(panelRedaguotiIrasa);
                        panelSecond.add(panelIsvestis);

                        panelRedaguotiIrasa.setVisible(true);
                        break;
                    case "Saugoti įvestus duomenis į failą":
                        panelFirst.add(panelSaugotiIFaila);
                        panelSecond.add(panelIsvestis);

                        panelSaugotiIFaila.setVisible(true);
                        break;
                    case "Ištrinti viską":
                        panelFirst.add(panelIstrintiViska);
                        panelSecond.add(panelIsvestis);

                        panelIstrintiViska.setVisible(true);
                        break;
                }
            }
        });

        frame.setVisible(true);

    }
}
