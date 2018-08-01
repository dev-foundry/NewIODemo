package pl.devfoundry.newio.demo;

import pl.devfoundry.newio.demo.domain.Employee;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("./employees.txt");

        List<String> lines = Files.readAllLines(path);

        List<Employee> employees = new ArrayList<>();

        for(int i=0;i<lines.size();i++) {
            if(lines.get(i).equals("EmployeeData")) {
                String[] name = lines.get(i + 1).split(",");
                int age = Integer.parseInt(lines.get(i+2));
                boolean fullTime = lines.get(i + 3).equalsIgnoreCase("y");

                employees.add(new Employee(name[0],name[1],age,fullTime));
            }
        }

        Path savePath = Paths.get("./employees.json");

        Files.write(savePath,toJson(employees).getBytes(Charset.forName("UTF-8")));

    }

    private static String toJson(List<Employee> employees) {
        String empl = employees.stream()
                        .map(Employee::toJson)
                        .collect(Collectors.joining(","));
        return "{\"employees\": ["+empl+"]}";
    }
}
