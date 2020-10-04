import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { CartItem } from '../common/cart-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: CartItem[] = [];
  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor() { }

  addToCart(cartItem: CartItem) {
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem = undefined;

    if (this.cartItems.length > 0) {
      for(let tempCartItem of this.cartItems){
        if(tempCartItem.id === cartItem.id) {
          existingCartItem = tempCartItem;
          break;
        }
      }
      alreadyExistsInCart = (existingCartItem != undefined);
    }

     if (alreadyExistsInCart) {
       existingCartItem.quantity++;
     } else {
       this.cartItems.push(cartItem);
     }

     this.computeCartTotals();
  }

  computeCartTotals() {
    
  }
}
