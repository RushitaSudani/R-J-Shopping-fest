package org.technous.validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.technous.validation.exception.OrderException;
import org.technous.validation.model.Address;
import org.technous.validation.model.Cart;
import org.technous.validation.model.Order;
import org.technous.validation.model.User;
import org.technous.validation.repository.AddressRepository;
import org.technous.validation.repository.OrderItemRepository;
import org.technous.validation.repository.OrderRepository;
import org.technous.validation.repository.UserRepository;
import org.technous.validation.service.CartService;
import org.technous.validation.service.impl.OrderServiceIMPL;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceIMPLTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private CartService cartService;

    @InjectMocks
    private OrderServiceIMPL orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder_Success() {
        User user = new User();
        Address shippingAddress = new Address();
        Cart cart = new Cart();
        // Mocking behavior of dependencies
        when(cartService.findUserCart(anyLong())).thenReturn(cart);
        when(addressRepository.save(any(Address.class))).thenReturn(shippingAddress);
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        Order createdOrder = orderService.createOrder(user, shippingAddress);

        assertNotNull(createdOrder);
        assertEquals(user, createdOrder.getUser());
        assertEquals(shippingAddress, createdOrder.getShippingAddress());
        // Verify method invocations
        verify(addressRepository, times(1)).save(any(Address.class));
        verify(userRepository, times(1)).save(user);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderItemRepository, times(cart.getCartItems().size())).save(any());
    }

    @Test
    public void testFindOrderById_Exists() throws OrderException {
        Long orderId = 1L;
        Order order = new Order();
        // Mocking behavior of dependencies
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order foundOrder = orderService.findOrderById(orderId);

        assertNotNull(foundOrder);
        assertEquals(order, foundOrder);
    }

    @Test
    public void testFindOrderById_NotExists() {
        Long orderId = 1L;
        // Mocking behavior of dependencies
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderException.class, () -> orderService.findOrderById(orderId));
    }

    // Add similar tests for other methods like placeOrder, confirmedOrder, etc.
}
