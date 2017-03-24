package com.sdi.tests.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PO_AltaForm {

	
	
   public void rellenaFormulario(WebDriver driver, String pnombre, String papellidos, String piduser, String pemail)
   {
		WebElement nombre = driver.findElement(By.id("form-principal:nombre"));
		nombre.click();
		nombre.clear();
		nombre.sendKeys(pnombre);
		WebElement apellidos = driver.findElement(By.id("form-principal:apellidos"));
		apellidos.click();
		apellidos.clear();
		apellidos.sendKeys(papellidos);
		WebElement iduser = driver.findElement(By.id("form-principal:iduser"));
		iduser.click();
		iduser.clear();
		iduser.sendKeys(piduser);
		WebElement idcorreo = driver.findElement(By.id("form-principal:correo"));
		idcorreo.click();
		idcorreo.clear();
		idcorreo.sendKeys(pemail);
		//Pulsar el boton de Alta.
		By boton = By.id("form-principal:boton");
		driver.findElement(boton).click();	   
   }
   
   
   public void rellenaFormularioLogin(WebDriver driver, String usuario,
			String contraseña) {
		WebElement loginUsuario = driver
				.findElement(By.id("form-login:nombreUsuario"));
		loginUsuario.click();
		loginUsuario.clear();
		loginUsuario.sendKeys(usuario);
		WebElement loginContraseña = driver.findElement(By
				.id("form-login:pass"));
		loginContraseña.click();
		loginContraseña.clear();
		loginContraseña.sendKeys(contraseña);
		// Pulsar el boton de IniciarSesión
		By boton = By.id("form-login:iniciarSesion");
		driver.findElement(boton).click();
	}

	public void rellenaFormularioRegistro(WebDriver driver, String usuario,
			String email, String contraseña, String contraseña2) {
		WebElement loginUsuario = driver
				.findElement(By.id("form-registro:nombre"));
		loginUsuario.click();
		loginUsuario.clear();
		loginUsuario.sendKeys(usuario);
		WebElement loginEmail = driver.findElement(By
				.id("form-registro:email"));
		loginEmail.click();
		loginEmail.clear();
		loginEmail.sendKeys(email);
		WebElement loginContraseña = driver.findElement(By
				.id("form-registro:passw"));
		loginContraseña.click();
		loginContraseña.clear();
		loginContraseña.sendKeys(contraseña);
		WebElement campoRepitaContraseña = driver.findElement(By
				.id("form-registro:passwConf"));
		campoRepitaContraseña.click();
		campoRepitaContraseña.clear();
		campoRepitaContraseña.sendKeys(contraseña2);
		// Pulsar el boton de Registro
		By boton = By.id("form-registro:registrar");
		driver.findElement(boton).click();
	}

}
	
	

