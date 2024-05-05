package com.carritodecompras.controllers;


import com.carritodecompras.model.ProductoStock;
import com.carritodecompras.repositories.ProductoCarritoRepository;
import com.carritodecompras.repositories.ProductoStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class CartController {

    @Autowired
    private ProductoCarritoRepository cartRepository;

    @Autowired
    private ProductoStockRepository productRepository;

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartRepository.findAll());
        return "cart";
    }}

  /*  @PostMapping("/cart/total")
    @Transactional
    public String calculateTotal(Model model) {
        double total = 0;
        Iterable<ProductoCarrito> cartItems = cartRepository.findAll();
        for (ProductoCarrito cartItem : cartItems) {
            ProductoStock product = cartItem.getProductoStock();
            int quantity = cartItem.getCantidadSelecionada();
            total += product.getPrecio() * quantity;
        }
        model.addAttribute("total", total);
        return "cart";
    }*/