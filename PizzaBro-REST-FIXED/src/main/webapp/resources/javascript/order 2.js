if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
} else {
    ready()
}


// needs page to load first before calling any buttons
function ready(){
	var removeCartItemButton = document.getElementsByClassName('btn-danger')
	console.log(removeCartItemButton)

	for(var i = 0; i < removeCartItemButton.length; i++){
		var button = removeCartItemButton[i]
			button.addEventListener('click', removeCartItem)
			console.log('clicked')		
	}
	
	var quantityInputs = document.getElementsByClassName('cart-quantity-input')
		for(var i = 0; i < quantityInputs.length; i++){
			var input = quantityInputs[i]
			input.addEventListener('change', quantityChanged)
		}
		
	var addToCartButtons= document.getElementsByClassName('shop-item-button')
	for(var i = 0; i < addToCartButtons.length; i++){
		var button = addToCartButtons[i]
		button.addEventListener('click', addToCartClicked)	
	}
}




function purchaseClicked() {
    alert('Thank you for your purchase')
    var cartItems = document.getElementsByClassName('cart-items')[0]
    while (cartItems.hasChildNodes()) {
        cartItems.removeChild(cartItems.firstChild)
    }
    updateCartTotal()
}

function removeCartItem(event){
	var buttonClicked = event.target
	buttonClicked.parentElement.parentElement.remove()	
	updateCartTotal()
}

function quantityChanged(event){
	
	var input = event.target
	
	// check to see if value is valid
	
	if(isNaN(input.value)|| input.value <= 0){
		input.value = 1;
	}
	updateCartTotal();
}

function addToCartClicked(event){
	var button = event.target
	var shopItem = button.parentElement
	
	
	var title = shopItem.getElementsByClassName('shop-item-title')[0].innerText
	console.log(title)
	var price = shopItem.getElementsByClassName('shop-item-price')[0].innerText
	var removeCartItemButton = document.getElementsByClassName('button2')
	console.log(removeCartItemButton)
	
	for(var i = 0; i < removeCartItemButton.length; i++){
		var button = removeCartItemButton[i]
		
		button.addEventListener('click', function(){
			console.log('clicked')
		})
		
		addItemToCart(title, price, imageSRC)	
	}
}

function addItemToCart(title, price, imageSRC){
	// creates any kind of element you specify
	var cartRow = document.createElement('div')
	var cartItems = document.getElementsByClassName('cart-items')[0]
	var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
	
	for (var i = 0; i < cartItemNames.length; i++) {
        if (cartItemNames[i].innerText == title) {
            alert('This item is already added to the cart')
            return
        }
    }
	
	var cartRowContents = `
        <div class="cart-item cart-column">
            <img class="cart-item-image" src="${imageSrc}" width="100" height="100">
            <span class="cart-item-title">${title}</span>
        </div>
        <span class="cart-price cart-column">${price}</span>
        <div class="cart-quantity cart-column">
            <input class="cart-quantity-input" type="number" value="1">
            <button class="btn btn-danger" type="button">REMOVE</button>
        </div>`
		
	cartRow.innerHTML = cartRowContents
    cartItems.append(cartRow)
    cartRow.getElementsByClassName('btn-danger')[0].addEventListener('click', removeCartItem)
    cartRow.getElementsByClassName('cart-quantity-input')[0].addEventListener('change', quantityChanged)
	
}


function updateCartTotal(){
	var cartItemContainer = document.getElementsByClassName('cart-items')[0]
	var cartRows = cartItemContainer.getElementsByClassName('cart-row')
	
	var total = 0
	
	for(var i = 0; i < cartRows.length; i++){
		
		var cartRow = cartRow[i]
		var priceElement = cartRow.getElementsByClassName('cart-price')[0]
		var quantityElement = cartRow.getElementsByClassName('cart-quantity-input')[0]
		
		var price = parseFloat(priceElement.innerText.replace('$', ''))
		
		var quantity = quantityElement.value
		
		total = total + (price * quantity)
	}
	// round total to 2 decimal places
	total = Math.round(total * 100)/100
	document.getElementsByClassName('cart-total-price')[0].innerText = '$' + total
}
