import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.IllegalFormatException;

import javax.swing.*;

public class UserInterface {
    public static void main(String[] args) {

        Biudzetas biudzetas = new Biudzetas();

        JFrame frame = new JFrame("BIUDZETAS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.8);
        int height = (int) (screenSize.height * 0.5);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
//        try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");}
//        catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {}

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> duomenysSpausdinimui = new JList<>(model);

        JLabel lblVeiksmai = new JLabel("galimi Veiksmai :");
        String[] veiksmai = {"Atidaryti biudžeto failą", "Rodyti visus įvestus įrašus", "Įvesti pajamas", "Įvesti išlaidas", "Ištrinti įrašą", "Redaguoti įrašą", "Saugoti įvestus duomenis į failą", "Ištrinti viską"};
        JComboBox<String> veiksmaiCB = new JComboBox<>(veiksmai);
        veiksmaiCB.setSelectedIndex(0);

        JLabel labelFailas = new JLabel("Failo pavadinimas: ");
        JTextField failoPavadinimas = new JTextField(20);

// formatuotas laukelis sumai su valiuta
        NumberFormat sumosFormatas = NumberFormat.getCurrencyInstance();
        JFormattedTextField sumaPI = new JFormattedTextField(sumosFormatas);
        sumaPI.setName("Suma");
        sumaPI.setColumns(10);
        sumaPI.setEditable(true);
        JLabel LBLsumaPI = new JLabel("Suma: ");
        LBLsumaPI.setLabelFor(sumaPI);
        sumaPI.setValue((double) 0);

        JLabel LBLdataPI = new JLabel("Data: ");
        JTextField dataPI = new JTextField(6);

        JLabel labelLaikas = new JLabel("Laikas: ");
        JTextField Laikas = new JTextField(6);

        JTextField irasoNr = new JTextField(6);
        irasoNr.setEditable(false);

        JLabel labelKategorija = new JLabel("Kategorija: ");
        JTextField kategorijaE = new JTextField(20);

        JLabel labelarGautaIBankoSask = new JLabel("Gavimo tipas: ");
        JLabel labelarMoketaIsBankoSask = new JLabel("Mokėjimo tipas: ");
        String[] kurGauta = {"  bankas  ", "  grynieji  "};
        JComboBox<String> kurGautaComboBox = new JComboBox<>(kurGauta);
        kurGautaComboBox.setSelectedIndex(0);

        JLabel labelpapildomaInfo = new JLabel("Papildoma info: ");
        JTextField papildomaInfo = new JTextField(20);

        JLabel LBLnumeris = new JLabel("Įrašo numeris: ");
        JTextField numeris = new JTextField(6);

        JLabel LBLtrintiViska = new JLabel("Ar tikrai norite ištrinti VISUS ĮVESTUS DUOMENIS? Bus ištrinta tiek iš sistemos, tiek iš failo ");

        JButton issaugotiPajamasMygtukas = new JButton(" SAUGOTI ");
        JButton issaugotiIslaidasMygtukas = new JButton(" SAUGOTI ");
        JButton redaguotiIrasaMygtukas = new JButton(" REDAGUOTI ");
        JButton saugotiRedaguotaIrasaMygtukas = new JButton(" SAUGOTI ");
        JButton rastiIrasaMygtukas = new JButton(" Rasti įrašą ");
        JButton vienoIrasoIstrynimoMygtukas = new JButton(" TRINTI ");
        JButton viskoIstrynimoMygtukas = new JButton(" TRINTI VISKĄ");
        JButton isvalymoMygtukas = new JButton(" išvalyti ekraną ");
        JButton saugotiViskaIFailaMygtukas = new JButton(" SAUGOTI ");
        JButton veiksmuMygtukas = new JButton(" go! ");
        JButton atidarytiFailaMygtukas = new JButton(" Atidaryti ");

        viskoIstrynimoMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                model.add(0, biudzetas.istrintiVisusIrasus());
            }
        });
        atidarytiFailaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                    model.add(0, biudzetas.atidarytiFaila(failoPavadinimas.getText()));
                }
        });

        saugotiViskaIFailaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {

                model.add(0, biudzetas.saugoti(failoPavadinimas.getText(), false));
            }
        });

        rastiIrasaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    String veiksmas = veiksmaiCB.getSelectedItem().toString();
                    String irasoNumeris = numeris.getText();
                        if(veiksmas.equals("Ištrinti įrašą")) {
                            model.add(0, "Spauskite TRINTI, kad ištrintumėte šį įrašą: ");
                        } else if (veiksmas.equals("Redaguoti įrašą")) {
                            model.add(0, "Spauskite REDAGUOTI, kad redaguotumėt šį įrašą: ");
                        }
                        model.add(1, biudzetas.spausdintiIrasa(irasoNumeris, biudzetas.rastiIrasa(irasoNumeris)));
                        model.add(2, " ");
                    } catch (Exception e) {
                    model.addElement("Klaida! Klaidos kodas:");
                    model.addElement(Exception.class.getName());
                    model.addElement(" ");
                }
            }
        });

        vienoIrasoIstrynimoMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                    String irasoNumeris = numeris.getText();
                    model.add(0, biudzetas.istrintiIrasaUI(biudzetas.rastiIrasa(irasoNumeris)));
                    model.add(1, " ");
                } catch (Exception e) {
                    model.addElement("Klaida! Klaidos kodas:");
                    model.addElement(Exception.class.getName());
                }
            }
        });

        redaguotiIrasaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try {
                String irasoNumeris = numeris.getText();
                irasoNr.setText(irasoNumeris);
                sumaPI.setValue(biudzetas.rastiIrasa(irasoNumeris).getSuma());
                kategorijaE.setText(biudzetas.rastiIrasa(irasoNumeris).getKategorija());
                papildomaInfo.setText(biudzetas.rastiIrasa(irasoNumeris).getPapildomaInfo());
                dataPI.setText(biudzetas.rastiIrasa(irasoNumeris).dataFormatuota());
                Laikas.setText(biudzetas.rastiIrasa(irasoNumeris).laikasBeDatos());
                } catch (Exception e) {
                    model.addElement("Klaida! Klaidos kodas:");
                    model.addElement(Exception.class.getName());
                }
            }
        });

        saugotiRedaguotaIrasaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                try{
                String irasoNumeris = numeris.getText();
                double suma = Double.parseDouble(sumaPI.getValue().toString());
                String kategorija = kategorijaE.getText();
                String iBanka = kurGautaComboBox.getSelectedItem().toString().replace(" ", "");
                String info = papildomaInfo.getText();
                String data = dataPI.getText();
                String laikas = Laikas.getText();

                ArrayList<String> rezultatai = new ArrayList<>(biudzetas.redaguotiIrasa(biudzetas.rastiIrasa(irasoNumeris), suma, kategorija, info, iBanka, data, laikas));
                for (String r : rezultatai) {
                    model.addElement(r);
                }
                model.addElement(" ");
                } catch (DateTimeParseException | IllegalFormatException | NumberFormatException e) {
                    model.addElement("Klaida! Netinkamas formatas: ");
                    model.addElement(e.getLocalizedMessage());
                }
            }
        });

        issaugotiPajamasMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {

                try {
                    double suma = Double.parseDouble(sumaPI.getValue().toString());
                    String kategorija = kategorijaE.getText();
                    String iBanka = kurGautaComboBox.getSelectedItem().toString().replace(" ", "");
                    String info = papildomaInfo.getText();
                    model.add(0, biudzetas.pridetiPajamasUI(suma, kategorija, info, iBanka));
                } catch (NumberFormatException e) {
                    model.addElement(String.format("Ivedete skaitmenis netinkamu formatu. klaidos klasifikacija: %s", e.getClass()));
                }
            }
        });

        issaugotiIslaidasMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {

                try {
                    double suma = Double.parseDouble(sumaPI.getValue().toString());
                    String kategorija = kategorijaE.getText();
                    String iBanka = kurGautaComboBox.getSelectedItem().toString().replace(" ", "");
                    String info = papildomaInfo.getText();
                    model.add(0, biudzetas.pridetiIslaidasUI(suma, kategorija, info, iBanka));
                } catch (NumberFormatException e) {
                    model.addElement(String.format("Ivedete skaitmenis netinkamu formatu. klaidos klasifikacija: %s", e.getClass()));
                }
            }
        });

        isvalymoMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                model.clear();
            }
        });


