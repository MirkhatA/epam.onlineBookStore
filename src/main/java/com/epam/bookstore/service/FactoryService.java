package com.epam.bookstore.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
