import Users.*;
import recipes.*;

import java.io.File;
import java.util.ArrayList;


public class main {

  
  public static void main(String[] args) {
    User1 bob = new User1("Bob the Builder");

    ArrayList<aaRecipeInterface> book = new ArrayList<>();

    File[] files = new File("src/recipes").listFiles();

    assert files != null;
    for (File file : files) {
      if (file.getName().contains("Interface")) {
        continue;
      }
      String name = file.getName().replace(".java", "");
      name = "recipes." + name;

      try {
        Class<?> newClass = Class.forName(name);
        book.add((aaRecipeInterface) newClass.newInstance());
      } catch (Exception ex) {
        System.out.println("Failure");
      }

    }

    bob.setRecipeRating(book, "Beef and Rice with Onions and Peppers", 4.0);
    bob.setRecipeRating(book, "Chicken and Pesto Pasta", 2.5);
    bob.setRecipeRating(book, "Chicken and Pesto Pasta", 2.5);
    bob.setRecipeRating(book, "Chicken and Rice (Simple)", 3.0);


    for (aaRecipeInterface recipe : book) {
      System.out.println(recipe.getName() + ": " + recipe.getRating());
    }

    aaRecipeInterface recommendation = bob.getRecipe(book);

    for (String ing : bob.getIngredientPreferences().keySet()) {
      System.out.println(ing + ": " + (bob.getIngredientPreferences().get(ing))[0]);
    }
    System.out.println();
    System.out.println("Based on " + bob.getName() + "'s preferences, we recommend: " + recommendation.getName());

    ArrayList<String> inclusion = new ArrayList<>();
    inclusion.add("pesto");

    ArrayList<String> exclusion = new ArrayList<>();
    //exclusion.add("pasta");

    aaRecipeInterface rec2 = bob.getRecipe(book, inclusion, exclusion);
    if (rec2 == null) {
      System.out.println("No recipe found for those constraints");
    } else {
      System.out.println("Based on " + bob.getName() + "'s preferences and ingredient inclusion(s), we recommend: " + rec2.getName());
    }
  }
}
