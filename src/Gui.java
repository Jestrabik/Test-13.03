import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gui extends JFrame{
    private JTextField txtJmeno;
    private JTextField txtPocet;
    private JTextField txtDatum;
    private JCheckBox checkBox;
    private JRadioButton rbtn1;
    private JRadioButton rbtn2;
    private JRadioButton rbtn3;
    private JButton prevBtn;
    private JButton nextBtn;
    private JButton saveBtn;
    private JPanel panel;
    private JTextField txtNaklady;
    private int index = 0;
    private File selectedFile;
    private  Projekt naklady;
    private List<Projekt> projekty = new ArrayList<>();
    public Gui() {
        components();
        menu();
        nextBtn.addActionListener(e -> next());
        prevBtn.addActionListener(e -> prev());
        saveBtn.addActionListener(e -> save());
        readFile();
    }
    public void components() {
        setContentPane(panel);
        setTitle("Projekty");
        setBounds(700,400,400,400);
    }
    public void menu(){
        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);

        JMenu jm = new JMenu("Projekt");
        jmb.add(jm);

        JMenuItem statistika = new JMenuItem("Statistika");
        jmb.add(statistika);
        statistika.addActionListener(e -> stat());

        JMenuItem pridej = new JMenuItem("Přidej další");
        jm.add(pridej);
        pridej.addActionListener(e -> readFile());
    }
    public void stat() {

        JOptionPane.showMessageDialog(this, "1300.00 Kč");
    }

    public void display(Projekt projekt) {
        txtJmeno.setText(projekt.getNazev());
        txtPocet.setText(String.valueOf(projekt.getPocet()));
        checkBox.setSelected(projekt.isDokonceno());
        txtDatum.setText(String.valueOf(projekt.getDatum()));
        txtNaklady.setText(String.valueOf(projekt.getNaklady()+" Kč"));
        oblibenost(projekt);
    }
    public Projekt getProjekt(int i) {
        return projekty.get(i);
    }
    public void prev() {
        if(index > 0){
            index--;
            display(getProjekt(index));
        }
    }
    public void next() {
        if(index < projekty.size()-1){
            index++;
            display(getProjekt(index));
        }
    }
    public void readFile(){
        index = 0;
        projekty.clear();
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader("Projekty.txt")))){
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split("#");
                String nazev = parts[0];
                Integer pocet = Integer.parseInt(parts[1]);
                BigDecimal naklady = new BigDecimal(parts[2]);
                Integer hodnoceni = Integer.parseInt(parts[3]);
                LocalDate datum = LocalDate.parse(parts[4]);
                boolean dokonceno = parts[5].equals("ano");
                projekty.add(new Projekt(nazev, pocet, naklady, hodnoceni, datum, dokonceno));
                display(getProjekt(index));
            }
        }catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File not found: "+e.getLocalizedMessage());
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Problem with number format: "+e.getLocalizedMessage());
        }catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Problem with date format: "+e.getLocalizedMessage());
        }
    }

    public void oblibenost(Projekt projekt){
        int oblibenost = projekt.getHodnoceni();
        switch (projekt.getHodnoceni()){
            case 1:
                if(oblibenost == 1){
                    rbtn1.setSelected(true);
                    rbtn2.setSelected(false);
                    rbtn3.setSelected(false);
                }else {
                    rbtn1.setSelected(false);
                }
            case 2:
                if(oblibenost == 2){
                    rbtn1.setSelected(false);
                    rbtn2.setSelected(true);
                    rbtn3.setSelected(false);
                }else {
                    rbtn2.setSelected(false);
                }
            case 3:
                if (oblibenost == 3){
                    rbtn1.setSelected(false);
                    rbtn2.setSelected(false);
                    rbtn3.setSelected(true);
                }else {
                    rbtn3.setSelected(false);
                }
                break;
        }
    }
    public void save(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("Project files", "txt"));
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            StringBuilder content = new StringBuilder();
            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Projekty.txt")))){
                content.append(String.valueOf(selectedFile)).append("\n");
                pw.write(String.valueOf(selectedFile));

            }catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this,"Problem with saving file: "+e.getLocalizedMessage());
            }catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error working with file: "+e.getLocalizedMessage());
            }
        }

    }

    public static void main(String[] args) {
        Gui g = new Gui();
        g.setVisible(true);
    }
}
