package com.epam.bookstore.service;

public class ServiceUrl {
    static final String errorService = "/error";

    static final String showMainPageService = "/showMainPage";
    static final String showBookDetailsService = "/showBookDetails";
    static final String showBooksByGenreIdService = "/showBooksByGenreId";
    static final String showBooksByAuthorIdService = "/showBooksByAuthorId";

    static final String registrationService = "/registration";
    static final String loginService = "/login";
    static final String logoutService = "/logout";
    static final String editProfileService = "/editProfile";
    static final String editPasswordService = "/editPassword";

    static final String addItemToCartService = "/addItemToCart";
    static final String deleteItemService = "/deleteItem";
    static final String decreaseItemInCartService = "/decreaseItemInCart";
    static final String showCartService = "/cart";

    static final String makeOrderService = "/makeOrder";
    static final String setOrderDataService = "/setOrderData";
}
