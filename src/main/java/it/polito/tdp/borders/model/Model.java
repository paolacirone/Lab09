package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.borders.db.BordersDAO;

import org.jgrapht.graph.*;

public class Model{

	private Graph<Country, DefaultEdge> grafo;
	private List<Border> confini;
	private Map<Integer, Country> idMap; 
	private BordersDAO dao;

	public Model() {
		//confini = new ArrayList<>();
		idMap = new HashMap<>();
		dao = new BordersDAO();
		
		dao.loadAllCountries(idMap);
	}

	public void creaGrafo(int anno) {

		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);

		Graphs.addAllVertices(this.grafo, this.idMap.values()); 
		
		
		confini = dao.getCountryPairs(anno, idMap);
		
		for(Border b: confini) {
			Graphs.addEdgeWithVertices(this.grafo, b.getStato1(), b.getStato2());
		}
		
		
		for(Border b: confini) {
			for(Country c: idMap.values()) {
				if(b.getStato1().equals(c) || b.getStato2().equals(c)) {
					c.setContatore(c.getContatore()+1);
				}
			}
		}
		
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	

	
	public Set<Country> elencoStati(){
		return  this.grafo.vertexSet();
	}
}

