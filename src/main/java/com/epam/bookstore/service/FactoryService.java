package com.epam.bookstore.service;

import java.util.HashMap;
import java.util.Map;

import static com.epam.bookstore.service.ServiceUrl.*;

public class FactoryService {

    private static final FactoryService factoryService = new FactoryService();
    private static final Map<String, Service> serviceMap = new HashMap<>();

    static {
        serviceMap.put(errorService, new ErrorService());
        serviceMap.put(showMainPageService, new ShowMainPageService());
        serviceMap.put(showBookDetailsService, new ShowBookDetailsService());
        serviceMap.put(showBooksByGenreIdService, new ShowBooksByGenreService());
        serviceMap.put(showBooksByAuthorIdService, new ShowBooksByAuthorService());
        serviceMap.put(registrationService, new RegistrationService());
        serviceMap.put(loginService, new LoginService());
        serviceMap.put(logoutService, new LogoutService());
        serviceMap.put(editProfileService, new EditProfileService());
        serviceMap.put(editPasswordService, new EditPasswordService());
        serviceMap.put(addItemToCartService, new AddItemToCartService());
        serviceMap.put(showCartService, new ShowCartService());
        serviceMap.put(decreaseItemInCartService, new DecreaseItemInCartService());
        serviceMap.put(deleteItemService, new DeleteItemFromCartService());
        serviceMap.put(makeOrderService, new MakeOrderService());
        serviceMap.put(setOrderDataService, new SetOrderDataService());
        serviceMap.put(submitOrderService, new SubmitOrderService());
        serviceMap.put(showAllOrdersService, new ShowAllOrdersService());
        serviceMap.put(changeLanguageService, new ChangeLanguageService());
        serviceMap.put(adminAllBooksService, new AdminAllBooksService());
        serviceMap.put(adminAllUsersService, new AdminAllUsersService());
        serviceMap.put(deleteUserService, new AdminDeleteUserService());
        serviceMap.put(allOrdersService, new AllOrdersService());
        serviceMap.put(editOrderService, new EditOrderService());
        serviceMap.put(blockUserService, new AdminBlockUserService());
        serviceMap.put(unblockUserService, new AdminUnblockUserService());
        serviceMap.put(addBookService, new AddBookService());
        serviceMap.put(editBookService, new EditBookService());
        serviceMap.put(showEditBook, new ShowBookEditDetailsService());
    }

    public static FactoryService getInstance() { return factoryService; }

    public Service getService(String req) {
        Service service = serviceMap.get(errorService);

        for (Map.Entry<String, Service> serviceEntry : serviceMap.entrySet()) {
            if (req.equalsIgnoreCase(serviceEntry.getKey())) {
                service = serviceMap.get(serviceEntry.getKey());
            }
        }

        return service;
    }
}
