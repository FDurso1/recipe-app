package recipes;

import java.util.Arrays;
import java.util.HashMap;

public abstract class aaRecipeInterface {

  HashMap<String, String> ingredients = new HashMap<>();
  HashMap<String, String> lowerIngredients = new HashMap<>();
  String[] nonVegetarian = new String[]{"beef", "chicken", "lamb", "pork", "hotdog", "burger", "bacon", "turkey"};
  String[] nonVegan = new String[]{"egg", "eggs", "milk", "yogurt", "cheese", "cream"}; //etc
  String name;
  String directions;
  int time;
  double[] avgRating = new double[2];

  abstract void setIngredients();
  abstract void setDirections();

  public HashMap<String, String> getIngredients() {return ingredients;}
  public HashMap<String, String> getLowerIngredients() { if (lowerIngredients.isEmpty()) { setLowerIngredients();} return lowerIngredients;}
  public String getName() { return name;}
  public String getDirections() { return directions;}
  public int getTime(){ return time;};

  public double getRating() {return avgRating[0];}

  public void updateRating(double rating) {
    double product = avgRating[0] * avgRating[1];
    product += rating;
    product /= ++avgRating[1];
    avgRating[0] = product;
  }

  public void setLowerIngredients() {
    for (String str : ingredients.keySet()) {
      lowerIngredients.put(str.toLowerCase(), ingredients.get(str));
    }
  }

  public boolean vegetarian() {
    for (String ingredient : ingredients.keySet()) {
      boolean contains = Arrays.asList(nonVegetarian).contains(ingredient);
      if (contains) { return false;}
    }
    return true;
  }

  public boolean vegan() {
    if (!vegetarian()) {
      return false;
    }
    for (String ingredient : ingredients.keySet()) {
      boolean contains = Arrays.asList(nonVegan).contains(ingredient);
      if (contains) { return false;}
    }
    return true;
  }

}
