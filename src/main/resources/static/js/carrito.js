//Variables
const carrito = document.querySelector('#carrito');
const contenedorCarrito = document.querySelector('#lista-carrito tbody');
const vaciarCarritoBtn = document.querySelector('#vaciar-carrito');
const listaProductos = document.querySelector('#lista-productos');
let articulosCarrito = [];

cargarEventListeners();

function cargarEventListeners(){
    listaProductos.addEventListener('click',agregarProducto);

    carrito.addEventListener('click',eliminarProducto);


    vaciarCarritoBtn.addEventListener('click', () => {
        articulosCarrito = []; 
        limpiarHTML(); 
    })
}

//Funciones
function agregarProducto(e){
    e.preventDefault();

    if(e.target.classList.contains('agregar-al-carro')){
        const ProductoSeleccionado = e.target.parentElement.parentElement;
        leerDatosProducto(ProductoSeleccionado);
    }
    
}

function eliminarProducto(e){
    if(e.target.classList.contains('borrar-producto')){
        const productoId = e.target.getAttribute('data-id');

        articulosCarrito = articulosCarrito.filter( producto => producto.id !== productoId);

        carritoHTML();
    }
}

function leerDatosProducto(producto){
    // console.log(curso);

    //Crear un objeto con el contenido del curso actual
    const infoProducto = {
        imagen: producto.querySelector('img').src,
        nombre: producto.querySelector('h3').textContent,
        precio: producto.querySelector('.precio').textContent,
        id: producto.querySelector('a').getAttribute('data-id'),
        cantidad: 1
    }

    //Revisar si un elemento ya existe en el carrito
    const existe = articulosCarrito.some( producto => producto.id === infoProducto.id);
    if(existe){
        //Actualizar la cantidad
        const productos = articulosCarrito.map( producto => {
            if(producto.id === infoProducto.id){
                producto.cantidad++;
                return producto; //retorna el objeto actualizado
            }else{
                return producto; //retorna los objetos que no son duplicados
            }
        });
        articulosCarrito = [...productos];
    }else{
        //Agregar elementos al arreglo del carrito
        articulosCarrito = [...articulosCarrito, infoProducto];
    }

    console.log(articulosCarrito);

    carritoHTML();

}

//Mostrar el carrito de compras en el HTML
function carritoHTML(){

    //Limpiar el HTML
    limpiarHTML();

    //Recorrer el carrito y generar el HTML
    articulosCarrito.forEach( producto => {
        const { imagen, nombre, precio, cantidad, id } = producto;
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>
                <img src="${imagen}">
            </td>
            <td style="	min-width: 150px;text-align:center;vertical-align: middle;">${nombre}</td>
            <td style="	min-width: 150px;text-align:center;vertical-align: middle;">${precio}</td>
            <td style="	min-width: 150px;text-align:center;vertical-align: middle;">${cantidad}</td>
            <td style="	min-width: 150px;text-align:center;vertical-align: middle;">
                <a href="#" class="borrar-producto" data-id="${id}"> X </a>
            </td>
        
        `;

        //Agregar el HTML del carrito en el tbody
        contenedorCarrito.appendChild(row);
    })

}

//Eliminar los cursos del tbody
function limpiarHTML(){
    //contenedorCarrito.innerHTML = '';

    while(contenedorCarrito.firstChild){
        contenedorCarrito.removeChild(contenedorCarrito.firstChild);
    }

}