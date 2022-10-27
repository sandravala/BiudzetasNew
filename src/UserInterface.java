import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.IllegalFormatException;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class UserInterface {

    private static JLabel nLBL(String lblName) {
        return new JLabel(lblName);
    }
    private static JPanel panelAdd( int rows, int columns, Component... components) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(rows, columns));
        for (Component component : components) {
            panel.add(component);
        }
        return panel;
    }
    private static JPanel panelAdd(Component... components) {
        JPanel panel = new JPanel();
        for (Component component : components) {
            panel.add(component);
        }
        panel.setLayout(new FlowLayout());
        return panel;
    }
    private static JPanel panelAdd(JPanel panel, Component[] components) {

        for (Component component : components) {
            panel.add(component);
        }
        return panel;
    }
    private static DefaultComboBoxModel<String> atnaujintiFailuSarasa() {

        DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
        m.addElement(" ");
        m.addElement("Kurti naują");
        m.addAll(FileReadWrite.getFailuSarasas());

        return m;

    }
    private static void panelRemoveAll(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            panel.remove(component);
        }
        panel.revalidate();
        panel.repaint();
    }


    public static void main(String[] args) {

        Biudzetas biudzetas = new Biudzetas();

        JFrame frame = new JFrame("BIUDZETAS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.8);
        int height = (int) (screenSize.height * 0.5);
        frame.setMinimumSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);

        //visokie pranešimai apie tai, kas įvykdyta
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> duomenysSpausdinimui = new JList<>(model);

        // visi biudžeto įrašai
        DefaultListModel<String> modelVisiIrasai = new DefaultListModel<>();
        JList<String> visiIrasaiSpausdinimui = new JList<>(modelVisiIrasai);
        JScrollPane visiIrasaiScrollPane = new JScrollPane(visiIrasaiSpausdinimui);

// elementai

        String[] veiksmai = {"", "Įvesti įrašą", "Redaguoti / trinti įrašą"};
        JComboBox<String> veiksmaiCB = new JComboBox<>(veiksmai);

        String[] tipas = {" bankas ", " grynieji "};
        JComboBox<String> kurGautaComboBox = new JComboBox<>(tipas);
        JComboBox<String> kurMoketaComboBox = new JComboBox<>(tipas);

        DefaultComboBoxModel<String> modelFailu = new DefaultComboBoxModel<String>();
        modelFailu.addElement(" ");
        modelFailu.addElement("Kurti naują");
        modelFailu.addAll(FileReadWrite.getFailuSarasas());
        JComboBox<String> failuSarasasCB = new JComboBox<>(modelFailu);

        // formatuotas laukelis Pajamų sumai su valiuta
        NumberFormat sumosFormatas = NumberFormat.getCurrencyInstance();
        JFormattedTextField sumaP = new JFormattedTextField(sumosFormatas);
        sumaP.setColumns(8);
        sumaP.setEditable(true);
        sumaP.setValue(0.00);

        // formatuotas laukelis Islaidu sumai su valiuta
        NumberFormat sumosFormatasI = NumberFormat.getCurrencyInstance();
        JFormattedTextField sumaI = new JFormattedTextField(sumosFormatasI);
        sumaI.setColumns(8);
        sumaI.setEditable(true);
        sumaI.setValue(0.00);

        //paprasti teksto laukeliai
        JTextField failoPavadinimas = new JTextField(10);
        failoPavadinimas.setVisible(false);
        JTextField dataPI = new JTextField(6);
        JTextField Laikas = new JTextField(3);
        JTextField irasoNr = new JTextField(3);
        irasoNr.setEditable(false);
        JTextField kategorijaP = new JTextField(20);
        JTextField kategorijaI = new JTextField(20);
        JTextField papildomaInfoP = new JTextField(20);
        JTextField papildomaInfoI = new JTextField(20);
        JTextField numeris = new JTextField(3); // paieskos laukelis

        // paspaudus paieškos mygtuką ir suradus įrašą, parodo šitą
        JTextArea redaguotiTrintiText = new JTextArea();
        redaguotiTrintiText.setEditable(false);
        redaguotiTrintiText.setLineWrap(true);
        redaguotiTrintiText.setWrapStyleWord(true);
        redaguotiTrintiText.setForeground(Color.RED);

        // intro pirmam ekrane. po to išjungiamas
        JTextPane intro = new JTextPane();
        intro.setEditable(false);
        intro.setOpaque(false);
        intro.setText(" \n\n\nSveiki! čia - biudžeto programėlė.\n\nKad pradėtumėt, pasirinkite ir atidarykite failą: \n");
        SimpleAttributeSet s = new SimpleAttributeSet();
        StyledDocument doc = intro.getStyledDocument();
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        StyleConstants.setBold(s,true);
        doc.setParagraphAttributes(0, doc.getLength(), s, false);

        // mygtukai
        JButton issaugotiPajamasMygtukas = new JButton(" ĮVESTI PAJAMAS ");
        JButton issaugotiIslaidasMygtukas = new JButton(" ĮVESTI IŠLAIDAS ");
        JButton redaguotiIrasaMygtukas = new JButton(" REDAGUOTI ĮRAŠĄ ");
        JButton saugotiRedaguotaIrasaMygtukas = new JButton(" SAUGOTI ");
        JButton rastiIrasaMygtukas = new JButton(" IEŠKOTI ");
        JButton vienoIrasoIstrynimoMygtukas = new JButton(" TRINTI ĮRAŠĄ ");
        JButton viskoIstrynimoMygtukas = new JButton(" OK ");
        viskoIstrynimoMygtukas.setForeground(Color.RED);
        JButton isvalymoMygtukas = new JButton(" IŠVALYTI ");
        JButton saugotiViskaIFailaMygtukas = new JButton(" SAUGOTI ");
        JButton atidarytiFailaMygtukas = new JButton(" ATIDARYTI ");
        JButton skaiciuotiBalansaMygtukas = new JButton(" BALANSAS ");
        JButton atidarytiFailaApacioj = new JButton(" ATIDARYTI FAILĄ ");
        JButton saugotiFailaApacioj = new JButton(" SAUGOTI FAILĄ ");
        JButton trintiViskaApacioj = new JButton(" TRINTI VISKĄ ");
        JButton kurtiNauja = new JButton( " KURTI ");
        kurtiNauja.setVisible(false);

// panelės ir išdėstymas

        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        JPanel panelVirsus = new JPanel();
        JPanel panelVidurys = new JPanel();
        JPanel panelApacia = new JPanel();
        panelVidurys.setLayout(new GridLayout(3,1));

        panelVirsus.add(nLBL("PASIRINKITE NORIMUS VEIKSMUS: "));
        panelVirsus.add(veiksmaiCB);
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 0.05;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(panelVirsus, c);
        panelVirsus.setVisible(false);

        panelVidurys.add(panelAdd(intro));
        panelVidurys.add(panelAdd(failuSarasasCB, failoPavadinimas, atidarytiFailaMygtukas, kurtiNauja));
        panelVidurys.add(panelAdd(duomenysSpausdinimui));
        c.anchor = GridBagConstraints.CENTER;
        c.weighty = c.weightx = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(panelVidurys, c);
        kurtiNauja.setVisible(false);

        c.anchor = GridBagConstraints.PAGE_END;
        c.weighty = 0.00000001;
        c.gridx = 0;
        c.gridy = 5;
        frame.add(panelAdd(panelApacia, new Component[]{atidarytiFailaApacioj, skaiciuotiBalansaMygtukas, isvalymoMygtukas, saugotiFailaApacioj, trintiViskaApacioj}), c);
        panelApacia.setVisible(false);

// mygtukų ir kt elementų metodai

        // PIRMAS LANGAS NAUDOJA TUOS PACIUS, KAIP IR ATIDARYTI FAILA


        // APACIOS MYGTUKAI

        atidarytiFailaApacioj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                failuSarasasCB.setModel(atnaujintiFailuSarasa());
                failuSarasasCB.setSelectedIndex(0);

                model.clear();
                modelVisiIrasai.clear();

                panelRemoveAll(panelVidurys);

                panelVidurys.add(panelAdd(nLBL("")));
                panelVidurys.add(panelAdd(failuSarasasCB, failoPavadinimas, atidarytiFailaMygtukas, kurtiNauja));
                panelVidurys.add(panelAdd(duomenysSpausdinimui));

                failoPavadinimas.setVisible(false);
                visiIrasaiScrollPane.setVisible(false);

                frame.validate();

            }
        });
            failuSarasasCB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.clear();

                    String pasirinktas = failuSarasasCB.getSelectedItem().toString();

                    switch (pasirinktas) {

                        case " ":
                            failoPavadinimas.setVisible(false);
                            kurtiNauja.setVisible(false);
                            atidarytiFailaMygtukas.setVisible(true);
                            frame.validate();
                            break;
                        case "Kurti naują":
                            failoPavadinimas.setVisible(true);
                            kurtiNauja.setVisible(true);
                            atidarytiFailaMygtukas.setVisible(false);
                            frame.validate();
                            break;
                        default:
                            atidarytiFailaMygtukas.setVisible(true);
                            kurtiNauja.setVisible(false);
                            failoPavadinimas.setVisible(false);
                            failoPavadinimas.setText(pasirinktas);
                            frame.validate();
                    }
                }
            });
            atidarytiFailaMygtukas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    intro.setVisible(false);
                    duomenysSpausdinimui.setForeground(Color.BLACK);

                    model.add(0, biudzetas.atidarytiFaila(failoPavadinimas.getText()));
                    model.add(1, " ");

                    panelRemoveAll(panelVidurys);
                    panelVidurys.add(panelAdd(nLBL("")));
                    panelVidurys.add(panelAdd(duomenysSpausdinimui));

                    panelVirsus.setVisible(true);
                    panelApacia.setVisible(true);

                    frame.validate();
                }
            });
            kurtiNauja.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    if(failoPavadinimas.getText().isEmpty()) {
                        model.add(0," KLAIDA!" );
                        model.add(1," Nenurodytas failo pavadinimas. Bandykite dar kartą ");
                        duomenysSpausdinimui.setForeground(Color.RED);
                        return;
                    }
                    duomenysSpausdinimui.setForeground(Color.BLACK);
                    String name = failoPavadinimas.getText();

                    model.clear();
                    intro.setVisible(false);

                    model.add(0, " ");
                    model.add(1, biudzetas.saugoti(name, false));
                    model.add(0, "Naujas failas \"" + name + "\" sukurtas ir atidarytas ");

                    FileReadWrite.setFailuSarasas(name);

                    panelRemoveAll(panelVidurys);
                    panelVidurys.add(panelAdd(nLBL("")));
                    panelVidurys.add(panelAdd(duomenysSpausdinimui));

                    panelVirsus.setVisible(true);
                    panelApacia.setVisible(true);

                    frame.validate();

                }
            });


        skaiciuotiBalansaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                duomenysSpausdinimui.setForeground(Color.BLACK);
                model.clear();
                modelVisiIrasai.clear();

                if (biudzetas.balansas().contains("apskritą")) {
                    duomenysSpausdinimui.setForeground(Color.RED);
                }
                model.add(0, biudzetas.balansas());

                panelVidurys.add(panelAdd(duomenysSpausdinimui));
                c.weightx = c.weighty = 0.1;
                c.gridx = 0;
                c.gridy = 1;
                frame.add(panelVidurys, c);


                frame.validate();
            }
        });


        isvalymoMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {

                model.clear();
                modelVisiIrasai.clear();

                panelRemoveAll(panelVidurys);

            }
        });


        saugotiFailaApacioj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                model.clear();
                modelVisiIrasai.clear();

                panelRemoveAll(panelVidurys);

                panelVidurys.add(panelAdd(nLBL("Failo pavadinimas:"), failoPavadinimas, saugotiViskaIFailaMygtukas));
                c.weightx = c.weighty = 0.5;
                c.gridx = 0;
                c.gridy = 1;
                frame.add(panelVidurys, c);

                panelVidurys.add(panelAdd(duomenysSpausdinimui));
                c.weightx = c.weighty = 0.1;
                c.gridx = 0;
                c.gridy = 2;
                frame.add(panelVidurys, c);

                c.weightx = c.weighty = 1;
                c.gridx = 0;
                c.gridy = 3;
                frame.add(visiIrasaiScrollPane, c);

                failoPavadinimas.setText(biudzetas.getFileName());
                failoPavadinimas.setVisible(true);

                frame.validate();
            }
        });
            saugotiViskaIFailaMygtukas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                model.clear();
                modelVisiIrasai.clear();
                if(failoPavadinimas.getText().isEmpty()) {
                    model.add(0," KLAIDA!" );
                    model.add(1," Nenurodytas failo pavadinimas. Bandykite dar kartą ");
                    duomenysSpausdinimui.setForeground(Color.RED);
                    return;
                }
                duomenysSpausdinimui.setForeground(Color.BLACK);
                String name = failoPavadinimas.getText();

                modelVisiIrasai.addAll(biudzetas.spausdintiUI());

                model.add(0, " ");
                model.add(1, biudzetas.saugoti(name, false));
                model.add(0, " Duomenys išsaugoti. Failą galima rasti čia:");

                FileReadWrite.setFailuSarasas(name);

                frame.validate();

            }
        });


        trintiViskaApacioj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
                modelVisiIrasai.clear();

                panelVidurys.add(panelAdd(nLBL("Ar tikrai norite ištrinti VISUS ĮVESTUS DUOMENIS? Bus ištrinta tiek iš sistemos, tiek iš failo"), viskoIstrynimoMygtukas));
                panelVidurys.add(panelAdd(duomenysSpausdinimui));

                c.weightx = c.weighty = 0.8;
                c.gridx = 0;
                c.gridy = 3;
                frame.add(visiIrasaiScrollPane, c);

                frame.validate();
            }
        });
            viskoIstrynimoMygtukas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    duomenysSpausdinimui.setForeground(Color.RED);
                    visiIrasaiScrollPane.setVisible(true);

                    model.clear();
                    model.add(0, biudzetas.istrintiVisusIrasus());


                    modelVisiIrasai.clear();
                    modelVisiIrasai.addAll(biudzetas.spausdintiUI());


                }
            });


       // PASIRINKIMAI VIRSUJE
        veiksmaiCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panelRemoveAll(panelVidurys);
                model.clear();
                modelVisiIrasai.clear();
                duomenysSpausdinimui.setForeground(Color.BLACK);

                redaguotiTrintiText.setVisible(false);
                redaguotiIrasaMygtukas.setVisible(false);
                vienoIrasoIstrynimoMygtukas.setVisible(false);

                String veiksmas = veiksmaiCB.getSelectedItem().toString();

                c.weightx = c.weighty = 1;
                c.gridx = 0;
                c.gridy = 4;
                frame.add(visiIrasaiScrollPane, c);
                visiIrasaiScrollPane.setVisible(true);

                switch (veiksmas) {

                    case "Įvesti įrašą":

                        //pajamos
                        panelVidurys.add(panelAdd(nLBL("Suma:"), sumaP, nLBL("Kategorija:"), kategorijaP, nLBL("Tipas:"), kurGautaComboBox, nLBL("Papildoma info:"), papildomaInfoP, issaugotiPajamasMygtukas));
                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 1;
                        frame.add(panelVidurys, c);

                        //islaidos
                        panelVidurys.add(panelAdd(nLBL("Suma:"), sumaI, nLBL("Kategorija:"), kategorijaI, nLBL("Tipas:"), kurMoketaComboBox, nLBL("Papildoma info:"), papildomaInfoI, issaugotiIslaidasMygtukas));
                        c.weightx = c.weighty = 0.001;
                        c.gridx = 0;
                        c.gridy = 2;
                        frame.add(panelVidurys, c);

                        panelVidurys.add(panelAdd(duomenysSpausdinimui));

                        frame.validate();
                        break;

                    case "Redaguoti / trinti įrašą":

                        //surasti
                        panelVidurys.add(panelAdd(nLBL("Įrašo numeris:"), numeris, rastiIrasaMygtukas, redaguotiIrasaMygtukas, vienoIrasoIstrynimoMygtukas));

                        panelVidurys.add(panelAdd(2, 1, redaguotiTrintiText, panelAdd(duomenysSpausdinimui)));

                        //redaguoti
                        panelVidurys.add(panelAdd(irasoNr, nLBL("Suma:"), sumaP, nLBL("Kategorija:"), kategorijaP, nLBL("Tipas:"), kurGautaComboBox, nLBL("Papildoma info:"), papildomaInfoP, nLBL("Data:"), dataPI, nLBL("Laikas:"), Laikas, saugotiRedaguotaIrasaMygtukas));


                        frame.validate();
                        break;


                }
            }
        });

        // ĮVESTI ĮRAŠĄ
            issaugotiPajamasMygtukas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    model.clear();

                    if(Double.parseDouble(sumaP.getValue().toString()) == 0.00) {
                        model.add(0, "Klaida! Išlaidos negali būti lygios nuliui");
                        duomenysSpausdinimui.setForeground(Color.RED);
                        return;
                    }
                    double suma = Double.parseDouble(sumaP.getValue().toString());
                    String kategorija = kategorijaP.getText();
                    String iBanka = kurGautaComboBox.getSelectedItem().toString().replace(" ", "");
                    String info = papildomaInfoP.getText();
                    model.add(0, biudzetas.pridetiPajamasUI(suma, kategorija, info, iBanka));

                    duomenysSpausdinimui.setForeground(Color.BLACK);

                    modelVisiIrasai.clear();
                    modelVisiIrasai.addAll(biudzetas.spausdintiUI());

                    sumaP.setValue((double) 0);
                    kategorijaP.setText(null);
                    papildomaInfoP.setText(null);

                }
            });
            issaugotiIslaidasMygtukas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    model.clear();

                    if(Double.parseDouble(sumaI.getValue().toString()) == 0.00) {
                        model.add(0, "Klaida! Išlaidos negali būti lygios nuliui");
                        duomenysSpausdinimui.setForeground(Color.RED);
                        return;
                    }
                    double suma = Double.parseDouble(sumaI.getValue().toString());
                    String kategorija = kategorijaI.getText();
                    String iBanka = kurMoketaComboBox.getSelectedItem().toString().replace(" ", "");
                    String info = papildomaInfoI.getText();
                    model.add(0, biudzetas.pridetiIslaidasUI(suma, kategorija, info, iBanka));

                    duomenysSpausdinimui.setForeground(Color.BLACK);

                    modelVisiIrasai.clear();
                    modelVisiIrasai.addAll(biudzetas.spausdintiUI());

                    sumaI.setValue((double) 0);
                    kategorijaP.setText(null);
                    papildomaInfoP.setText(null);
                }
            });

        // REDAGUOTI / TRINTI

            rastiIrasaMygtukas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                        model.clear();
                        String irasoNumeris = numeris.getText();
                        if (biudzetas.yraToksIrasas(irasoNumeris)) {
                            duomenysSpausdinimui.setForeground(Color.BLACK);
                            redaguotiTrintiText.setText("Spauskite REDAGUOTI, kad įrašo duomenys būtų perkelti į žemiau esančius laukelius korekcijoms.\nSpauskite TRINTI, kad įrašas būtų ištrintas.");
                            redaguotiTrintiText.setVisible(true);
                            redaguotiIrasaMygtukas.setVisible(true);
                            vienoIrasoIstrynimoMygtukas.setVisible(true);

                            model.add(0, "Surastas įrašas: " + biudzetas.spausdintiIrasa(irasoNumeris, biudzetas.rastiIrasa(irasoNumeris)));
                        } else {
                            model.add(0, biudzetas.spausdintiIrasa(irasoNumeris, biudzetas.rastiIrasa(irasoNumeris)));
                            duomenysSpausdinimui.setForeground(Color.RED);
                        }

                }
            });
                vienoIrasoIstrynimoMygtukas.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a) {
                        try {
                            biudzetas.rastiIrasa(numeris.getText());
                            model.clear();
                            duomenysSpausdinimui.setForeground(Color.BLACK);
                            String irasoNumeris = numeris.getText();
                            model.add(0, biudzetas.istrintiIrasaUI(biudzetas.rastiIrasa(irasoNumeris)));
                            model.add(1, " ");
                            modelVisiIrasai.clear();
                            modelVisiIrasai.addAll(biudzetas.spausdintiUI());

                        } catch (NullPointerException e) {
                            model.add(0,"Klaida! Failas nerastas");
                            duomenysSpausdinimui.setForeground(Color.RED);
                        }


                    }
                });
                redaguotiIrasaMygtukas.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a) {
                            model.clear();
                            String irasoNumeris = numeris.getText();
                            irasoNr.setText(irasoNumeris);
                            sumaP.setValue(biudzetas.rastiIrasa(irasoNumeris).getSuma());
                            kategorijaP.setText(biudzetas.rastiIrasa(irasoNumeris).getKategorija());
                            papildomaInfoP.setText(biudzetas.rastiIrasa(irasoNumeris).getPapildomaInfo());
                            dataPI.setText(biudzetas.rastiIrasa(irasoNumeris).dataFormatuota());
                            Laikas.setText(biudzetas.rastiIrasa(irasoNumeris).laikasBeDatos());
                    }
                });
                    saugotiRedaguotaIrasaMygtukas.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent a) {
                            try{
                                model.clear();
                                String irasoNumeris = numeris.getText();
                                double suma = Double.parseDouble(sumaP.getValue().toString());
                                String kategorija = kategorijaP.getText();
                                String iBanka = kurGautaComboBox.getSelectedItem().toString().replace(" ", "");
                                String info = papildomaInfoP.getText();
                                String data = dataPI.getText();
                                String laikas = Laikas.getText();

                                ArrayList<String> rezultatai = new ArrayList<>(biudzetas.redaguotiIrasa(biudzetas.rastiIrasa(irasoNumeris), suma, kategorija, info, iBanka, data, laikas));
                                duomenysSpausdinimui.setForeground(Color.BLACK);
                                for (String r : rezultatai) {
                                    model.add(0, r);
                                }
                                model.add(1," ");
                            } catch (DateTimeParseException | IllegalFormatException | NumberFormatException e) {
                                model.add(0,"Klaida! Netinkamas formatas: ");
                                model.add(1, e.getLocalizedMessage());
                                duomenysSpausdinimui.setForeground(Color.RED);
                            }
                            irasoNr.setText(null);
                            sumaP.setValue((double) 0);
                            kategorijaP.setText(null);
                            papildomaInfoP.setText(null);
                            dataPI.setText(null);
                            Laikas.setText(null);

                            modelVisiIrasai.clear();
                            modelVisiIrasai.addAll(biudzetas.spausdintiUI());

                        }
                    });

// VISKAS

        frame.setVisible(true);

    }
}