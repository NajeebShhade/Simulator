package server;

import java.util.List;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {

	protected PriorityQueue<State<T>> openList;
	private int eValueuatedNodes;

	public CommonSearcher() {
		openList = new PriorityQueue<State<T>>();
		eValueuatedNodes = 0;
	}

	protected void addToOpenList(State<T> s) {
		openList.add(s);
	}

	protected State<T> popOpenList() {
		eValueuatedNodes++;
		return openList.poll();
	}

	@Override
	public abstract List<State<T>> search(Searchable<T> s);

	@Override
	public int getNumberOfNodesEValueuated() {
		return eValueuatedNodes;
	}
}