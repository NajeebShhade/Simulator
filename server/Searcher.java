package server;

import java.util.List;

public interface Searcher<T> {

	// search method
	public List<State<T>> search(Searchable<T> s);

	// get how many nodes were eValueuated by the algorithm
	public int getNumberOfNodesEValueuated();
}