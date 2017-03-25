package com.sdi.presentation;
import java.io.Serializable;
import javax.faces.bean.*;
import javax.faces.event.ActionEvent;
import com.sdi.dto.Category;

@ManagedBean(name="category")
@SessionScoped
public class BeanCategory extends Category implements Serializable {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
public BeanCategory() {
    iniciaCategoria(null);
  }

//Este método es necesario para copiar el alumno a editar cuando
//se pincha el enlace Editar en la vista listado.xhtml. Podría sustituirse 
//por un método editar en BeanAlumnos.
  public void setCategory(Category cat) {
    setId(cat.getId());
    setUserId(cat.getUserId());
    setName(cat.getName());
   
  }
//Iniciamos los datos del alumno con los valores por defecto 
//extraídos del archivo de propiedades correspondiente
    public void iniciaCategoria(ActionEvent event) {
//      FacesContext facesContext = FacesContext.getCurrentInstance();
//          ResourceBundle bundle = 
//           facesContext.getApplication().getResourceBundle(facesContext, "msgs");
          setId(null);
          setName("");
          setUserId(null);
    }        
    
}
