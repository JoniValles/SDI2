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

import com.sdi.business.Services;
import com.sdi.business.exception.BusinessException;
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
		driver.get("http://localhost:8280/SDI-53/");	
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
		testLoginParametros("form-login", "admin123",
				"admin");

		//Seguimos en la misma pagina
		driver.getCurrentUrl().equals(
				"http://localhost:8280/SDI2.0/index.xhtml");

		//Esta presente el formulario login?
		//driver.findElement(By.id("form-login"));

		//Seguimos en index
SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-login", 3);
	}


	// PR03: Fallo en la autenticación del administrador por introducir mal la
	// password.
	@Test
	public void prueba03() {
		testLoginParametros("form-login", "admin",
				"admin123");

		//Esta presente el formulario login?
		//driver.findElement(By.id("form-login"));

		//Seguimos en index
SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-login", 3);
	}



/*
		//PR04: Probar que la base de datos contiene los datos insertados con
		//conexión correcta a la base de datos.
		@Test
		public void prueba04() {
			assertTrue(false);
		}
*/


		//PR05: Visualizar correctamente la lista de usuarios normales.
		@Test
		public void prueba05() {
			testLoginParametros("form-login", "admin", "admin");

		SeleniumUtils.EsperaCargaPagina(driver, "id", 
				"listaUsuarios", 10);


		// Comprobamos que existen los usuarios
		SeleniumUtils.textoPresentePagina(driver, "admin");
		SeleniumUtils.textoPresentePagina(driver, "me@system.gtd");
		SeleniumUtils.textoPresentePagina(driver, "user1");
		SeleniumUtils.textoPresentePagina(driver, "user1@mail.com");
		SeleniumUtils.textoPresentePagina(driver, "user2");
		SeleniumUtils.textoPresentePagina(driver, "user2@mail.com");
		SeleniumUtils.textoPresentePagina(driver, "user3");
		SeleniumUtils.textoPresentePagina(driver, "user3@mail.com");
		}



		//PR06: Cambiar el estado de un usuario de ENABLED a DISABLED. Y tratar de
		//entrar con el usuario que se desactivado.
		@Test
		public void prueba06() throws BusinessException {
			//Login
			testLoginParametros("form-login", "admin",
					"admin");

			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"listaUsuarios", 3);

			//Se comprueba usuario
			SeleniumUtils.textoPresentePagina(driver, "user1");
			SeleniumUtils.textoPresentePagina(driver, "user1@mail.com");


			By enlace = By
					.xpath("/html/body/form[@id='listaUsuarios']/div[@id='listaUsuarios:listado']/div[@class='ui-datatable-tablewrapper']/table/tbody[@id='listaUsuarios:listado_data']/tr[@class='ui-widget-content ui-datatable-even'][3]/td[5]/a[@id='listaUsuarios:listado:4:j_idt10']");
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
					"form-cuerpo:menuUsuario", 3);
			
			//Dejar la BD como estaba
			Services.getAdminService().restartBD();
		}





		//PR07: Cambiar el estado de un usuario a DISABLED a ENABLED. Y Y tratar de
		//entrar con el usuario que se ha activado.
		//SOLO PASA LA PRIMERA PRUEBA QUE SE HAGA, LUEGO QUEDA ENABLED
		@Test
		public void prueba07() throws BusinessException {

			//Login
			testLoginParametros("form-login", "admin",
					"admin");

			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"listaUsuarios", 3);

			//Se comprueba usuario
			SeleniumUtils.textoPresentePagina(driver, "user3");
			SeleniumUtils.textoPresentePagina(driver, "user3@mail.com");


			By enlace = By
					.xpath("/html/body/form[@id='listaUsuarios']/div[@id='listaUsuarios:listado']/div[@class='ui-datatable-tablewrapper']/table/tbody[@id='listaUsuarios:listado_data']/tr[@class='ui-widget-content ui-datatable-even'][4]/td[5]/a[@id='listaUsuarios:listado:6:j_idt10']");
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
					"form-cuerpo:menuUsuario", 3);
			
			//Dejar la BD como estaba
			Services.getAdminService().restartBD();
			
			}




		//PR08: Ordenar por Login
		@Test
		public void prueba08() throws InterruptedException {
			//Login
			testLoginParametros("form-login", "admin",
					"admin");

			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"listaUsuarios", 3);

			//Ordenados inicialmente
			List<WebElement> logins = driver.findElements(By
					.xpath("//span[contains(@id, 'loginUsers')]"));
			assertTrue(logins.get(0).getText().equals("admin"));
			assertTrue(logins.get(1).getText().equals("john"));
			assertTrue(logins.get(2).getText().equals("ian"));
			assertTrue(logins.get(3).getText().equals("mary"));

			By button = By.xpath("//th[contains(@id, 'ordenarLogin')]");
			driver.findElement(button).click();
			//Espera para acceder correctamente al elemento del DOM
			Thread.sleep(1000);

			//Se vuelven a asignar al vector despues de ordenar
			logins = driver.findElements(By
					.xpath("//span[contains(@id, 'loginUsers')]"));

			//Ordenados despues del click
			assertTrue(logins.get(0).getText().equals("admin"));
			assertTrue(logins.get(1).getText().equals("ian"));
			assertTrue(logins.get(2).getText().equals("john"));
			assertTrue(logins.get(3).getText().equals("mary"));



		}


		//PR09: Ordenar por Email
		@Test
		public void prueba09() throws InterruptedException {
			//Login
			testLoginParametros("form-login", "admin",
					"admin");

			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"listaUsuarios", 3);

			//Ordenados inicialmente
			List<WebElement> logins = driver.findElements(By
					.xpath("//span[contains(@id, 'emailUsers')]"));
			assertTrue(logins.get(0).getText().equals("me@system.gtd"));
			assertTrue(logins.get(1).getText().equals("john@mail.com"));
			assertTrue(logins.get(2).getText().equals("ian@mail.com"));
			assertTrue(logins.get(3).getText().equals("mary5@mail.com"));

			By button = By.xpath("//th[contains(@id, 'ordenarEmail')]");
			driver.findElement(button).click();
			//Espera para acceder correctamente al elemento del DOM
			Thread.sleep(1000);

			//Se vuelven a asignar al vector despues de ordenar
			logins = driver.findElements(By
					.xpath("//span[contains(@id, 'emailUsers')]"));

			//Ordenados despues del click
			assertTrue(logins.get(0).getText().equals("ian@mail.com"));
			assertTrue(logins.get(1).getText().equals("john@mail.com"));
			assertTrue(logins.get(2).getText().equals("mary5@mail.com"));
			assertTrue(logins.get(3).getText().equals("me@system.gtd"));
		}





		//PR10: Ordenar por Status
		@Test
		public void prueba10() throws InterruptedException {
			//Login
			testLoginParametros("form-login", "admin",
					"admin");

			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"listaUsuarios", 3);

			//Ordenados inicialmente
			List<WebElement> logins = driver.findElements(By
					.xpath("//span[contains(@id, 'statusUsers')]"));
			assertTrue(logins.get(0).getText().equals("ENABLED"));
			assertTrue(logins.get(1).getText().equals("ENABLED"));
			assertTrue(logins.get(2).getText().equals("ENABLED"));
			assertTrue(logins.get(3).getText().equals("ENABLED"));

			By button = By.xpath("//th[contains(@id, 'ordenarStatus')]");
			driver.findElement(button).click();
			//Espera para acceder correctamente al elemento del DOM
			Thread.sleep(1000);

			//Se vuelven a asignar al vector despues de ordenar
			logins = driver.findElements(By
					.xpath("//span[contains(@id, 'statusUsers')]"));

			//Ordenados despues del click
			assertTrue(logins.get(0).getText().equals("ENABLED"));
			assertTrue(logins.get(1).getText().equals("ENABLED"));
			assertTrue(logins.get(2).getText().equals("ENABLED"));
			assertTrue(logins.get(3).getText().equals("ENABLED"));
		}

	 


	//PR11: Borrar una cuenta de usuario normal y datos relacionados.
	@Test
	public void prueba11() throws InterruptedException, BusinessException {
		//Login
		testLoginParametros("form-login", "admin",
				"admin");

		//Se carga el form
		SeleniumUtils.EsperaCargaPagina(driver, "id",
				"listaUsuarios", 3);

		//Se comprueba usuario
		SeleniumUtils.textoPresentePagina(driver, "user1");
		SeleniumUtils.textoPresentePagina(driver, "user1@mail.com");


		By enlace = By
				.xpath("/html/body/form[@id='listaUsuarios']/div[@id='listaUsuarios:listado']/div[@class='ui-datatable-tablewrapper']/table/tbody[@id='listaUsuarios:listado_data']/tr[@class='ui-widget-content ui-datatable-even'][4]/td[6]/a[@id='listaUsuarios:listado:6:j_idt13']");
		driver.findElement(enlace).click();//Ahora estaria eliminado


		enlace = By.xpath("/html/body/div[1]/button[@id='botonCerrarSesion']/span[@class='ui-button-text ui-c']");
		driver.findElement(enlace).click();

		//Se accede como usuario
		SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-login", 3);
		testLoginParametros("formLogin", "user3",
				"user3");
		//Como se ha eliminado no se puede acceder
		SeleniumUtils.EsperaCargaPagina(driver, "id",
				"form-login", 3);

		//Se deja la BD como estaba
		Services.getAdminService().restartBD();

	}
	

	


		//PR12: Crear una cuenta de usuario normal con datos válidos.
		@Test
		public void prueba12() throws InterruptedException {
			//Accedemos al registro
			By enlace = By
					.xpath("/html/body/form[@id='form-login']/div[@id='form-login:panel']/div[@id='form-login:panel_content']/table[@id='form-login:Grid']/tbody/tr[4]/td/button[@id='form-login:btnRegistroUsuario']/span[@class='ui-button-text ui-c']");
			driver.findElement(enlace).click();	
			
			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-registro", 3);
			
			//Se rellena el formulario
			new PO_AltaForm().rellenaFormularioRegistro(driver, "user4", "user4@mail.gt", "contraseña123", "contraseña123");
		}

		public void testRegistro(String usuario, String email,
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
		
				//Accedemos al registro
				By enlace = By
						.xpath("/html/body/form[@id='form-login']/div[@id='form-login:panel']/div[@id='form-login:panel_content']/table[@id='form-login:Grid']/tbody/tr[4]/td/button[@id='form-login:btnRegistroUsuario']/span[@class='ui-button-text ui-c']");
				driver.findElement(enlace).click();	
				
				//Se carga el form
				SeleniumUtils.EsperaCargaPagina(driver, "id",
						"form-registro", 3);
				
				//Se rellena el formulario
				new PO_AltaForm().rellenaFormularioRegistro(driver, "user1", "user1@mail.gt", "contraseña123", "contraseña123");
		}
	 
	//PR14: Crear una cuenta de usuario normal con Email incorrecto.
			@Test
			public void prueba14() throws InterruptedException {
				
				//Accedemos al registro
				By enlace = By
						.xpath("/html/body/form[@id='form-login']/div[@id='form-login:panel']/div[@id='form-login:panel_content']/table[@id='form-login:Grid']/tbody/tr[4]/td/button[@id='form-login:btnRegistroUsuario']/span[@class='ui-button-text ui-c']");
				driver.findElement(enlace).click();	
				
				//Se carga el form
				SeleniumUtils.EsperaCargaPagina(driver, "id",
						"form-registro", 3);
				
				//Se rellena el formulario
				new PO_AltaForm().rellenaFormularioRegistro(driver, "user4", "user4mail.gt", "contraseña123", "contraseña123");
			
				
			}
	
		//PR15: Crear una cuenta de usuario normal con Password incorrecta.
		@Test
		public void prueba15() throws InterruptedException {
			
			//Accedemos al registro
			By enlace = By
					.xpath("/html/body/form[@id='form-login']/div[@id='form-login:panel']/div[@id='form-login:panel_content']/table[@id='form-login:Grid']/tbody/tr[4]/td/button[@id='form-login:btnRegistroUsuario']/span[@class='ui-button-text ui-c']");
			driver.findElement(enlace).click();	
			
			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-registro", 3);
			
			//Se rellena el formulario
			new PO_AltaForm().rellenaFormularioRegistro(driver, "user4", "user4@mail.gt", "u", "i");
		}
		
		/*

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
*/

		//PR33: Salir de sesión desde cuenta de administrador.
		@Test
		public void prueba33() {


			//Login
			testLoginParametros("form-login", "admin",
					"admin");

			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"listaUsuarios", 3);

			//Esta dentro del menu admin
			SeleniumUtils.textoPresentePagina(driver, "user1");
			SeleniumUtils.textoPresentePagina(driver, "user1@mail.com");

			//Se cierra la sesion
			By enlace = By.xpath("/html/body/div[1]/button[@id='botonCerrarSesion']/span[@class='ui-button-text ui-c']");
			driver.findElement(enlace).click();

			//Se comprueba que esta en index
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-login", 3);
		
		}
		
		

		//PR34: Salir de sesión desde cuenta de usuario normal.
		@Test
		public void prueba34() {
			//Login como usuario
			testLoginParametros("form-login", "user1",
					"user1");

			//Esta dentro del menu Usuario
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-cuerpo:menuUsuario", 3);
	

			//Se cierra la sesion
			By enlace = By
					.xpath("/html/body/form[@id='form-cuerpo']/div[2]/button[@id='form-cuerpo:botonCerrarSesion']/span[@class='ui-button-text ui-c']");			
			driver.findElement(enlace).click();

			//Se comprueba que esta en index
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-login", 3);
		}

		//PR35: Cambio del idioma por defecto a un segundo idioma. (Probar algunas
		//vistas)
		@Test
		public void prueba35() {
			
			//Se comprueba que esta en el primer lenguaje
			SeleniumUtils.textoPresentePagina(driver, "Crear usuario");
			
			//Login
			testLoginParametros("form-login", "admin",
					"admin");

			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"listaUsuarios", 3);
			//Se cambia el idioma al segundo
			By enlace = By
					.xpath("/html/body/div[1]/button[@id='EN']/span[@class='ui-button-text ui-c']");			
			driver.findElement(enlace).click();

			//Se comprueba que sigue en ingles
			SeleniumUtils.textoPresentePagina(driver, "user1");
			//SeleniumUtils.textoPresentePagina(driver, "ENGLISH");


		}

	
		//PR36: Cambio del idioma por defecto a un segundo idioma y vuelta al
		//idioma por defecto. (Probar algunas vistas)
		@Test
		public void prueba36() throws InterruptedException {
			
			
			//Login
			testLoginParametros("form-login", "admin",
					"admin");
			
			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"listaUsuarios", 3);
			
			//Se cambia el idioma al segundo
			By enlace = By
					.xpath("/html/body/div[1]/button[@id='EN']/span[@class='ui-button-text ui-c']");			
			driver.findElement(enlace).click();

			//Se comprueba que sigue en ingles
			SeleniumUtils.textoPresentePagina(driver, "user1");
			
			//SeleniumUtils.textoPresentePagina(driver, "Back");
			Thread.sleep(1000);
			//Pulsamos atras
			enlace = By.xpath("/html/body/button[@id='j_idt16']/span[@class='ui-button-text ui-c']");
			
			driver.findElement(enlace).click();
			Thread.sleep(1000);
			//SeleniumUtils.textoPresentePagina(driver, "Create user");
			
			//Login
			testLoginParametros("form-login", "admin",
					"admin");
			Thread.sleep(1000);

			
			//Cambiamos al primer lenguaje otra vez 
			enlace = By
					.xpath("/html/body/div[1]/button[@id='ES']/span[@class='ui-button-text ui-c']");			
			driver.findElement(enlace).click();
			
			//Se comprueba que se ha cambiado al primer lenguaje
			//SeleniumUtils.textoPresentePagina(driver, "Atras");

				
			
			
		}

		
		//PR37: Intento de acceso a un URL privado de administrador con un usuario
		//autenticado como usuario normal.
		@Test
		public void prueba37() {
			
			//Login como usuario
			testLoginParametros("form-login", "user1",
					"user1");

			//Se carga el form
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-cuerpo:menuUsuario", 3);
			
			driver.get(
					"http://localhost:8280/SDI-53/admin/listaUsuarios.xhtml");
			
			//Te devuelve al index por no tener privilegios
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-login", 3);
			
			//Se comprueba que estamos en index por ser acceso restringido
			driver.getCurrentUrl().equals(
					"http://localhost:8180/SDI-53/index.xhtml");
			SeleniumUtils.textoPresentePagina(driver, "Usuario");

			
		}

		//PR38: Intento de acceso a un URL privado de usuario normal con un usuario
		//no autenticado.
		@Test
		public void prueba38() {
			//Intentamos acceder a una pagina restringida
			driver.get("http://localhost:8280/SDI-53/restricted/listaTareas.xhtml");

			//Te devuelve al index por no tener privilegios
			SeleniumUtils.EsperaCargaPagina(driver, "id",
					"form-login", 3);
			
			//Se comprueba que estamos en index por ser acceso restringido
			driver.getCurrentUrl().equals(
					"http://localhost:8180/SDI-53/index.xhtml");
			SeleniumUtils.textoPresentePagina(driver, "Usuario");

		}

	 
}
