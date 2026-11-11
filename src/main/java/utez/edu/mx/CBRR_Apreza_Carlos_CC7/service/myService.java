package utez.edu.mx.CBRR_Apreza_Carlos_CC7.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.controller.dto.CrearPedido;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.excepcion.CustomException;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.model.EstadoPedido;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.model.Pedido;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.model.Producto;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.repository.PedidoRepository;
import utez.edu.mx.CBRR_Apreza_Carlos_CC7.repository.ProductoRepository;


@Service
public class myService {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;


    @Transactional
    public Pedido crearPedido(CrearPedido payload) {
        Producto producto = productoRepository.findById(payload.getIdProducto())
                .orElseThrow(() -> new CustomException("No se encontro el producto"));


        if (!producto.isActivo()) {
            throw new CustomException("Error: El producto no esta activo");
        }


        if (producto.getStock() < payload.getCantidadSolicitada()) {
            throw new CustomException("Error: Stock insuficiente");
        }


        producto.setStock(producto.getStock() - payload.getCantidadSolicitada());
        productoRepository.save(producto);


        Pedido pedido = new Pedido();
        pedido.setProducto(producto);
        pedido.setCantidad(payload.getCantidadSolicitada());
        pedido.setPrecioUnitario(producto.getPrecio());
        pedido.setTotal(producto.getPrecio() * payload.getCantidadSolicitada());
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);


        return pedidoRepository.save(pedido);
    }


    @Transactional
    public Pedido cancelarPedido(CrearPedido payload) {
        Pedido pedido = pedidoRepository.findById(payload.getIdPedido())
                .orElseThrow(() -> new CustomException("Error: No se encontro el pedido"));


        if (pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) {
            throw new CustomException("Error: Solo se pueden cancelar pedidos en estado PENDIENTE");
        }


        Producto producto = pedido.getProducto();
        producto.setStock(producto.getStock() + pedido.getCantidad());
        productoRepository.save(producto);


        pedido.setEstadoPedido(EstadoPedido.CANCELADO);
        return pedidoRepository.save(pedido);
    }


    public Pedido procesarPedido(CrearPedido payload) {
        Pedido pedido = pedidoRepository.findById(payload.getIdPedido())
                .orElseThrow(() -> new CustomException("No se encontro el pedido"));


        if (pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) {
            throw new CustomException("Error: El pedido no esta en estado PENDIENTE");
        }


        pedido.setEstadoPedido(EstadoPedido.PROCESADO);
        return pedidoRepository.save(pedido);
    }


    @Transactional
    public Producto desactivarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new CustomException("No se encontro el producto"));


        boolean tienePedidosPendientes = pedidoRepository.existsByProductoIdAndEstadoPedido(id, EstadoPedido.PENDIENTE);
        if (tienePedidosPendientes) {
            throw new CustomException("Error: El producto tiene pedidos pendientes asociadas");
        }


        producto.setActivo(false);
        return productoRepository.save(producto);
    }
}
}
