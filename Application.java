import nl.saxion.app.CsvReader;
import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.ArrayList;

public class Application implements Runnable {
    ArrayList<education> eductations = new ArrayList<education>();

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 1024, 768);
    }

    public void run() {
        csvread();
        boolean menu = true;
        while (menu) {


                SaxionApp.printLine("Welcome to the Enschede, Deventer and Apeldoorn school system.");
                SaxionApp.printLine("--------------------------------------------------------------");
                SaxionApp.printLine("1. Print all school names.");
                SaxionApp.printLine("2. Get total number of students per year.");
                SaxionApp.printLine("3. Draw student overview chart.");
                SaxionApp.printLine("4. Exit");
                char select = SaxionApp.readChar();

                if (select == '1') {
                    printall();
                } else if (select == '2') {
                    totalstudents();
                } else if (select == '3') {
                    studentoverview();
                } else if (select == '4') {
                    menu = false;
                }
                SaxionApp.pause();
                SaxionApp.clear();

        }
    }

    public void csvread() {
        CsvReader reader = new CsvReader("C:\\programeer opdrachten\\StudentInformationSystem(1)\\StudentInformationSystem\\students.csv");
        reader.setSeparator(';');
        reader.skipRow();
        while (reader.loadRow()) {
            education schoolinfo = new education();

            schoolinfo.name = reader.getString(0);
            schoolinfo.city = reader.getString(1);
            schoolinfo.type = reader.getString(2);
            schoolinfo.direction = reader.getString(3);
            schoolinfo.year4m = reader.getInt(4);
            schoolinfo.year4f = reader.getInt(5);
            schoolinfo.year5m = reader.getInt(6);
            schoolinfo.year5f = reader.getInt(7);
            schoolinfo.year6m = reader.getInt(8);
            schoolinfo.year6f = reader.getInt(9);

            eductations.add(schoolinfo);
        }
    }
    public void printall(){
        ArrayList<String> namen = new ArrayList<String>();
        for (education data: eductations){
            if (!namen.contains(data.name))
            namen.add(data.name);
        }
        for (int i = 0; i < namen.size(); i++) {
            SaxionApp.printLine("- "+namen.get(i));

        }
    }
    public void totalstudents(){
        SaxionApp.printLine("what year? 4/5/6");
        int year = SaxionApp.readInt();

        int enschede = 0;
        int deventer = 0;
        int apeldoorn = 0;

        for (education data: eductations){

            if (year == 4 ){
                if (data.city.equalsIgnoreCase("enschede")){
                    enschede = enschede+ data.year4f + data.year4m;
                } else if (data.city.equalsIgnoreCase("deventer")){
                    deventer = deventer+ data.year4m+ data.year4f;
                } else if (data.city.equalsIgnoreCase("apeldoorn")){
                    apeldoorn = apeldoorn + data.year4f + data.year4m;
                }

            } else if (year == 5) {
                if (data.city.equalsIgnoreCase("enschede")){
                    enschede = enschede+ data.year5m + data.year5f;
                } else if (data.city.equalsIgnoreCase("deventer")){
                    deventer = deventer+ data.year5f+ data.year5m;
                } else if (data.city.equalsIgnoreCase("apeldoorn")){
                    apeldoorn = apeldoorn + data.year5f + data.year5m;
                }

            } else if (year == 6) {
                if (data.city.equalsIgnoreCase("enschede")){
                    enschede = enschede+ data.year6m + data.year6f;
                } else if (data.city.equalsIgnoreCase("deventer")){
                    deventer = deventer+ data.year6f+ data.year6m;
                } else if (data.city.equalsIgnoreCase("apeldoorn")){
                    apeldoorn = apeldoorn + data.year6f + data.year6m;
                }
            }
        }
        SaxionApp.printLine("in enschede there are" + " "+ enschede);
        SaxionApp.printLine("in deventer there are" + " "+ deventer);
        SaxionApp.printLine("in apeldoorn there are" + " "+ apeldoorn);
    }

    public void studentoverview(){
        SaxionApp.printLine("what city?");
        String city = SaxionApp.readString();
        SaxionApp.printLine("what type? (havo/VWO)");
        String type = SaxionApp.readString();
        int y = 50;

        SaxionApp.clear();
        for (education data: eductations){

            if (data.city.equalsIgnoreCase(city)&& data.type.equalsIgnoreCase(type)){
                SaxionApp.drawText(data.name+", "+ data.direction,0,y,16);
                int counter = 0;
                int counter2 = 0;
                int middle = 600;

                while (data.year4f> counter2) {

                    while (data.year4m > counter) {
                        SaxionApp.turnBorderOff();
                        SaxionApp.drawCircle(middle, y + 2, 4);
                        counter++;
                        middle = middle + 8;
                    }
                    SaxionApp.setFill(Color.magenta);
                    SaxionApp.drawCircle(middle,y+2,4);
                    middle = middle+8;
                    counter2++;
                }
                y =y+16;

            }
        }
    }
}