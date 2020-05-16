/**
 * Sets the flower for the timer session to the given flower
 * @param newFlower
 */
function setFlower(newFlower) {
    flower = newFlower;
}

/**
 * Checks if the user ahs enough coins to but the flower and if the do,
 * saves the bought flower to the backend
 * @param flowerToBuy - the flower the user wants to buy
 * @param coins - the amount of coins the user currently has
 * @param cost - the amount of coins needed to buy the flower
 */
function buyFlower(flowerToBuy, coins, cost) {
    if (parseInt(coins, 10) >= parseInt(cost, 10)) {
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/buyFlower",
            data : JSON.stringify(flowerToBuy),
            dataType : 'text',
            success : function(response) {
                if ($(response).find('.has-error').length) {
                    location.reload();
                }
                else{
                    location.reload();
                }
            }
        });
    }
}