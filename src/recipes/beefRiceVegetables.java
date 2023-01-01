package recipes;

public class beefRiceVegetables extends aaRecipeInterface {

  public beefRiceVegetables() {
    name = "Beef and Rice with Onions and Peppers";
    time = 60;
    setIngredients();
    setDirections();
  }

  @Override
  void setIngredients() {
    ingredients.put("Ground beef", "3 oz");
    ingredients.put("Rice", "0.5 cup");
    ingredients.put("Onion", "0.5 onion");
    ingredients.put("Bell Pepper", "0.5 pepper");
  }

  @Override
  void setDirections() {
      directions = "Cook the beef. Cook the rice. Dice and cook the onions and peppers. Combine and serve with hot " +
              "sauce / yum-yum sauce";
  }
}
