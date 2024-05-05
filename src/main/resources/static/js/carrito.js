function calcular() {
    var totalPrice = 0;
    /* Iterar sobre cada fila de la tabla para calcular el subtotal */
    /* Asumiendo que el precio está en la tercera columna y la cantidad seleccionada en la cuarta columna */
    var tableRows = document.querySelectorAll('tbody tr');
    let botones = document.querySelector(".btns")
    let col = document.querySelector("#operaciones")
    tableRows.forEach(function (row) {
        var price = parseFloat(row.cells[1].innerText); // Obtener el precio como número flotante
        var quantity = parseInt(row.cells[2].innerText); // Obtener la cantidad como entero
        var subtotal = price * quantity; // Calcular el subtotal
        totalPrice += subtotal; // Sumar al total
        
    });
    
    function ocultarElementosPorClase(botones) {
    var elementos = document.getElementsByClassName(botones);
    for (var i = 0; i < elementos.length; i++) {
    elementos[i].style.display = 'none';
}
}
    function mostrarCliente(inputId, etiquetaId) {
    var input = document.getElementById(inputId);
    var etiqueta = document.getElementById(etiquetaId);
    
    if (input && etiqueta) {
        var texto = input.value;
        etiqueta.innerText = texto;
    } else {
        console.error('El input o la etiqueta no fueron encontrados.');
    }
   

}


    ocultarElementosPorClase('btns')
    col.style.display="none";
    var formattedTotal = formatNumber(totalPrice);
    document.getElementById('totalSection').innerHTML = '<h2>Total: $<span class="total-carrito">' + formattedTotal + '</span></h2>';
    
  


    mostrarCliente("cliente", "clienteSeccion");
  
    function agregarClase(){
      
        const factura = document.getElementById('factura');
      const container = document.getElementById('container');
        const card = document.getElementById('card-principal');
        
        factura.classList.add('factura_final');
       
        container.classList.replace('container', 'factura_final');
        card.classList.replace('card', 'factura_final');
        
        alert('Factura emitida!.');
    };


    agregarClase();


   
}


    // Itera sobre cada elemento y agrega la clase 'nueva-clase'
    

/* Función para formatear un número y separar las cifras cada tres dígitos con un punto */
function formatNumber(number) {
    return number.toFixed(2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}