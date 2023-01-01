package recipes;

public class chickenRice extends aaRecipeInterface {

  public chickenRice() {
    name = "Chicken and Rice (Simple)";
    time = 45;
    setIngredients();
    setDirections();
  }

  public void setIngredients() {
    ingredients.put("Chicken", "1 Breast");
    ingredients.put("Rice", "1 cup");
    ingredients.put("Salt", "1 tsp");
  }

  public void setDirections() {
    directions = "Heat rice in pot with water. Cook chicken until done. Combine.";
  }


}
