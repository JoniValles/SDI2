package com.sdi.tests.Tests;
import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.sdi.tests.pageobjects.PO_AltaForm;
import com.sdi.tests.utils.SeleniumUtils;



public class SDI2_Tests {

	WebDriver driver; // = new FirefoxDriver();
	List<WebElement> elementos = null;
	public SDI2_Tests()
	{
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	}

	//	@Before
	//	public void run()
	//	{
	//		//Vamos a la pagina principal
	//		driver = new FirefoxDriver();
	//		driver.get("http://localhost:8280/Notaneitorv2_0_SOLUCION_pruebas/");			
	//	}
	@Before
	public void run()
	{
		//Este código es para ejecutar con la versión portale de Firefox 46.0
		File pathToBinary = new File("S:\\firefox\\FirefoxPortable.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();       
		driver = new FirefoxDriver(ffBinary,firefoxProfile);
		driver.get("http://localhost:8280/SDI2.0/");	
		//Este código es para ejecutar con una versión instalada de Firex 46.0 
		//driver = new FirefoxDriver();
		//driver.get("http://localhost:8180/Notaneitorv2_0_SOLUCION_pruebas/");			
	}
	@After
	public void end()
	{
		//Cerramos el navegador
		driver.quit();
	}

	public void testLoginParametros(String nombreForm, String usuario,
			String contraseña) {
		//Se rellena el formulario
		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		/*
		//Esperamos a que se cargue la pagina del admin
		//concretamente el formulario
		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-login", 10);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, usuario);
		 */
	}

	public void testLoginErroneoParametros(String nombreform, String usuario,
			String contraseña) {

		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		// Esperamos a que se cargue la pagina del admin
		//SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);

		// Comprobamos que aparezca el mensaje para el administrador
		SeleniumUtils.textoPresentePagina(driver, "error");
	}
	
	public void testVisualizarUsuarios(String nombreform, String usuario,
			String contraseña) {

		new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

		SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);
		SeleniumUtils.textoPresentePagina(driver, usuario);

		//FALTA

		// Comprobamos que aparezca el elemento en la tabla
		SeleniumUtils.textoPresentePagina(driver, usuario);
		SeleniumUtils.textoPresentePagina(driver, "usuario1");
	}

/*
	//PR01: Autentificar correctamente al administrador.
	@Test
	public void prueba01() {
		testLoginParametros("form-login", "admin", "admin");

		// Esperamos a que se cargue la pagina del admin
		SeleniumUtils.EsperaCargaPagina(driver, "id",
				"listaUsuarios", 3);

		//Esta el texto presente?
		SeleniumUtils.textoPresentePagina(driver, "admin");
		SeleniumUtils.textoPresentePagina(driver, "me@system.gtd");
	}



	//PR02: Fallo en la autenticación del administrador por introducir mal el login.
	@Test
	public void prueba02() {
		testLoginErroneoParametros("form-login", "admin123",
				"admin");

		//Seguimos en la misma pagina
		driver.getCurrentUrl().equals(
				"http://localhost:8280/SDI2.0/index.xhtml");

		//Esta presente el formulario login?
		//driver.findElement(By.id("form-login"));
		
		//Esta presente el texto error?
		SeleniumUtils.textoPresentePagina(driver, "error");

	}


	// PR03: Fallo en la autenticación del administrador por introducir mal la
	// password.
	@Test
	public void prueba03() {
		testLoginErroneoParametros("form-login", "admin",
				"admin123");

		//Esta presente el formulario login?
		//driver.findElement(By.id("form-login"));
		
		//Esta presente el texto error?
				SeleniumUtils.textoPresentePagina(driver, "error");
	}

	
	

		//PR04: Probar que la base de datos contiene los datos insertados con
		//conexión correcta a la base de datos.
		@Test
		public void prueba04() {
			assertTrue(false);
		}
		
		

		//PR05: Visualizar correctamente la lista de usuarios normales.
		@Test
		public void prueba05() {
			testLoginParametros("form-login", "admin", "admin");

		SeleniumUtils.EsperaCargaPagina(driver, "id", 
				"form-admin", 10);


		// Comprobamos que existen los usuarios
		SeleniumUtils.textoPresentePagina(driver, "admin");
		SeleniumUtils.textoPresentePagina(driver, "me@system.gtd");
		SeleniumUtils.textoPresentePagina(driver, "user1");
		SeleniumUtils.textoPresentePagina(driver, "user1@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "user2");
		SeleniumUtils.textoPresentePagina(driver, "user2@gmail.com");
		SeleniumUtils.textoPresentePagina(driver, "user3");
		SeleniumUtils.textoPresentePagina(driver, "user3@gmail.com");
		}



		//PR06: Cambiar el estado de un usuario de ENABLED a DISABLED. Y tratar de
		//entrar con el usuario que se desactivado.
		@Test
		public void prueba06() {
			testModificarStatus("form-login", "admin", "admin",
					"DISABLED");
			testLoginErroneoParametros("form-login", "usuario1", "usuario1");
		}

		public void testModificarStatus(String nombreform, String usuario,
				String contraseña, String status) {
			new PO_AltaForm().rellenaFormularioLogin(driver, usuario, contraseña);

			SeleniumUtils.EsperaCargaPagina(driver, "id", "form-admin", 10);
			SeleniumUtils.textoPresentePagina(driver, usuario);
			
			
			//FALTA

		}

*/
	
	
		//PR07: Cambiar el estado de un usuario a DISABLED a ENABLED. Y Y tratar de
		//entrar con el usuario que se ha activado.
		//SOLO PASA LA PRIMERA PRUEBA QUE SE HAGA, LUEGO QUEDA ENABLED
		@Test
		public void prueba07() {
			
			//Login
			testLoginParametros("form-login", "admin",
					"admin");
			
			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"listaUsuarios", 3);

