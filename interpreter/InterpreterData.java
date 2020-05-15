package interpreter;

import java.util.HashMap;
import command.ConnectCommand;

public class InterpreterData {

	public HashMap<String, Double> SymbolsHash;
	public HashMap<String, String> BindHash;
	public HashMap<String, Double> SimBindHash;

	public InterpreterData() {
		BindHash = new HashMap<String, String>();
		SimBindHash = new HashMap<String, Double>();
		SymbolsHash = new HashMap<String, Double>();
	}

	/*public void addBind(String Symbol, String Value) {
		BindHash.put(Symbol, Value);
	}
	public void addSimulatorBindValue(String Symbol, Double Value) {
		SimBindHash.put(Symbol, Value);
	}
	public void addSymbol(String Symbol, Double Value) {
		SymbolsHash.put(Symbol, Value);
	}
      */

	public boolean exists(String Symbol) {
		return (SymbolsHash.containsKey(Symbol) || BindHash.containsKey(Symbol));
	}

	public void setValue(String Symbol, Double Value) {
		if (BindHash.containsKey(Symbol)) {
			ConnectCommand.simulatorSetCommand("set " + BindHash.get(Symbol) + " " + Value);
			SimBindHash.put(BindHash.get(Symbol), Value);
		}
		else if (SymbolsHash.containsKey(Symbol)) {
			SymbolsHash.put(Symbol, Value);
		}
	}

	public Double getValueue(String Symbol) {
		if (BindHash.containsKey(Symbol)) {
			return SimBindHash.get(BindHash.get(Symbol));
		} 
		else if (SymbolsHash.containsKey(Symbol)) {
			return SymbolsHash.get(Symbol);
		}
		return null;
	}
}