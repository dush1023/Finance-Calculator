package sample;

import exceptions.Commonexception;
import exceptions.Finexception;
import exceptions.Techexception;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import Stock.NSDQFinStock;
import Stock.NSDQStock;
import Stock.NSDQTechStock;
import Stock.NSDQcommonStock;
import exceptions.Notinmarket;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.URI;
import java.util.Scanner;

import java.io.IOException;
import java.net.URL;


public class Controller {
    public Button btnName;
    public TextField inputvalue2;
    public TextField inputcategory;
    public TextField inputvalue1;
    public TextField inputaction;
    public Label report;
    public TextField inputname;
    public Label inf1;
    public Label inf2;
    public Label inf3;


    Scanner scanner = new Scanner(System.in);
    public void Calculate(ActionEvent actionEvent) throws Finexception, Commonexception, Techexception, IOException {
        this.Music();
        report.textProperty().set("Daily Report");
        String category = inputcategory.textProperty().get();
        NSDQStock stockentry1 = new NSDQFinStock();
        NSDQStock stockentry2 = new NSDQTechStock();
        NSDQStock stockentry3 = new NSDQcommonStock();

        if (category.equals("Fin")) {
            setstock(category, stockentry1);
        }

        if (category.equals("Tech")) {
            setstock(category, stockentry2);
        }

        if ((!category.equals("Fin")) && (!category.equals("Tech"))) {
            setstock(category, stockentry3);
        }
    }

    public void setstock(String category, NSDQStock stockentry) {
        String action = inputaction.textProperty().get();
        String companyname = inputname.textProperty().get();
        stockentry.setStock(companyname);
        stockentry.setCategories(category);
        try {
            clarrify(stockentry, action);
        }
        catch (Finexception finexception) {
            inf1.textProperty().set("!");
        } catch (Notinmarket exception) {
            inf1.textProperty().set("Try again");
        } finally {
            inf3.textProperty().set("Welcome Stock World!");
        }
    }

    public void clarrify(NSDQStock stockentry1, String action) throws Commonexception, Techexception, Finexception {
        if (action.equals("Publisher")) {
            inf1.textProperty().set("The world famous publishers are JPMorgan, Goldman Sachs, Bank of America Merrill Lynch");
            inf2.textProperty().set("You can visit their website for further information");
        }

        if (action.equals("EV")) {
            inf1.textProperty().set("After calculation!");
            inf2.textProperty().set(EV(stockentry1));
        }

        if(action.equals("cal")){
            changecalculation(stockentry1);
        }

        else if (!action.equals("cal") && !action.equals("Publisher")&& !action.equals("EV")) {
            inf1.textProperty().set("Sorry, You have to input the right request!");
        }
    }


    public void changecalculation(NSDQStock s1) throws Commonexception, Techexception, Finexception {
        inf1.textProperty().set(s1.stockcategory());
        double result = calculation(s1);
        s1.setResult(result);
        if (result > 0) {
            inf2.textProperty().set(s1.stockname + " today's price:" + s1.prices.get(s1.prices.size()-1) + " increased by " + result + "%");
        } else if (result < 0) {
            inf2.textProperty().set(s1.stockname + " today's price:" + s1.prices.get(s1.prices.size()-1) + " decreased by " + result+ "%");
        } else if (result == 0) {
            inf2.textProperty().set(s1.stockname + " today's price:" + s1.prices.get(s1.prices.size()-1) + " stay still ");
        }
    }

    public String EV(NSDQStock s){
        String value1 = inputvalue1.textProperty().get();
        s.addPrice(Double.parseDouble(value1));
        String value2 = inputvalue1.textProperty().get();
        s.setShares(Integer.parseInt(value2));
        return (s.stockname+ " 's EV is " + s.EV());
    }

    private double calculation(NSDQStock n1){
        String value1 = inputvalue1.textProperty().get();
        String value2 = inputvalue2.textProperty().get();
        n1.addPrice(Double.parseDouble(value1));
        n1.addPrice(Double.parseDouble(value2));
        double edge = 100 * ((Double.parseDouble(value2)/ Double.parseDouble(value1)) - 1);
        return edge;
    }

    public void Music(){
        File f;
        URI uri;
        URL url;
        try {
            f = new File("carlin_children.wav");
            uri = f.toURI();
            url = uri.toURL();
            AudioClip aau;
            aau = Applet.newAudioClip(url);
            aau.loop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}