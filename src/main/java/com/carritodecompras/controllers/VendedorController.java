package com.carritodecompras.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carritodecompras.model.ProductoStock;
import com.carritodecompras.model.Usuario;
import com.carritodecompras.servicies.CategoriaServices;
import com.carritodecompras.servicies.ProductoStockServices;
import com.carritodecompras.servicies.VendedorServices;

import jakarta.validation.Valid;


/**
 * controlador de las vistas y del funcionamiento de la aplicacion para los usuarios de tipo Vendedor.
 * 
 
 *
 *@since 1.0
 * 
 */

@Controller
@RequestMapping(value = "/carritodecompras/vendedor")
public class VendedorController {
	
	/**
	 * 
     * @param authentication
	 * @param model  contiene los atributos de las vistas.
	 * @return la pagina con el listado de productoStock asociado al id del vendedor.
	 */
	@GetMapping(value ="/misproductos")
	public String listarProductos(Authentication authentication, Model model ) {
		
		Usuario usuario=vendedorServices.GetbyEmail(authentication.getName());
		
		model.addAttribute("productoStocks", usuario.getProductoStocks());
		
		
		return listaProductos;
	}
	
	/**
	 * busca un productoStock por id y luego lo agrega al modelo.
	 * 
	 * @param id identificador del productoStock  que se va a actualizar.
	 * @param model contiene los atributos de las vistas.
	 * @return la pagina de actualizacion de producto cargada con los datos del producto que será actualizado.
	 */
	@GetMapping(value ="/producto/actualizar/{id}")
	public String vistaactualizarProducto(@PathVariable(name = "id")Long id,Model model) {
		
		ProductoStock productoActualizar=productoStockServices.getById(id);
		model.addAttribute("productoActualizar", productoActualizar);
		return vistaactualizar;
	}
	
	
	/**
	 * elimina un productoStock. Busca un ProductoStock por id y luego lo elimina. 
	 * 
	 * @param id identificador del productoStock  que se va a eliminar.
	 * @param redirectAttributes tributos de redireccion de paginas. 
	 * @return redirecciona a la pagina actual.
	 */
	@GetMapping(value ="/producto/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id")Long id,RedirectAttributes redirectAttributes) {
		
		productoStockServices.eliminarProducto(id);
		redirectAttributes.addFlashAttribute("msjEliminado",msjEliminado);
		
		return eliminar;
	}
	
	@GetMapping(value ="/producto/nuevo")
	public String vistaagregarProducto(ProductoStock producto) {
		
		return vistaagregar;
	}
	
	
	
	/**
	 * 
	 * @param producto  recibe el producto que sera guatdado en la base de datos.
	 * @param bindingResult contiene algunas caracteristicas de la validacion del productoActualizar y permite saber el estado de la valdacion.
	 * @param redirectAttributes  contiene atributos de redireccion de paginas. 
	 * @return
	 */
	@PostMapping(value ="/producto/agregar")
	public String agregarProducto(@Valid ProductoStock producto,BindingResult bindingResult,Authentication auth,RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return vistaagregar;
		}
		else {
			productoStockServices.guardarProducto(producto,auth);
			redirectAttributes.addFlashAttribute("msj",msjAgregado);
			return redirectListado;
		}
		
	}
	
	/**
	 * 
	 * @param productoActualizar recibe el producto que contiene los datos que se van a actualizar de un producto en la base de datos.
	 * @param bindingResult contiene algunas caracteristicas de la validacion del productoActualizar y permite saber el estado de la valdacion.
	 * @param redirectAttributes contiene atributos de redireccion de paginas. 
	 * @return en caso de que la validacion tenga errores devolverá la vista actual mostrando los errores,
	 * de lo contrario redireccionara a la vista al listado de productosStock.
	 */
	@PostMapping(value ="/producto/actualizado")
	public String actualizarProducto(@Valid @ModelAttribute("productoActualizar") ProductoStock productoActualizar,BindingResult bindingResult,RedirectAttributes redirectAttributes) {
		
		
		if(bindingResult.hasErrors()) {
			return vistaactualizar;
		}
		else {
			
			
			productoStockServices.actualizarProducto(productoActualizar);
			redirectAttributes.addFlashAttribute("msj",msjActualizado);
			return redirectListado;
		}
		
	}

	
	
	/**
	 * Contiene algunos atributos necesarios para las vistas.
	 * 
	 * @param model contiene los atributos de las vistas.
	 */
	@ModelAttribute
	public void atributes(Model model) {
		
		model.addAttribute("categorias", categoriaServices.getCategoriasAsc());
		model.addAttribute("productos", productoStockServices.getProductos());
	}
	
	
	
	
	private static final String listaProductos="vistasvendedores/misproductos";
	private static final String vistaactualizar="vistasvendedores/actualizarproducto";
	private static final String vistaagregar="vistasvendedores/agregarproducto";
	private static final String eliminar="redirect:/carritodecompras/vendedor/misproductos";
	private static final String redirectListado="redirect:/carritodecompras/vendedor/misproductos";
	private static final String msjAgregado="Se ha agregado un Nuevo producto al listado";
	private static final String msjActualizado="Se ha actualizado un producto del listado ";
	private static final String msjEliminado="Se ha eliminado un producto de la tienda ";
	
	
	@Autowired
	private CategoriaServices categoriaServices;

	@Autowired
	private ProductoStockServices productoStockServices;
	
	@Autowired
	private VendedorServices vendedorServices;
}
