
package it.polito.tdp.borders;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    private ComboBox<Country> comboBox;

    @FXML
    private Button bttnTrova;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	txtResult.clear();
    	int anno=0; 
    	try{
    		anno= Integer.parseInt(this.txtAnno.getText()); 
    	} catch(NumberFormatException e) {
    		this.txtResult.appendText("Inserire un anno compreso tra 1816 e 2016.");
    		return;
    		
    	}
    	
    	if(anno<1816 || anno >2016) {
    		this.txtResult.appendText("Inserire un anno compreso tra 1816 e 2016.");
    		return; 
    	}
    	
    	this.model.creaGrafo(anno);
    	
    	for(Country c: this.model.elencoStati()) {
    		if(c.getContatore()>0) {
    		txtResult.appendText(c.toString()+" "+c.getContatore()+"\n");
    		}
    	}
    	
    	this.txtResult.appendText("\nNumero componenti connesse: "+model.numeroComponentiConnesse());
    	this.comboBox.getItems().addAll(model.tutte());
    }
    @FXML
    void doTrovaiVicini(ActionEvent event) {

    	this.txtResult.clear();
    	Country c = this.comboBox.getValue();
    	
    	//this.txtResult.appendText(" "+this.model.elencoVicini(c));
    	//this.txtResult.appendText(" "+this.model.trovaVicini(c));
    	//this.txtResult.appendText(" "+this.model.getVicini(c));
    	this.txtResult.appendText(" "+this.model.getViciniDFI(c));
    	//this.txtResult.appendText(""+model.lista(c));
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
 
       assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
       assert bttnTrova != null : "fx:id=\"bttnTrova\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
