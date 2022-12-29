package com.sistema.inventario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.usuario.Rol;
import com.sistema.inventario.usuario.Usuario;
import com.sistema.inventario.usuario.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCrearRoles() {
		Rol rolAdmin = new Rol("Administrador");
		Rol rolEditor = new Rol("Editor");
		Rol rolVisitante = new Rol("Visitante");
		
		
		entityManager.persist(rolAdmin);
		entityManager.persist(rolEditor);
		entityManager.persist(rolVisitante);
		
	}
	
	@Test
	public void testCrearNuevoUsuarioConRol() {
		Rol rolAdmin = entityManager.find(Rol.class, 1);
		Usuario usuario = new Usuario("bamv@gmail.com"," 123456");

		usuario.añadirRol(rolAdmin);
		usuarioRepository.save(usuario);
	}
	
	@Test
	public void testCrearNuevoUsuarioConRolDosRoles() {
		Rol rolAdmin = entityManager.find(Rol.class, 1);
		Rol rolVisitante = entityManager.find(Rol.class, 3);
		Usuario usuario = new Usuario("alejandra@gmail.com"," 54321");

		usuario.añadirRol(rolAdmin);
		usuario.añadirRol(rolVisitante);
		usuarioRepository.save(usuario);
	}
	
	
	@Test
	public void testAsignarRolUsuarioExistente() {
		Usuario usuario = usuarioRepository.findById(1).get();
		Rol rolVisitante = entityManager.find(Rol.class, 3);


		usuario.añadirRol(rolVisitante);
	}
	
	@Test
	public void testEliminarRolDeUsuarioExistente() {
		Usuario usuario = usuarioRepository.findById(1).get();
		Rol rol = new Rol(3); // id del rol a eliminar
		usuario.eliminarRol(rol);
	}
	
	
	@Test
	public void testCrearNuevoUsuarioConNuevoRol() {
		Rol rolVendedor = new Rol("Vendedor");
		Usuario usuario = new Usuario("Carlos@gmail.com"," 951357");

		usuario.añadirRol(rolVendedor);
		usuarioRepository.save(usuario);
	}
	
	
	@Test
	public void testObtenerUsuario() {
		Usuario usuario = usuarioRepository.findById(1).get();
		entityManager.detach(usuario);
		
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getRoles());
	
	}
	
	@Test
	public void testEliminarUsuario() {
		usuarioRepository.deleteById(2);
	}
	
	
}
