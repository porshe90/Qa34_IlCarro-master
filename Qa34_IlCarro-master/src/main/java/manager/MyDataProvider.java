package manager;

import models.Car;
import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {
    @DataProvider
    public Iterator<Object []> dataLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"noa@gmail.com","Nnoa12345$"});
        list.add(new Object[]{"sonya@gmail.com","Ss12345$"});
        list.add(new Object[]{"noa@gmail.com","Nnoa12345$"});
        list.add(new Object[]{"sonya@gmail.com","Ss12345$"});


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]>dataRegistration(){
        List<Object[]> list= new ArrayList<>();
        list.add(new Object[]{new User().setName("Dorati").setLastName("Doe").setEmail("dor@mail.com").setPassword("Dd12345$")});
        list.add(new Object[]{new User().setName("Dorati").setLastName("Doe").setEmail("dor1@mail.com").setPassword("Dd12345$")});
        list.add(new Object[]{new User().setName("Dorati").setLastName("Doe").setEmail("dor2@mail.com").setPassword("Dd12345$")});

        return list.iterator();
    }



    @DataProvider
    public Iterator<Object[]>loginCSV() throws IOException {
        List<Object[]> list= new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/login.csv")));
        String line = reader.readLine();// "noa@gmail.com,Nnoa12345$"

        while (line!=null){
            String[] split = line.split(";");  //["noa@gmail.com"]["Nnoa12345$"]
            list.add(new Object[]{new User().setEmail(split[0]).setPassword(split[1])});
            line=reader.readLine();
        }

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]>validDataCar() throws IOException {
        List<Object[]> list= new ArrayList<>();
        BufferedReader reader =new BufferedReader(new FileReader(new File("src/test/resources/car.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[]split = line.split(";");
            list.add(new Object[]{Car.builder()
                    .address(split[0])
                    .make(split[1])
                    .model(split[2])
                    .year(split[3])
                    .engine(split[4])
                    .fuel(split[5])
                    .gear(split[6])
                    .wD(split[7])
                    .doors(split[8])
                    .seats(split[9])
                    .clasS(split[10])
                    .fuelConsumption(split[11])
                    .carRegNumber(split[12])
                    .price(split[13])
                    .distanceIncluded(split[14])
                    .features(split[15])
                    .about(split[16])
                    .build()});
            line= reader.readLine();
        }



        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]>dataBase(){
        List<Object[]> list= new ArrayList<>();

        return list.iterator();
    }
}
