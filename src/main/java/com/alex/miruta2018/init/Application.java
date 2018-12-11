/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alex.miruta2018.init;

import com.alex.miruta2018.model.Empresa;
import com.alex.miruta2018.model.PuntoInteres;
import com.alex.miruta2018.model.TipoInteres;
import com.alex.miruta2018.model.UnidadLinea;
import com.alex.miruta2018.model.Usuario;
import com.alex.miruta2018.repo.crud.RepositorioEmpresaCrud;
import com.alex.miruta2018.repo.crud.RepositorioPtoInteresCrud;
import com.alex.miruta2018.repo.crud.RepositorioUnidadLineaCrud;
import com.alex.miruta2018.repo.crud.RepositorioUsuarioCrud;
import com.alex.miruta2018.services.ConsumeServiceWeb;
import com.alex.miruta2018.services.EmpresaService;
import com.alex.miruta2018.services.TipoInteresService;
import com.alex.miruta2018.services.UsuarioService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author alextc6
 */
@SpringBootApplication
@EntityScan("com.alex.miruta2018.model")
// le especificamos a partir de donde buscar los controladores, servicios y componentes a instanciar
@ComponentScan(basePackages = {"com.alex.*"})
// especificamos donde se encuentran los repositorios que vamos a usar para trabajar con las entidades creadas
@EnableJpaRepositories(basePackages = {"com.alex.miruta2018.repo.*"})
// deshabilita la configuracion automatica para las rutas erroneas y con acceso restringido
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class Application {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
//    ###############################################################################
//    ################################### Usuario ################################### 
    @Bean
    public CommandLineRunner cargaInicialUsuario(UsuarioService service) {
        return (args) -> {
            
//            Usuario u;
//            // guardamos algunos usuarios
//            u = new Usuario(1L, "admin", "123", "mailalgo@gmail.com");
//            service.create(u);
//            
//            u = new Usuario(2L, "alex", "111", "mail");
//            service.create(u);
//
//            // buscamos todas las paradas a modo de prueba
//            log.info("**** Usuarios encontrados con findAll(): ****");
//            for (Usuario usuario : service.getAll()) {
//                log.info(usuario.toString());
//            }
//            log.info("*********************************************");
        };
    }
    
//    ####################################################################################
//    ##################################### Empresa ######################################
    @Bean
    public CommandLineRunner cargaInicialEmpresas(EmpresaService service) {
        return (args) -> {
            
//            Empresa emp;
//            
//            emp = new Empresa(1L, "Benitez", "23-23456789-2", "benitez@gmail.com", "4474536");
//            service.create(emp);
//            log.info("Se creo la empresa BENITEZ");
//            
//            emp = new Empresa(2L, "Ceferino", "25-23251789-0", "ceferino@gmail.com", "4471256");
//            service.create(emp);
//            log.info("Se creo la empresa CEFERINO");
//
//            // buscamos todas las paradas a modo de prueba
//            log.info("**** Empresas guardadas: ****");
//            for (Empresa empresa : service.getAll()) {
//                log.info(empresa.toString());
//            }
//            log.info("*********************************************");
        };
    }

//    ####################################################################################
//    ################################## TipoInteres #####################################
    @Bean
    public CommandLineRunner cargaInicialTipoInteres(TipoInteresService service) {
        return (args) -> {
            
//            TipoInteres tipo;
//            
//            tipo = new TipoInteres("carga");
//            service.create(tipo);
//            
//            tipo = new TipoInteres("ParadaTaxi/Remis");
//            service.create(tipo);
//
//            // buscamos todas las paradas a modo de prueba
//            log.info("**** Tipos guardados: ****");
//            for (TipoInteres tipointeres : service.getAll()) {
//                log.info(tipointeres.toString());
//            }
//            log.info("*********************************************");
        };
    }
    
        
//    ####################################################################################
//    ################################### PuntoInteres ###################################
    @Bean
    public CommandLineRunner cargaInicialPtoInteres(RepositorioPtoInteresCrud repoPtoInteres, RepositorioUsuarioCrud repoUsuario) {
        return (args) -> {
            
//            GeometryFactory fabricaGeom = new GeometryFactory();
//            
//            PuntoInteres ptoInt;
//            // guardamos algunos puntos
//            ptoInt = new PuntoInteres("ranchoLider", fabricaGeom.createPoint(new Coordinate(-65.0435781612698, -42.76403644138906)), repoUsuario.findByNombre("alex").get());
//            repoPtoInteres.save(ptoInt);
//            ptoInt = new PuntoInteres("unRanchito", fabricaGeom.createPoint(new Coordinate(-65.04562200453915, -42.75792890613414)), repoUsuario.findByNombre("alex").get());
//            repoPtoInteres.save(ptoInt);

//
//            // buscamos todas las paradas a modo de prueba
//            log.info("**** Punto de Interes encontrados con findAll(): ****");
//            for (PuntoInteres punto : repoPtoInteres.findAll()) {
//                log.info(punto.toString());
//            }
//            log.info("*********************************************");
        };
    }
    
//    ####################################################################################
//    ################################ UnidadLinea #######################################
    
    @Bean
    public CommandLineRunner cargaInicialUnidadLinea(RepositorioUnidadLineaCrud repoUniLinea, RepositorioEmpresaCrud repoEmpresa) {
        return (args) -> {
            
//            UnidadLinea unidad;
//            unidad = new UnidadLinea("Linea1", LocalTime.of(8, 0), LocalTime.of(22, 0), 20, "Por seccion", repoEmpresa.findByNombre("Benitez").get());
//            repoUniLinea.save(unidad);
//            
//            unidad = new UnidadLinea("Linea2", LocalTime.of(7, 30), LocalTime.of(22, 15), 15, "Precio unico", repoEmpresa.findByNombre("Ceferino").get());
//            repoUniLinea.save(unidad);
//            
//            unidad = new UnidadLinea("Linea3", LocalTime.of(8, 0), LocalTime.of(21, 0), 20, "Por seccion", repoEmpresa.findByNombre("Benitez").get());
//            repoUniLinea.save(unidad);

            // buscamos todas las paradas a modo de prueba
//            log.info("**** Unidades de transporte guardadas: ****");
//            for (UnidadLinea unidadT : repoUniLinea.findAll()) {
//                log.info(unidadT.toString());
//            }
//            log.info("*********************************************");
        };
    }
    
    
//    ###############################################################################
//    ################################### TEST CONSUME API ################################### 
    @Bean
    public CommandLineRunner testServiceWeb(ConsumeServiceWeb service) {
        return (args) -> {
            
            System.out.println("############################ Testing del API de RUTAS ############################");
            System.out.println("##################################################################################");
            
            service.getCoordPuntoMasCercano("-51.11354827880858","-30.026056381156316");
//            service.getCoordPuntoMasCercanoCustom("-51.11354827880858","-30.026056381156316");
            
            String lon1 = "-51.07714";
            String lat1 = "-29.993041";
            String lon2 = "-51.077202";
            String lat2 = "-29.989399";
//            service.getRuta(lon1, lat1, lon2, lat2);
        };
    }
}