			//Se comprueba usuario
			SeleniumUtils.textoPresentePagina(driver, "user1");
			SeleniumUtils.textoPresentePagina(driver, "user1@mail.com");

			/*
			By enlace = By
					.xpath("/html/body/form[@id='listaUsuarios']/div[@id='listaUsuarios:listado']/div[@class='ui-datatable-tablewrapper']/table/tbody[@id='listaUsuarios:listado_data']/tr[@class='ui-widget-content ui-datatable-even'][3]/td[4]");
			driver.findElement(enlace).click();// Ahora estaria disabled
			*/
			
			By enlace = By
					.xpath("/html/body/form[@id='listaUsuarios']/div[@id='listaUsuarios:listado']/div[@class='ui-datatable-tablewrapper']/table/tbody[@id='listaUsuarios:listado_data']/tr[@class='ui-widget-content ui-datatable-even'][3]/td[5]/a[@id='listaUsuarios:listado:4:j_idt19']");
			driver.findElement(enlace).click();// Ahora estaria enabled
			

			enlace = By.xpath("/html/body/div[1]/button[@id='botonCerrarSesion']/span[@class='ui-button-text ui-c']");
			driver.findElement(enlace).click();

			//Se accede como usuario
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-login", 3);
			testLoginParametros("formLogin", "user1",
					"user1");
			//Se visualiza el menu del usuario
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"mimenu", 3);
			
		
			

		}

		/*
		
		//PR08: Ordenar por Login
		@Test
		public void prueba08() throws InterruptedException {
			//Templates
		}


		//PR09: Ordenar por Email
		@Test
		public void prueba09() throws InterruptedException {
			//Templates
		}

		//PR10: Ordenar por Status
		@Test
		public void prueba10() throws InterruptedException {
			//Templates
		}

		//PR11: Borrar una cuenta de usuario normal y datos relacionados.
		@Test
		public void prueba11() throws InterruptedException {
			//Templates
		}


		//PR12: Crear una cuenta de usuario normal con datos válidos.
		@Test
		public void prueba12() throws InterruptedException {
			testRegistroCorrecto("usuarioprueba1", "usuarioprueba1@gmail.com",
					"usuarioprueba1", "usuarioprueba1");
		}

		public void testRegistroCorrecto(String usuario, String email,
				String contraseña, String contraseña2) throws InterruptedException {
			SeleniumUtils.clickLink(driver, "linkRegistro");

			Thread.sleep(500);

			new PO_AltaForm().rellenaFormularioRegistro(driver, usuario, email,
					contraseña, contraseña2);

			SeleniumUtils.EsperaCargaPagina(driver, "id", "form-login", 10);


			SeleniumUtils.textoPresentePagina(driver, "Iniciar sesión");
			SeleniumUtils.textoPresentePagina(driver, "Usuario");
			SeleniumUtils.textoPresentePagina(driver, "Contraseña");
		}

		//PR13: Crear una cuenta de usuario normal con login repetido.
		@Test
		public void prueba13() throws InterruptedException {
			testRegistroIncorrecto("usuarioprueba1", "usuarioprueba1@gmail.com",
					"usuarioprueba1", "usuarioprueba1");
		}

		public void testRegistroIncorrecto(String usuario, String email,
				String contraseña, String contraseña2) throws InterruptedException {
			SeleniumUtils.clickLink(driver, "linkRegistro");

			Thread.sleep(500);

			new PO_AltaForm().rellenaFormularioRegistro(driver, usuario, email,
					contraseña, contraseña2);

			SeleniumUtils.EsperaCargaPagina(driver, "id", "form-login", 10);

			SeleniumUtils.textoPresentePagina(driver, "error");

		}

		//PR14: Crear una cuenta de usuario normal con Email incorrecto.
		@Test
		public void prueba14() throws InterruptedException {
			testRegistroIncorrecto("usuariopruebaemail", "usuarioprueba1@gmail.com", 
					"usuariopruebaemail1", "usuariopruebaemail1");
		}

		//PR15: Crear una cuenta de usuario normal con Password incorrecta.
		@Test
		public void prueba15() throws InterruptedException {
			testRegistroIncorrecto("usuarioprueba1", "emailincorrecto",
					"usuarioprueba1", "usuarioprueba2");
		}

		//USUARIO
		//PR16: Comprobar que en Inbox sólo aparecen listadas las tareas sin
		//categoría y que son las que tienen que. Usar paginación navegando por las
		//tres páginas.
		@Test
		public void prueba16() {
			assertTrue(false);
		}

		//PR17: Funcionamiento correcto de la ordenación por fecha planeada.
		@Test
		public void prueba17() {
			assertTrue(false);
		}

		//PR18: Funcionamiento correcto del filtrado.
		@Test
		public void prueba18() {
			assertTrue(false);
		}

		//PR19: Funcionamiento correcto de la ordenación por categoría.
		@Test
		public void prueba19() {
			assertTrue(false);
		}

		//PR20: Funcionamiento correcto de la ordenación por fecha planeada.
		@Test
		public void prueba20() {
			assertTrue(false);
		}

		//PR21: Comprobar que las tareas que no están en rojo son las de hoy y
		//además las que deben ser.
		@Test
		public void prueba21() {
			assertTrue(false);
		}

		//PR22: Comprobar que las tareas retrasadas están en rojo y son las que
		//deben ser.
		@Test
		public void prueba22() {
			assertTrue(false);
		}

		//PR23: Comprobar que las tareas de hoy y futuras no están en rojo y que
		//son las que deben ser.
		@Test
		public void prueba23() {
			assertTrue(false);
		}

		//PR24: Funcionamiento correcto de la ordenación por día.
		@Test
		public void prueba24() {
			assertTrue(false);
		}

		//PR25: Funcionamiento correcto de la ordenación por nombre.
		@Test
		public void prueba25() {
			assertTrue(false);
		}

		//PR26: Confirmar una tarea, inhabilitar el filtro de tareas terminadas, ir
		//a la pagina donde está la tarea terminada y comprobar que se muestra.
		@Test
		public void prueba26() {
			assertTrue(false);
		}

		//PR27: Crear una tarea sin categoría y comprobar que se muestra en la
		//lista Inbox.
		@Test
		public void prueba27() {
			assertTrue(false);
		}

		//PR28: Crear una tarea con categoría categoria1 y fecha planeada Hoy y
		//comprobar que se muestra en la lista Hoy.
		@Test
		public void prueba28() {
			assertTrue(false);
		}

		//PR29: Crear una tarea con categoría categoria1 y fecha planeada posterior
		//a Hoy y comprobar que se muestra en la lista Semana.
		@Test
		public void prueba29() {
			assertTrue(false);
		}

		//PR30: Editar el nombre, y categoría de una tarea (se le cambia a
		//categoría1) de la lista Inbox y comprobar que las tres pseudolista se
		//refresca correctamente.
		@Test
		public void prueba30() {
			assertTrue(false);
		}

		//PR31: Editar el nombre, y categoría (Se cambia a sin categoría) de una
		//tarea de la lista Hoy y comprobar que las tres pseudolistas se refrescan
		//correctamente.
		@Test
		public void prueba31() {
			assertTrue(false);
		}

		//PR32: Marcar una tarea como finalizada. Comprobar que desaparece de las
		//tres pseudolistas.
		@Test
		public void prueba32() {
			assertTrue(false);
		}

		//PR33: Salir de sesión desde cuenta de administrador.
		@Test
		public void prueba33() {
			assertTrue(false);
		}

		//PR34: Salir de sesión desde cuenta de usuario normal.
		@Test
		public void prueba34() {
			assertTrue(false);
		}

		//PR35: Cambio del idioma por defecto a un segundo idioma. (Probar algunas
		//vistas)
		@Test
		public void prueba35() {
			assertTrue(false);
		}

		//PR36: Cambio del idioma por defecto a un segundo idioma y vuelta al
		//idioma por defecto. (Probar algunas vistas)
		@Test
		public void prueba36() {
			assertTrue(false);
		}

		//PR37: Intento de acceso a un URL privado de administrador con un usuario
		//autenticado como usuario normal.
		@Test
		public void prueba37() {
			assertTrue(false);
		}

		//PR38: Intento de acceso a un URL privado de usuario normal con un usuario
		//no autenticado.
		@Test
		public void prueba38() {
			assertTrue(false);
		}
	 
	 */
}