/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import Models.DTO.Cart;
import javax.servlet.http.Cookie;   
import javax.servlet.http.HttpSession;
/**
 *
 * @author Admin
 */
public class CartUtil {
    public HashMap<String, Cart> getCartFromSession (HttpSession session){
        return (HashMap<String, Cart>)session.getAttribute("Cart");
    }
    public void saveCartToSession(HttpSession session, HashMap<String, Cart> cart){
        session.setAttribute("Cart", cart);
    }
    public HashMap<String, Cart> getCartFromCookie(Cookie cookieCart, String accountId){
        HashMap<String, Cart> cart = null;
        if (cookieCart != null && cookieCart.getName().equals(accountId)) {
            String[] arrItemDetail;
            String itemId, itemName;
            int quantity;
            float unitPrice;
            Cart item;
            Base64.Decoder base64Decoder = Base64.getDecoder();
            cart = new HashMap();
            String encodedString = new String(base64Decoder.decode(cookieCart.getValue().getBytes()));
            String[] itemsList = encodedString.split("\\|");
            for(String strItem : itemsList){
                arrItemDetail = strItem.split(",");
                itemId = arrItemDetail[0].trim();
                itemName = arrItemDetail[1].trim();
                quantity = Integer.parseInt(arrItemDetail[2].trim());
                unitPrice = Float.parseFloat(arrItemDetail[3].trim());
                item = new Cart(itemId, itemName, quantity, unitPrice);
                cart.put(itemId, item);
            }
        }
        return cart;
    }
    
    public Cookie getCookieByName(HttpServletRequest request, String cookieName){
        Cookie[] arrCookies = request.getCookies();
        if(arrCookies != null){
            for(Cookie cookie : arrCookies){
                if(cookie.getName().equals(cookieName)){
                    cookie.setMaxAge(1800);
                    return cookie;
                }
            }
        }
        return null;
    }
    
    public void saveCartToCookie(HttpServletRequest request, HttpServletResponse response, String strItemsInCart, String accountId){
        String cookieName = accountId;
        Cookie cookieCart = getCookieByName(request, cookieName);
        if(cookieCart != null){
            cookieCart.setValue(strItemsInCart);
        }else{
            cookieCart = new Cookie(cookieName, strItemsInCart);
        }
        cookieCart.setMaxAge(1800);
        response.addCookie(cookieCart);
    }

    public String convertCartToString(List<Cart> itemsList){
        StringBuilder strItemsInCart = new StringBuilder();
        for(Cart item : itemsList){
            strItemsInCart.append(item + "|");
        }
        Base64.Encoder base64Encoder = Base64.getEncoder();
        String encodedString = base64Encoder.encodeToString(strItemsInCart.toString().getBytes());
        return encodedString;
    }
}