// panelės ir išdėstymas

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        JPanel panelVeiksmai = new JPanel();
        panelVeiksmai.add(lblVeiksmai);
        panelVeiksmai.add(veiksmaiCB);
        panelVeiksmai.add(veiksmuMygtukas);
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 0.05;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(panelVeiksmai, c);


        JPanel panelApacia = new JPanel();
        panelApacia.add(isvalymoMygtukas);
        c.anchor = GridBagConstraints.PAGE_END;
        c.weighty = 0.00000001;
        c.gridx = 0;
        c.gridy = 4;
        frame.add(panelApacia, c);


        JPanel panelAtidaryti = new JPanel();
        JPanel panelSaugotiIFaila = new JPanel();
        JPanel panelIstrintiViska = new JPanel();
        JPanel panelIvestiPajamuIrasa = new JPanel();
        JPanel panelIvestiIslaiduIrasa = new JPanel();
        JPanel panelRedaguotiIrasa = new JPanel();
        JPanel panelRastiRedaguotaIrasa = new JPanel();
        JPanel panelIstrintiIrasa = new JPanel();

        JScrollPane scrollPane = new JScrollPane(duomenysSpausdinimui);


        veiksmuMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                String veiksmas = veiksmaiCB.getSelectedItem().toString();
                panelRedaguotiIrasa.setVisible(false);
                panelRastiRedaguotaIrasa.setVisible(false);
                panelSaugotiIFaila.setVisible(false);
                panelIstrintiViska.setVisible(false);
                panelIvestiPajamuIrasa.setVisible(false);
                panelIvestiIslaiduIrasa.setVisible(false);
                panelIstrintiIrasa.setVisible(false);
                panelAtidaryti.setVisible(false);

                switch (veiksmas) {
                    case "Atidaryti biudžeto failą":
                        panelAtidaryti.add(labelFailas);
                        panelAtidaryti.add(failoPavadinimas);
                        panelAtidaryti.add(atidarytiFailaMygtukas);

                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(panelAtidaryti, c);

                        c.weightx = c.weighty = 1.0;
                        c.gridx = 0;
                        c.gridy = 2;
                        frame.add(scrollPane, c);

                        scrollPane.setVisible(true);
                        panelAtidaryti.setVisible(true);
                        break;
                    case "Rodyti visus įvestus įrašus":
                        model.clear();

                        ArrayList<String> pajamuIrasai = new ArrayList<>(biudzetas.spausdintiUI("pajamas"));
                        for (String irasas : pajamuIrasai) {
                            model.addElement(irasas);
                        }
                        ArrayList<String> islaiduIrasai = new ArrayList<>(biudzetas.spausdintiUI("islaidas"));
                        for (String irasas : islaiduIrasai) {
                            model.addElement(irasas);
                        }

                        c.weightx = c.weighty = 1.0;
                        c.anchor = GridBagConstraints.BASELINE;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(scrollPane, c);
                        scrollPane.setVisible(true);
                        frame.validate();

                        break;
                    case "Įvesti pajamas":
                        model.clear();

                        panelIvestiPajamuIrasa.add(LBLsumaPI);
                        panelIvestiPajamuIrasa.add(sumaPI);
                        panelIvestiPajamuIrasa.add(labelKategorija);
                        panelIvestiPajamuIrasa.add(kategorijaE);
                        panelIvestiPajamuIrasa.add(labelarGautaIBankoSask);
                        panelIvestiPajamuIrasa.add(kurGautaComboBox);
                        panelIvestiPajamuIrasa.add(labelpapildomaInfo);
                        panelIvestiPajamuIrasa.add(papildomaInfo);
                        panelIvestiPajamuIrasa.add(issaugotiPajamasMygtukas);

                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(panelIvestiPajamuIrasa, c);

                        c.weightx = c.weighty = 1.0;
                        c.gridx = 0;
                        c.gridy = 2;
                        frame.add(scrollPane, c);

                        scrollPane.setVisible(true);
                        panelIvestiPajamuIrasa.setVisible(true);

                        frame.validate();

                        break;
                    case "Įvesti išlaidas":
                        model.clear();

                        panelIvestiIslaiduIrasa.add(LBLsumaPI);
                        panelIvestiIslaiduIrasa.add(sumaPI);
                        panelIvestiIslaiduIrasa.add(labelKategorija);
                        panelIvestiIslaiduIrasa.add(kategorijaE);
                        panelIvestiIslaiduIrasa.add(labelarMoketaIsBankoSask);
                        panelIvestiIslaiduIrasa.add(kurGautaComboBox);
                        panelIvestiIslaiduIrasa.add(labelpapildomaInfo);
                        panelIvestiIslaiduIrasa.add(papildomaInfo);
                        panelIvestiIslaiduIrasa.add(issaugotiIslaidasMygtukas);

                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(panelIvestiIslaiduIrasa, c);

                        c.weightx = c.weighty = 1.0;
                        c.gridx = 0;
                        c.gridy = 2;
                        frame.add(scrollPane, c);

                        scrollPane.setVisible(true);
                        panelIvestiIslaiduIrasa.setVisible(true);

                        frame.validate();

                        break;
                    case "Ištrinti įrašą":
                        model.clear();

                        panelIstrintiIrasa.add(LBLnumeris);
                        panelIstrintiIrasa.add(numeris);
                        panelIstrintiIrasa.add(rastiIrasaMygtukas);
                        panelIstrintiIrasa.add(vienoIrasoIstrynimoMygtukas);

                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(panelIstrintiIrasa, c);

                        c.weightx = c.weighty = 1.0;
                        c.gridx = 0;
                        c.gridy = 2;
                        frame.add(scrollPane, c);
                        scrollPane.setVisible(true);
                        panelIstrintiIrasa.setVisible(true);

                        frame.validate();
                        break;
                    case "Redaguoti įrašą":
                        model.clear();

                        panelRastiRedaguotaIrasa.add(LBLnumeris);
                        panelRastiRedaguotaIrasa.add(numeris);
                        panelRastiRedaguotaIrasa.add(rastiIrasaMygtukas);
                        panelRastiRedaguotaIrasa.add(redaguotiIrasaMygtukas);

                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(panelRastiRedaguotaIrasa, c);

                        panelRedaguotiIrasa.add(irasoNr);
                        panelRedaguotiIrasa.add(LBLsumaPI);
                        panelRedaguotiIrasa.add(sumaPI);
                        panelRedaguotiIrasa.add(labelKategorija);
                        panelRedaguotiIrasa.add(kategorijaE);
                        panelRedaguotiIrasa.add(labelarGautaIBankoSask);
                        panelRedaguotiIrasa.add(kurGautaComboBox);
                        panelRedaguotiIrasa.add(labelpapildomaInfo);
                        panelRedaguotiIrasa.add(papildomaInfo);
                        panelRedaguotiIrasa.add(LBLdataPI);
                        panelRedaguotiIrasa.add(dataPI);
                        panelRedaguotiIrasa.add(labelLaikas);
                        panelRedaguotiIrasa.add(Laikas);
                        panelRedaguotiIrasa.add(saugotiRedaguotaIrasaMygtukas);

                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 2;
                        frame.add(panelRedaguotiIrasa, c);

                        c.weightx = c.weighty = 1.0;
                        c.gridx = 0;
                        c.gridy = 3;
                        frame.add(scrollPane, c);
                        scrollPane.setVisible(true);
                        panelRastiRedaguotaIrasa.setVisible(true);
                        panelRedaguotiIrasa.setVisible(true);
                        break;
                    case "Saugoti įvestus duomenis į failą":
                        failoPavadinimas.setText(biudzetas.getFileName());

                        panelSaugotiIFaila.add(labelFailas);
                        panelSaugotiIFaila.add(failoPavadinimas);
                        panelSaugotiIFaila.add(saugotiViskaIFailaMygtukas);

                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(panelSaugotiIFaila, c);

                        c.weightx = c.weighty = 1.0;
                        c.gridx = 0;
                        c.gridy = 2;
                        frame.add(scrollPane, c);

                        scrollPane.setVisible(true);
                        panelSaugotiIFaila.setVisible(true);
                        break;

                    case "Ištrinti viską":
                        model.clear();

                        panelIstrintiViska.add(LBLtrintiViska);
                        panelIstrintiViska.add(viskoIstrynimoMygtukas);

                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(panelIstrintiViska, c);

                        c.weightx = c.weighty = 1.0;
                        c.gridx = 0;
                        c.gridy = 2;
                        frame.add(scrollPane, c);
                        scrollPane.setVisible(true);
                        panelIstrintiViska.setVisible(true);

                        frame.validate();
                        break;
                }
            }
        });

        frame.setVisible(true);

    }
}
