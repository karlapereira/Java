package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UsuarioTeste {

	@Test
	public void testConstrutor1() {
		Usuario usuario0 = new Usuario(0, "Alonso", "teste@teste", "Blonso", 1);
		assertEquals(0, usuario0.getId());
		assertEquals("Alonso", usuario0.getNome());
		assertEquals("teste@teste", usuario0.getEmail());
		assertEquals("Blonso", usuario0.getSenha());
		assertEquals(1, usuario0.getFuncao());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testConstrutor2() {
		Usuario usuario1 = new Usuario(0, "Alonso", "teste@teste", "Blonso", 1);
		Usuario usuario0 = new Usuario(usuario1);
		assertEquals(0, usuario0.getId());
		assertEquals("Alonso", usuario0.getNome());
		assertEquals("teste@teste", usuario0.getEmail());
		assertEquals("Blonso", usuario0.getSenha());
		assertEquals(1, usuario0.getFuncao());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSetNome() {
		Usuario usuario0 = new Usuario(0, "Alonso", "teste@teste", "Blonso", 1);
		usuario0.setNome("Clonso");
		assertEquals("Clonso", usuario0.getNome());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSetEmail() {
		Usuario usuario0 = new Usuario(0, "Alonso", "teste@teste", "Blonso", 1);
		usuario0.setEmail("alonso@alonso");
		assertEquals("alonso@alonso", usuario0.getEmail());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSetSenha() {
		Usuario usuario0 = new Usuario(0, "Alonso", "teste@teste", "Blonso", 1);
		usuario0.setSenha("Dlonso");
		assertEquals("Dlonso", usuario0.getSenha());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSetFuncao() {
		Usuario usuario0 = new Usuario(0, "Alonso", "teste@teste", "Blonso", 1);
		usuario0.setFuncao(2);
		assertEquals(2, usuario0.getFuncao());
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSetUser() {
		Usuario usuario0 = new Usuario(0, "Alonso", "teste@teste", "Blonso", 1);
		usuario0.setUser(usuario0);
		assertEquals(usuario0.getInstance(), usuario0);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testUsuarioLogout() {
		Usuario usuario0 = new Usuario(0, "Alonso", "teste@teste", "Blonso", 1);
		usuario0.setUser(usuario0);
		usuario0.usuarioLogout();
		assertEquals(usuario0.getInstance(), null);
		//fail("Not yet implemented");
	}

}
