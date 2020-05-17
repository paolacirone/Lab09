package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

import org.jgrapht.graph.*;

public class Model {

	private Graph<Country, DefaultEdge> grafo;
	private List<Border> confini;
	private Map<Integer, Country> idMap;
	private BordersDAO dao;
	private Map<Country, Country> visita = new HashMap<>();

	public Model() {
		// confini = new ArrayList<>();
		idMap = new HashMap<>();
		dao = new BordersDAO();

		dao.loadAllCountries(idMap);
	}

	public void creaGrafo(int anno) {

		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);

		Graphs.addAllVertices(this.grafo, this.idMap.values());

		confini = dao.getCountryPairs(anno, idMap);

		for (Border b : confini) {
			Graphs.addEdgeWithVertices(this.grafo, b.getStato1(), b.getStato2());
		}

		for (Border b : confini) {
			for (Country c : idMap.values()) {
				if (b.getStato1().equals(c) || b.getStato2().equals(c)) {
					c.setContatore(c.getContatore() + 1);
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

	public Collection<Country> elencoStati() {
		return this.grafo.vertexSet();
	}

	public int numeroComponentiConnesse() {

		ConnectivityInspector<Country, DefaultEdge> c = new ConnectivityInspector<>(grafo);
		return c.connectedSets().size();

	}

	public List<Country> tutte() {
		return dao.loadCountries();
	}
	
	public Collection<Country> lista(Country c){
	ConnectivityInspector<Country, DefaultEdge> cx = new ConnectivityInspector<>(grafo);
	return cx.connectedSetOf(c);
	
	}
	//LISTA DI STATI VICINI
	public List<Country> elencoVicini(Country c) {
		List<Country> vicini = new ArrayList<>();
		vicini = Graphs.neighborListOf(grafo, c);
		return vicini;
	}

	
	//LISTA DI STATI RAGGIUNGIBILI DA QUEL NODO
	List<Country> vicini = new ArrayList<>();

	public Collection<Country> trovaVicini(Country c) {
		
		List<Country> result = new ArrayList<>();
		
		BreadthFirstIterator<Country, DefaultEdge> it = new BreadthFirstIterator<Country, DefaultEdge>(grafo,c);
		
		//visita.put(c, null); 
		
		it.addTraversalListener(new TraversalListener<Country, DefaultEdge>(){

			@Override
			public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void vertexTraversed(VertexTraversalEvent<Country> e) {
							vicini.add(e.getVertex());
			}

			@Override
			public void vertexFinished(VertexTraversalEvent<Country> e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		while(it.hasNext()) {
			it.next();
		}
		
		vicini.remove(c);

	return vicini;
		
		

}
	
	//IN AMPIEZZA
	public List<Country> getViciniBFI(Country c){
		BreadthFirstIterator<Country, DefaultEdge> it = new BreadthFirstIterator<Country, DefaultEdge>(grafo,c);
		List<Country> vicini = new ArrayList<>();
		while(it.hasNext()) {
			vicini.add(it.next());
		}

		vicini.remove(c);
	return vicini;
	}
	
	//IN PROFONDITA'
	public List<Country> getViciniDFI(Country c){
		DepthFirstIterator<Country, DefaultEdge> it = new DepthFirstIterator<Country, DefaultEdge>(grafo,c);
		List<Country> vicini = new ArrayList<>();
		while(it.hasNext()) {
			vicini.add(it.next());
		}

		vicini.remove(c);
	return vicini;
	}

}
