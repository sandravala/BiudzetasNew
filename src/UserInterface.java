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


        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> duomenysSpausdinimui = new JList<>(model);

        JLabel lblVeiksmai = new JLabel("GALIMI VEIKSMAI:");
        String[] veiksmai = {"", "Atidaryti biudžeto failą" /* šitas bus mygtukas apačioj */, "Rodyti visus įvestus įrašus" /* šitas bus atskiras scrollpane apačioj ir rodys visada */, "Įvesti pajamas", "Įvesti išlaidas", "Ištrinti įrašą", "Redaguoti įrašą", "Saugoti įvestus duomenis į failą" /* šitas bus mygtukas apačioj */, "Ištrinti viską" /* šitas bus mygtukas apačioj */};
        JComboBox<String> veiksmaiCB = new JComboBox<>(veiksmai);
        veiksmaiCB.setSelectedIndex(0);

        JLabel labelFailas = new JLabel("Failo pavadinimas: ");
        JTextField failoPavadinimas = new JTextField(10);

// formatuotas laukelis sumai su valiuta
        NumberFormat sumosFormatas = NumberFormat.getCurrencyInstance();
        JFormattedTextField sumaPI = new JFormattedTextField(sumosFormatas);
        sumaPI.setName("Suma");
        sumaPI.setColumns(8);
        sumaPI.setEditable(true);
        JLabel LBLsumaPI = new JLabel("Suma:");
        LBLsumaPI.setLabelFor(sumaPI);
        sumaPI.setValue((double) 0);

        JLabel LBLdataPI = new JLabel("Data:");
        JTextField dataPI = new JTextField(6);

        JLabel labelLaikas = new JLabel("Laikas:");
        JTextField Laikas = new JTextField(3);

        JTextField irasoNr = new JTextField(3);
        irasoNr.setEditable(false);

        JLabel labelKategorija = new JLabel("Kategorija:");
        JTextField kategorijaE = new JTextField(20);

        JLabel labelarGautaIBankoSask = new JLabel("Gavimo tipas:");
        JLabel labelarMoketaIsBankoSask = new JLabel("Mokėjimo tipas:");
        String[] kurGauta = {" bankas ", " grynieji "};
        JComboBox<String> kurGautaComboBox = new JComboBox<>(kurGauta);
        kurGautaComboBox.setSelectedIndex(0);

        JLabel labelpapildomaInfo = new JLabel("Papildoma info:");
        JTextField papildomaInfo = new JTextField(20);

        JLabel LBLnumeris = new JLabel("Įrašo Nr.:");
        JTextField numeris = new JTextField(3);

        JLabel LBLtrintiViska = new JLabel("Ar tikrai norite ištrinti VISUS ĮVESTUS DUOMENIS? Bus ištrinta tiek iš sistemos, tiek iš failo ");
        JLabel labelIspejimas = new JLabel("Ar tikrai norite atidaryti failą? Bus ištrinti visi neišsaugoti duomenys. Jei jau įvesta įrašų, pirmiau juos saugokit į failą");
        JTextArea titulinisText = new JTextArea("\n\n  Sveiki!\n\n  Čia - biudžeto programėlė.  \n\n  Galimus veiksmus pasirinkite iš viršuje esančio sąrašo.  \n\n  Apačioje esantis mygtukas išvalys ekraną (tai neįtakos įvestų duomenų)  \n\n");
        titulinisText.setEditable(false);

        JButton issaugotiPajamasMygtukas = new JButton(" ĮVESTI ");
        JButton issaugotiIslaidasMygtukas = new JButton(" ĮVESTI ");
        JButton redaguotiIrasaMygtukas = new JButton(" REDAGUOTI ");
        JButton saugotiRedaguotaIrasaMygtukas = new JButton(" SAUGOTI ");
        JButton rastiIrasaMygtukas = new JButton(" IEŠKOTI ");
        JButton vienoIrasoIstrynimoMygtukas = new JButton(" TRINTI ");
        JButton viskoIstrynimoMygtukas = new JButton(" TRINTI VISKĄ");
        viskoIstrynimoMygtukas.setForeground(new Color(226, 80, 80));
        JButton isvalymoMygtukas = new JButton(" IŠVALYTI ");
        JButton saugotiViskaIFailaMygtukas = new JButton(" SAUGOTI ");
        JButton atidarytiFailaMygtukas = new JButton(" ATIDARYTI ");
        JButton skaiciuotiBalansaMygtukas = new JButton(" BALANSAS ");

        skaiciuotiBalansaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                model.add(0, biudzetas.balansas());
            }
        });

        viskoIstrynimoMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                model.add(0, biudzetas.istrintiVisusIrasus());
            }
        });
        atidarytiFailaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                    model.add(0, biudzetas.atidarytiFaila(failoPavadinimas.getText()));
                    model.add(1, " ");
                }
        });

        saugotiViskaIFailaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                model.add(0, biudzetas.saugoti(failoPavadinimas.getText(), false));
                model.add(1, " ");
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
                    model.add(0,"Klaida! Klaidos kodas:");
                    model.add(1, Exception.class.getName());
                    model.add(2," ");
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
                    model.add(0,"Klaida! Klaidos kodas:");
                    model.add(1, Exception.class.getName());
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
                    model.add(0, r);
                }
                model.add(1," ");
                } catch (DateTimeParseException | IllegalFormatException | NumberFormatException e) {
                    model.add(0,"Klaida! Netinkamas formatas: ");
                    model.add(1, e.getLocalizedMessage());
                }
                irasoNr.setText(null);
                sumaPI.setValue(null);
                kategorijaE.setText(null);
                papildomaInfo.setText(null);
                dataPI.setText(null);
                Laikas.setText(null);

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
                    model.add(0, String.format("Ivedete skaitmenis netinkamu formatu. klaidos klasifikacija: %s", e.getClass()));
                }

                sumaPI.setValue(0);
                kategorijaE.setText(null);
                papildomaInfo.setText(null);

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
                    model.add(0, String.format("Ivedete skaitmenis netinkamu formatu. klaidos klasifikacija: %s", e.getClass()));
                }

                sumaPI.setValue(null);
                kategorijaE.setText(null);
                papildomaInfo.setText(null);
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
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 0.05;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(panelVeiksmai, c);

        JPanel panelTitulinis = new JPanel();
        panelTitulinis.add(titulinisText);
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = c.weightx = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(panelTitulinis, c);


        JPanel panelApacia = new JPanel();
        panelApacia.add(isvalymoMygtukas);
        c.anchor = GridBagConstraints.PAGE_END;
        c.weighty = 0.00000001;
        c.gridx = 0;
        c.gridy = 4;
        frame.add(panelApacia, c);


        JPanel panelAtidaryti = new JPanel();
        JPanel panelIspejimas = new JPanel();
        JPanel panelSaugotiIFaila = new JPanel();
        JPanel panelIstrintiViska = new JPanel();
        JPanel panelIvestiPajamuIrasa = new JPanel();
        JPanel panelIvestiIslaiduIrasa = new JPanel();
        JPanel panelRedaguotiIrasa = new JPanel();
        JPanel panelRastiRedaguotaIrasa = new JPanel();
        JPanel panelIstrintiIrasa = new JPanel();


        JScrollPane scrollPane = new JScrollPane(duomenysSpausdinimui);

        veiksmaiCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String veiksmas = veiksmaiCB.getSelectedItem().toString();
                panelTitulinis.setVisible(false);
                panelRedaguotiIrasa.setVisible(false);
                panelRastiRedaguotaIrasa.setVisible(false);
                panelSaugotiIFaila.setVisible(false);
                panelIstrintiViska.setVisible(false);
                panelIvestiPajamuIrasa.setVisible(false);
                panelIvestiIslaiduIrasa.setVisible(false);
                panelIstrintiIrasa.setVisible(false);
                panelAtidaryti.setVisible(false);
                panelIspejimas.setVisible(false);
                panelApacia.add(skaiciuotiBalansaMygtukas);

                switch (veiksmas) {
                    case "Atidaryti biudžeto failą":
                        model.clear();

                        panelIspejimas.add(labelIspejimas);
                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(panelIspejimas, c);

                        panelAtidaryti.add(labelFailas);
                        panelAtidaryti.add(failoPavadinimas);
                        panelAtidaryti.add(atidarytiFailaMygtukas);

                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 2;
                        frame.add(panelAtidaryti, c);

                        c.weightx = c.weighty = 1.0;
                        c.gridx = 0;
                        c.gridy = 3;
                        frame.add(scrollPane, c);

                        scrollPane.setVisible(true);
                        panelAtidaryti.setVisible(true);
                        panelIspejimas.setVisible(true);

                        frame.validate();
                        break;
                    case "Rodyti visus įvestus įrašus":
                        spausdintiViska();

                        c.weightx = c.weighty = 1.0;
                        c.anchor = GridBagConstraints.BASELINE;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(scrollPane, c);
                        scrollPane.setVisible(true);
                        frame.validate();

                        break;
                    case "Įvesti pajamas":
                        spausdintiViska();
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
                        spausdintiViska();

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
                        spausdintiViska();
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
                        spausdintiViska();
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
                        spausdintiViska();
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
                        spausdintiViska();

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
            private void spausdintiViska() {
                model.clear();
                ArrayList<String> visiIrasai = new ArrayList<>(biudzetas.spausdintiUI());
                for (String irasas : visiIrasai) {
                    model.addElement(irasas);
                }
            }
        });

        frame.setVisible(true);

    }
}
