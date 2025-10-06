package tests;

import java.util.HashMap;

public class MapTest {
    public static void main(String[] args) {
        HashMap<String, String> capitalCities = new HashMap<String, String>();
        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        capitalCities.put("Canada", "Ottawa");
        capitalCities.put("France", "Paris");
        capitalCities.put("Brasil", "Brasília");

        capitalCities.computeIfAbsent("Canada", (k) -> "Toronto (" + k + ")");

//        String a = capitalCities.getOrDefault("Brasil", "Not Found");
//        System.out.println(a);

//        capitalCities.values().stream().filter(c -> c.startsWith(("B"))).forEach(System.out::println);

    }
}
