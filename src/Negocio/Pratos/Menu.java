
package Negocio.Pratos;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static ArrayList<Menu_Item> items;
    private Scanner scanf = new Scanner(System.in);
    
    public static void PrintMenu(){
        PrintFoods();
        PrintDrinks();

    }
    public static void PrintFoods(){
        System.out.println("Comidas");
        Menu_Item item;
        for(int index = 0; index < items.size(); index++){
            item = items.get(index);
            if(item instanceof Food){
                System.out.print(item.getName());
                System.out.println("\t" + item.getPrice());
            }
        }
    }
    
    public static void PrintDrinks(){
        Menu_Item item;
        System.out.println("Bebidas");
        for(int index = 0; index < items.size(); index++){
            item = items.get(index);
            if(item instanceof Drink){
                System.out.print(item.getName());
                System.out.println("\t" + item.getPrice());
            }
        }
    }

    public boolean SearchItem(Menu_Item wanted_item){
        Menu_Item item;
        for(int index = 0; index < items.size(); index++){
            item = items.get(index);
            if(item.getName().equals(wanted_item.getName())){
                return true;
            }
        }
        return false;
    }

    public boolean AddItem(Menu_Item item){
        items.add(item);
        return true;
    }
    
    public boolean DelItem(Menu_Item item){
        items.remove(item);
        return true;
    }

    public boolean ModifyItem(Menu_Item item){
        if(SearchItem(item)){
            item.setName(scanf.nextLine());
            item.setPrice(scanf.nextFloat());
            if(item instanceof Drink)
                ((Drink) item).setProvider(scanf.nextLine());
            if(item instanceof Food)
                ((Food) item).setSteps(scanf.nextLine());
            return true;
        }
        System.out.println("Registro não encontrado");
        return false;
    }
}
