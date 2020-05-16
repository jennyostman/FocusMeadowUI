function setFlower(newFlower) {
    flower = newFlower;
}

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