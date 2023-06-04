package com.latam.jcvq.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.jcvq.tienda.Utils.JPAUtils;
import com.latam.jcvq.tienda.dao.CategoriaDAO;
import com.latam.jcvq.tienda.dao.ProductoDAO;
import com.latam.jcvq.tienda.modelo.Categoria;
import com.latam.jcvq.tienda.modelo.Producto;

public class PruebaDeParametros {

	public static void main(String[] args) {
        cargarBancoDeDatos();
        //Metodo mas recomendable
        /*EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDao =new ProductoDAO(em);
        List<Producto> resultado = productoDao.consultarPorParametro("FIFA", new BigDecimal(10000), null);
        System.out.println(resultado.get(0).getDescripcion());*/
        
        //Mas dificil de entender
        EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDao =new ProductoDAO(em);
        List<Producto> resultado = productoDao.consultarPorParametroConApiCriteria("X", null, null);
        System.out.println(resultado.get(0).getDescripcion());

    }

    private static void cargarBancoDeDatos() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videoJuegos = new Categoria("VIDEO_JUEGOS");
        Categoria electronicos = new Categoria("ELECTRONICOS");

        Producto celular = new Producto("X", "producto nuevo", new BigDecimal(10000), celulares);
        Producto videoJuego = new Producto("FIFA", "2000", new BigDecimal(10000), videoJuegos);
        Producto memoria = new Producto("memoria ram", "30 GB", new BigDecimal(10000), electronicos);

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDao = new ProductoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDao.guardar(celulares);
        categoriaDao.guardar(videoJuegos);
        categoriaDao.guardar(electronicos);

        productoDao.guardar(celular);
        productoDao.guardar(videoJuego);
        productoDao.guardar(memoria);

        em.getTransaction().commit();
        em.close();
    }
}
